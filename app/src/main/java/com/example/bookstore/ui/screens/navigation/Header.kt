package com.example.bookstore.ui.screens.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.R
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun AppHeader(
    text: String,
    description: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        onBack()
    }
    Surface(
//        shadowElevation = dimensionResource(id = R.dimen.elevation),
        tonalElevation = dimensionResource(id = R.dimen.elevation),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = text.uppercase(),
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
            Image(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = description,
//                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
        }
    }
}

@Composable
fun DrawerHeader(
    text: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        shadowElevation = dimensionResource(id = R.dimen.elevation),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text.uppercase(),
                style = MaterialTheme.typography.headlineSmall
            )
            Image(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = description,
//                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        }
    }
}

@Preview
@Composable
fun AppHeaderPreview() {
    BookStoreTheme {
        AppHeader(
            text = stringResource(id = R.string.app_name),
            description = stringResource(id = R.string.app_name),
            onBack = { /*TODO*/ })
    }
}

@Preview
@Composable
fun DrawerHeaderPreview() {
    BookStoreTheme {
        DrawerHeader(
            text = stringResource(id = R.string.app_name),
            description = stringResource(id = R.string.app_name),
        )
    }
}