package com.example.gbook.data

import com.example.gbook.network.BookInfo
import com.example.gbook.network.BookApiService

interface BooksRepository {
    suspend fun getMathBooks(): List<BookInfo>
}

class NetworkBooksRepository(
    private val bookApiService: BookApiService
): BooksRepository {
    override suspend fun getMathBooks(): List<BookInfo> = bookApiService.getBooks()
}