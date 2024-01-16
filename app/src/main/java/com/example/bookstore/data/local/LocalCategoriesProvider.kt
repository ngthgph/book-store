package com.example.bookstore.data.local

import com.example.bookstore.R
import com.example.bookstore.data.model.Category

object LocalCategoriesProvider {
    val categories = listOf(
        Category(R.string.economics, R.drawable.economics),
        Category(R.string.fiction, R.drawable.fiction),
        Category(R.string.health, R.drawable.health),
        Category(R.string.history, R.drawable.history),
        Category(R.string.music, R.drawable.music),
        Category(R.string.science, R.drawable.science),
        Category(R.string.self_help, R.drawable.self_help),
        Category(R.string.sport, R.drawable.sport)
    )
}