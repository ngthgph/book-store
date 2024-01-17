package com.example.gbook.ui.utils

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val screen: Screen,
    val icon: ImageVector,
    @StringRes val  text: Int
)
