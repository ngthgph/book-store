package com.example.gbook.ui.screens.library

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.data.database.account.Account
import com.example.gbook.data.database.books.Book
import com.example.gbook.data.fake.MockData
import com.example.gbook.data.database.collection.BookCollection
import com.example.gbook.data.fake.MockData.fakeOnFunction
import com.example.gbook.data.fake.MockData.fakeOnNetworkFunction
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.ui.items.CollectionGrid
import com.example.gbook.ui.items.FABItem
import com.example.gbook.ui.items.SearchBar
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.data.database.books.SearchQuery
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType
import com.example.gbook.ui.utils.NetworkFunction

@Composable
fun MyLibraryScreen(
    uiState: GBookUiState,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    navigateToCollection: (BookCollection) -> Unit,
    modifier: Modifier = Modifier,
    navigationType: NavigationType = NavigationType.BOTTOM_NAVIGATION,
) {
    MyLibraryContent(
        modifier = modifier,
        onFunction = onFunction,
        onNetworkFunction = onNetworkFunction,
        navigateToCollection = navigateToCollection,
        navigationType = navigationType,
        library = uiState.collection
    )
}

@Composable
fun MyLibraryContent(
    navigationType: NavigationType,
    library: List<BookCollection>,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    navigateToCollection: (BookCollection) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FABItem(
                function = Function.AddCollection,
                onFunction = onFunction,
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                onNetworkFunction = onNetworkFunction,
            )
            CollectionGrid(
                modifier = modifier,
                navigationType = navigationType,
                collections = library,
                onFunction = onFunction,
                onNetworkFunction = onNetworkFunction,
                navigateToCollection = navigateToCollection,
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
            onFunction = fakeOnFunction,
            onNetworkFunction = fakeOnNetworkFunction,
            navigateToCollection = {}
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
            onFunction = fakeOnFunction,
            onNetworkFunction = fakeOnNetworkFunction,
            navigateToCollection = {}
        )
    }
}