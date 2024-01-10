package com.example.bookstore.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.bookstore.R
import com.example.bookstore.ui.screens.navigation.AppNavigationBarScreen
import com.example.bookstore.ui.screens.navigation.AppNavigationDrawerScreen
import com.example.bookstore.ui.screens.navigation.AppNavigationRailScreen
import com.example.bookstore.ui.utils.NavigationType

@Composable
fun HomeScreen(
    navigationType: NavigationType,
    modifier: Modifier = Modifier
) {
    when (navigationType) {
        NavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            AppNavigationDrawerScreen (
                modifier = modifier
            ) {
            }
        }
        NavigationType.NAVIGATION_RAIL -> {
            AppNavigationRailScreen(
                title = stringResource(id = R.string.app_name),
                modifier = modifier
            ) {
            }
        }
        else -> {
            AppNavigationBarScreen(
                title = stringResource(id = R.string.app_name),
                modifier = modifier
            ) {
            }
        }
    }
}

