package com.example.gbook.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BookCollection(
    var name: String? = null,
    @DrawableRes var image: Int? = null,
    var bookList: List<Book>? = null,
)
