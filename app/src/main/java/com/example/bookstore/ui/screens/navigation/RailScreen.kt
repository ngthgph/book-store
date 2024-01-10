package com.example.bookstore.ui.screens.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookstore.data.local.LocalScreenProvider
import com.example.bookstore.data.model.Screen
import com.example.bookstore.ui.screens.home.AppHeader
import com.example.bookstore.ui.screens.home.SearchBar

@Composable
fun AppNavigationRailScreen(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row {
        AppNavigationRail(
            currentScreen = Screen.Home,
            onPressed = {},
            navigationItemList = LocalScreenProvider.screenList
        )
        Column(
            modifier = modifier.weight(1f)
        ) {
            AppHeader(text = title, description = title, onBack = {})
            SearchBar(onSearch = {}, onClear = {}, isSearching = false)
            content()
        }
    }
}