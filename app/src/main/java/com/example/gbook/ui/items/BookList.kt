package com.example.gbook.ui.items

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.gbook.R
import com.example.gbook.data.fake.MockData
import com.example.gbook.data.database.books.Book
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType
import androidx.compose.ui.text.TextStyle
import com.example.gbook.data.database.account.Account
import com.example.gbook.data.database.collection.BookCollection
import com.example.gbook.data.fake.MockData.fakeOnFunction
import com.example.gbook.data.model.NetworkBookUiState
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.data.database.books.SearchQuery
import com.example.gbook.data.model.OfflineBookUiState
import com.example.gbook.ui.utils.NetworkFunction

@Composable
fun BooksListSection(
    navigationType: NavigationType,
    networkBookUiState: NetworkBookUiState,
    bookListTitle: String,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    modifier: Modifier = Modifier,
    searchQuery: SearchQuery? = null,
    offlineBookUiState: OfflineBookUiState? = null,
    isLibrary: Boolean = false,
) {
    Column(modifier = modifier) {
        CollectionTitle(
            navigationType = navigationType,
            title = bookListTitle
        )
        NetworkBooksList(
            navigationType = navigationType,
            networkBookUiState = networkBookUiState,
            searchQuery = searchQuery,
            isLibrary = isLibrary,
            onFunction = onFunction,
            onNetworkFunction = onNetworkFunction,
        )
    }
}
@Composable
fun NetworkBooksList(
    navigationType: NavigationType,
    networkBookUiState: NetworkBookUiState,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    modifier: Modifier = Modifier,
    searchQuery: SearchQuery? = null,
    offlineBookUiState: OfflineBookUiState? = null,
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
                is NetworkBookUiState.Success ->
                    BooksList(
                        navigationType = navigationType,
                        bookList = networkBookUiState.books,
                        isLibrary = isLibrary,
                        onFunction = onFunction,
                        modifier = modifier
                    )
                is NetworkBookUiState.Error ->
                    searchQuery?.let {
                        ErrorContent(
                            searchQuery = it,
                            onNetworkFunction = onNetworkFunction,
                            modifier = modifier
                        )
                    }
            }
        } else {
            BooksList(
                navigationType = navigationType,
                bookList = offlineBookUiState?.bookList!!,
                isLibrary = isLibrary,
                onFunction = onFunction,
                modifier = modifier
            )
        }
    }
}
@Composable
fun BooksList(
    navigationType: NavigationType,
    bookList: List<Book>,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    modifier: Modifier = Modifier,
    isLibrary: Boolean = false,
) {
    var padding = dimensionResource(id = R.dimen.padding_small)
    var padding_start = dimensionResource(id = R.dimen.padding_medium)
    if(navigationType != NavigationType.BOTTOM_NAVIGATION) {
        padding = dimensionResource(id = R.dimen.padding_small)
        padding_start = dimensionResource(id = R.dimen.padding_large)
    }
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(bookList) {
            BookItemCard(
                navigationType = navigationType,
                isLibrary = isLibrary,
                book = it,
                onFunction = onFunction,
                modifier = Modifier
                    .padding(
                        top = padding,
                        bottom = padding,
                        start = padding_start
                    )
                    .fillMaxWidth()
            )
        }
    }
}
@Composable
fun BookItemCard(
    book: Book,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    modifier: Modifier = Modifier,
    isCart: Boolean = false,
    amount: Int = 0,
    isLibrary: Boolean = false,
    navigationType: NavigationType,
) {
    var maxHeight = dimensionResource(id = R.dimen.book_item_row)
    var padding = dimensionResource(id = R.dimen.padding_small)
    var buttonSize = dimensionResource(id = R.dimen.button_medium)
    val functions = if(isCart)
        arrayOf(Function.IncreaseAmount, Function.DecreaseAmount)
        else arrayOf(Function.Cart, Function.Share)
    val besideFunction =
        if(isLibrary) Function.RemoveFromLibrary
        else if(isCart)Function.Delete
        else Function.AddToLibrary
    if(navigationType != NavigationType.BOTTOM_NAVIGATION) {
        maxHeight = dimensionResource(id = R.dimen.book_item_row_medium)
        padding = dimensionResource(id = R.dimen.padding_medium)
        buttonSize = dimensionResource(id = R.dimen.button_large)
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val context = LocalContext.current
        Card(
            modifier = Modifier
                .weight(1f)
                .clickable { onFunction(Function.BookCard,book,null,null,null,context) },
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_small)),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary),
            elevation = CardDefaults
                .cardElevation(dimensionResource(id = R.dimen.elevation_large)),
            border = BorderStroke(
                dimensionResource(id = R.dimen.divider_thickness),
                MaterialTheme.colorScheme.outlineVariant
            )
        ) {
            Row {
                BookItem(
                    navigationType = navigationType,
                    book = book,
                    amount = amount,
                    modifier = Modifier
                        .weight(1f)
                )
                Column(
                    modifier = Modifier
                        .height(maxHeight)
                ) {
                    for(function in functions) {
                        CardIconButton(
                            function = function,
                            book = book,
                            context = context,
                            onFunction = onFunction,
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CardIconButton(
                function = besideFunction,
                book = book,
                context = context,
                onFunction = onFunction,
                modifier = Modifier
            )
        }
    }
}
@Composable
fun BookItem(
    book: Book,
    modifier: Modifier = Modifier,
    amount: Int = 0,
    isCart: Boolean = false,
    navigationType: NavigationType,
) {

    var maxHeight = dimensionResource(id = R.dimen.book_item_row)
    var imagePadding = dimensionResource(id = R.dimen.padding_small)
    var titlePadding = dimensionResource(id = R.dimen.padding_small)
    var contentPadding = dimensionResource(id = R.dimen.padding_extra_small)
    var tileStyle = MaterialTheme.typography.bodyMedium
    var contentStyle = MaterialTheme.typography.bodySmall
    if(navigationType != NavigationType.BOTTOM_NAVIGATION) {
        maxHeight = dimensionResource(id = R.dimen.book_item_row_medium)
        imagePadding = dimensionResource(id = R.dimen.padding_medium)
        titlePadding = dimensionResource(id = R.dimen.padding_small)
        contentPadding = dimensionResource(id = R.dimen.padding_extra_small)
        tileStyle = MaterialTheme.typography.bodyLarge
        contentStyle = MaterialTheme.typography.bodyMedium
    }
    Row(
        modifier = modifier
            .sizeIn(maxHeight = maxHeight)
            .fillMaxWidth()
    ) {
        BookPhoto(
            title = book.title,
            imgSrc = book.imageLinks,
            modifier = Modifier
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.width(imagePadding))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = titlePadding),
            verticalArrangement = Arrangement
                .spacedBy(titlePadding)
        ) {
            Text(
                text = book.title,
                style = tileStyle,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                modifier = Modifier,
            )
            if(isCart) {
                BookInformation(
                    first = stringResource(id = R.string.price),
                    firstInfo = stringResource(
                        R.string.price_display,
                        book.retailPrice,
                        book.currencyCode
                    ),
                    second = stringResource(id = R.string.amount),
                    secondInfo = amount.toString(),
                    contentStyle = contentStyle,
                    titlePadding = titlePadding,
                    contentPadding = contentPadding
                )
            } else {
                BookInformation(
                    first = stringResource(R.string.author),
                    firstInfo = book.author,
                    second = stringResource(id = R.string.price),
                    secondInfo = stringResource(
                        R.string.price_display,
                        book.retailPrice,
                        book.currencyCode
                    ),
                    contentStyle = contentStyle,
                    titlePadding = titlePadding,
                    contentPadding = contentPadding
                )
            }
        }
    }
}
@Composable
fun BookInformation(
    first: String,
    firstInfo: String,
    second: String,
    secondInfo: String,
    contentStyle: TextStyle,
    titlePadding: Dp,
    contentPadding: Dp,
    modifier: Modifier = Modifier
) {
    Row (
        horizontalArrangement = Arrangement.Start
    ) {
        InfoColumn(
            first = first,
            second = second,
            contentStyle = contentStyle,
            contentPadding = contentPadding
        )
        Spacer(modifier = Modifier.width(titlePadding))
        Column(
            verticalArrangement = Arrangement.spacedBy(contentPadding)
        ) {
            InfoColumn(
                first = firstInfo,
                second = secondInfo,
                contentStyle = contentStyle,
                contentPadding = contentPadding
            )
        }
    }
}

@Composable
fun InfoColumn(
    first: String,
    second: String,
    contentStyle: TextStyle,
    contentPadding: Dp,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(contentPadding),
        modifier = modifier
    ) {
        Text(
            text = first,
            style = contentStyle,
            maxLines = 1,
            fontWeight = FontWeight.Normal,
            modifier = Modifier,
            )
        Text(
            text = second,
            style = contentStyle,
            maxLines = 1,
            fontWeight = FontWeight.Normal,
            modifier = Modifier,
        )
    }
}

@Preview
@Composable
fun BookListPreview() {
    GBookTheme {
        BooksList(
            navigationType = NavigationType.BOTTOM_NAVIGATION,
            bookList = MockData.bookList,
            onFunction = fakeOnFunction
        )
    }
}

@Preview(widthDp = 700)
@Composable
fun RailBookListPreview() {
    GBookTheme {
        BooksList(
            navigationType = NavigationType.NAVIGATION_RAIL,
            bookList = MockData.bookList,
            onFunction = fakeOnFunction
        )
    }
}
