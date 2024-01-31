package com.example.gbook.data.local

import com.example.gbook.R
import com.example.gbook.data.database.collection.BookCollection

object LocalCategoriesProvider {
    val categories = listOf(
        BookCollection("Economics", R.drawable.economics),
        BookCollection("Fiction", R.drawable.fiction),
        BookCollection("Health", R.drawable.health),
        BookCollection("History", R.drawable.history),
        BookCollection("Literature", R.drawable.literature),
        BookCollection("Music", R.drawable.music),
        BookCollection("Science", R.drawable.science),
        BookCollection("Self-help", R.drawable.self_help),
        BookCollection("Sport", R.drawable.sport)
    )
}