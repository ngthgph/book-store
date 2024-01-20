package com.example.gbook.fake

import com.example.gbook.data.model.Book
import com.example.gbook.network.BooksRepository

class FakeNetworkBooksRepository: BooksRepository {
    override suspend fun searchBookTerm(query: String): List<Book> {
        return FakeDataSource.fakeBookList
    }
}