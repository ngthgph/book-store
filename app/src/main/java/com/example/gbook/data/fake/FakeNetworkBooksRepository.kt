package com.example.gbook.data.fake

import com.example.gbook.data.database.books.Book
import com.example.gbook.data.database.books.SearchQuery
import com.example.gbook.data.network.NetworkRepository

class FakeNetworkBooksRepository: NetworkRepository {
    override suspend fun searchBookTerm(searchQuery: SearchQuery): List<Book> {
        return FakeDataSource.fakeBookList
    }

    override suspend fun searchBookItem(id: String): Book {
        return FakeDataSource.fakeBookList[0]
    }
}