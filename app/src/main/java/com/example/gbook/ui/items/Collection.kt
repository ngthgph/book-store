package com.example.gbook.ui.items

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.gbook.R
import com.example.gbook.data.database.account.Account
import com.example.gbook.data.database.books.Book
import com.example.gbook.data.database.collection.BookCollection
import com.example.gbook.data.local.LocalCategoriesProvider
import com.example.gbook.data.database.books.SearchQuery
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType
import com.example.gbook.ui.utils.NetworkFunction

@Composable
fun CollectionGrid(
    navigationType: NavigationType,
    collections: List<BookCollection>,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    navigateToCollection: (BookCollection) -> Unit,
    modifier: Modifier = Modifier,
    isLibrary: Boolean = false,
) {
    val column: Int
    val padding: Int
    if(navigationType == NavigationType.BOTTOM_NAVIGATION) {
        column = R.dimen.grid_column_small
        padding = R.dimen.padding_extra_small
    } else {
        column = R.dimen.grid_column
        padding = R.dimen.padding_small
    }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(id = column)),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(dimensionResource(id = padding))
    ) {
        items(collections) {
            CollectionCard(
                navigationType = navigationType,
                bookCollection = it,
                onFunction = onFunction,
                onNetworkFunction = onNetworkFunction,
                navigateToCollection = navigateToCollection,
                selected = false,
                isLibrary = isLibrary,
                modifier = Modifier
                    .padding(dimensionResource(id = padding))
                    .fillMaxWidth()
                    .aspectRatio(0.8f)
            )
        }
    }
}

@Composable
fun CollectionCard(
    navigationType: NavigationType,
    selected: Boolean,
    bookCollection: BookCollection,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    navigateToCollection: (BookCollection) -> Unit,
    modifier: Modifier = Modifier,
    isLibrary: Boolean = false,
) {
    val padding = if (navigationType == NavigationType.BOTTOM_NAVIGATION)
        R.dimen.padding_small else R.dimen.padding_medium
    Card(
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.elevation)),
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = modifier
            .clickable {
                navigateToCollection(bookCollection)
            }
    ) {
        Box(modifier = Modifier) {
            Image(
                painter = painterResource(
                    id = bookCollection.image
                ),
                contentDescription = bookCollection.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            if (!isLibrary) {
                CardIconButton(
                    function = Function.AddToLibrary,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .align(Alignment.TopEnd)
                        .background(MaterialTheme.colorScheme.onPrimary, CircleShape)
                        .sizeIn(
                            maxWidth = dimensionResource(id = R.dimen.button_small),
                            maxHeight = dimensionResource(id = R.dimen.button_small)
                        ),
                    onFunction = onFunction,
                )
            }
            Row(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.onPrimaryContainer,
                                Color.Transparent
                            ),
                            startY = 100f,
                            endY = 0f
                        )
                    )
                    .align(Alignment.BottomStart)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = bookCollection.name,
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(dimensionResource(id = padding))
                        .background(Color.Transparent)
                )
            }
        }
    }
}

