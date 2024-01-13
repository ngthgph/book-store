package com.example.bookstore.ui.screens.categories

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.data.local.MockData
import com.example.bookstore.data.model.BookStoreUiState
import com.example.bookstore.ui.screens.home.SearchBar
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun CategoriesScreen(
    uiState: BookStoreUiState,
    modifier: Modifier = Modifier
) {
    CategoriesContent()
}

@Composable
fun CategoriesContent(
    modifier: Modifier = Modifier
) {
    SearchBar(onSearch = {}, isSearching = false)
}

@Preview(showBackground = true)
@Composable
fun CompactCategoriesScreenPreview() {
    BookStoreTheme {
        CategoriesScreen(uiState = MockData.categoriesUiState)
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumCategoriesScreenPreview() {
    BookStoreTheme {
        CategoriesScreen(uiState = MockData.categoriesUiState)
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedCategoriesScreenPreview() {
    BookStoreTheme {
        CategoriesScreen(uiState = MockData.categoriesUiState)
    }
}