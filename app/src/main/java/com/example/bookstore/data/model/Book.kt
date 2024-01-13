package com.example.bookstore.data.model

data class Book(
    val id: Int = 0,
    val title: String,
    val author: String,
    val publisher: String,
    val publishedDate: String,
    val description: String,
    val ISBN_13: String,
    val ISBN_10: String,
    val pageCount: Int,
    val categories: String,
    val imageLinks: String,
    val retailPrice: Int,
    val currencyCode: String,
    val rating: Int = 0,
    val note: String = "",
    val favorite: Boolean = false
)
