package com.example.bookstore.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bookstore.data.model.BookStoreUiState
import com.example.bookstore.ui.screens.account.AccountScreen
import com.example.bookstore.ui.screens.book.BookDetailScreen
import com.example.bookstore.ui.screens.cart.CartScreen
import com.example.bookstore.ui.screens.categories.CategoriesScreen
import com.example.bookstore.ui.screens.categories.CategoryScreen
import com.example.bookstore.ui.screens.favorite.FavoriteScreen
import com.example.bookstore.ui.screens.home.HomeScreen
import com.example.bookstore.ui.utils.Function
import com.example.bookstore.ui.utils.NavigationType
import com.example.bookstore.ui.utils.Screen

@Composable
fun BookStoreNavHost(
    navigationType: NavigationType,
    uiState: BookStoreUiState,
    navController: NavHostController,
    onButtonClick: (Function) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.name
    ) {
        composable(Screen.Home.name) {
            HomeScreen(
                navigationType = navigationType,
                uiState = uiState,
                modifier = modifier
            )
        }
        composable(Screen.Favorite.name) {
            FavoriteScreen(
                uiState = uiState,
                modifier = modifier
            )
        }
        composable(Screen.Cart.name) {
            CartScreen(
                uiState = uiState,
                modifier = modifier
            )
        }
        composable(Screen.Categories.name) {
            CategoriesScreen(
                navigationType = navigationType,
                modifier = modifier
            )
        }
        composable(Screen.Category.name) {
            CategoryScreen(
                navigationType = navigationType,
                uiState = uiState,
                modifier = modifier
            )
        }
        composable(Screen.Book.name) {
            BookDetailScreen(
                navigationType = navigationType,
                uiState = uiState,
                onButtonClick = onButtonClick,
                modifier = modifier
            )
        }
        composable(Screen.Account.name) {
            AccountScreen(
                uiState = uiState,
                modifier = modifier
            )
        }
    }
}