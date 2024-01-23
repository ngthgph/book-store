package com.example.gbook.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.R
import com.example.gbook.data.fake.FakeNetworkBooksRepository
import com.example.gbook.data.fake.MockData
import com.example.gbook.data.model.Book
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.data.model.NetworkBookUiState
import com.example.gbook.ui.GBookViewModel
import com.example.gbook.ui.items.BooksGridSection
import com.example.gbook.ui.items.SearchBar
import com.example.gbook.ui.screens.book.ListDetailHandler
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType

@Composable
fun HomeScreen(
    navigationType: NavigationType,
    viewModel: GBookViewModel,
    uiState: GBookUiState,
    networkBookUiState: NetworkBookUiState,
    onButtonClick: (Function) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ListDetailHandler(
        navigationType = navigationType,
        viewModel = viewModel,
        uiState = uiState,
        networkBookUiState = networkBookUiState,
        onButtonClick = onButtonClick,
        modifier = modifier
    ) {
        HomeContent(
            navigationType = navigationType,
            uiState = uiState,
            networkBookUiState = networkBookUiState,
            bookListTitle = stringResource(R.string.recommended),
            onButtonClick = onButtonClick,
            onCardClick = { viewModel.handleOnCardClick(it) },
            onSearch = onSearch,
        )
    }
}

@Composable
fun HomeContent(
    navigationType: NavigationType,
    uiState: GBookUiState,
    networkBookUiState: NetworkBookUiState,
    bookListTitle: String,
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
        HomeBrand()
        SearchBar(onSearch = onSearch)
        BooksGridSection(
            navigationType = navigationType,
            uiState = networkBookUiState,
            bookListTitle = bookListTitle,
            onButtonClick = onButtonClick,
            onCardClick = onCardClick
        )
    }
}

@Composable
fun HomeBrand(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
        )
//        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
        Text(
            text = stringResource(R.string.g_book_slogan),
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
//            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun HomeBrandPreview() {
    GBookTheme {
        HomeBrand(
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CompactHomeScreenPreview() {
    GBookTheme {
        HomeScreen(
            navigationType = NavigationType.BOTTOM_NAVIGATION,
            viewModel = GBookViewModel(FakeNetworkBooksRepository()),
            uiState = GBookUiState(),
            networkBookUiState = MockData.networkBookUiState,
            onButtonClick = {},
            onSearch = {},
        )
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumHomeScreenPreview() {
    GBookTheme {
        HomeScreen(
            navigationType = NavigationType.NAVIGATION_RAIL,
            viewModel = GBookViewModel(FakeNetworkBooksRepository()),
            uiState = GBookUiState(),
            networkBookUiState = MockData.networkBookUiState,
            onButtonClick = {},
            onSearch = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedHomeScreenPreview() {
    GBookTheme {
        HomeScreen(
            navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER,
            viewModel = GBookViewModel(FakeNetworkBooksRepository()),
            uiState = GBookUiState(),
            networkBookUiState = MockData.networkBookUiState,
            onButtonClick = {},
            onSearch = {},
        )
    }
}