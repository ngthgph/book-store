package com.example.bookstore.ui.screens.book

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.R
import com.example.bookstore.data.model.Book
import com.example.bookstore.ui.screens.home.BookStoreUiState
import com.example.bookstore.ui.utils.Screen
import com.example.bookstore.ui.theme.BookStoreTheme
import com.example.bookstore.ui.utils.Function
import com.example.bookstore.ui.utils.NavigationType
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun BookDetailScreen(
    navigationType: NavigationType,
    uiState: BookStoreUiState,
    onIconClick: (Screen) -> Unit,
    onButtonClick: (Function) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        BookDetailHeader(
            navigationType = navigationType,
            uiState = uiState,
            onBack = { /*TODO*/ }
        )
        BookDetailContent(
            navigationType = navigationType,
            book = uiState.currentBook,
            onButtonClick = onButtonClick,
        )
    }
}

@Composable
fun BookDetailHeader(
    navigationType: NavigationType,
    uiState: BookStoreUiState,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        if(navigationType != NavigationType.PERMANENT_NAVIGATION_DRAWER) {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium))
                    .background(MaterialTheme.colorScheme.surface, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = dimensionResource(id = R.dimen.padding_medium))
        ) {
            Text(
                text = uiState.currentBook.title,
                style = MaterialTheme.typography.headlineMedium
            )
        }

    }
}


@Composable
fun BookDetailContent(
    navigationType: NavigationType,
    book: Book,
    onButtonClick: (Function) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = book.author,
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.padding_medium))
                )
                BookDetailCard(
                    navigationType = navigationType,
                    book = book,
                    modifier = modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.padding_large),
                        vertical = dimensionResource(id = R.dimen.padding_medium),
                    ),
                )
                DetailsButtonRow(
                    onButtonClick = onButtonClick,
                    modifier = Modifier
                        .padding(end = dimensionResource(id = R.dimen.padding_large))
                )
            }
        }
    }
}
@Composable
fun BookDetailCard(
    navigationType: NavigationType,
    modifier: Modifier = Modifier,
    book: Book,
) {
    Card(
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.elevation)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            BookPhoto(
                title = book.title,
                imgSrc = book.imageLinks,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
            if(navigationType == NavigationType.BOTTOM_NAVIGATION) {
                CompactBookDetailInfo(book = book)
            } else {
                BookDetailInfo(book = book)
            }
        }
    }
}
@Composable
fun BookDetailInfo(
    book: Book,
    modifier: Modifier = Modifier,
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier
    ){
        Row {
            Column(modifier = Modifier.weight(1f)) {
                BookInfoRow(stringResource(R.string.categories), book.categories)
                BookInfoRow(stringResource(R.string.publisher), book.publisher)
                BookInfoRow(stringResource(R.string.publish_date), book.publishedDate)
            }
            Column(modifier = Modifier.weight(1f)) {
                BookInfoRow(stringResource(R.string.isbn_13), book.ISBN_13)
                BookInfoRow(stringResource(R.string.isbn_10), book.ISBN_10)
                BookInfoRow(stringResource(R.string.page), book.pageCount.toString())
            }
        }

        ContentDescription(false,stringResource(R.string.content), book.description)
        Divider(thickness = dimensionResource(id = R.dimen.divider_thickness_large))
        val price = book.retailPrice.toString()
        Row {
            Column(modifier = Modifier.weight(1f)) {
                BookInfoRow(
                    stringResource(R.string.price),
                    stringResource(R.string.price_display, price, book.currencyCode),
                )
            }
            Column(modifier = Modifier.weight(1f)){}
        }

    }
}

@Composable
fun CompactBookDetailInfo(
    book: Book,
    modifier: Modifier = Modifier,
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier
    ){
        BookInfoRow(stringResource(R.string.categories), book.categories)
        BookInfoRow(stringResource(R.string.publisher), book.publisher)
        BookInfoRow(stringResource(R.string.publish_date), book.publishedDate)

        BookInfoRow(stringResource(R.string.isbn_13), book.ISBN_13)
        BookInfoRow(stringResource(R.string.isbn_10), book.ISBN_10)
        BookInfoRow(stringResource(R.string.page), book.pageCount.toString())
        ContentDescription(true, stringResource(R.string.content), book.description)
        Divider(thickness = dimensionResource(id = R.dimen.divider_thickness_large))
        val price = book.retailPrice.toString()
        BookInfoRow(
            stringResource(R.string.price),
            stringResource(R.string.price_display, price, book.currencyCode),
        )
    }
}
@Composable
fun ContentDescription(
    compact: Boolean,
    title: String,
    content: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    Row (
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
    ){
        Text(
            text = title,
            modifier = Modifier.weight(1f)
        )
        Box(modifier = Modifier
            .weight(if(compact) 2f else 5f)
            .clickable { expanded = !expanded }
        ) {
            Column {
                Text(
                    text = content,
                    maxLines = if (!expanded) 3 else Int.MAX_VALUE,
                    modifier = Modifier
                )
                if(expanded) {
                    Text(text = "")
                }
            }
            Text(
                text = if(!expanded) "...Show More" else "...Show Less",
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(MaterialTheme.colorScheme.onPrimary)
            )
        }
    }
}
@Composable
fun BookInfoRow(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
) {
    Row (
        horizontalArrangement = Arrangement.Start
    ){
        Text(
            text = title,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = content,
            modifier = Modifier
                .weight(2f)
        )
    }
}
@Composable
fun DetailsButtonRow(
    modifier: Modifier = Modifier,
    onButtonClick: (Function) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.inverseOnSurface),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            for (function in enumValues<Function>()) {
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
                Card (
                    modifier = Modifier
                        .weight(1f),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
                    shape = CircleShape,
                )  {
                    IconButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onButtonClick(function) },
                    ) {
                        Icon(
                            imageVector = function.icon,
                            contentDescription = stringResource(function.description),
                            modifier = Modifier.fillMaxWidth(),
                            tint = MaterialTheme.colorScheme.outline
                        )
                    }
                }

            }
        }
    }
}

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
@Preview
@Composable
fun ContentDescriptionPreview(
) {
    BookStoreTheme {
        ContentDescription(
            compact = true,
            title = stringResource(R.string.content),
            content = book.description
        )
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun BookDetailInfoPreview() {
    BookStoreTheme {
        BookDetailInfo(book)
    }
}
@Preview
@Composable
fun CompactBookDetailInfoPreview() {
    BookStoreTheme {
        CompactBookDetailInfo(book)
    }
}
@Preview
@Composable
fun BookInfoRowPreview() {
    BookStoreTheme {
        BookInfoRow(title = stringResource(R.string.page), content = book.pageCount.toString())
    }
}

@Preview
@Composable
fun ButtonRowPreview() {
    BookStoreTheme {
        DetailsButtonRow(onButtonClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun CompactBookScreenPreview() {
    BookStoreTheme {
        val uiState = BookStoreUiState(book)
        BookDetailScreen(
            uiState = uiState,
            navigationType = NavigationType.BOTTOM_NAVIGATION,
            onIconClick = {},
            onButtonClick = {}
        )
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumBookScreenPreview() {
    BookStoreTheme {
        val uiState = BookStoreUiState(book)
        BookDetailScreen(
            uiState = uiState,
            navigationType = NavigationType.NAVIGATION_RAIL,
            onIconClick = {},
            onButtonClick = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedBookScreenPreview() {
    BookStoreTheme {
        val uiState = BookStoreUiState(book)
        BookDetailScreen(
            uiState = uiState,
            navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER,
            onIconClick = {},
            onButtonClick = {}
        )
    }
}