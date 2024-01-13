package com.example.bookstore.ui.utils

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bookstore.ui.utils.Screen

data class NavigationItem(
    val screen: Screen,
    val icon: ImageVector,
    @StringRes val  text: Int
)
