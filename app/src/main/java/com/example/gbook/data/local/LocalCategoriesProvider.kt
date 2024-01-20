package com.example.gbook.data.local

import com.example.gbook.R
import com.example.gbook.data.model.Collection

object LocalCategoriesProvider {
    val favorite = Collection(R.string.favorite, R.drawable.favorite)
    val categories = listOf(
        Collection(R.string.economics, R.drawable.economics),
        Collection(R.string.fiction, R.drawable.fiction),
        Collection(R.string.health, R.drawable.health),
        Collection(R.string.history, R.drawable.history),
        Collection(R.string.music, R.drawable.music),
        Collection(R.string.science, R.drawable.science),
        Collection(R.string.self_help, R.drawable.self_help),
        Collection(R.string.sport, R.drawable.sport)
    )
}