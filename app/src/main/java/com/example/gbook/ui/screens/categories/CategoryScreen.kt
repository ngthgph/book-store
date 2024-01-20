package com.example.gbook.ui.screens.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.R
import com.example.gbook.data.local.MockData
import com.example.gbook.data.model.Book
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.ui.items.BooksGrid
import com.example.gbook.ui.items.SearchBar
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType

@Composable
fun CategoryScreen(
    navigationType: NavigationType,
    uiState: GBookUiState,
    onButtonClick: (Function) -> Unit,
    onCardClick: (Book) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val mockData = List(10){ MockData.homeUiState.currentBook!!}
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        CategoryContent(
            navigationType = navigationType,
            bookList = mockData,
            title = stringResource(id = uiState.currentCollection!!.name).replaceFirstChar { it.uppercase() },
            image = painterResource(id = uiState.currentCollection.image),
            onButtonClick = onButtonClick,
            onCardClick = onCardClick,
            onSearch = onSearch,
        )
    }
}

@Composable
fun CategoryContent(
    navigationType: NavigationType,
    bookList: List<Book>,
    title: String,
    image: Painter,
    onButtonClick: (Function) -> Unit,
    onCardClick: (Book) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val space = if (navigationType == NavigationType.BOTTOM_NAVIGATION)
        R.dimen.padding_medium else R.dimen.padding_medium
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(space)),
        modifier = modifier.fillMaxWidth()
    ) {
        CategoryTitle(
            navigationType = navigationType,
            title = title,
            image = image,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Column(
            modifier = Modifier.weight(3f)
        ) {
            SearchBar(onSearch = onSearch)
            BooksGrid(
                navigationType = navigationType,
                bookList = bookList,
                onButtonClick = onButtonClick,
                onCardClick = onCardClick
            )
        }
    }
}

@Composable
fun CategoryTitle(
    navigationType: NavigationType,
    title: String,
    image: Painter,
    modifier: Modifier = Modifier
) {
    val padding = if (navigationType == NavigationType.BOTTOM_NAVIGATION)
        R.dimen.padding_extra_small else R.dimen.padding_small
    Box(modifier = modifier.fillMaxWidth()) {
        Image(
            painter = image,
            contentDescription = title,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Row (
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.onPrimaryContainer.copy(0.5f),
                            Color.Transparent
                        ),
                        startY = 100f,
                        endY = 0f // Adjust the endX value based on your preference
                    )
                )
                .align(Alignment.BottomStart)
                .fillMaxWidth(),
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier
                    .padding(dimensionResource(id = padding))
                    .background(Color.Transparent),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompactCategoryScreenPreview() {
    GBookTheme {
        CategoryScreen(
            navigationType = NavigationType.BOTTOM_NAVIGATION,
            uiState = MockData.categoryUiState,
            onButtonClick = {},
            onCardClick = {},
            onSearch = {},
        )
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumCategoryScreenPreview() {
    GBookTheme {
        CategoryScreen(
            navigationType = NavigationType.NAVIGATION_RAIL,
            uiState = MockData.categoryUiState,
            onButtonClick = {},
            onCardClick = {},
            onSearch = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedCategoryScreenPreview() {
    GBookTheme {
        CategoryScreen(
            navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER,
            uiState = MockData.categoryUiState,
            onButtonClick = {},
            onCardClick = {},
            onSearch = {},
        )
    }
}