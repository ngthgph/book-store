package com.example.gbook.ui.utils

import androidx.annotation.StringRes
import com.example.gbook.R

enum class Screen(@StringRes val title: Int){
    Home(title = R.string.home),
    MyLibrary(title = R.string.my_library),
    Cart(title = R.string.cart),
    Categories(title = R.string.categories_screen),
    Category(title = R.string.category),
    Book(title = R.string.book),
    Account(title = R.string.account),
    Drawer(title = 0)
}
