package com.example.gbook.ui.items

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.example.gbook.R
import com.example.gbook.ui.utils.Function

@Composable
fun CardButton(
    function: Function,
    onButtonClick: (Function) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.tertiary
) {
    IconButton(
        modifier = modifier,
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
fun ButtonItem(
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