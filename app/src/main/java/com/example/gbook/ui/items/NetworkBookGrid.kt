package com.example.gbook.ui.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.R
import com.example.gbook.data.model.Book
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.data.model.NetworkBookUiState
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.NavigationType
import com.example.gbook.ui.utils.Function

@Composable
fun NetworkBooksGrid(
    navigationType: NavigationType,
    uiState: NetworkBookUiState,
    onButtonClick: (Function) -> Unit,
    onCardClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false,
) {
    when(uiState) {
        is NetworkBookUiState.Loading -> LoadingContent(modifier = modifier)
        is NetworkBookUiState.Success ->
            BooksGrid(
                navigationType = navigationType,
                bookList = uiState.books,
                isFavorite = isFavorite,
                onButtonClick = onButtonClick,
                onCardClick = onCardClick,
                modifier = modifier
            )
        is NetworkBookUiState.Error ->
            ErrorContent(
                onButtonClick = onButtonClick,
                modifier = modifier
                )
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
    onButtonClick: (Function) -> Unit,
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
        DescriptionButton(
            function = Function.Retry,
            onButtonClick = onButtonClick
        )
    }
}
@Preview
@Composable
fun LoadingPreview() {
    GBookTheme {
        LoadingContent()
    }
}
@Preview
@Composable
fun ErrorPreview() {
    GBookTheme {
        ErrorContent(onButtonClick = {})
    }
}