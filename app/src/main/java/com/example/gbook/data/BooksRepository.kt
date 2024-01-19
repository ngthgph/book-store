package com.example.gbook.data

import com.example.gbook.data.model.Book
import com.example.gbook.network.BookApiService
import com.example.gbook.network.BookItem

interface BooksRepository {
    suspend fun searchBookTerm(query: String): List<Book>
}

class NetworkBooksRepository(
    private val bookApiService: BookApiService
): BooksRepository {
    override suspend fun searchBookTerm(query: String): List<Book> {
        val searchResult = bookApiService.searchBooks(query)
        val booksList = searchResult.items?.map { resultItem ->
            bookApiService.getBook(resultItem.id)
        }
        return booksList?.map { transformToBook(it, booksList.indexOf(it))
        } ?: emptyList()
    }

    private fun transformToBook(bookInfo: BookItem, bookId: Int): Book {
        return Book(
            id = bookId,
            title = bookInfo.volumeInfo?.title.orEmpty(),
            author = bookInfo.volumeInfo?.author?.joinToString(", ").orEmpty(),
            publisher = bookInfo.publisher.orEmpty(),
            publishedDate = bookInfo.publishedDate.orEmpty(),
            description = bookInfo.description.orEmpty(),
            isbn10 = bookInfo.industryIdentifiers?.first?.identifier.orEmpty(),
            isbn13 = bookInfo.industryIdentifiers?.second?.identifier.orEmpty(),
            pageCount = bookInfo.printedPageCount?: 0,
            categories = bookInfo.categories?.joinToString(", ").orEmpty(),
            imageLinks = bookInfo.imageLinks?.thumbnail.orEmpty(),
            saleability = bookInfo.saleInfo?.saleability.orEmpty(),
            retailPrice = bookInfo.saleInfo?.retailPrice?.amount?: 0.0,
            currencyCode = bookInfo.saleInfo?.retailPrice?.currencyCode.orEmpty(),
        )
    }
}