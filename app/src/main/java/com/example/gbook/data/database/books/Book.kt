package com.example.gbook.data.database.books

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val networkId: String,
    val title: String,
    val author: String,
    val publisher: String,
    val publishedDate: String,
    val description: String,
    val isbn13: String,
    val isbn10: String,
    val pageCount: Int,
    val categories: String,
    val imageLinks: String,
    val saleability: String,
    val retailPrice: Double,
    val currencyCode: String,
    var rating: Int? = null,
    var note: String? = null,
    var collectionName: String? = null,
    var cartAmount: Int? = null
)
