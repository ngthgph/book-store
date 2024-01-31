package com.example.gbook.data.model

import com.example.gbook.data.database.books.Book

data class OfflineBookUiState(
    val bookList: List<Book> = listOf(),
)
