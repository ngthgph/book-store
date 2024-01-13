package com.example.bookstore.ui.screens.categories

import com.example.bookstore.data.model.Book

sealed interface NetworkBookUiState {
    data class Success(val books: List<Book>): NetworkBookUiState
    object Loading: NetworkBookUiState
    object Error: NetworkBookUiState
}