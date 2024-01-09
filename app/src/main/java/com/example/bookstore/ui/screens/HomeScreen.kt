package com.example.bookstore.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.example.bookstore.R
import com.example.bookstore.data.local.LocalScreenProvider
import com.example.bookstore.data.model.Screen
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
                modifier = modifier
            ) {
            }
        }
        else -> {
            AppNavigationBarScreen(
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
            content()
        }
    }
}

@Composable
fun AppNavigationBarScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = modifier.weight(1f)
        ) {
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
fun TopAppBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
}

@Composable
fun AppHeader(
    text: String,
    image: ImageVector,
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
            Image(imageVector = image, contentDescription = description)
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onInput: (String) -> Unit,
    onSearch: (String) -> Unit
) {
}
