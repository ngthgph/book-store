package com.example.bookstore.ui.screens.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.R
import com.example.bookstore.ui.screens.home.BooksGrid
import com.example.bookstore.ui.theme.BookStoreTheme
import com.example.bookstore.ui.utils.NavigationType

@Composable
fun NetworkBooksGrid(
    navigationType: NavigationType,
    uiState: NetworkBookUiState,
    retryAction: () -> Unit,
) {
    when(uiState) {
        is NetworkBookUiState.Loading -> LoadingContent()
        is NetworkBookUiState.Success ->
            BooksGrid(
                navigationType = navigationType,
                bookList = uiState.books,
                onCardClick = {},
                onButtonClick = {}
            )
        is NetworkBookUiState.Error -> ErrorContent(retryAction = retryAction)
    }
}

@Composable
fun LoadingContent(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier.size(dimensionResource(id = R.dimen.image_size_large)),
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}
@Composable
fun ErrorContent(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(
            text = stringResource(R.string.fail_to_load),
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
        )
        Button(onClick = retryAction) {
            Text(
                text = stringResource(R.string.retry)
            )
        }
    }
}
@Preview
@Composable
fun LoadingPreview() {
    BookStoreTheme {
        LoadingContent()
    }
}
@Preview
@Composable
fun ErrorPreview() {
    BookStoreTheme {
        ErrorContent(retryAction = {})
    }
}