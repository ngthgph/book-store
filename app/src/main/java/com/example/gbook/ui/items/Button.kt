package com.example.gbook.ui.items

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
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Function

@Composable
fun PageNavigation(
    onButtonClick: (Function) -> Unit,
    PrevEnabled: Boolean,
    NextEnabled: Boolean,
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
            onButtonClick = onButtonClick,
            enabled = PrevEnabled,
        )
        Text(text = " | ")
        ClickableText(
            function = Function.NextPage,
            onButtonClick = onButtonClick,
            enabled = NextEnabled,
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
    onButtonClick: (Function) -> Unit,
) {
    Card (
        shape = CircleShape,
        modifier = modifier
            .clip(CircleShape)
            .aspectRatio(1f)
            .clickable { onButtonClick(function) },
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
    onButtonClick: (Function) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
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
                onButtonClick(function)
            }
        )
    )
}

@Composable
fun CardIconButton(
    function: Function,
    onButtonClick: (Function) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.tertiary
) {
    IconButton(
        modifier = modifier
            .wrapContentWidth(),
        onClick = { onButtonClick(function) },
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
    onButtonClick: (Function) -> Unit,
    modifier: Modifier = Modifier,
    padding: Dp = dimensionResource(id = R.dimen.padding_medium),
) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.onPrimary,
        shape = CircleShape,
        onClick = {onButtonClick(function)},
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
    enable: Boolean = true,
    onButtonClick: (Function) -> Unit,
    modifier: Modifier = Modifier
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
            PrevEnabled = false,
            NextEnabled = true,
            onButtonClick = {}
        )
    }
}
@Preview
@Composable
fun PreviewButtonCard() {
    GBookTheme {
        ButtonCard(function = Function.Share, onButtonClick = {})
    }
}
@Preview
@Composable
fun PreviewClickableText() {
    GBookTheme {
        ClickableText(function = Function.NextPage, onButtonClick = {})
    }
}
@Preview
@Composable
fun PreviewCardIconButton() {
    GBookTheme {
        CardIconButton(function = Function.Add, onButtonClick = {})
    }
}
@Preview
@Composable
fun PreviewFABItem() {
    GBookTheme {
        FABItem(function = Function.Add, onButtonClick = {})
    }
}
@Preview
@Composable
fun PreviewDescriptionButton() {
    GBookTheme {
        DescriptionButton(function = Function.SignIn, onButtonClick = {})
    }
}