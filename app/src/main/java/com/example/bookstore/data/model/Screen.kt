package com.example.bookstore.data.model

import androidx.annotation.StringRes
import com.example.bookstore.R

enum class Screen(@StringRes val title: Int){
    Home(title = R.string.home),
    Favorite(title = R.string.favorite),
    Cart(title = R.string.cart),
    Categories(title = R.string.categories),
    Category(title = R.string.category),
    Book(title = R.string.book),
    Account(title = R.string.account)
}
