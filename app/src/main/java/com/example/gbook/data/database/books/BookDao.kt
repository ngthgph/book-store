package com.example.gbook.data.database.books

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: Book)

    @Update
    suspend fun update(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Query("SELECT * FROM books WHERE id = :id")
    fun getBook(id: Int): Flow<Book>

    @Query("SELECT * FROM books WHERE collectionName = :collectionName")
    fun getCollection(collectionName: String): Flow<List<Book>>

    @Query("SELECT * FROM books WHERE cartAmount > 0")
    fun getCart(): Flow<List<Book>>

    @Query("SELECT * FROM books ORDER BY title ASC")
    fun getAllBooks(): Flow<List<Book>>
}