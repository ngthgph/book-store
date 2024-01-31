package com.example.gbook.data.database.collection

import com.example.gbook.R

object LocalLibraryProvider {
    val favorite = BookCollection("Favorite", R.drawable.favorite)
    val library = listOf(favorite)
}