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
import com.example.bookstore.data.model.Book
import com.example.bookstore.ui.utils.Screen
import com.example.bookstore.ui.screens.book.BookDetailScreen
import com.example.bookstore.ui.screens.cart.CartScreen
import com.example.bookstore.ui.screens.categories.CategoriesScreen
import com.example.bookstore.ui.screens.categories.CategoryScreen
import com.example.bookstore.ui.screens.favorite.FavoriteScreen
import com.example.bookstore.ui.screens.home.BookStoreUiState
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
    val book = Book(
        title = "The History of Jazz",
        author = "Ted Gioia",
        publisher = "HarperCollins UK",
        publishedDate = "2014-04-24",
        ISBN_10 = "000758203X",
        ISBN_13 = "9780007582037",
        description = "Jazz is the most colorful and varied art form in the world and it was born in one of the most colorful and varied cities, New Orleans. From the seed first planted by slave dances held in Congo Square and nurtured by early ensembles led by Buddy Belden and Joe \"King\" Oliver, jazz began its long winding odyssey across America and around the world, giving flower to a thousand different forms--swing, bebop, cool jazz, jazz-rock fusion--and a thousand great musicians. Now, in The History of Jazz, Ted Gioia tells the story of this music as it has never been told before, in a book that brilliantly portrays the legendary jazz players, the breakthrough styles, and the world in which it evolved. Here are the giants of jazz and the great moments of jazz history--Jelly Roll Morton (\"the world's greatest hot tune writer\"), Louis Armstrong (whose O-keh recordings of the mid-1920s still stand as the most significant body of work that jazz has produced), Duke Ellington at the Cotton Club, cool jazz greats such as Gerry Mulligan, Stan Getz, and Lester Young, Charlie Parker's surgical precision of attack, Miles Davis's 1955 performance at the Newport Jazz Festival, Ornette Coleman's experiments with atonality, Pat Metheny's visionary extension of jazz-rock fusion, the contemporary sounds of Wynton Marsalis, and the post-modernists of the Knitting Factory. Gioia provides the reader with lively portraits of these and many other great musicians, intertwined with vibrant commentary on the music they created. Gioia also evokes the many worlds of jazz, taking the reader to the swamp lands of the Mississippi Delta, the bawdy houses of New Orleans, the rent parties of Harlem, the speakeasies of Chicago during the Jazz Age, the after hours spots of corrupt Kansas city, the Cotton Club, the Savoy, and the other locales where the history of jazz was made. And as he traces the spread of this protean form, Gioia provides much insight into the social context in which the music was born. He shows for instance how the development of technology helped promote the growth of jazz--how ragtime blossomed hand-in-hand with the spread of parlor and player pianos, and how jazz rode the growing popularity of the record industry in the 1920s. We also discover how bebop grew out of the racial unrest of the 1940s and '50s, when black players, no longer content with being \"entertainers,\" wanted to be recognized as practitioners of a serious musical form. Jazz is a chameleon art, delighting us with the ease and rapidity with which it changes colors. Now, in Ted Gioia's The History of Jazz, we have at last a book that captures all these colors on one glorious palate. Knowledgeable, vibrant, and comprehensive, it is among the small group of books that can truly be called classics of jazz literature.",
        pageCount = 481,
        categories = "Music",
        imageLinks = "http://books.google.com/books/content?id=C1MI_4nZyD4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
        retailPrice = 255647,
        currencyCode = "VND"
    )
    val uiState = BookStoreUiState(book)

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
                onIconClick = { navController.navigate(it.name) },
                modifier = modifier
            )
        }
        composable(Screen.Favorite.name) {
            FavoriteScreen(
                navigationType = navigationType,
                onIconClick = { navController.navigate(it.name) },
                modifier = modifier
            )
        }
        composable(Screen.Cart.name) {
            CartScreen(
                navigationType = navigationType,
                onIconClick = { navController.navigate(it.name) },
                modifier = modifier
            )
        }
        composable(Screen.Categories.name) {
            CategoriesScreen(
                navigationType = navigationType,
                onIconClick = { navController.navigate(it.name) },
                modifier = modifier,
            )
        }
        composable(Screen.Category.name) {
            CategoryScreen(
                navigationType = navigationType,
                onIconClick = { navController.navigate(it.name) },
                modifier = modifier,
            )
        }
        composable(Screen.Book.name) {
            BookDetailScreen(
                uiState = uiState,
                navigationType = navigationType,
                onIconClick = { navController.navigate(it.name) },
                modifier = modifier,
                onButtonClick = {}
            )
        }
    }
}