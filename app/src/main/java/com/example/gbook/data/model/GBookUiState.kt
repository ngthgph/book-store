package com.example.gbook.data.model

import com.example.gbook.data.local.LocalCategoriesProvider

data class GBookUiState(
    var currentBookCollection: BookCollection? = null,
    var currentBook: Book? = null,
    val account: Account? = null,
    var cart: List<Pair<Book, Int>> = listOf(),
    var library: List<BookCollection> = listOf(LocalCategoriesProvider.favorite),
    var favorite: List<Book> = listOf(),
)
