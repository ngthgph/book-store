package com.example.gbook.ui.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.R
import com.example.gbook.ui.theme.GBookTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
) {
    var query by rememberSaveable { mutableStateOf("") }
    var isSearching by rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        shadowElevation = dimensionResource(id = R.dimen.elevation),
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.padding_small))
            )
            BasicTextField(
                value = query,
                onValueChange = {
                    query = it
                    onSearch(it)
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        isSearching = !isSearching
                    }
                ),
                modifier = Modifier
                    .weight(1f)
            )
            if(query.isNotEmpty() || isSearching) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(R.string.clear),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .clickable { query = "" }
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    GBookTheme {
        SearchBar(onSearch = {})
    }
}