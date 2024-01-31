package com.example.gbook.data.fake

import com.example.gbook.data.network.BookApiService
import com.example.gbook.data.network.BookItem
import com.example.gbook.data.network.BookSearchResponse

class FakeBookApiService: BookApiService {
    override suspend fun searchBooks(
        query: String,
        filter: String?,
        startIndex: Int?,
        maxResults: Int?,
        printType: String?,
        projection: String?,
        orderBy: String?,
    ): BookSearchResponse {
        return FakeDataSource.fakeBookResponse
    }
    override suspend fun getBook(bookId: String): BookItem {
        return if(FakeDataSource.fakeBookItem1.networkId == bookId)
            FakeDataSource.fakeBookItem1
        else
            FakeDataSource.fakeBookItem2
    }
}