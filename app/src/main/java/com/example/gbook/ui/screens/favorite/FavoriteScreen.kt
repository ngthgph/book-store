package com.example.gbook.ui.screens.favorite

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.data.local.MockData
import com.example.gbook.data.model.Book
import com.example.gbook.data.model.GbookUiState
import com.example.gbook.ui.items.SearchBar
import com.example.gbook.ui.theme.GBookTheme

@Composable
fun FavoriteScreen(
    uiState: GbookUiState,
    modifier: Modifier = Modifier
) {

}

@Composable
fun FavoriteContent(
    bookList: List<Book>,
    onSearch: (String) -> Unit,
    isSearching: Boolean,
    modifier: Modifier = Modifier
) {
    if(bookList.isNotEmpty()) {
        SearchBar(onSearch = {}, isSearching = false)
    }
}

@Preview(showBackground = true)
@Composable
fun CompactFavoriteScreenPreview() {
    GBookTheme {
        FavoriteScreen(uiState = MockData.favoriteUiState)
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumFavoriteScreenPreview() {
    GBookTheme {
        FavoriteScreen(uiState = MockData.favoriteUiState)
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedFavoriteScreenPreview() {
    GBookTheme {
        FavoriteScreen(uiState = MockData.favoriteUiState)
    }
}