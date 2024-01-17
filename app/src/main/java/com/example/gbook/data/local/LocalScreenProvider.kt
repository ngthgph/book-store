package com.example.gbook.data.local

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import com.example.gbook.R
import com.example.gbook.ui.utils.NavigationItem
import com.example.gbook.ui.utils.Screen

object LocalScreenProvider {
    val screenList = listOf(
        NavigationItem(
            screen = Screen.Home,
            icon = Icons.Default.Home,
            text = R.string.home
        ),
        NavigationItem(
            screen = Screen.Favorite,
            icon = Icons.Default.Favorite,
            text = R.string.favorite
        ),
        NavigationItem(
            screen = Screen.Cart,
            icon = Icons.Default.ShoppingCart,
            text = R.string.cart
        ),
        NavigationItem(
            screen = Screen.Categories,
            icon = Icons.Default.List,
            text = R.string.book_categories
        ),
    )
}