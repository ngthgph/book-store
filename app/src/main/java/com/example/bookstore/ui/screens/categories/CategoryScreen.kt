package com.example.bookstore.ui.screens.categories

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.data.local.MockData
import com.example.bookstore.data.model.BookStoreUiState
import com.example.bookstore.ui.screens.home.SearchBar
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun CategoryScreen(
    uiState: BookStoreUiState,
    modifier: Modifier = Modifier
) {
    CategoryContent()
}

@Composable
fun CategoryContent(
    modifier: Modifier = Modifier
) {
    SearchBar(onSearch = {}, onClear = {}, isSearching = false)
}

@Preview(showBackground = true)
@Composable
fun CompactCategoryScreenPreview() {
    BookStoreTheme {
        CategoryScreen(uiState = MockData.categoryUiState)
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumCategoryScreenPreview() {
    BookStoreTheme {
        CategoryScreen(uiState = MockData.categoryUiState)
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedCategoryScreenPreview() {
    BookStoreTheme {
        CategoryScreen(uiState = MockData.categoryUiState)
    }
}