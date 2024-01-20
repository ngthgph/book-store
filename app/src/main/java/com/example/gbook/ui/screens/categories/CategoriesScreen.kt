package com.example.gbook.ui.screens.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.data.local.LocalCategoriesProvider
import com.example.gbook.data.model.Collection
import com.example.gbook.ui.items.CollectionGrid
import com.example.gbook.ui.items.SearchBar
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType

@Composable
fun CategoriesScreen(
    navigationType: NavigationType,
    onButtonClick: (Function) -> Unit,
    onCategoryClick: (Collection) -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = LocalCategoriesProvider.categories
    CategoriesContent(
        modifier = modifier,
        navigationType = navigationType,
        categories = categories,
        onButtonClick = onButtonClick,
        onCategoryClick = onCategoryClick,
    )
}

@Composable
fun CategoriesContent(
    navigationType: NavigationType,
    categories: List<Collection>,
    onButtonClick: (Function) -> Unit,
    onCategoryClick: (Collection) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        SearchBar(onSearch = {})
        CollectionGrid(
            navigationType = navigationType,
            categories = categories,
            onButtonClick = onButtonClick,
            onCategoryClick = onCategoryClick,
        )
    }
}
@Preview(showBackground = true)
@Composable
fun CompactCategoriesScreenPreview() {
    GBookTheme {
        CategoriesScreen(
            navigationType = NavigationType.BOTTOM_NAVIGATION,
            onButtonClick = {},
            onCategoryClick = {},
        )
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumCategoriesScreenPreview() {
    GBookTheme {
        CategoriesScreen(
            navigationType = NavigationType.NAVIGATION_RAIL,
            onButtonClick = {},
            onCategoryClick = {},
        )
    }
}