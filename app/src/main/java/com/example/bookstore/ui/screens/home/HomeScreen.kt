package com.example.bookstore.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.data.local.MockData
import com.example.bookstore.data.model.BookStoreUiState
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun HomeScreen(
    uiState: BookStoreUiState,
    modifier: Modifier = Modifier
) {
    HomeContent()
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier
) {
    SearchBar(onSearch = {}, isSearching = false)
}

@Preview(showBackground = true)
@Composable
fun CompactHomeScreenPreview() {
    BookStoreTheme {
        HomeScreen(
            uiState = MockData.accountUiState,
        )
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumHomeScreenPreview() {
    BookStoreTheme {
        HomeScreen(
            uiState = MockData.accountUiState,
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedHomeScreenPreview() {
    BookStoreTheme {
        HomeScreen(
            uiState = MockData.accountUiState,
        )
    }
}