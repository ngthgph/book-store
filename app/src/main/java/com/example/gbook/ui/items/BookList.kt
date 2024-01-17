package com.example.gbook.ui.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.gbook.R
import com.example.gbook.data.local.MockData
import com.example.gbook.data.model.Book
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType
import androidx.compose.ui.text.TextStyle
import com.example.gbook.ui.theme.GBookTheme

@Composable
fun BookList(
    navigationType: NavigationType,
    bookList: List<Book>,
    isFavorite: Boolean = false,
    onButtonClick: (Function) -> Unit,
    onCardClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
) {
    var padding = dimensionResource(id = R.dimen.padding_large)
    if(navigationType != NavigationType.BOTTOM_NAVIGATION) {
        padding = dimensionResource(id = R.dimen.padding_medium)
    }
    LazyColumn {
        items(bookList) {
            BookItemCard(
                navigationType = navigationType,
                isFavorite = isFavorite,
                book = it,
                onButtonClick = onButtonClick,
                onCardClick = onCardClick,
                modifier = Modifier
                    .padding(padding)
                    .fillMaxWidth()
            )
        }
    }
}
@Composable
fun BookItemCard(
    navigationType: NavigationType = NavigationType.BOTTOM_NAVIGATION,
    book: Book,
    isCart: Boolean = false,
    amount: Int = 0,
    isFavorite: Boolean = false,
    onButtonClick: (Function) -> Unit,
    onCardClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
) {
    var maxHeight = dimensionResource(id = R.dimen.book_item_row)
    var padding = dimensionResource(id = R.dimen.padding_small)
    var buttonSize = dimensionResource(id = R.dimen.button_medium)
    val functions = if(isCart)
        arrayOf(Function.Add, Function.Decline)
        else arrayOf(Function.Cart, Function.Share)
    val besideFunction = if(!isCart) Function.Favorite else Function.Delete
    if(navigationType != NavigationType.BOTTOM_NAVIGATION) {
        maxHeight = dimensionResource(id = R.dimen.book_item_row_medium)
        padding = dimensionResource(id = R.dimen.padding_medium)
        buttonSize = dimensionResource(id = R.dimen.button_large)
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .weight(1f)
                .clickable { onCardClick(book) },
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
                        .padding(padding)
                ) {
                    for(function in functions) {
                        OutlinedButtonCard(
                            function = function,
                            onButtonClick = onButtonClick,
                            modifier = Modifier
                                .weight(1f)
                        )
                        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.padding_extra_small)))
                    }
                }
                Spacer(modifier = Modifier.width(padding))
            }
        }
        if (!isFavorite) {
            Row(
                modifier = Modifier
                    .padding(start = padding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButtonCard(
                    function = besideFunction,
                    onButtonClick = onButtonClick,
                    modifier = Modifier
                        .size(buttonSize)
                        .fillMaxHeight()
                )
            }
        }
    }
}
@Composable
fun OutlinedButtonCard(
    function: Function,
    enable: Boolean = true,
    modifier: Modifier = Modifier,
    onButtonClick: (Function) -> Unit
) {
    Card (
        shape = CircleShape,
        elevation = CardDefaults
            .cardElevation(dimensionResource(id = R.dimen.elevation)),
        border = BorderStroke(
            dimensionResource(id = R.dimen.divider_thickness),
            MaterialTheme.colorScheme.outlineVariant),
        modifier = modifier
            .clip(CircleShape)
            .aspectRatio(1f)
            .clickable { onButtonClick(function) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
    )  {
        IconButton(
            modifier = Modifier.fillMaxSize(),
            onClick = {},
        ) {
            Icon(
                imageVector = function.icon,
                contentDescription = stringResource(function.description),
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_extra_small)),
                tint = if(enable) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.outline
            )
        }
    }
}
@Composable
fun BookItem(
    book: Book,
    amount: Int = 0,
    isCart: Boolean = false,
    navigationType: NavigationType = NavigationType.BOTTOM_NAVIGATION,
    modifier: Modifier = Modifier
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
                .aspectRatio(0.75f)
        )
        Spacer(modifier = Modifier.width(imagePadding))
        Column(
            modifier = Modifier
                .weight(2f)
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
        BookItemCard(
            book = MockData.bookUiState.currentBook!!,
            onButtonClick = {},
            onCardClick = {}
        )
    }
}
