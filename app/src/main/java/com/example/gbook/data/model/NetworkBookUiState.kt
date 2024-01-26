package com.example.gbook.data.model

sealed interface NetworkBookUiState {
    data class Success(
        var books: List<Book>,
        var page: Int = 0,
        var maxPages: Int = 0
    ): NetworkBookUiState
    object Loading: NetworkBookUiState
    object Error: NetworkBookUiState
}