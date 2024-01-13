package com.example.bookstore.data.model

import com.example.bookstore.ui.utils.Screen

data class BookStoreUiState(
    val currentCategory: Category? = null,
    val currentBook: Book? = null,
    val currentScreen: Screen,
    val account: Account? = null
)
