package com.example.gbook.ui.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.data.local.MockData
import com.example.gbook.data.model.GbookUiState
import com.example.gbook.ui.items.AppHeaderBar
import com.example.gbook.ui.items.OnlyAccountHomeHeader
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Screen

@Composable
fun BottomBarScreen(
    currentScreen: Screen,
    uiState: GbookUiState,
    onIconClick: (Screen) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = { if (currentScreen != Screen.Home)
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
        if (currentScreen == Screen.Home) {
            Box(
                modifier = modifier
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .fillMaxSize()
            ) {
                OnlyAccountHomeHeader(
                    account = uiState.account!!.name,
                    onAccountClick = { onIconClick(Screen.Account) },
                    modifier = Modifier.background(Color.Transparent)
                )
                Column(modifier = Modifier) {
                    content()
//                AdsBanner()
                }
            }
        }
        else {
            Column(
                modifier = Modifier
                    .padding(it)
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .fillMaxSize()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    content()
//                AdsBanner()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookBottomBarScreenPreview() {
    GBookTheme {
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
    GBookTheme {
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
    GBookTheme {
        BottomBarScreen(
            currentScreen = Screen.Category,
            MockData.categoryUiState,
            onIconClick = {},
            onBack = {}
        ) {}
    }
}