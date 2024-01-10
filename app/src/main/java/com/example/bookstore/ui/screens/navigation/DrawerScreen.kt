package com.example.bookstore.ui.screens.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.data.local.LocalScreenProvider
import com.example.bookstore.data.model.Screen
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun DrawerScreen(
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

@Preview(showBackground = true, widthDp = 700)
@Composable
fun DrawerScreenPreview() {
    BookStoreTheme {
        DrawerScreen() {}
    }
}
