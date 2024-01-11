package com.example.bookstore.ui.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.R
import com.example.bookstore.data.local.LocalScreenProvider
import com.example.bookstore.data.model.NavigationItem
import com.example.bookstore.data.model.Screen
import com.example.bookstore.ui.screens.navigation.DrawerHeader
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun AppBottomNavigationBar(
    currentScreen: Screen,
    onPressed: (Screen) -> Unit,
    navigationItemList: List<NavigationItem>,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        for (item in navigationItemList) {
            NavigationBarItem(
                selected = currentScreen == item.screen,
                onClick = { onPressed(item.screen) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(item.text)
                    )
                },
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small),)

            )
        }
    }
}

@Composable
fun AppNavigationRail(
    currentScreen: Screen,
    onPressed: (Screen) -> Unit,
    navigationItemList: List<NavigationItem>,
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier = modifier) {
        for(item in navigationItemList) {
            NavigationRailItem(
                selected = currentScreen == item.screen,
                onClick = { onPressed(item.screen) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(item.text)
                    )
                },
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
fun AppNavigationDrawer(
    currentScreen: Screen,
    onPressed: (Screen) -> Unit,
    navigationItemList: List<NavigationItem>,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(
                modifier = modifier
                    .width(dimensionResource(id = R.dimen.drawer_width))
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                Column(
                    modifier = Modifier
                ) {
                    DrawerHeader(
                        text = stringResource(id = R.string.app_name),
                        description = stringResource(R.string.account),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Column (
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small),)
                    ){
                        for (item in navigationItemList) {
                            NavigationDrawerItem(
                                label = {
                                    Text(
                                        text = stringResource(item.text),
                                        modifier = Modifier
                                    )
                                },
                                selected = currentScreen == item.screen,
                                onClick = { onPressed(item.screen) },
                                icon = {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = stringResource(item.text),
//                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                },
//                                colors = NavigationDrawerItemDefaults.colors(
//                                    unselectedContainerColor = Color.Transparent
                                )
                        }
                    }
                }
            }
                        },
        content = content
    )
}

@Preview
@Composable
fun NavigationBarPreview() {
    BookStoreTheme {
        AppBottomNavigationBar(
            currentScreen = Screen.Home,
            onPressed = {},
            navigationItemList = LocalScreenProvider.screenList
        )
    }
}


@Preview
@Composable
fun NavigationRailPreview() {
    BookStoreTheme {
        AppNavigationRail(
            currentScreen = Screen.Home,
            onPressed = {},
            navigationItemList = LocalScreenProvider.screenList
        )
    }
}

@Preview
@Composable
fun NavigationDrawerPreview() {
    BookStoreTheme {
        AppNavigationDrawer(
            currentScreen = Screen.Home,
            onPressed = {},
            navigationItemList = LocalScreenProvider.screenList
        ) {
        }
    }
}