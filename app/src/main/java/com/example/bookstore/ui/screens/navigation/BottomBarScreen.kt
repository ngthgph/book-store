package com.example.bookstore.ui.screens.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.R
import com.example.bookstore.data.local.LocalScreenProvider
import com.example.bookstore.data.model.Screen
import com.example.bookstore.ui.screens.home.AppHeader
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun BottomBarScreen(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeader(text = title, description = title, onBack = {})
        },
        bottomBar = {
            AppBottomNavigationBar(
                currentScreen = Screen.Home,
                onPressed = {},
                navigationItemList = LocalScreenProvider.screenList
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)) {
                content()
            }
        }
}

@Preview(showBackground = true)
@Composable
fun BottomBarScreenPreview() {
    BookStoreTheme {
        BottomBarScreen(title = stringResource(id = R.string.app_name)) {}
    }
}