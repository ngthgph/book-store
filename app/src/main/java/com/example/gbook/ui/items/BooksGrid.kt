package com.example.gbook.ui.items

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gbook.R
import com.example.gbook.data.database.account.Account
import com.example.gbook.data.fake.MockData
import com.example.gbook.data.database.books.Book
import com.example.gbook.data.database.collection.BookCollection
import com.example.gbook.data.fake.MockData.fakeOnFunction
import com.example.gbook.data.model.NetworkBookUiState
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.data.database.books.SearchQuery
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType
import com.example.gbook.ui.utils.NetworkFunction

@Composable
fun BooksGridSection(
    navigationType: NavigationType,
    networkBookUiState: NetworkBookUiState,
    bookListTitle: String,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    modifier: Modifier = Modifier,
    searchQuery: SearchQuery? = null,
    offlineBookList: List<Book>? = null,
    isLibrary: Boolean = false,
) {
    Column(
        modifier = modifier
    ) {
        CollectionTitle(
            navigationType = navigationType,
            title = bookListTitle
        )
        NetworkBooksGrid(
            navigationType = navigationType,
            networkBookUiState = networkBookUiState,
            searchQuery = searchQuery,
            offlineBookList = offlineBookList,
            isLibrary = isLibrary,
            onFunction = onFunction,
            onNetworkFunction = onNetworkFunction,
        )
    }
}
@Composable
fun CollectionTitle(
    navigationType: NavigationType,
    title: String,
    modifier: Modifier = Modifier,
) {
    val thickness: Int
    val padding: Int
    if(navigationType == NavigationType.BOTTOM_NAVIGATION) {
        thickness = R.dimen.divider_thickness
        padding = R.dimen.padding_extra_small
    } else {
        thickness = R.dimen.divider_thickness_large
        padding = R.dimen.padding_small
    }
    Column(modifier = modifier) {
        if(title.isNotEmpty()) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(
                        start = dimensionResource(id = padding),
                    )
            )
        }
        Divider(
            thickness = dimensionResource(id = thickness),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = padding),
                    top = dimensionResource(id = padding),
                    bottom = dimensionResource(id = padding)
                )
        )
    }
}
@Composable
fun NetworkBooksGrid(
    navigationType: NavigationType,
    networkBookUiState: NetworkBookUiState,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    modifier: Modifier = Modifier,
    searchQuery: SearchQuery? = null,
    offlineBookList: List<Book>? = null,
    isLibrary: Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//        PageNavigation(
//            PrevEnabled = false,
//            NextEnabled = false,
//            onButtonClick = onButtonClick
//        )
        if(!isLibrary) {
            when(networkBookUiState) {
                is NetworkBookUiState.Loading -> LoadingContent(modifier = modifier)
                is NetworkBookUiState.Success -> {
                    BooksGrid(
                        navigationType = navigationType,
                        bookList = networkBookUiState.books,
                        isLibrary = false,
                        onFunction = onFunction,
                        modifier = modifier
                    )
                }
                is NetworkBookUiState.Error ->
                    searchQuery?.let {
                        ErrorContent(
                            searchQuery = it,
                            onNetworkFunction = onNetworkFunction,
                            modifier = modifier
                        )
                    }
            }
        } else{
            offlineBookList?.let {
                BooksGrid(
                    navigationType = navigationType,
                    bookList = it,
                    isLibrary = true,
                    onFunction = onFunction,
                    modifier = modifier
                )
            }
        }
    }
}
@Composable
fun BooksGrid(
    navigationType: NavigationType,
    bookList: List<Book>,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
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
        modifier = modifier.fillMaxWidth(),
        columns = GridCells.Adaptive(dimensionResource(id = column)),
        contentPadding = PaddingValues(dimensionResource(id = padding)),
    ) {
        items(bookList) {
            BooksCard(
                navigationType = navigationType,
                isLibrary = isLibrary,
                book = it,
                onFunction = onFunction,
                selected = false,
                modifier = Modifier
                    .padding(dimensionResource(id = padding))
                    .fillMaxWidth()
                    .aspectRatio(0.8f)
            )
        }
    }
}

@Composable
fun BooksCard(
    selected: Boolean,
    book: Book,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    modifier: Modifier = Modifier,
    isLibrary: Boolean = false,
    navigationType: NavigationType = NavigationType.BOTTOM_NAVIGATION,
) {
    Card(
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.elevation)),
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.secondaryContainer
            else
                MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
            .clickable { onFunction(Function.BookCard, book, null, null, null, null) }
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = book.title,
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_extra_small)),
            )
            Text(
                text = book.author,
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_extra_small)),
            )
            Box(modifier = Modifier.weight(1f)) {
                BookPhoto(title = book.title, imgSrc = book.imageLinks)
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.onPrimaryContainer.copy(0.75f),
                                        Color.Transparent
                                    ),
                                    startY = 100f,
                                    endY = 0f // Adjust the endX value based on your preference
                                )
                            )
                    ) {
                        val price = book.retailPrice.toString()
                        Row(
                            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_extra_small)),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.price),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_extra_small)))
                            Text(
                                text = stringResource(R.string.price_display, price, book.currencyCode),
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 1,
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Divider(
                            thickness = dimensionResource(id = R.dimen.divider_thickness),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        val context = LocalContext.current
                        CardButtonRow(
                            context = context,
                            book = book,
                            isLibrary = isLibrary,
                            onFunction = onFunction,
                            modifier = Modifier.height(
                                if(navigationType != NavigationType.BOTTOM_NAVIGATION)
                                    dimensionResource(id = R.dimen.button_medium)
                                else dimensionResource(id = R.dimen.button_small)
                            ),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun CardButtonRow(
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    modifier: Modifier = Modifier,
    isLibrary: Boolean = false,
    color: Color = MaterialTheme.colorScheme.tertiary,
    book: Book? = null,
    collection: BookCollection? = null,
    account: Account? = null,
    string: String? = null,
    context: Context? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for(function in if(isLibrary)
            arrayOf(Function.RemoveFromLibrary, Function.Cart, Function.Share)
            else
            arrayOf(Function.AddToLibrary, Function.Cart, Function.Share)
        ) {
            CardIconButton(
                function = function,
                book = book,
                context = context,
                modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(id = R.dimen.padding_extra_small)),
                onFunction = onFunction,
                color = color
            )
        }
    }
}
@Composable
fun BookPhoto(
    title: String,
    imgSrc: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(imgSrc.replace("http","https"))
            .crossfade(true)
            .build(),
        placeholder = painterResource(id = R.drawable.loading_img),
        error = painterResource(id = R.drawable.ic_broken_image),
        contentDescription = stringResource(R.string.image, title),
        contentScale = ContentScale.Crop,
        modifier = modifier.fillMaxSize()
    )
}

@Preview
@Composable
fun BookGridSectionPreview() {
    GBookTheme {
        BooksGridSection(
            navigationType = NavigationType.BOTTOM_NAVIGATION,
            bookListTitle = "Music",
            onFunction = fakeOnFunction,
            onNetworkFunction = MockData.fakeOnNetworkFunction,
            networkBookUiState = MockData.fakeNetworkBookUiState
        )
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumBookGridSectionPreview() {
    GBookTheme {
        BooksGridSection(
            navigationType = NavigationType.NAVIGATION_RAIL,
            bookListTitle = "Music",
            onFunction = fakeOnFunction,
            onNetworkFunction = MockData.fakeOnNetworkFunction,
            networkBookUiState = MockData.fakeNetworkBookUiState
        )
    }
}