package com.example.bookstore.ui.screens.book

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookstore.R
import com.example.bookstore.data.model.Book
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun BooksGrid(
    modifier: Modifier = Modifier,
    bookList: List<Book>
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.grid_column)),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_extra_small))
    ) {
        items(bookList) {
            BooksCard(
                book = it,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_extra_small))
                    .fillMaxWidth()
                    .aspectRatio(0.8f)
            )
        }
    }
}

@Composable
fun BooksCard(
    modifier: Modifier = Modifier,
    book: Book,
) {
    Card(
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.elevation)),
        modifier = modifier
            .size(dimensionResource(id = R.dimen.card_size_small))
            .clickable { }
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = book.author,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Box(modifier = Modifier.weight(1f)) {
                BookPhoto(title = book.title, imgSrc = book.imageLinks)
            }
            val price = book.retailPrice.toString()
            Row(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.price),
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = stringResource(R.string.price_display, price, book.currencyCode),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Divider(thickness = dimensionResource(id = R.dimen.divider_thickness))
            CardButtonRow()
        }
    }
}
@Composable
fun CardButtonRow(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CardButton(
            icon = Icons.Default.Favorite,
            function = stringResource(id = R.string.favorite)
        )
        CardButton(
            icon = Icons.Default.ShoppingCart,
            function = stringResource(id = R.string.cart)
        )
        CardButton(
            icon = Icons.Default.Share,
            function = stringResource(id = R.string.share),
        )
    }
}
@Composable
fun CardButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    function: String,
) {
    Button(
        modifier = modifier,
        onClick = {  },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary
        ),
//        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_extra_small))
    ) {
        Icon(
            imageVector = icon,
            contentDescription = function,
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_small))
        )
    }
}
@Composable
fun BookPhoto(
    title: String,
    imgSrc: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(imgSrc)
            .crossfade(true)
            .build(),
        placeholder = painterResource(id = R.drawable.loading_img),
        error = painterResource(id = R.drawable.ic_broken_image),
        contentDescription = stringResource(R.string.image, title),
        contentScale = ContentScale.Crop,
        modifier = modifier.fillMaxSize()
    )
}


@Preview
@Composable
fun BookGridPreview() {
    BookStoreTheme {
        val book = Book(
            title = "The History of Jazz",
            author = "Ted Gioia",
            description = "Jazz is the most colorful and varied art form in the world and it was born in one of the most colorful and varied cities, New Orleans. From the seed first planted by slave dances held in Congo Square and nurtured by early ensembles led by Buddy Belden and Joe \"King\" Oliver, jazz began its long winding odyssey across America and around the world, giving flower to a thousand different forms--swing, bebop, cool jazz, jazz-rock fusion--and a thousand great musicians. Now, in The History of Jazz, Ted Gioia tells the story of this music as it has never been told before, in a book that brilliantly portrays the legendary jazz players, the breakthrough styles, and the world in which it evolved. Here are the giants of jazz and the great moments of jazz history--Jelly Roll Morton (\"the world's greatest hot tune writer\"), Louis Armstrong (whose O-keh recordings of the mid-1920s still stand as the most significant body of work that jazz has produced), Duke Ellington at the Cotton Club, cool jazz greats such as Gerry Mulligan, Stan Getz, and Lester Young, Charlie Parker's surgical precision of attack, Miles Davis's 1955 performance at the Newport Jazz Festival, Ornette Coleman's experiments with atonality, Pat Metheny's visionary extension of jazz-rock fusion, the contemporary sounds of Wynton Marsalis, and the post-modernists of the Knitting Factory. Gioia provides the reader with lively portraits of these and many other great musicians, intertwined with vibrant commentary on the music they created. Gioia also evokes the many worlds of jazz, taking the reader to the swamp lands of the Mississippi Delta, the bawdy houses of New Orleans, the rent parties of Harlem, the speakeasies of Chicago during the Jazz Age, the after hours spots of corrupt Kansas city, the Cotton Club, the Savoy, and the other locales where the history of jazz was made. And as he traces the spread of this protean form, Gioia provides much insight into the social context in which the music was born. He shows for instance how the development of technology helped promote the growth of jazz--how ragtime blossomed hand-in-hand with the spread of parlor and player pianos, and how jazz rode the growing popularity of the record industry in the 1920s. We also discover how bebop grew out of the racial unrest of the 1940s and '50s, when black players, no longer content with being \"entertainers,\" wanted to be recognized as practitioners of a serious musical form. Jazz is a chameleon art, delighting us with the ease and rapidity with which it changes colors. Now, in Ted Gioia's The History of Jazz, we have at last a book that captures all these colors on one glorious palate. Knowledgeable, vibrant, and comprehensive, it is among the small group of books that can truly be called classics of jazz literature.",
            pageCount = 481,
            imageLinks = "http://books.google.com/books/content?id=C1MI_4nZyD4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
            retailPrice = 255647,
            currencyCode = "VND"
        )
        val mockData = List(10){book}
        BooksGrid(bookList = mockData)
    }
}

