package com.example.gbook.ui.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.data.fake.FakeNetworkBooksRepository
import com.example.gbook.data.fake.MockData
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.data.model.NetworkBookUiState
import com.example.gbook.ui.GBookViewModel
import com.example.gbook.ui.items.AppHeaderBar
import com.example.gbook.ui.screens.book.BookDetailScreen
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Screen
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType

@Composable
fun DrawerScreen(
    currentScreen: Screen,
    uiState: GBookUiState,
    networkBookUiState: NetworkBookUiState,
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
            if (currentScreen != Screen.Book) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Spacer(modifier = Modifier.weight(0.5f))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(2f)
                    ) {
                        //                AdsBanner()
                        content()
                    }
                    Spacer(modifier = Modifier.weight(0.5f))
                }
            } else {
                Row (modifier = Modifier.fillMaxSize()) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        //                AdsBanner()
                        content()
                    }
                    BookDetailScreen(
                        navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER,
                        uiState = uiState,
                        networkBookUiState = networkBookUiState,
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
            networkBookUiState = MockData.networkBookUiState,
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
            networkBookUiState = MockData.networkBookUiState,
            onButtonClick = {},
            onBack = {},
            onIconClick = {}
        ) {}
    }
}
