package com.example.gbook.data.model

import com.example.gbook.data.local.LocalCategoriesProvider

data class GBookUiState(
    val currentCollection: Collection? = null,
    val currentBook: Book? = null,
    val account: Account? = null,
    val cart: List<Pair<Book, Int>> = listOf(),
    val library: List<Collection> = listOf(LocalCategoriesProvider.favorite),
    val favorite: List<Book> = listOf(),
)
