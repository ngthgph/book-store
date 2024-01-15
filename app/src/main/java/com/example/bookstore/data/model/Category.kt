package com.example.bookstore.data.model

import androidx.annotation.DrawableRes

data class Category(
    val name: String,
    @DrawableRes val image: Int,
)
