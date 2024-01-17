package com.example.gbook.ui.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
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
import com.example.gbook.R
import com.example.gbook.data.local.MockData
import com.example.gbook.data.model.Book
import com.example.gbook.data.model.GbookUiState
import com.example.gbook.ui.items.BookItemCard
import com.example.gbook.ui.items.ButtonItem
import com.example.gbook.ui.items.FABItem
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType

@Composable
fun CartScreen(
    navigationType: NavigationType = NavigationType.BOTTOM_NAVIGATION,
    uiState: GbookUiState,
    modifier: Modifier = Modifier
) {
    CartContent(
        navigationType = navigationType,
        shoppingList = uiState.cart,
        onButtonClick = {},
        onCardClick = {},
        modifier = modifier
    )
}
@Composable
fun CartContent(
    navigationType: NavigationType,
    shoppingList: List<Pair<Book, Int>>,
    onButtonClick: (Function) -> Unit,
    onCardClick: (Book) -> Unit,
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
                onCardClick = onCardClick,
                modifier = Modifier.weight(3f)
            )

            Divider()
            if(shoppingList.isNotEmpty()) {
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
    onCardClick: (Book) -> Unit,
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
        items(shoppingList) {
            BookItemCard(
                navigationType = navigationType,
                isCart = true,
                book = it.first,
                amount = it.second,
                onButtonClick = onButtonClick,
                onCardClick = onCardClick,
            )
        }
        item {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                FABItem(
                    function = Function.AddToCart,
                    padding = space,
                    onButtonClick = onButtonClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompactCartScreenPreview() {
    GBookTheme {
        CartScreen(uiState = MockData.cartUiState)
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumCartScreenPreview() {
    GBookTheme {
        CartScreen(uiState = MockData.cartUiState)
    }
}