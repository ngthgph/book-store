package com.example.bookstore.ui.screens.categories

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.ui.screens.account.AccountScreen
import com.example.bookstore.ui.theme.BookStoreTheme
import com.example.bookstore.ui.utils.NavigationType

@Composable
fun CategoryScreen(
    navigationType: NavigationType,
    modifier: Modifier = Modifier
) {
}

@Preview(showBackground = true)
@Composable
fun CompactCategoryScreenPreview() {
    BookStoreTheme {
        CategoryScreen(navigationType = NavigationType.BOTTOM_NAVIGATION)
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumCategoryScreenPreview() {
    BookStoreTheme {
        CategoryScreen(navigationType = NavigationType.NAVIGATION_RAIL)
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedCategoryScreenPreview() {
    BookStoreTheme {
        CategoryScreen(navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER)
    }
}