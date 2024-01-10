package com.example.bookstore.ui.screens.account

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.ui.theme.BookStoreTheme
import com.example.bookstore.ui.utils.NavigationType

@Composable
fun AccountScreen(
    navigationType: NavigationType,
    modifier: Modifier = Modifier
) {
}

@Preview(showBackground = true)
@Composable
fun CompactAccountScreenPreview() {
    BookStoreTheme {
        AccountScreen(navigationType = NavigationType.BOTTOM_NAVIGATION)
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumAccountScreenPreview() {
    BookStoreTheme {
        AccountScreen(navigationType = NavigationType.NAVIGATION_RAIL)
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedAccountScreenPreview() {
    BookStoreTheme {
        AccountScreen(navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER)
    }
}