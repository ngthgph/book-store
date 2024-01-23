package com.example.gbook.ui.screens.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.data.fake.MockData
import com.example.gbook.data.model.BookCollection
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.ui.items.CollectionGrid
import com.example.gbook.ui.items.FABItem
import com.example.gbook.ui.items.SearchBar
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType

@Composable
fun MyLibraryScreen(
    uiState: GBookUiState,
    onSearch: (String) -> Unit,
    onButtonClick: (Function) -> Unit,
    onCollectionClick: (BookCollection) -> Unit,
    modifier: Modifier = Modifier,
    navigationType: NavigationType = NavigationType.BOTTOM_NAVIGATION,
) {
    MyLibraryContent(
        modifier = modifier,
        onSearch = onSearch,
        onButtonClick = onButtonClick,
        onCollectionClick = onCollectionClick,
        navigationType = navigationType,
        library = MockData.libraryUiState.library
    )
}

@Composable
fun MyLibraryContent(
    navigationType: NavigationType,
    library: List<BookCollection>,
    onSearch: (String) -> Unit,
    onButtonClick: (Function) -> Unit,
    onCollectionClick: (BookCollection) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FABItem(
                function = Function.Add,
                onButtonClick = onButtonClick
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                onSearch = onSearch,
            )
            CollectionGrid(
                modifier = modifier,
                navigationType = navigationType,
                categories = library,
                onButtonClick = onButtonClick,
                onCollectionClick = onCollectionClick,
                isLibrary = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompactFavoriteScreenPreview() {
    GBookTheme {
        MyLibraryScreen(
            MockData.libraryUiState,
            onButtonClick = {},
            onCollectionClick = {},
            onSearch = {},
        )
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumFavoriteScreenPreview() {
    GBookTheme {
        MyLibraryScreen(
            uiState = MockData.libraryUiState,
            navigationType = NavigationType.NAVIGATION_RAIL,
            onButtonClick = {},
            onCollectionClick = {},
            onSearch = {},
        )
    }
}