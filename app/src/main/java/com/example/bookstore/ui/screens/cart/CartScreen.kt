package com.example.bookstore.ui.screens.cart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.data.local.MockData
import com.example.bookstore.data.model.BookStoreUiState
import com.example.bookstore.ui.screens.home.SearchBar
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun CartScreen(
    uiState: BookStoreUiState,
    modifier: Modifier = Modifier
) {
    CartContent()
}

@Composable
fun CartContent(
    modifier: Modifier = Modifier
) {
    SearchBar(onSearch = {}, onClear = {}, isSearching = false)
}

@Preview(showBackground = true)
@Composable
fun CompactCartScreenPreview() {
    BookStoreTheme {
        CartScreen(uiState = MockData.cartUiState)
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumCartScreenPreview() {
    BookStoreTheme {
        CartScreen(uiState = MockData.cartUiState)
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedCartScreenPreview() {
    BookStoreTheme {
        CartScreen(uiState = MockData.cartUiState)
    }
}