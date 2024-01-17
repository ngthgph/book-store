package com.example.gbook.ui.items

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.gbook.R
import com.example.gbook.ui.utils.Function

@Composable
fun ButtonItem(
    function: Function,
    enable: Boolean = true,
    onButtonClick: (Function) -> Unit,
    modifier: Modifier = Modifier
) {
//    Button(
//        modifier = modifier,
//        enabled = enable,
//        onClick = { onButtonClick(function) },
//        elevation = ButtonDefaults.buttonElevation(dimensionResource(id = R.dimen.elevation)),
//        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
//    ) {
//        Text(
//            text = stringResource(id = function.description),
//            color = MaterialTheme.colorScheme.onPrimary
//        )
//    }
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