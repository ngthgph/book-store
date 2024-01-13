package com.example.bookstore.ui.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
                uiState = uiState,
                onIconClick = onIconClick,
                onBack = onBack)
        },
        bottomBar = {
            AppBottomNavigationBar(
                currentScreen = uiState.currentScreen,
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
            MockData.categoryUiState,
            onIconClick = {},
            onBack = {}
        ) {}
    }
}