package com.example.bookstore.ui.screens.home

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.R
import com.example.bookstore.data.model.Screen
import com.example.bookstore.ui.screens.navigation.BottomBarScreen
import com.example.bookstore.ui.screens.navigation.DrawerScreen
import com.example.bookstore.ui.screens.navigation.RailScreen
import com.example.bookstore.ui.theme.BookStoreTheme
import com.example.bookstore.ui.utils.NavigationType

@Composable
fun HomeScreen(
    navigationType: NavigationType,
    onIconClick: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    when (navigationType) {
        NavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            DrawerScreen (
                modifier = modifier,
                onIconClick = onIconClick
            ) {
                Row {
                    HomeContent()
                }
            }
        }
        NavigationType.NAVIGATION_RAIL -> {
            RailScreen(
                title = stringResource(id = R.string.app_name),
                modifier = modifier,
                onIconClick = onIconClick
            ) {
                HomeContent()
            }
        }
        else -> {
            BottomBarScreen(
                title = stringResource(id = R.string.app_name),
                modifier = modifier,
                onIconClick = onIconClick
            ) {
                HomeContent()
            }
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier
) {
    SearchBar(onSearch = {}, onClear = {}, isSearching = false)
}

@Preview(showBackground = true)
@Composable
fun CompactHomeScreenPreview() {
    BookStoreTheme {
        HomeScreen(navigationType = NavigationType.BOTTOM_NAVIGATION, onIconClick = {})
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumHomeScreenPreview() {
    BookStoreTheme {
        HomeScreen(navigationType = NavigationType.NAVIGATION_RAIL, onIconClick = {})
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedHomeScreenPreview() {
    BookStoreTheme {
        HomeScreen(navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER, onIconClick = {})
    }
}