package com.example.bookstore.ui.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.data.local.MockData
import com.example.bookstore.data.model.BookStoreUiState
import com.example.bookstore.ui.screens.book.BookDetailScreen
import com.example.bookstore.ui.utils.Screen
import com.example.bookstore.ui.theme.BookStoreTheme
import com.example.bookstore.ui.utils.Function
import com.example.bookstore.ui.utils.NavigationType

@Composable
fun DrawerScreen(
    uiState: BookStoreUiState,
    onIconClick: (Screen) -> Unit,
    onButtonClick: (Function) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AppNavigationDrawer(
        currentScreen = uiState.currentScreen,
        onIconClick = onIconClick,
        modifier = modifier.padding()
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .fillMaxSize()
        ) {
            if(uiState.currentScreen != Screen.Home) {
                AppHeaderBar(
                    uiState = uiState,
                    onIconClick = onIconClick,
                    onBack = onBack
                )
            }
            if (uiState.currentBook == null) {
                content()
            } else {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        content()
                    }
                    BookDetailScreen(
                        navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER,
                        uiState = uiState,
                        onButtonClick = onButtonClick,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun DrawerScreenPreview() {
    BookStoreTheme {
        DrawerScreen(
            uiState = MockData.bookUiState,
            onButtonClick = {},
            onBack = {},
            onIconClick = {}
        ) {}
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun HomeDrawerScreenPreview() {
    BookStoreTheme {
        DrawerScreen(
            uiState = MockData.homeUiState,
            onButtonClick = {},
            onBack = {},
            onIconClick = {}
        ) {}
    }
}
