package com.example.bookstore.data.model

data class BookStoreUiState(
    val currentCategory: Category? = null,
    val currentBook: Book? = null,
    val account: Account? = null,
    val cart: List<Pair<Book, Int>> = listOf()
)
