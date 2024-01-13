package com.example.bookstore.ui.screens.favorite

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.data.local.MockData
import com.example.bookstore.data.model.BookStoreUiState
import com.example.bookstore.ui.screens.home.SearchBar
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun FavoriteScreen(
    uiState: BookStoreUiState,
    modifier: Modifier = Modifier
) {

}

@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier
) {
    SearchBar(onSearch = {}, isSearching = false)
}

@Preview(showBackground = true)
@Composable
fun CompactFavoriteScreenPreview() {
    BookStoreTheme {
        FavoriteScreen(uiState = MockData.favoriteUiState)
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumFavoriteScreenPreview() {
    BookStoreTheme {
        FavoriteScreen(uiState = MockData.favoriteUiState)
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedFavoriteScreenPreview() {
    BookStoreTheme {
        FavoriteScreen(uiState = MockData.favoriteUiState)
    }
}