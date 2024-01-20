package com.example.gbook.network

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
        val bookList = searchResult.items?.map { resultItem ->
            bookApiService.getBook(resultItem.id)
        }
        return bookList?.map { transformToBook(it, bookList.indexOf(it))
        } ?: emptyList()
    }

    private fun transformToBook(bookInfo: BookItem, bookId: Int): Book {
        return Book(
            id = bookId,
            title = bookInfo.volumeInfo.title,
            author = bookInfo.volumeInfo.author?.joinToString(", ").orEmpty(),
            publisher = bookInfo.volumeInfo.publisher.orEmpty(),
            publishedDate = bookInfo.volumeInfo.publishedDate.orEmpty(),
            description = bookInfo.volumeInfo.description.orEmpty(),
            isbn10 = bookInfo.volumeInfo.industryIdentifiers?.first?.identifier.orEmpty(),
            isbn13 = bookInfo.volumeInfo.industryIdentifiers?.second?.identifier.orEmpty(),
            pageCount = bookInfo.volumeInfo.printedPageCount?: 0,
            categories = bookInfo.volumeInfo.categories?.joinToString(", ").orEmpty(),
            imageLinks = bookInfo.volumeInfo.imageLinks?.thumbnail.orEmpty(),
            saleability = bookInfo.saleInfo.saleability.orEmpty(),
            retailPrice = bookInfo.saleInfo.retailPrice?.amount?: 0.0,
            currencyCode = bookInfo.saleInfo.retailPrice?.currencyCode.orEmpty(),
        )
    }
}