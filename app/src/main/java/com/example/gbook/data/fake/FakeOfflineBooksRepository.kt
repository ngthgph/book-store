package com.example.gbook.data.fake

import com.example.gbook.data.database.books.Book
import com.example.gbook.data.database.books.OfflineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeOfflineBooksRepository: OfflineRepository {

    override fun getAllBooksStream(): Flow<List<Book>> =
        flowOf(FakeDataSource.fakeBookList)

    override fun getCollectionStream(collectionName: String): Flow<List<Book>> =
        flowOf(FakeDataSource.fakeBookList)

    override fun getCartStream(): Flow<List<Book>> =
        flowOf(FakeDataSource.fakeBookList)

    override fun getBookStream(id: Int): Flow<Book> =
        flowOf(FakeDataSource.fakeBookList[0])

    override suspend fun insertBook(book: Book) {}
    override suspend fun updateBook(book: Book) {}
    override suspend fun deleteBook(book: Book) {}

}