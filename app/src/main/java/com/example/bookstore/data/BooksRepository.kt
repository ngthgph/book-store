package com.example.bookstore.data

import com.example.bookstore.network.Book
import com.example.bookstore.network.BookApiService

interface BooksRepository {
    suspend fun getMathBooks(): List<Book>
}

class NetworkBooksRepository(
    private val bookApiService: BookApiService
): BooksRepository {
    override suspend fun getMathBooks(): List<Book> = bookApiService.getBooks()
}