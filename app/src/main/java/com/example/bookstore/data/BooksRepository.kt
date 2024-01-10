package com.example.bookstore.data

import com.example.bookstore.network.BookInfo
import com.example.bookstore.network.BookApiService

interface BooksRepository {
    suspend fun getMathBooks(): List<BookInfo>
}

class NetworkBooksRepository(
    private val bookApiService: BookApiService
): BooksRepository {
    override suspend fun getMathBooks(): List<BookInfo> = bookApiService.getBooks()
}