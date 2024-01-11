package com.example.bookstore.ui.screens.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.data.local.LocalScreenProvider
import com.example.bookstore.data.model.Screen
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun DrawerScreen(
    modifier: Modifier = Modifier,
    onIconClick: (Screen) -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold() {
        AppNavigationDrawer(
            currentScreen = Screen.Home,
            onIconClick = onIconClick,
            modifier = modifier.padding(it)
        ) {
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                content()
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun DrawerScreenPreview() {
    BookStoreTheme {
        DrawerScreen(onIconClick = {}) {}
    }
}
