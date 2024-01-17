package com.example.gbook.ui.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.data.local.MockData
import com.example.gbook.data.model.GbookUiState
import com.example.gbook.ui.items.AppHeaderBar
import com.example.gbook.ui.screens.book.BookDetailScreen
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Screen
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType

@Composable
fun DrawerScreen(
    currentScreen: Screen,
    uiState: GbookUiState,
    onIconClick: (Screen) -> Unit,
    onButtonClick: (Function) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AppNavigationDrawer(
        currentScreen = currentScreen,
        onIconClick = onIconClick,
        uiState = uiState,
        modifier = modifier.padding()
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .fillMaxSize()
        ) {
            if(currentScreen != Screen.Home) {
                AppHeaderBar(
                    currentScreen = currentScreen,
                    uiState = uiState,
                    onIconClick = onIconClick,
                    onBack = onBack
                )
            }
            if (uiState.currentBook == null) {
                Spacer(modifier = Modifier.weight(0.5f))
                Column(
                    modifier = Modifier.weight(2f)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        content()
//                AdsBanner()
                    }
                }
                Spacer(modifier = Modifier.weight(0.5f))
            } else {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            content()
//                AdsBanner()
                        }
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
    GBookTheme {
        DrawerScreen(
            currentScreen = Screen.Book,
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
    GBookTheme {
        DrawerScreen(
            currentScreen = Screen.Home,
            uiState = MockData.homeUiState,
            onButtonClick = {},
            onBack = {},
            onIconClick = {}
        ) {}
    }
}
