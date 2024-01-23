package com.example.gbook.data.fake

import com.example.gbook.data.model.Book
import com.example.gbook.network.BooksRepository

class FakeNetworkBooksRepository: BooksRepository {
    override suspend fun searchBookTerm(query: String): List<Book> {
        return FakeDataSource.fakeBookList
    }

    override suspend fun searchBookItem(id: String): Book {
        return FakeDataSource.fakeBookList[0]
    }
}