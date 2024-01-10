package com.example.bookstore.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.R
import com.example.bookstore.data.local.LocalScreenProvider
import com.example.bookstore.data.model.Screen
import com.example.bookstore.ui.theme.BookStoreTheme
import com.example.bookstore.ui.utils.NavigationType

@Composable
fun HomeScreen(
    navigationType: NavigationType,
    modifier: Modifier = Modifier
) {
    when (navigationType) {
        NavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            AppNavigationDrawerScreen (
                modifier = modifier
            ) {
            }
        }
        NavigationType.NAVIGATION_RAIL -> {
            AppNavigationRailScreen(
                title = stringResource(id = R.string.app_name),
                modifier = modifier
            ) {
            }
        }
        else -> {
            AppNavigationBarScreen(
                title = stringResource(id = R.string.app_name),
                modifier = modifier
            ) {
            }
        }
    }
}
@Composable
fun AppNavigationDrawerScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AppNavigationDrawer(
        currentScreen = Screen.Home,
        onPressed = {},
        navigationItemList = LocalScreenProvider.screenList,
        modifier = modifier
    ) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            content()
        }
    }
}

@Composable
fun AppNavigationRailScreen(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row {
        AppNavigationRail(
            currentScreen = Screen.Home,
            onPressed = {},
            navigationItemList = LocalScreenProvider.screenList
        )
        Column(
            modifier = modifier.weight(1f)
        ) {
            AppHeader(text = title, description = title, onBack = {})
            SearchBar(onSearch = {}, onClear = {}, isSearching = false)
            content()
        }
    }
}

@Composable
fun AppNavigationBarScreen(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .weight(1f)
        ) {
            AppHeader(text = title, description = title, onBack = {})
            SearchBar(onSearch = {}, onClear = {}, isSearching = false)
            content()
        }
        AppBottomNavigationBar(
            currentScreen = Screen.Home,
            onPressed = {},
            navigationItemList = LocalScreenProvider.screenList
        )
    }
}

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
            Image(imageVector = Icons.Default.AccountCircle, contentDescription = description)
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
            Image(imageVector = Icons.Default.AccountCircle, contentDescription = description)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    onClear: () -> Unit,
    isSearching: Boolean
) {
    var query by remember { mutableStateOf("") }
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
                        .clickable {
                            query = ""
                            onClear()
                        }
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    BookStoreTheme {
        SearchBar(onSearch = {}, onClear = {}, isSearching = false)
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


