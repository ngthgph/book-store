package com.example.gbook.ui.items

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gbook.data.database.account.Account
import com.example.gbook.data.database.books.Book
import com.example.gbook.data.database.collection.BookCollection
import com.example.gbook.data.model.LayoutPreferencesUiState
import com.example.gbook.data.model.NetworkBookUiState
import com.example.gbook.data.database.books.SearchQuery
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType
import com.example.gbook.ui.utils.NetworkFunction

@Composable
fun GridOrLinearLayout(
    navigationType: NavigationType,
    networkBookUiState: NetworkBookUiState,
    layoutPreferencesUiState: LayoutPreferencesUiState,
    searchQuery: SearchQuery,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    modifier: Modifier = Modifier,
    offlineBookList: List<Book>? = null,
    isLibrary: Boolean = false,
) {
    if(layoutPreferencesUiState.isGridLayout) {
        NetworkBooksGrid(
            navigationType = navigationType,
            networkBookUiState = networkBookUiState,
            offlineBookList = offlineBookList,
            searchQuery = searchQuery,
            onFunction = onFunction,
            onNetworkFunction = onNetworkFunction,
            isLibrary = isLibrary,
            modifier = modifier
        )
    } else {
        NetworkBooksList(
            navigationType = navigationType,
            networkBookUiState = networkBookUiState,
            offlineBookList = offlineBookList,
            searchQuery = searchQuery,
            onFunction = onFunction,
            onNetworkFunction = onNetworkFunction,
            isLibrary = isLibrary,
            modifier = modifier
        )
    }
}