@Preview
@Composable
fun CardPreview() {
    BookStoreTheme {
        val book = Book(
            title = "The History of Jazz",
            author = "Ted Gioia",
            description = "Jazz is the most colorful and varied art form in the world and it was born in one of the most colorful and varied cities, New Orleans. From the seed first planted by slave dances held in Congo Square and nurtured by early ensembles led by Buddy Belden and Joe \"King\" Oliver, jazz began its long winding odyssey across America and around the world, giving flower to a thousand different forms--swing, bebop, cool jazz, jazz-rock fusion--and a thousand great musicians. Now, in The History of Jazz, Ted Gioia tells the story of this music as it has never been told before, in a book that brilliantly portrays the legendary jazz players, the breakthrough styles, and the world in which it evolved. Here are the giants of jazz and the great moments of jazz history--Jelly Roll Morton (\"the world's greatest hot tune writer\"), Louis Armstrong (whose O-keh recordings of the mid-1920s still stand as the most significant body of work that jazz has produced), Duke Ellington at the Cotton Club, cool jazz greats such as Gerry Mulligan, Stan Getz, and Lester Young, Charlie Parker's surgical precision of attack, Miles Davis's 1955 performance at the Newport Jazz Festival, Ornette Coleman's experiments with atonality, Pat Metheny's visionary extension of jazz-rock fusion, the contemporary sounds of Wynton Marsalis, and the post-modernists of the Knitting Factory. Gioia provides the reader with lively portraits of these and many other great musicians, intertwined with vibrant commentary on the music they created. Gioia also evokes the many worlds of jazz, taking the reader to the swamp lands of the Mississippi Delta, the bawdy houses of New Orleans, the rent parties of Harlem, the speakeasies of Chicago during the Jazz Age, the after hours spots of corrupt Kansas city, the Cotton Club, the Savoy, and the other locales where the history of jazz was made. And as he traces the spread of this protean form, Gioia provides much insight into the social context in which the music was born. He shows for instance how the development of technology helped promote the growth of jazz--how ragtime blossomed hand-in-hand with the spread of parlor and player pianos, and how jazz rode the growing popularity of the record industry in the 1920s. We also discover how bebop grew out of the racial unrest of the 1940s and '50s, when black players, no longer content with being \"entertainers,\" wanted to be recognized as practitioners of a serious musical form. Jazz is a chameleon art, delighting us with the ease and rapidity with which it changes colors. Now, in Ted Gioia's The History of Jazz, we have at last a book that captures all these colors on one glorious palate. Knowledgeable, vibrant, and comprehensive, it is among the small group of books that can truly be called classics of jazz literature.",
            pageCount = 481,
            imageLinks = "http://books.google.com/books/content?id=C1MI_4nZyD4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
            retailPrice = 255647,
            currencyCode = "VND"
        )
        BooksCard(
            book = book,
        )
    }
}