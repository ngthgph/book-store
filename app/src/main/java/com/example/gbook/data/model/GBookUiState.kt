package com.example.gbook.data.model

import com.example.gbook.data.database.account.Account
import com.example.gbook.data.database.books.Book
import com.example.gbook.data.database.collection.BookCollection
import com.example.gbook.data.database.collection.LocalLibraryProvider
import com.example.gbook.data.local.LocalCategoriesProvider

data class GBookUiState(
    var currentBook: Book? = null,
    val account: Account? = null,
    val collection: List<BookCollection> = listOf(LocalLibraryProvider.favorite),
)
