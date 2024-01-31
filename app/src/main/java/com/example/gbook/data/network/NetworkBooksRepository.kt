package com.example.gbook.data.network

import com.example.gbook.data.database.books.Book
import com.example.gbook.data.database.books.SearchQuery

interface NetworkRepository {
    //You can paginate the volumes list by specifying two values in the parameters for the request:
    //- startIndex - The position in the collection at which to start. The index of the first item is 0.
    //- maxResults - The maximum number of results to return. The default is 10, and the maximum allowable value is 40.
    suspend fun searchBookTerm(searchQuery: SearchQuery): List<Book>
    suspend fun searchBookItem(networkId: String): Book
}

class NetworkBooksRepository(
    private val bookApiService: BookApiService
): NetworkRepository {
    override suspend fun searchBookTerm(searchQuery: SearchQuery): List<Book> {

        // maxResults - The maximum number of results to return. The default is 10, and the maximum allowable value is 40.
        var max: Int? = null
        if(searchQuery.maxResults != null) {
            max = if (searchQuery.maxResults!! > 40) 40 else searchQuery.maxResults
        }

        // q - Search for volumes that contain this text string. There are special keywords you can
        // specify in the search terms to search in particular fields
        val queryPrefix = QueryType.values()
        val queryType = listOf(
            searchQuery.intitle,
            searchQuery.inauthor,
            searchQuery.inpublisher,
            searchQuery.subject,
            searchQuery.isbn,
            searchQuery.lccn,
            searchQuery.oclc
        )
        var request = searchQuery.query
        for (q in queryType) {
            if(q != null) {
                request += queryPrefix[queryType.indexOf(q)].prefix + q
            }
        }
        val searchResult = bookApiService
            .searchBooks(
                request.convertSpacesToPlus(),
                searchQuery.filter?.value,
                searchQuery.startIndex,
                max,
                searchQuery.printType?.value,
                searchQuery.projection?.value,
                searchQuery.orderBy?.value
            )
        val bookList = searchResult.items?.map { resultItem ->
            bookApiService.getBook(resultItem.networkId)
        }
        return bookList?.map { transformToBook(it, bookList.indexOf(it))
        } ?: emptyList()
    }

    override suspend fun searchBookItem(networkId: String): Book {
        val bookItem = bookApiService.getBook(networkId)
        return transformToBook(bookItem, 0)
    }

    private fun transformToBook(bookInfo: BookItem, listId: Int): Book {
        return Book(
            id = listId,
            networkId = bookInfo.networkId,
            title = bookInfo.volumeInfo.title,
            author = bookInfo.volumeInfo.author?.joinToString(", ").orEmpty(),
            publisher = bookInfo.volumeInfo.publisher.orEmpty(),
            publishedDate = bookInfo.volumeInfo.publishedDate.orEmpty(),
            description = bookInfo.volumeInfo.description.orEmpty(),
            isbn10 = bookInfo.volumeInfo.industryIdentifiers?.get(0)?.identifier.orEmpty(),
            isbn13 = if (bookInfo.volumeInfo.industryIdentifiers != null && bookInfo.volumeInfo.industryIdentifiers.size >= 2)
                bookInfo.volumeInfo.industryIdentifiers[1].identifier else "",
            pageCount = bookInfo.volumeInfo.printedPageCount?: 0,
            categories = bookInfo.volumeInfo.categories?.joinToString(", ").orEmpty(),
            imageLinks = bookInfo.volumeInfo.imageLinks?.thumbnail.orEmpty(),
            saleability = bookInfo.saleInfo.saleability,
            retailPrice = bookInfo.saleInfo.retailPrice?.amount?: 0.0,
            currencyCode = bookInfo.saleInfo.retailPrice?.currencyCode.orEmpty(),
        )
    }

    private fun String.convertSpacesToPlus() :String {
        return this.replace(" ", "+")
    }
}