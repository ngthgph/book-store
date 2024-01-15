package com.example.bookstore.ui.screens.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.Key.Companion.F
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.R
import com.example.bookstore.data.local.MockData
import com.example.bookstore.data.model.Book
import com.example.bookstore.data.model.BookStoreUiState
import com.example.bookstore.ui.screens.home.BooksGrid
import com.example.bookstore.ui.screens.home.SearchBar
import com.example.bookstore.ui.theme.BookStoreTheme
import com.example.bookstore.ui.utils.Function
import com.example.bookstore.ui.utils.NavigationType

@Composable
fun CategoryScreen(
    navigationType: NavigationType,
    uiState: BookStoreUiState,
    modifier: Modifier = Modifier
) {
    val mockData = List(10){MockData.homeUiState.currentBook!!}
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        CategoryContent(
            navigationType = navigationType,
            bookList = mockData,
            title = uiState.currentCategory?.name?: stringResource(R.string.category),
            image = painterResource(id = uiState.currentCategory!!.image),
            onButtonClick = {},
            onCardClick = {},
            onSearch = {},
            isSearching = false
        )
    }
}

@Composable
fun CategoryContent(
    navigationType: NavigationType,
    bookList: List<Book>,
    title: String,
    image: Painter,
    onButtonClick: (Function) -> Unit,
    onCardClick: (Book) -> Unit,
    onSearch: (String) -> Unit,
    isSearching: Boolean,
    modifier: Modifier = Modifier,
) {
    val space = if (navigationType == NavigationType.BOTTOM_NAVIGATION)
        R.dimen.padding_medium else R.dimen.padding_medium
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(space)),
        modifier = modifier.fillMaxWidth()
    ) {
        CategoryTitle(
            navigationType = navigationType,
            title = title,
            image = image,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Column(
            modifier = Modifier.weight(3f)
        ) {
            SearchBar(onSearch = onSearch, isSearching = isSearching)
            BooksGrid(
                navigationType = navigationType,
                bookList = bookList,
                onButtonClick = onButtonClick,
                onCardClick = onCardClick
            )
        }
    }
}

@Composable
fun CategoryTitle(
    navigationType: NavigationType,
    title: String,
    image: Painter,
    modifier: Modifier = Modifier
) {
    val padding = if (navigationType == NavigationType.BOTTOM_NAVIGATION)
        R.dimen.padding_small else R.dimen.padding_medium
    Box(modifier = modifier.fillMaxWidth()) {
        Image(
            painter = image,
            contentDescription = title,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = title,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(dimensionResource(id = padding)),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CompactCategoryScreenPreview() {
    BookStoreTheme {
        CategoryScreen(
            navigationType = NavigationType.BOTTOM_NAVIGATION,
            uiState = MockData.categoryUiState
        )
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumCategoryScreenPreview() {
    BookStoreTheme {
        CategoryScreen(
            navigationType = NavigationType.NAVIGATION_RAIL,
            uiState = MockData.categoryUiState
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedCategoryScreenPreview() {
    BookStoreTheme {
        CategoryScreen(
            navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER,
            uiState = MockData.categoryUiState
        )
    }
}