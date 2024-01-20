package com.example.gbook.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gbook.data.model.Book
import com.example.gbook.data.model.Collection
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.data.model.NetworkBookUiState
import com.example.gbook.ui.screens.account.AccountScreen
import com.example.gbook.ui.screens.book.BookDetailScreen
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
    uiState: GBookUiState,
    networkBookUiState: NetworkBookUiState,
    navController: NavHostController,
    onButtonClick: (Function) -> Unit,
    onCardClick: (Book) -> Unit,
    onCategoryClick: (Collection) -> Unit,
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
                uiState = networkBookUiState,
                onButtonClick = onButtonClick,
                onCardClick = onCardClick,
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
                onCategoryClick = onCategoryClick,
                modifier = modifier
            )
        }
        composable(Screen.Cart.name) {
            CartScreen(
                navigationType = navigationType,
                uiState = uiState,
                onButtonClick = onButtonClick,
                onCardClick = onCardClick,
                modifier = modifier
            )
        }
        composable(Screen.Categories.name) {
            CategoriesScreen(
                navigationType = navigationType,
                onButtonClick = onButtonClick,
                onCategoryClick = onCategoryClick,
                modifier = modifier
            )
        }
        composable(Screen.Category.name) {
            CategoryScreen(
                navigationType = navigationType,
                uiState = uiState,
                onButtonClick = onButtonClick,
                onCardClick = onCardClick,
                onSearch = onSearch,
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
                navigationType = navigationType,
                uiState = uiState,
                onButtonClick = onButtonClick,
                onInput = onInput,
                modifier = modifier
            )
        }
    }
}