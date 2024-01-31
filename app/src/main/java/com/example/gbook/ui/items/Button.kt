package com.example.gbook.ui.items

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.gbook.R
import com.example.gbook.data.database.account.Account
import com.example.gbook.data.database.books.Book
import com.example.gbook.data.database.collection.BookCollection
import com.example.gbook.data.fake.MockData.fakeOnFunction
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Function

@Composable
fun PageNavigation(
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    prevEnabled: Boolean,
    nextEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        ClickableText(
            function = Function.PreviousPage,
            onFunction = onFunction,
            enabled = prevEnabled,
        )
        Text(text = " | ")
        ClickableText(
            function = Function.NextPage,
            onFunction = onFunction,
            enabled = nextEnabled,
        )
    }
}
@Composable
fun HeaderButton(
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    painter: Painter? = null,
    imageVector: ImageVector? = null
){
    IconButton(
        onClick = onClick,
        modifier = modifier
            .background(Color.Transparent, CircleShape)
            .clip(CircleShape)
    ) {
        if(painter!=null) {
            Icon(
                painter = painter,
                contentDescription = description,
                tint = MaterialTheme.colorScheme.primary,
            )
        } else {
            Icon(
                imageVector = imageVector!!,
                contentDescription = description,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
@Composable
fun ButtonCard(
    function: Function,
    modifier: Modifier = Modifier,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    book: Book? = null,
    collection: BookCollection? = null,
    account: Account? = null,
    string: String? = null,
    context: Context? = null,
) {
    Card (
        shape = CircleShape,
        modifier = modifier
            .clip(CircleShape)
            .aspectRatio(1f)
            .clickable { onFunction(function,book,collection,account,string,context) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
    )  {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = function.icon,
                contentDescription = stringResource(function.description),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_extra_small)),
                tint = MaterialTheme.colorScheme.outline
            )
        }
    }
}
@Composable
fun ClickableText(
    function: Function,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    book: Book? = null,
    collection: BookCollection? = null,
    account: Account? = null,
    string: String? = null,
    context: Context? = null,
) {
    var isClicking by remember { mutableStateOf(false) }
    Text(
        text = stringResource(function.description),
        textDecoration = if(isClicking) TextDecoration.Underline else null,
        color = if(!isClicking && enabled) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = modifier.clickable(
            enabled = enabled,
            onClick = {
                isClicking = !isClicking
                onFunction(function,book,collection,account,string,context)
            }
        )
    )
}

@Composable
fun CardIconButton(
    function: Function,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.tertiary,
    book: Book? = null,
    collection: BookCollection? = null,
    account: Account? = null,
    string: String? = null,
    context: Context? = null,
) {
    IconButton(
        modifier = modifier
            .wrapContentWidth(),
        onClick = { onFunction(function,book,collection,account,string,context) },
    ) {
        Icon(
            imageVector = function.icon,
            contentDescription = stringResource(function.description),
            modifier = Modifier,
            tint = color,
        )
    }
}
@Composable
fun FABItem(
    function: Function,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    modifier: Modifier = Modifier,
    padding: Dp = dimensionResource(id = R.dimen.padding_medium),
    book: Book? = null,
    collection: BookCollection? = null,
    account: Account? = null,
    string: String? = null,
    context: Context? = null,
) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.onPrimary,
        shape = CircleShape,
        onClick = { onFunction(function,book,collection,account,string,context) },
        modifier = modifier
            .wrapContentWidth()
            .padding(padding)
    ) {
        Icon(
            imageVector = Function.AddToCart.icon,
            contentDescription = stringResource(id = Function.AddToCart.description),
            tint = MaterialTheme.colorScheme.tertiary
        )
    }
}
@Composable
fun DescriptionButton(
    function: Function,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    book: Book? = null,
    collection: BookCollection? = null,
    account: Account? = null,
    string: String? = null,
    context: Context? = null,
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
        onClick = { onFunction(function,book,collection,account,string,context) }
    ) {
        Text(
            text = stringResource(id = function.description)
        )
    }
}

@Preview
@Composable
fun PreviewHeaderButton() {
    GBookTheme {
        HeaderButton(
            description = "Button",
            onClick = { /*TODO*/ },
            imageVector = Icons.Default.AccountCircle
        )
    }
}
@Preview
@Composable
fun PreviewPageNavigation() {
    GBookTheme {
        PageNavigation(
            prevEnabled = false,
            nextEnabled = true,
            onFunction = fakeOnFunction,
        )
    }
}
@Preview
@Composable
fun PreviewButtonCard() {
    GBookTheme {
        ButtonCard(function = Function.Share, onFunction = fakeOnFunction)
    }
}
@Preview
@Composable
fun PreviewClickableText() {
    GBookTheme {
        ClickableText(function = Function.NextPage, onFunction = fakeOnFunction)
    }
}
@Preview
@Composable
fun PreviewCardIconButton() {
    GBookTheme {
        CardIconButton(function = Function.IncreaseAmount, onFunction = fakeOnFunction)
    }
}
@Preview
@Composable
fun PreviewFABItem() {
    GBookTheme {
        FABItem(function = Function.IncreaseAmount, onFunction = fakeOnFunction)
    }
}
@Preview
@Composable
fun PreviewDescriptionButton() {
    GBookTheme {
        DescriptionButton(function = Function.Checkout, onFunction = fakeOnFunction)
    }
}