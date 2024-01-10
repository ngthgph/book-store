package com.example.bookstore.data.model

data class Book(
    val id: Int = 0,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int,
    val imageLinks: String,
    val retailPrice: Int,
    val currencyCode: String,
    val rating: Int = 0,
    val note: String = "",
    val favorite: Boolean = false
)
