package com.example.gbook.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gbook.data.local.LocalCategoriesProvider
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.ui.GBookViewModel
import com.example.gbook.ui.screens.account.AccountScreen
import com.example.gbook.ui.screens.cart.CartScreen
import com.example.gbook.ui.screens.categories.CategoriesScreen
import com.example.gbook.ui.screens.categories.CategoryScreen
import com.example.gbook.ui.screens.home.HomeScreen
import com.example.gbook.ui.screens.library.MyLibraryScreen
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType
import com.example.gbook.ui.utils.Screen

@Composable
fun GBookNavHost(
    navigationType: NavigationType,
    viewModel: GBookViewModel,
    uiState: GBookUiState,
    navController: NavHostController,
    onButtonClick: (Function) -> Unit,
    onSearch: (String) -> Unit,
    onInput:(String) -> Unit,
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
                onButtonClick = onButtonClick,
                onSearch = onSearch,
                modifier = modifier,
            )
        }
        composable(Screen.MyLibrary.name) {
            MyLibraryScreen(
                navigationType = navigationType,
                uiState = uiState,
                onSearch = onSearch,
                onButtonClick = onButtonClick,
                onCollectionClick = { },
                modifier = modifier
            )
        }
        composable(Screen.Cart.name) {
            CartScreen(
                navigationType = navigationType,
                uiState = uiState,
                onButtonClick = onButtonClick,
                onCardClick = { viewModel.handleOnCardClick(it) },
                modifier = modifier
            )
        }
        val categories = LocalCategoriesProvider.categories
        composable(Screen.Categories.name) {
            CategoriesScreen(
                navigationType = navigationType,
                onButtonClick = onButtonClick,
                onCollectionClick = {
                    viewModel.getSubjectBookList(it.name!!)
                    navController.navigate("${Screen.Category.name}/${it.name!!}")
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
                onButtonClick = onButtonClick,
                onCardClick = { viewModel.handleOnCardClick(it) },
                onSearch = onSearch,
                modifier = modifier
            )
        }
        composable(Screen.Account.name) {
            AccountScreen(
                navigationType = navigationType,
                uiState = uiState,
                onButtonClick = onButtonClick,
                onInput = onInput,
                modifier = modifier
            )
        }
    }
}