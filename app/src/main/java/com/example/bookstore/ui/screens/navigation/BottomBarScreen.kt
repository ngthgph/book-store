package com.example.bookstore.ui.screens.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.R
import com.example.bookstore.ui.utils.Screen
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun BottomBarScreen(
    title: String,
    modifier: Modifier = Modifier,
    onIconClick: (Screen) -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeader(
                text = title,
                description = title,
                onBack = {},
                onAccountClick = { onIconClick(Screen.Home) }
            )
        },
        bottomBar = {
            AppBottomNavigationBar(
                currentScreen = Screen.Home,
                onIconClick = onIconClick,
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
        BottomBarScreen(
            onIconClick = {},
            title = stringResource(id = R.string.app_name)
        ) {}
    }
}