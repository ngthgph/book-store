package com.example.bookstore.ui.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.R
import com.example.bookstore.data.local.MockData
import com.example.bookstore.data.model.Book
import com.example.bookstore.data.model.BookStoreUiState
import com.example.bookstore.ui.theme.BookStoreTheme
import com.example.bookstore.ui.utils.Function
import com.example.bookstore.ui.utils.NavigationType

@Composable
fun CartScreen(
    uiState: BookStoreUiState,
    modifier: Modifier = Modifier
) {
    CartContent(
        navigationType = NavigationType.BOTTOM_NAVIGATION,
        shoppingList = uiState.cart,
        onButtonClick = {}
    )
}
@Composable
fun CartContent(
    navigationType: NavigationType,
    shoppingList: List<Pair<Book, Int>>,
    onButtonClick: (Function) -> Unit,
    modifier: Modifier = Modifier
) {
    var padding = dimensionResource(id = R.dimen.padding_extra_large)
    if(navigationType == NavigationType.BOTTOM_NAVIGATION) {
        padding = dimensionResource(id = R.dimen.padding_large)
    }
    Scaffold(
        modifier = modifier.padding(padding),
    ) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            CartList(
                navigationType = navigationType,
                shoppingList = shoppingList,
                onButtonClick = onButtonClick,
                modifier = Modifier.weight(3f)
            )

            Divider()
            if(shoppingList.isNotEmpty()) {
                CartSummary()
                Divider()
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    ButtonItem(
                        function = Function.Checkout,
                        enable = shoppingList.isNotEmpty(),
                        onButtonClick = onButtonClick,
                        modifier = Modifier.wrapContentWidth()
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
@Composable
fun CartSummary(
    price: Double = 0.0,
    shipping: Double = 0.0,
    tax: Double = 0.0,
    total: Double = 0.0,
    currency: String = "VND",
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        Text(
            text = stringResource(R.string.summary),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
        Row {
            Spacer(modifier = Modifier.weight(3f))
            Column(
                verticalArrangement = Arrangement
                    .spacedBy(dimensionResource(id = R.dimen.padding_small)),
                modifier = Modifier
            ) {
                Text(text = stringResource(R.string.total_price))
                Text(text = stringResource(R.string.shipping_fee))
                Text(text = stringResource(R.string.vat))
                Text(
                    text = stringResource(R.string.subtotal),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement
                    .spacedBy(dimensionResource(id = R.dimen.padding_small)),
                modifier = Modifier
            ) {
                Text(text = stringResource(id = R.string.price_summary,price.toString()))
                Text(text = stringResource(id = R.string.price_summary,shipping.toString()))
                Text(text = stringResource(id = R.string.price_summary,tax.toString()))
                Text(
                    text = stringResource(id = R.string.price_summary,total.toString()),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CartList(
    navigationType: NavigationType,
    shoppingList: List<Pair<Book, Int>>,
    onButtonClick: (Function) -> Unit,
    modifier: Modifier = Modifier
) {

    val rowSize: Int
    var padding = dimensionResource(id = R.dimen.padding_small)
    when(navigationType) {
        NavigationType.BOTTOM_NAVIGATION -> rowSize = 1
        else -> rowSize = 2
    }
    when(rowSize) {
        2 -> {
            padding = dimensionResource(id = R.dimen.padding_medium)
        }
        3 -> {
            padding = dimensionResource(id = R.dimen.padding_medium)
        }
    }
    LazyColumn(
        verticalArrangement = Arrangement
            .spacedBy(padding),
        modifier = modifier
            .padding(padding)
            .fillMaxWidth()
    ) {
        items(shoppingList) {
            CartItemCard(
                rowSize = rowSize,
                book = it.first,
                amount = it.second,
                onButtonClick = onButtonClick,
                modifier = Modifier
            )
        }
        item {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                FloatingActionButton(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    shape = CircleShape,
                    onClick = {onButtonClick(Function.AddToCart)},
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(padding)
                ) {
                    Icon(
                        imageVector = Function.AddToCart.icon,
                        contentDescription = stringResource(id = Function.AddToCart.description)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompactCartScreenPreview() {
    BookStoreTheme {
        CartScreen(uiState = MockData.cartUiState)
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumCartScreenPreview() {
    BookStoreTheme {
        CartScreen(uiState = MockData.cartUiState)
    }
}