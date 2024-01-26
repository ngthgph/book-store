package com.example.gbook.data.fake

import com.example.gbook.data.local.BookFilter
import com.example.gbook.data.local.OrderBy
import com.example.gbook.data.local.PrintType
import com.example.gbook.data.local.Projection
import com.example.gbook.data.model.Book
import com.example.gbook.network.BooksRepository

class FakeNetworkBooksRepository: BooksRepository {
    override suspend fun searchBookTerm(
        query: String,
        intitle: String?,
        inauthor: String?,
        inpublisher: String?,
        subject: String?,
        isbn: String?,
        lccn: String?,
        oclc: String?,
        filter: BookFilter?,
        startIndex: Int?,
        maxResults: Int?,
        printType: PrintType?,
        projection: Projection?,
        orderBy: OrderBy?,
        ): List<Book> {
        return FakeDataSource.fakeBookList
    }

    override suspend fun searchBookItem(id: String): Book {
        return FakeDataSource.fakeBookList[0]
    }
}