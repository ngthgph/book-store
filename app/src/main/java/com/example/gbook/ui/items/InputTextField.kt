package com.example.gbook.ui.items

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.R
import com.example.gbook.ui.theme.GBookTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputTextField(
    onInput:(String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
) {
    var input by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        value = input,
        onValueChange = { input = it},
        placeholder = {
            Text(
                text = placeholder.replaceFirstChar { it.uppercase() },
                color = MaterialTheme.colorScheme.outline
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onInput(input)
                keyboardController?.hide()
            }
        ),
        maxLines = 1,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_small))
    )
}

@Preview
@Composable
fun InputPreview() {
    GBookTheme {
        InputTextField(
            onInput = {},
            placeholder = "Placeholder"
        )
    }
}