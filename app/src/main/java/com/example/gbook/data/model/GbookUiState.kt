package com.example.gbook.data.model

import com.example.gbook.data.local.LocalCategoriesProvider

data class GbookUiState(
    val currentCategory: Category? = null,
    val currentBook: Book? = null,
    val account: Account? = null,
    val cart: List<Pair<Book, Int>> = listOf(),
    val library: List<Category> = listOf(LocalCategoriesProvider.favorite),
    val favorite: List<Book> = listOf(),
)
