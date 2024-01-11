package com.example.bookstore.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookstore.data.model.Screen
import com.example.bookstore.ui.screens.book.BookDetailScreen
import com.example.bookstore.ui.screens.cart.CartScreen
import com.example.bookstore.ui.screens.categories.CategoriesScreen
import com.example.bookstore.ui.screens.categories.CategoryScreen
import com.example.bookstore.ui.screens.favorite.FavoriteScreen
import com.example.bookstore.ui.screens.home.HomeScreen
import com.example.bookstore.ui.utils.NavigationType

@Composable
fun BookStoreApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val navigationType: NavigationType
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route?: Screen.Home.name
    )

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = NavigationType.BOTTOM_NAVIGATION
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = NavigationType.NAVIGATION_RAIL
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER
        }
        else -> {
            navigationType = NavigationType.BOTTOM_NAVIGATION
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.name, ) {
        composable(Screen.Home.name) {
            HomeScreen(
                navigationType = navigationType,
                modifier = modifier
            )
        }
        composable(Screen.Favorite.name) {
            FavoriteScreen(
                navigationType = navigationType,
                modifier = modifier
            )
        }
        composable(Screen.Cart.name) {
            CartScreen(
                navigationType = navigationType,
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
                modifier = modifier
            )
        }
        composable(Screen.Book.name) {
            BookDetailScreen(
                navigationType = navigationType,
                modifier = modifier
            )
        }
    }
}