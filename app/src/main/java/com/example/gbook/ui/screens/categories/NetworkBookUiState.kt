package com.example.gbook.ui.screens.categories

import com.example.gbook.data.model.Book

sealed interface NetworkBookUiState {
    data class Success(val books: List<Book>): NetworkBookUiState
    object Loading: NetworkBookUiState
    object Error: NetworkBookUiState
}