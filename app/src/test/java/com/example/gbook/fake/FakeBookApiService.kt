package com.example.gbook.fake

import com.example.gbook.network.BookApiService
import com.example.gbook.network.BookItem
import com.example.gbook.network.BookSearchResponse

class FakeBookApiService: BookApiService {
    override suspend fun searchBooks(query: String): BookSearchResponse {
        return FakeDataSource.fakeBookResponse
    }
    override suspend fun getBook(bookId: String): BookItem {
        return if(FakeDataSource.fakeBookItem1.id == bookId)
            FakeDataSource.fakeBookItem1
        else
            FakeDataSource.fakeBookItem2
    }
}