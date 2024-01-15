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
    var currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route?: Screen.Home.name
    )
    val uiState = MockData.bookUiState

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
            var isBookScreen by remember { mutableStateOf(currentScreen == Screen.Book) }
            if(isBookScreen) currentScreen = Screen.valueOf(navController.previousBackStackEntry?.destination?.route!!)
            DrawerScreen(
                modifier = modifier,
                currentScreen = currentScreen,
                uiState = uiState,
                onIconClick = { navController.navigate(it.name) },
                onBack = {navController.navigateUp()},
                onButtonClick = {}
            ) {
                BookStoreNavHost(
                    navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER,
                    uiState = uiState,
                    navController = navController,
                    onButtonClick = {}
                )
            }
        }

        NavigationType.NAVIGATION_RAIL -> {
            RailScreen(
                currentScreen = currentScreen,
                uiState = uiState,
                onBack = {navController.navigateUp()},
                onIconClick = { navController.navigate(it.name) }
            ) {
                BookStoreNavHost(
                    navigationType = NavigationType.NAVIGATION_RAIL,
                    uiState = uiState,
                    navController = navController,
                    onButtonClick = {}
                )
            }
        }

        else -> {
            BottomBarScreen(
                currentScreen = currentScreen,
                uiState = uiState,
                onIconClick = { navController.navigate(it.name) },
                onBack = {navController.navigateUp()}
            ) {
                BookStoreNavHost(
                    navigationType = NavigationType.BOTTOM_NAVIGATION,
                    uiState = uiState,
                    navController = navController,
                    onButtonClick = {}
                )
            }
        }
    }
}