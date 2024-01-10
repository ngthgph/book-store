package com.example.bookstore.ui.screens.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookstore.data.local.LocalScreenProvider
import com.example.bookstore.data.model.Screen
import com.example.bookstore.ui.screens.home.AppHeader
import com.example.bookstore.ui.screens.home.SearchBar

@Composable
fun AppNavigationBarScreen(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .weight(1f)
        ) {
            AppHeader(text = title, description = title, onBack = {})
            SearchBar(onSearch = {}, onClear = {}, isSearching = false)
            content()
        }
        AppBottomNavigationBar(
            currentScreen = Screen.Home,
            onPressed = {},
            navigationItemList = LocalScreenProvider.screenList
        )
    }
}