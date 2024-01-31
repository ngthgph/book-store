package com.example.gbook.data.database.books

import kotlinx.coroutines.flow.Flow

interface OfflineRepository {
    fun getAllBooksStream(): Flow<List<Book>>
    fun getCollectionStream(collectionName: String): Flow<List<Book>>
    fun getCartStream(): Flow<List<Book>>
    fun getBookStream(id: Int): Flow<Book>
    suspend fun insertBook(book: Book)
    suspend fun updateBook(book: Book)
    suspend fun deleteBook(book: Book)
}

class OfflineBooksRepository(private val bookDao: BookDao): OfflineRepository {
    override fun getAllBooksStream(): Flow<List<Book>> =
        bookDao.getAllBooks()
    override fun getCollectionStream(collectionName: String): Flow<List<Book>> =
        bookDao.getCollection(collectionName)
    override fun getCartStream(): Flow<List<Book>> =
        bookDao.getCart()
    override fun getBookStream(id: Int): Flow<Book> =
        bookDao.getBook(id)
    override suspend fun insertBook(book: Book) =
        bookDao.insert(book)
    override suspend fun updateBook(book: Book) =
        bookDao.update(book)
    override suspend fun deleteBook(book: Book) =
        bookDao.delete(book)
}