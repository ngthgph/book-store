package com.example.gbook.ui.screens.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.data.local.LocalCategoriesProvider
import com.example.gbook.data.model.Category
import com.example.gbook.ui.items.CollectionGrid
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
        modifier = modifier,
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
        CollectionGrid(
            navigationType = navigationType,
            categories = categories,
            onButtonClick = onButtonClick,
            onCardClick = onCardClick
        )
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