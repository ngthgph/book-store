package com.example.gbook.data.model

sealed interface NetworkBookUiState {
    data class Success(var books: List<Book>): NetworkBookUiState
    object Loading: NetworkBookUiState
    object Error: NetworkBookUiState
}