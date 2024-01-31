package com.example.gbook.ui.screens.categories

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.data.database.account.Account
import com.example.gbook.data.database.books.Book
import com.example.gbook.data.local.LocalCategoriesProvider
import com.example.gbook.data.database.collection.BookCollection
import com.example.gbook.data.fake.MockData.fakeOnFunction
import com.example.gbook.data.fake.MockData.fakeOnNetworkFunction
import com.example.gbook.ui.items.CollectionGrid
import com.example.gbook.ui.items.SearchBar
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.data.database.books.SearchQuery
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType
import com.example.gbook.ui.utils.NetworkFunction

@Composable
fun CategoriesScreen(
    navigationType: NavigationType,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    navigateToCollection: (BookCollection) -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = LocalCategoriesProvider.categories
    CategoriesContent(
        modifier = modifier,
        navigationType = navigationType,
        categories = categories,
        onFunction = onFunction,
        onNetworkFunction = onNetworkFunction,
        navigateToCollection = navigateToCollection,
    )
}

@Composable
fun CategoriesContent(
    navigationType: NavigationType,
    categories: List<BookCollection>,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    navigateToCollection: (BookCollection) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        SearchBar(onNetworkFunction = onNetworkFunction)
        CollectionGrid(
            navigationType = navigationType,
            collections = categories,
            onFunction = onFunction,
            onNetworkFunction = onNetworkFunction,
            navigateToCollection = navigateToCollection,
        )
    }
}
@Preview(showBackground = true)
@Composable
fun CompactCategoriesScreenPreview() {
    GBookTheme {
        CategoriesScreen(
            navigationType = NavigationType.BOTTOM_NAVIGATION,
            onFunction = fakeOnFunction,
            onNetworkFunction = fakeOnNetworkFunction,
            navigateToCollection = {}
        )
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumCategoriesScreenPreview() {
    GBookTheme {
        CategoriesScreen(
            navigationType = NavigationType.NAVIGATION_RAIL,
            onFunction = fakeOnFunction,
            onNetworkFunction = fakeOnNetworkFunction,
            navigateToCollection = {}
        )
    }
}