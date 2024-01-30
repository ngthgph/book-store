package com.example.gbook.ui.screens.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gbook.data.database.account.Account
import com.example.gbook.data.database.books.Book
import com.example.gbook.data.database.collection.BookCollection
import com.example.gbook.data.local.LocalCategoriesProvider
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.ui.GBookViewModel
import com.example.gbook.ui.screens.account.AccountScreen
import com.example.gbook.ui.screens.cart.CartScreen
import com.example.gbook.ui.screens.categories.CategoriesScreen
import com.example.gbook.ui.screens.categories.CategoryScreen
import com.example.gbook.ui.screens.home.HomeScreen
import com.example.gbook.ui.screens.library.CollectionScreen
import com.example.gbook.ui.screens.library.MyLibraryScreen
import com.example.gbook.data.database.books.SearchQuery
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType
import com.example.gbook.ui.utils.NetworkFunction
import com.example.gbook.ui.utils.Screen

@Composable
fun GBookNavHost(
    navigationType: NavigationType,
    viewModel: GBookViewModel,
    uiState: GBookUiState,
    navController: NavHostController,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    onNetworkFunction: (NetworkFunction, SearchQuery?) -> Unit,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.name
    ) {
        composable(Screen.Home.name) {
            HomeScreen(
                navigationType = navigationType,
                viewModel = viewModel,
                uiState = uiState,
                onFunction = onFunction,
                onNetworkFunction = onNetworkFunction,
                modifier = modifier,
            )
        }
        composable(Screen.MyLibrary.name) {
            MyLibraryScreen(
                navigationType = navigationType,
                uiState = uiState,
                onFunction = onFunction,
                onNetworkFunction = onNetworkFunction,
                navigateToCollection = {
                    navController.navigate("${Screen.Collection.name}/${it.name}")
                },
                modifier = modifier
            )
        }
        val collectionArgument = "collectionName"
        composable(
            route = Screen.Collection.name + "/{$collectionArgument}",
            arguments = listOf(navArgument(collectionArgument){type = NavType.StringType})
        ) { backStackEntry ->
            val collectionName = backStackEntry.arguments?.getString(collectionArgument)
                ?: error("collectionArgument cannot be null")
            val collection = uiState.collection.find { it.name == collectionName }!!

            CollectionScreen(
                navigationType = navigationType,
                collection = collection,
                viewModel = viewModel,
                uiState = uiState,
                onFunction = onFunction,
                onNetworkFunction = onNetworkFunction,
                modifier = modifier
            )
        }
        composable(Screen.Cart.name) {
            CartScreen(
                navigationType = navigationType,
                uiState = uiState,
                onFunction = onFunction,
                onNetworkFunction = onNetworkFunction,
                modifier = modifier
            )
        }
        val categories = LocalCategoriesProvider.categories
        composable(Screen.Categories.name) {
            CategoriesScreen(
                navigationType = navigationType,
                onFunction = onFunction,
                onNetworkFunction = onNetworkFunction,
                navigateToCollection = {
                    navController.navigate("${Screen.Category.name}/${it.name}")
                                    },
                modifier = modifier
            )
        }
        val categoryArgument = "categoryName"
        composable(
            route = Screen.Category.name + "/{$categoryArgument}",
            arguments = listOf(navArgument(categoryArgument){type = NavType.StringType})
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString(categoryArgument)
                ?: error("categoryArgument cannot be null")
            val category = categories.find { it.name.equals(categoryName,ignoreCase = true) }!!

            CategoryScreen(
                navigationType = navigationType,
                category = category,
                viewModel = viewModel,
                uiState = uiState,
                onFunction = onFunction,
                onNetworkFunction = onNetworkFunction,
                modifier = modifier
            )
        }
        composable(Screen.Account.name) {
            AccountScreen(
                navigationType = navigationType,
                uiState = uiState,
                onFunction = onFunction,
                modifier = modifier
            )
        }
    }
}