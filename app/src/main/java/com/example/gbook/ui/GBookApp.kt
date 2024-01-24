package com.example.gbook.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gbook.ui.utils.Screen
import com.example.gbook.ui.screens.navigation.BottomBarScreen
import com.example.gbook.ui.screens.navigation.DrawerScreen
import com.example.gbook.ui.screens.navigation.GBookNavHost
import com.example.gbook.ui.screens.navigation.RailScreen
import com.example.gbook.ui.utils.NavigationType

@Composable
fun GBookApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val viewModel: GBookViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val uiState = viewModel.uiState.collectAsState().value

    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    var screen = backStackEntry?.destination?.route
    var currentScreen = if
            (screen == null) Screen.Home else if
                    (Screen.values().any {it.name.equals(screen) }) Screen.valueOf(screen) else
                        Screen.Category

    val navigationType: NavigationType = when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            NavigationType.BOTTOM_NAVIGATION
        }
        WindowWidthSizeClass.Medium -> {
            NavigationType.NAVIGATION_RAIL
        }
        WindowWidthSizeClass.Expanded -> {
            NavigationType.PERMANENT_NAVIGATION_DRAWER
        }
        else -> {
            NavigationType.BOTTOM_NAVIGATION
        }
    }
    when (navigationType) {
        NavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            DrawerScreen(
                modifier = modifier,
                currentScreen = currentScreen,
                uiState = uiState,
                onIconClick = {
                    viewModel.onBackFromBookDetail()
                    navController.navigate(it.name)
                              },
                onBack = {navController.navigateUp()},
            ) {
                GBookNavHost(
                    navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER,
                    viewModel = viewModel,
                    uiState = uiState,
                    navController = navController,
                    onButtonClick = {},
                    onSearch = {},
                    onInput = {},
                )
            }
        }

        NavigationType.NAVIGATION_RAIL -> {
            RailScreen(
                currentScreen = currentScreen,
                uiState = uiState,
                onBack = {
                    if(uiState.currentBook == null) {
                        navController.navigateUp()
                    } else {
                        viewModel.onBackFromBookDetail()
                    }
                         },
                onIconClick = {
                    viewModel.onBackFromBookDetail()
                    navController.navigate(it.name)
                }
            ) {
                GBookNavHost(
                    navigationType = NavigationType.NAVIGATION_RAIL,
                    viewModel = viewModel,
                    uiState = uiState,
                    navController = navController,
                    onButtonClick = {},
                    onSearch = {},
                    onInput = {},
                )
            }
        }

        else -> {
            BottomBarScreen(
                currentScreen = currentScreen,
                uiState = uiState,
                onIconClick = {
                    viewModel.onBackFromBookDetail()
                    navController.navigate(it.name)
                              },
                onBack = {
                    if(uiState.currentBook == null) {
                        navController.navigateUp()
                    } else {
                        viewModel.onBackFromBookDetail()
                    }
                         },
            ) {
                GBookNavHost(
                    navigationType = NavigationType.BOTTOM_NAVIGATION,
                    viewModel = viewModel,
                    uiState = uiState,
                    navController = navController,
                    onButtonClick = {},
                    onSearch = {},
                    onInput = {},
                )
            }
        }
    }
}