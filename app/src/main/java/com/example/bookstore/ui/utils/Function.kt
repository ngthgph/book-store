package com.example.bookstore.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

enum class Function(val icon: ImageVector) {
    Favorite(icon = Icons.Default.Favorite),
    Cart(icon = Icons.Default.ShoppingCart),
    Share(icon = Icons.Default.Share)
}