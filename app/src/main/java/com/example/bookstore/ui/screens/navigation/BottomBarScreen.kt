package com.example.bookstore.ui.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.data.local.MockData
import com.example.bookstore.data.model.BookStoreUiState
import com.example.bookstore.ui.utils.Screen
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun BottomBarScreen(
    currentScreen: Screen,
    uiState: BookStoreUiState,
    onIconClick: (Screen) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeaderBar(
                currentScreen = currentScreen,
                uiState = uiState,
                onIconClick = onIconClick,
                onBack = onBack
            )
        },
        bottomBar = {
            AppBottomNavigationBar(
                currentScreen = currentScreen,
                onIconClick = onIconClick,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .fillMaxSize()
        ) {
                content()
            }
        }
}

@Preview(showBackground = true)
@Composable
fun BookBottomBarScreenPreview() {
    BookStoreTheme {
        BottomBarScreen(
            currentScreen = Screen.Book,
            MockData.bookUiState,
            onIconClick = {},
            onBack = {}
        ) {}
    }
}
@Preview(showBackground = true)
@Composable
fun HomeBottomBarScreenPreview() {
    BookStoreTheme {
        BottomBarScreen(
            currentScreen = Screen.Home,
            MockData.homeUiState,
            onIconClick = {},
            onBack = {}
        ) {}
    }
}
@Preview(showBackground = true)
@Composable
fun CategoryBottomBarScreenPreview() {
    BookStoreTheme {
        BottomBarScreen(
            currentScreen = Screen.Category,
            MockData.categoryUiState,
            onIconClick = {},
            onBack = {}
        ) {}
    }
}