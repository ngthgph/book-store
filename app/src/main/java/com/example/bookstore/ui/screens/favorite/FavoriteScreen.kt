package com.example.bookstore.ui.screens.favorite

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.R
import com.example.bookstore.ui.screens.home.SearchBar
import com.example.bookstore.ui.screens.navigation.BottomBarScreen
import com.example.bookstore.ui.screens.navigation.DrawerScreen
import com.example.bookstore.ui.screens.navigation.RailScreen
import com.example.bookstore.ui.theme.BookStoreTheme
import com.example.bookstore.ui.utils.NavigationType

@Composable
fun FavoriteScreen(
    navigationType: NavigationType,
    modifier: Modifier = Modifier
) {
    when (navigationType) {
        NavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            DrawerScreen (
                modifier = modifier
            ) {
                Row {
                    FavoriteContent()
                }
            }
        }
        NavigationType.NAVIGATION_RAIL -> {
            RailScreen(
                title = stringResource(id = R.string.app_name),
                modifier = modifier
            ) {
                FavoriteContent()
            }
        }
        else -> {
            BottomBarScreen(
                title = stringResource(id = R.string.app_name),
                modifier = modifier
            ) {
                FavoriteContent()
            }
        }
    }
}

@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier
) {
    SearchBar(onSearch = {}, onClear = {}, isSearching = false)
}

@Preview(showBackground = true)
@Composable
fun CompactFavoriteScreenPreview() {
    BookStoreTheme {
        FavoriteScreen(navigationType = NavigationType.BOTTOM_NAVIGATION)
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumFavoriteScreenPreview() {
    BookStoreTheme {
        FavoriteScreen(navigationType = NavigationType.NAVIGATION_RAIL)
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedFavoriteScreenPreview() {
    BookStoreTheme {
        FavoriteScreen(navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER)
    }
}