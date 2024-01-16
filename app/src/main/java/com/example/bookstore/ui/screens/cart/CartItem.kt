package com.example.bookstore.ui.screens.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.R
import com.example.bookstore.data.local.MockData
import com.example.bookstore.data.model.Book
import com.example.bookstore.ui.screens.home.BookPhoto
import com.example.bookstore.ui.theme.BookStoreTheme
import com.example.bookstore.ui.utils.Function

@Composable
fun CartItemCard(
    rowSize: Int = 1,
    book: Book,
    amount: Int = 1,
    onButtonClick: (Function) -> Unit,
    modifier: Modifier = Modifier,
) {
    var maxHeight = dimensionResource(id = R.dimen.book_item_row)
    var padding = dimensionResource(id = R.dimen.padding_small)
    when(rowSize) {
        2 -> {
            maxHeight = dimensionResource(id = R.dimen.book_item_row_medium)
            padding = dimensionResource(id = R.dimen.padding_medium)
        }
        3 -> {
            maxHeight = dimensionResource(id = R.dimen.book_item_row_large)
            padding = dimensionResource(id = R.dimen.padding_medium)
        }
    }
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_small)),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary),
        elevation = CardDefaults
            .cardElevation(dimensionResource(id = R.dimen.elevation_large)),
        border = BorderStroke(
            dimensionResource(id = R.dimen.divider_thickness),
            MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Row(
        ) {
            CartItem(
                rowSize = rowSize,
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
                VectorIcon(
                    function = Function.Add,
                    onButtonClick = onButtonClick,
                    modifier = Modifier
                        .weight(1f)
                )
                Spacer(modifier = modifier
                    .height(padding)
                )
                VectorIcon(
                    function = Function.Remove,
                    onButtonClick = onButtonClick,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.width(padding))
        }
        
    }
}
@Composable
fun CartItem(
    rowSize: Int = 1,
    book: Book,
    amount: Int = 1,
    modifier: Modifier = Modifier,
) {
    var maxHeight = dimensionResource(id = R.dimen.book_item_row)
    var imagePadding = dimensionResource(id = R.dimen.padding_medium)
    var titlePadding = dimensionResource(id = R.dimen.padding_small)
    var contentPadding = dimensionResource(id = R.dimen.padding_extra_small)
    var tileStyle = MaterialTheme.typography.bodyMedium
    var contentStyle = MaterialTheme.typography.bodySmall
    when(rowSize) {
        2 -> {
            maxHeight = dimensionResource(id = R.dimen.book_item_row_medium)
            imagePadding = dimensionResource(id = R.dimen.padding_medium)
            titlePadding = dimensionResource(id = R.dimen.padding_small)
            contentPadding = dimensionResource(id = R.dimen.padding_extra_small)
            tileStyle = MaterialTheme.typography.bodyLarge
            contentStyle = MaterialTheme.typography.bodyMedium
        }
        3 -> {
            maxHeight = dimensionResource(id = R.dimen.book_item_row_large)
            imagePadding = dimensionResource(id = R.dimen.padding_medium)
            titlePadding = dimensionResource(id = R.dimen.padding_medium)
            contentPadding = dimensionResource(id = R.dimen.padding_small)
            tileStyle = MaterialTheme.typography.headlineSmall
            contentStyle = MaterialTheme.typography.bodyLarge
        }
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
                .padding(titlePadding),
            verticalArrangement = Arrangement
                .spacedBy(titlePadding)
        ) {
            Text(
                text = book.title,
                style = tileStyle,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
            )
            Row (
                horizontalArrangement = Arrangement.Start
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(contentPadding)
                ) {
                    Text(
                        text = stringResource(id = R.string.price),
                        style = contentStyle,
                        color = MaterialTheme.colorScheme.outline,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier,
                    )
                    Text(
                        text = stringResource(id = R.string.amount),
                        style = contentStyle,
                        color = MaterialTheme.colorScheme.outline,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier,
                    )
                }
                Spacer(modifier = Modifier.width(titlePadding))
                Column(
                    verticalArrangement = Arrangement.spacedBy(contentPadding)
                ) {
                    Text(
                        text = stringResource(R.string.price_display, book.retailPrice, book.currencyCode),
                        style = contentStyle,
                        color = MaterialTheme.colorScheme.outline,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier,
                    )
                    Text(
                        text = amount.toString(),
                        style = contentStyle,
                        color = MaterialTheme.colorScheme.outline,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier,
                    )
                }
            }
        }
    }
}
@Composable
fun PainterIcon(
    function: Function,
    painter: Painter,
    enable: Boolean = true,
    onButtonClick: (Function) -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        enabled = enable,
        onClick = {onButtonClick(function)},
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = modifier
            .background(MaterialTheme.colorScheme.onPrimary,CircleShape)
    ) {
        Icon(
            painter = painter,
            contentDescription = stringResource(id = function.description),
            modifier = Modifier.background(Color.Transparent)
        )
    }
}
@Composable
fun VectorIcon(
    function: Function,
    enable: Boolean = true,
    onButtonClick: (Function) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary),
        shape = CircleShape,
        elevation = CardDefaults
            .cardElevation(dimensionResource(id = R.dimen.elevation)),
        border = BorderStroke(
            dimensionResource(id = R.dimen.divider_thickness),
            MaterialTheme.colorScheme.outlineVariant
        ),
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
            .clickable(
                enabled = enable,
                onClick = { onButtonClick(function) },
            )
            .sizeIn(
                maxWidth = dimensionResource(id = R.dimen.button_medium),
                maxHeight = dimensionResource(id = R.dimen.button_medium)
            )
            .aspectRatio(1f)
    ) {
        Icon(
            imageVector = function.icon,
            tint = if(enable) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surfaceVariant,
            contentDescription = stringResource(id = function.description),
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxSize()
        )
    }
}
@Composable
fun ButtonItem(
    function: Function,
    enable: Boolean = true,
    onButtonClick: (Function) -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier,
        enabled = enable,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.outlineVariant,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        elevation = ButtonDefaults
            .buttonElevation(dimensionResource(id = R.dimen.elevation)),
        onClick = { onButtonClick(function) }
    ) {
        Text(
            text = stringResource(id = function.description)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BookItemPreview() {
    BookStoreTheme {
        CartItemCard(
            book = MockData.bookUiState.currentBook!!,
            onButtonClick = {}
        )
    }
}