package com.example.bookstore.ui.utils

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bookstore.R

enum class Function(
    val icon: ImageVector,
    val doneIcon: ImageVector = Icons.Default.Done,
    @StringRes val description: Int
) {
    Favorite(
        icon = Icons.Default.Add,
        description = R.string.add_to_favorite
    ),
    Cart(
        icon = Icons.Default.ShoppingCart,
        description = R.string.add_to_cart
    ),
    Share(
        icon = Icons.Default.Share,
        description = R.string.share
    )
}