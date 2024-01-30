package com.example.gbook.data.database.collection

import androidx.annotation.DrawableRes
import com.example.gbook.R
import com.example.gbook.data.database.books.Book

data class BookCollection(
    var name: String = "Untitled",
    @DrawableRes var image: Int = R.drawable.collection_default,
    var imageLink: String? = null
)
