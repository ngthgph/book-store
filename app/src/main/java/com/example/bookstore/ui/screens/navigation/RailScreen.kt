package com.example.bookstore.ui.screens.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.R
import com.example.bookstore.data.local.LocalScreenProvider
import com.example.bookstore.data.model.Screen
import com.example.bookstore.ui.screens.navigation.AppHeader
import com.example.bookstore.ui.screens.home.SearchBar
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun RailScreen(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold() {
        Row(
            modifier = Modifier.padding(it)
        ) {
            AppNavigationRail(
                currentScreen = Screen.Home,
                onPressed = {},
                navigationItemList = LocalScreenProvider.screenList
            )
            Column(
                modifier = modifier.weight(1f)
            ) {
                AppHeader(text = title, description = title, onBack = {})

                content()
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun RailScreenPreview() {
    BookStoreTheme {
        RailScreen(title = stringResource(id = R.string.app_name)) {}
    }
}