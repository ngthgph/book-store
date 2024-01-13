package com.example.bookstore.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookstore.data.local.MockData
import com.example.bookstore.ui.utils.Screen
import com.example.bookstore.ui.screens.navigation.BookStoreNavHost
import com.example.bookstore.ui.screens.navigation.BottomBarScreen
import com.example.bookstore.ui.screens.navigation.DrawerScreen
import com.example.bookstore.ui.screens.navigation.RailScreen
import com.example.bookstore.ui.utils.NavigationType

@Composable
fun BookStoreApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val navigationType: NavigationType
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route?: Screen.Home.name
    )
    val uiState = MockData.homeUiState

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = NavigationType.BOTTOM_NAVIGATION
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = NavigationType.NAVIGATION_RAIL
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER
        }
        else -> {
            navigationType = NavigationType.BOTTOM_NAVIGATION
        }
    }
    when (navigationType) {
        NavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            var isBookScreen by remember { mutableStateOf(uiState.currentScreen == Screen.Book) }
            if(isBookScreen)
            DrawerScreen(
                modifier = modifier,
                uiState = uiState,
                onIconClick = { navController.navigate(it.name) },
                onBack = {},
                onButtonClick = {}
            ) {
                BookStoreNavHost(
                    navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER,
                    uiState = uiState,
                    navController = navController
                )
            }
        }

        NavigationType.NAVIGATION_RAIL -> {
            RailScreen(
                uiState = uiState,
                onBack = {},
                onIconClick = { navController.navigate(it.name) }
            ) {
                BookStoreNavHost(
                    navigationType = NavigationType.NAVIGATION_RAIL,
                    uiState = uiState,
                    navController = navController
                )
            }
        }

        else -> {
            BottomBarScreen(
                uiState = uiState,
                onIconClick = { navController.navigate(it.name) },
                onBack = {}
            ) {
                BookStoreNavHost(
                    navigationType = NavigationType.BOTTOM_NAVIGATION,
                    uiState = uiState,
                    navController = navController
                )
            }
        }
    }
}