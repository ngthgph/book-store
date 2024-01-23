package com.example.gbook.data.model

data class Book(
    val id: Int,
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
    val rating: Int? = null,
    val note: String = "",
    val favorite: Boolean = false
)
