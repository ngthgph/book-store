package com.example.gbook.ui.screens.cart

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.R
import com.example.gbook.data.database.account.Account
import com.example.gbook.data.fake.MockData
import com.example.gbook.data.database.books.Book
import com.example.gbook.data.database.collection.BookCollection
import com.example.gbook.data.fake.MockData.fakeOnFunction
import com.example.gbook.data.fake.MockData.fakeOnNetworkFunction
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.ui.items.BookItemCard
import com.example.gbook.ui.items.DescriptionButton
import com.example.gbook.ui.items.FABItem
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.data.database.books.SearchQuery
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType
import com.example.gbook.ui.utils.NetworkFunction

@Composable
fun CartScreen(
    uiState: GBookUiState,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    modifier: Modifier = Modifier,
    navigationType: NavigationType = NavigationType.BOTTOM_NAVIGATION,
) {
    CartContent(
        navigationType = navigationType,
        bookList = MockData.bookList,
        onFunction = onFunction,
        onNetworkFunction = onNetworkFunction,
        modifier = modifier
    )
}
@Composable
fun CartContent(
    navigationType: NavigationType,
    bookList: List<Book>,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    modifier: Modifier = Modifier
) {
    var padding = dimensionResource(id = R.dimen.padding_extra_large)
    if(navigationType == NavigationType.BOTTOM_NAVIGATION) {
        padding = dimensionResource(id = R.dimen.padding_large)
    }
    Surface(
        modifier = modifier.padding(padding),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_small)),
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            if(bookList.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = stringResource(R.string.your_cart_is_empty),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                CartList(
                    navigationType = navigationType,
                    bookList = bookList,
                    onFunction = onFunction,
                    onNetworkFunction = onNetworkFunction,
                    modifier = Modifier.weight(3f)
                )
            }

            Divider()

            if(bookList.isNotEmpty()) {
                CartSummary()
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
                    DescriptionButton(
                        function = Function.Checkout,
                        enable = bookList.isNotEmpty(),
                        onFunction = onFunction,
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
    modifier: Modifier = Modifier,
    price: Double = 0.0,
    shipping: Double = 0.0,
    tax: Double = 0.0,
    total: Double = 0.0,
    currency: String = "VND",
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
    bookList: List<Book>,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    modifier: Modifier = Modifier
) {
    var space = dimensionResource(id = R.dimen.padding_small)
    if(navigationType != NavigationType.BOTTOM_NAVIGATION) {
        space = dimensionResource(id = R.dimen.padding_medium)
    }
    LazyColumn(
        verticalArrangement = Arrangement
            .spacedBy(space),
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(bookList) {
            BookItemCard(
                navigationType = navigationType,
                isCart = true,
                book = it,
                amount = it.cartAmount!!,
                onFunction = onFunction,
            )
        }
        item {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                FABItem(
                    function = Function.AddToCart,
                    padding = space,
                    onFunction = onFunction,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompactCartScreenPreview() {
    GBookTheme {
        CartScreen(
            uiState = MockData.cartUiState,
            onFunction = fakeOnFunction,
            onNetworkFunction = fakeOnNetworkFunction,
        )
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumCartScreenPreview() {
    GBookTheme {
        CartScreen(
            uiState = MockData.cartUiState,
            onFunction = fakeOnFunction,
            onNetworkFunction = fakeOnNetworkFunction,
        )
    }
}