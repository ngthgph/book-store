package com.example.gbook.data.model

data class GbookUiState(
    val currentCategory: Category? = null,
    val currentBook: Book? = null,
    val account: Account? = null,
    val cart: List<Pair<Book, Int>> = listOf()
)
