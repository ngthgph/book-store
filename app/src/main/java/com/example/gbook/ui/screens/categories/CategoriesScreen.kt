package com.example.gbook.ui.screens.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.R
import com.example.gbook.data.local.LocalCategoriesProvider
import com.example.gbook.data.model.Category
import com.example.gbook.ui.items.CardButton
import com.example.gbook.ui.items.SearchBar
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType

@Composable
fun CategoriesScreen(
    navigationType: NavigationType,
    modifier: Modifier = Modifier
) {
    val categories = LocalCategoriesProvider.categories
    CategoriesContent(
        navigationType = navigationType,
        categories = categories,
        onButtonClick = {},
        onCardClick = {}
    )
}

@Composable
fun CategoriesContent(
    navigationType: NavigationType,
    categories: List<Category>,
    onButtonClick: (Function) -> Unit,
    onCardClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        SearchBar(onSearch = {}, isSearching = false)
        CategoriesGrid(
            navigationType = navigationType,
            categories = categories,
            onButtonClick = onButtonClick,
            onCardClick = onCardClick
        )
    }


}

@Composable
fun CategoriesGrid(
    navigationType: NavigationType,
    categories: List<Category>,
    onButtonClick: (Function) -> Unit,
    onCardClick: (Category) -> Unit,
    modifier: Modifier = Modifier,
) {
    val column: Int
    val padding: Int
    if(navigationType == NavigationType.BOTTOM_NAVIGATION) {
        column = R.dimen.grid_column_small
        padding = R.dimen.padding_extra_small
    } else {
        column = R.dimen.grid_column
        padding = R.dimen.padding_small
    }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(id = column)),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(dimensionResource(id = padding))
    ) {
        items(categories) {
            CategoryCard(
                navigationType = navigationType,
                category = it,
                onButtonClick = onButtonClick,
                selected = false,
                onCardClick = onCardClick,
                modifier = Modifier
                    .padding(dimensionResource(id = padding))
                    .fillMaxWidth()
                    .aspectRatio(0.8f)
            )
        }
    }
}

@Composable
fun CategoryCard(
    navigationType: NavigationType,
    selected: Boolean,
    category: Category,
    onButtonClick: (Function) -> Unit,
    onCardClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    val padding = if (navigationType == NavigationType.BOTTOM_NAVIGATION)
        R.dimen.padding_small else R.dimen.padding_medium
    Card(
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.elevation)),
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = modifier
            .clickable { onCardClick(category) }
    ) {
        Box(modifier = Modifier) {
            Image(
                painter = painterResource(id = category.image),
                contentDescription = stringResource(id = category.name),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            CardButton(
                function = Function.Favorite,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .align(Alignment.TopEnd)
                    .background(MaterialTheme.colorScheme.onPrimary, CircleShape)
                    .sizeIn(
                        maxWidth = dimensionResource(id = R.dimen.button_small),
                        maxHeight = dimensionResource(id = R.dimen.button_small)
                    ),
                onButtonClick = onButtonClick
            )
            Row (
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.onPrimaryContainer,
                                Color.Transparent
                            ),
                            startY = 100f,
                            endY = 0f // Adjust the endX value based on your preference
                        )
                    )
                    .align(Alignment.BottomStart)
                    .fillMaxWidth(),
            ){
                Text(
                    text = stringResource(id = category.name).replaceFirstChar { it.uppercase() },
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(dimensionResource(id = padding))
                        .background(Color.Transparent)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompactCategoriesScreenPreview() {
    GBookTheme {
        CategoriesScreen(navigationType = NavigationType.BOTTOM_NAVIGATION)
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumCategoriesScreenPreview() {
    GBookTheme {
        CategoriesScreen(navigationType = NavigationType.NAVIGATION_RAIL)
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedCategoriesScreenPreview() {
    GBookTheme {
        CategoriesScreen(navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER)
    }
}