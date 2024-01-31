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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.data.database.layout.LayoutPreferencesRepository
import com.example.gbook.data.dataStore
import com.example.gbook.data.fake.FakeDataSource.fakeViewModel
import com.example.gbook.data.fake.FakeNetworkBooksRepository
import com.example.gbook.data.fake.MockData
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.ui.GBookViewModel
import com.example.gbook.ui.items.AppHeaderBar
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Screen

@Composable
fun DrawerScreen(
    currentScreen: Screen,
    viewModel: GBookViewModel,
    uiState: GBookUiState,
    onIconClick: (Screen) -> Unit,
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
                    viewModel = viewModel,
                    uiState = uiState,
                    onIconClick = onIconClick,
                    onBack = onBack
                )
            }
            if (uiState.currentBook == null) {
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
            }
            else {
                Row (modifier = Modifier.fillMaxSize()) {
                    content()
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
            viewModel = LocalContext.current.fakeViewModel,
            uiState = MockData.bookUiState,
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
            viewModel = LocalContext.current.fakeViewModel,
            uiState = MockData.homeUiState,
            onBack = {},
            onIconClick = {}
        ) {}
    }
}
