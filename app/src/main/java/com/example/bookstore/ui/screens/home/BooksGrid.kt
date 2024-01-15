package com.example.bookstore.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookstore.R
import com.example.bookstore.data.local.MockData
import com.example.bookstore.data.model.Book
import com.example.bookstore.ui.theme.BookStoreTheme
import com.example.bookstore.ui.utils.Function
import com.example.bookstore.ui.utils.NavigationType

@Composable
fun BooksGridSection(
    navigationType: NavigationType,
    bookList: List<Book>,
    bookListTitle: String,
    onButtonClick: (Function) -> Unit,
    onCardClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        BooksGridTitle(
            navigationType = navigationType,
            title = bookListTitle
        )
        BooksGrid(
            navigationType = navigationType,
            bookList = bookList,
            onButtonClick = onButtonClick,
            onCardClick = onCardClick
        )
    }
}
@Composable
fun BooksGridTitle(
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
fun BooksGrid(
    navigationType: NavigationType,
    bookList: List<Book>,
    onButtonClick: (Function) -> Unit,
    onCardClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
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
        items(bookList) {
            BooksCard(
                book = it,
                onButtonClick = onButtonClick,
                selected = false,
                onCardClick = onCardClick,
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
    modifier: Modifier = Modifier,
    selected: Boolean,
    book: Book,
    onButtonClick: (Function) -> Unit,
    onCardClick: (Book) -> Unit,
) {
    Card(
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.elevation)),
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = modifier
            .size(dimensionResource(id = R.dimen.card_size_small))
            .clickable { onCardClick(book) }
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = book.author,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Box(modifier = Modifier.weight(1f)) {
                BookPhoto(title = book.title, imgSrc = book.imageLinks)
            }
            val price = book.retailPrice.toString()
            Row(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.price),
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = stringResource(R.string.price_display, price, book.currencyCode),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Divider(thickness = dimensionResource(id = R.dimen.divider_thickness))
            CardButtonRow(onButtonClick = onButtonClick)
        }
    }
}
@Composable
fun CardButtonRow(
    modifier: Modifier = Modifier,
    onButtonClick: (Function) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (function in enumValues<Function>()) {
            CardButton(
                function = function,
                modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(id = R.dimen.padding_extra_small)),
                onButtonClick = onButtonClick
            )
        }
    }
}
@Composable
fun CardButton(
    modifier: Modifier = Modifier,
    function: Function,
    onButtonClick: (Function) -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = { onButtonClick(function) },
    ) {
        Icon(
            imageVector = function.icon,
            contentDescription = stringResource(function.description),
            modifier = Modifier,
            tint = MaterialTheme.colorScheme.outline
        )
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
            .data(imgSrc)
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
    BookStoreTheme {
        val mockData = List(10){MockData.bookUiState.currentBook!!}
        BooksGridSection(
            navigationType = NavigationType.BOTTOM_NAVIGATION,
            bookListTitle = "Music",
            bookList = mockData,
            onButtonClick = {},
            onCardClick = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumBookGridSectionPreview() {
    BookStoreTheme {
        val mockData = List(10){MockData.bookUiState.currentBook!!}
        BooksGridSection(
            navigationType = NavigationType.NAVIGATION_RAIL,
            bookListTitle = "Music",
            bookList = mockData,
            onButtonClick = {},
            onCardClick = {}
        )
    }
}
@Preview
@Composable
fun BookGridPreview() {
    BookStoreTheme {
        val mockData = List(10){MockData.bookUiState.currentBook!!}
        BooksGrid(
            navigationType = NavigationType.BOTTOM_NAVIGATION,
            bookList = mockData,
            onButtonClick = {},
            onCardClick = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumBookGridPreview() {
    BookStoreTheme {
        val mockData = List(10){MockData.bookUiState.currentBook!!}
        BooksGrid(
            navigationType = NavigationType.NAVIGATION_RAIL,
            bookList = mockData,
            onButtonClick = {},
            onCardClick = {}
        )
    }
}

@Preview
@Composable
fun CardPreview() {
    BookStoreTheme {
        BooksCard(
            book = MockData.bookUiState.currentBook!!,
            onButtonClick = {},
            selected = true,
            onCardClick = {}
        )
    }
}