package com.example.bookstore.ui

import com.example.bookstore.data.model.Book

sealed interface BookUiState {
    data class Success(val books: List<Book>): BookUiState
    object Loading: BookUiState
    object Error: BookUiState
}