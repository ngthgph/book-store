package com.example.bookstore.ui.screens.account

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.data.local.MockData
import com.example.bookstore.data.model.BookStoreUiState
import com.example.bookstore.ui.screens.home.SearchBar
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun AccountScreen(
    uiState: BookStoreUiState,
    modifier: Modifier = Modifier
) {
    AccountContent()
}

@Composable
fun AccountContent(
    modifier: Modifier = Modifier
) {
    SearchBar(onSearch = {}, onClear = {}, isSearching = false)
}

@Preview(showBackground = true)
@Composable
fun CompactAccountScreenPreview() {
    BookStoreTheme {
        AccountScreen(
            uiState = MockData.accountUiState,
        )
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumAccountScreenPreview() {
    BookStoreTheme {
        AccountScreen(
            uiState = MockData.accountUiState,
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedAccountScreenPreview() {
    BookStoreTheme {
        AccountScreen(
            uiState = MockData.accountUiState,
        )
    }
}