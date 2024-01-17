package com.example.gbook.ui.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.R
import com.example.gbook.data.local.LocalScreenProvider
import com.example.gbook.data.local.MockData
import com.example.gbook.data.model.GbookUiState
import com.example.gbook.ui.items.DrawerHeader
import com.example.gbook.ui.items.OnlyAccountHomeHeader
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Screen

@Composable
fun AppBottomNavigationBar(
    currentScreen: Screen,
    onIconClick: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationItemList = LocalScreenProvider.screenList
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.onPrimary,
        modifier = modifier
    ) {
        for (item in navigationItemList) {
            NavigationBarItem(
                selected = currentScreen == item.screen,
                onClick = { onIconClick(item.screen) },
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
fun AppNavigationRail(
    currentScreen: Screen,
    onIconClick: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationItemList = LocalScreenProvider.screenList
    NavigationRail(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.onPrimary
    ) {
        for(item in navigationItemList) {
            NavigationRailItem(
                selected = currentScreen == item.screen,
                onClick = { onIconClick(item.screen) },
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
    uiState: GbookUiState,
    onIconClick: (Screen) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val navigationItemList = LocalScreenProvider.screenList
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.onPrimary,
                modifier = modifier
                    .sizeIn(maxWidth = dimensionResource(id = R.dimen.drawer_width))
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                Column(
                    modifier = Modifier
                ) {
                    if (currentScreen == Screen.Home) {
                        Surface(
                            shadowElevation = dimensionResource(id = R.dimen.elevation),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OnlyAccountHomeHeader(
                                account = uiState.account?.name
                                    ?: stringResource(id = R.string.account),
                                onAccountClick = { onIconClick(Screen.Account) }
                            )
                        }
                    } else {
                        DrawerHeader(
                            text = stringResource(id = R.string.app_name),
                            account = stringResource(R.string.account),
                            onAccountClick = { onIconClick(Screen.Account) },
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    Column (
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
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
                                onClick = { onIconClick(item.screen) },
                                icon = {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = stringResource(item.text),
                                    )
                                       },
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
    GBookTheme {
        AppBottomNavigationBar(
            currentScreen = Screen.Home,
            onIconClick = {}
        )
    }
}


@Preview
@Composable
fun NavigationRailPreview() {
    GBookTheme {
        AppNavigationRail(
            currentScreen = Screen.Home,
            onIconClick = {}
        )
    }
}

@Preview
@Composable
fun HomeNavigationDrawerPreview() {
    GBookTheme {
        AppNavigationDrawer(
            currentScreen = Screen.Home,
            uiState = MockData.homeUiState,
            onIconClick = {}
        ) {
        }
    }
}

@Preview
@Composable
fun NavigationDrawerPreview() {
    GBookTheme {
        AppNavigationDrawer(
            currentScreen = Screen.Book,
            uiState = MockData.bookUiState,
            onIconClick = {}
        ) {
        }
    }
}