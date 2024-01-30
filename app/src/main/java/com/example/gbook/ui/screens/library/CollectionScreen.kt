package com.example.gbook.ui.screens.library

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.R
import com.example.gbook.data.database.account.Account
import com.example.gbook.data.database.books.Book
import com.example.gbook.data.database.collection.BookCollection
import com.example.gbook.data.fake.FakeDataSource.fakeViewModel
import com.example.gbook.data.fake.MockData
import com.example.gbook.data.fake.MockData.fakeOnFunction
import com.example.gbook.data.fake.MockData.fakeOnNetworkFunction
import com.example.gbook.data.local.LocalCategoriesProvider
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.ui.GBookViewModel
import com.example.gbook.ui.items.GridOrLinearLayout
import com.example.gbook.ui.screens.book.ListDetailHandler
import com.example.gbook.ui.screens.categories.CategoryTitle
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.data.database.books.SearchQuery
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType
import com.example.gbook.ui.utils.NetworkFunction

@Composable
fun CollectionScreen(
    navigationType: NavigationType,
    viewModel: GBookViewModel,
    uiState: GBookUiState,
    collection: BookCollection,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        ListDetailHandler(
            navigationType = navigationType,
            viewModel = viewModel,
            uiState = uiState,
            onFunction = onFunction,
            modifier = Modifier,
            isLibrary = true
        ) {
            CollectionContent(
                navigationType = navigationType,
                viewModel = viewModel,
                title = collection.name,
                image = painterResource(id = collection.image),
                onFunction = onFunction,
                onNetworkFunction = onNetworkFunction,
            )
        }
    }
}

@Composable
fun CollectionContent(
    navigationType: NavigationType,
    viewModel: GBookViewModel,
    title: String,
    image: Painter,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    modifier: Modifier = Modifier,
) {
    val space = if (navigationType == NavigationType.BOTTOM_NAVIGATION)
        R.dimen.padding_medium else R.dimen.padding_medium

    val offlineBookList = viewModel.offlineBookUiState.collectAsState().value.bookList
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
            if(offlineBookList.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = stringResource(R.string.no_books_on_the_shelf),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        textAlign = TextAlign.Center
                    )
                }
            }
            else {
                GridOrLinearLayout(
                    navigationType = navigationType,
                    networkBookUiState = viewModel.networkBookUiState,
                    offlineBookList = offlineBookList,
                    layoutPreferencesUiState = viewModel.layoutPreferencesUiState.collectAsState().value,
                    searchQuery = SearchQuery(title),
                    onFunction = onFunction,
                    onNetworkFunction = onNetworkFunction,
                    isLibrary = true
                )
            }
        }
    }
}

@Composable
fun CollectionTitle(
    navigationType: NavigationType,
    title: String,
    image: Painter,
    modifier: Modifier = Modifier
) {
    val padding = if (navigationType == NavigationType.BOTTOM_NAVIGATION)
        R.dimen.padding_extra_small else R.dimen.padding_small
    Box(modifier = modifier.fillMaxWidth()) {
        Image(
            painter = image,
            contentDescription = title,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Row (
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.onPrimaryContainer.copy(0.5f),
                            Color.Transparent
                        ),
                        startY = 100f,
                        endY = 0f // Adjust the endX value based on your preference
                    )
                )
                .align(Alignment.BottomStart)
                .fillMaxWidth(),
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier
                    .padding(dimensionResource(id = padding))
                    .background(Color.Transparent),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompactCollectionScreenPreview() {
    GBookTheme {
        CollectionScreen(
            navigationType = NavigationType.BOTTOM_NAVIGATION,
            viewModel = LocalContext.current.fakeViewModel,
            uiState = MockData.categoryUiState,
            collection = LocalCategoriesProvider.categories[1],
            onFunction = fakeOnFunction,
            onNetworkFunction = fakeOnNetworkFunction,
        )
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumCollectionScreenPreview() {
    GBookTheme {
        CollectionScreen(
            navigationType = NavigationType.NAVIGATION_RAIL,
            viewModel = LocalContext.current.fakeViewModel,
            uiState = MockData.categoryUiState,
            collection = LocalCategoriesProvider.categories[1],
            onFunction = fakeOnFunction,
            onNetworkFunction = fakeOnNetworkFunction,
        )
    }
}