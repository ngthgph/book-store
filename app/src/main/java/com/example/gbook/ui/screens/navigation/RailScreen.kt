package com.example.gbook.ui.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.R
import com.example.gbook.data.fake.MockData
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.ui.items.AppHeaderBar
import com.example.gbook.ui.items.OnlyAccountHomeHeader
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Screen

@Composable
fun RailScreen(
    currentScreen: Screen,
    uiState: GBookUiState,
    onIconClick: (Screen) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
    ) {
        Row(
            modifier = Modifier
                .padding(it)
        ) {
            AppNavigationRail(
                currentScreen = currentScreen,
                onIconClick = onIconClick
            )
            if (currentScreen == Screen.Home && uiState.currentBook == null) {
                Box(
                    modifier = modifier
                        .weight(1f)
                        .background(MaterialTheme.colorScheme.inverseOnSurface)
                        .fillMaxSize()
                ) {
                    OnlyAccountHomeHeader(
                        account = uiState.account?.name?: stringResource(id = R.string.account),
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
                    modifier = modifier
                        .weight(1f)
                        .background(MaterialTheme.colorScheme.inverseOnSurface)
                        .fillMaxSize()
                ) {
                    AppHeaderBar(
                        currentScreen = currentScreen,
                        uiState = uiState,
                        onIconClick = onIconClick,
                        onBack = onBack)
                    Column(modifier = Modifier.weight(1f)) {
                        content()
//                AdsBanner()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun RailScreenPreview() {
    GBookTheme {
        RailScreen(
            currentScreen = Screen.Categories,
            uiState = MockData.categoriesUiState,
            onIconClick = {},
            onBack = { /*TODO*/ }
        ) {}
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun HomeRailScreenPreview() {
    GBookTheme {
        RailScreen(
            currentScreen = Screen.Home,
            uiState = MockData.homeUiState,
            onIconClick = {},
            onBack = { /*TODO*/ }
        ) {}
    }
}