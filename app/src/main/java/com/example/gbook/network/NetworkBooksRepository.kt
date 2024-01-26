package com.example.gbook.network

import com.example.gbook.data.local.BookFilter
import com.example.gbook.data.local.OrderBy
import com.example.gbook.data.local.PrintType
import com.example.gbook.data.local.Projection
import com.example.gbook.data.local.QueryType
import com.example.gbook.data.model.Book

interface BooksRepository {
    //You can paginate the volumes list by specifying two values in the parameters for the request:
    //- startIndex - The position in the collection at which to start. The index of the first item is 0.
    //- maxResults - The maximum number of results to return. The default is 10, and the maximum allowable value is 40.
    suspend fun searchBookTerm(
        query: String,
        intitle: String? = null,
        inauthor: String? = null,
        inpublisher: String? = null,
        subject: String? = null,
        isbn: String? = null,
        lccn: String? = null,
        oclc: String? = null,
        filter: BookFilter? = null,
        startIndex: Int? = null,
        maxResults: Int? = null,
        printType: PrintType? = null,
        projection: Projection? = null,
        orderBy: OrderBy? = null,
        ): List<Book>
    suspend fun searchBookItem(networkId: String): Book
}

class NetworkBooksRepository(
    private val bookApiService: BookApiService
): BooksRepository {
    override suspend fun searchBookTerm(
        query: String,
        intitle: String?,
        inauthor: String?,
        inpublisher: String?,
        subject: String?,
        isbn: String?,
        lccn: String?,
        oclc: String?,
        filter: BookFilter?,
        startIndex: Int?,
        maxResults: Int?,
        printType: PrintType?,
        projection: Projection?,
        orderBy: OrderBy?,
        ): List<Book> {

        // maxResults - The maximum number of results to return. The default is 10, and the maximum allowable value is 40.
        var max: Int? = null
        if(maxResults != null) {
            max = if (maxResults > 40) 40 else maxResults
        }

        // q - Search for volumes that contain this text string. There are special keywords you can
        // specify in the search terms to search in particular fields
        val queryPrefix = QueryType.values()
        val queryType = listOf(intitle, inauthor, inpublisher, subject, isbn, lccn, oclc)
        var request = query
        for (q in queryType) {
            if(q != null) {
                request += queryPrefix[queryType.indexOf(q)].prefix + q
            }
        }
        val searchResult = bookApiService
            .searchBooks(
                request.convertSpacesToPlus(),
                filter?.value,
                startIndex,
                max,
                printType?.value,
                projection?.value,
                orderBy?.value
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