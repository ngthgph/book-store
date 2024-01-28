package com.example.gbook.ui.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gbook.data.model.Book
import com.example.gbook.data.model.LayoutPreferencesUiState
import com.example.gbook.data.model.NetworkBookUiState
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType

@Composable
fun GridOrLinearLayout(
    navigationType: NavigationType,
    networkBookUiState: NetworkBookUiState,
    layoutPreferencesUiState: LayoutPreferencesUiState,
    onButtonClick: (Function) -> Unit,
    onCardClick: (Book) -> Unit,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false,
) {
    if(layoutPreferencesUiState.isGridLayout) {
        NetworkBooksGrid(
            navigationType = navigationType,
            networkBookUiState = networkBookUiState,
            onButtonClick = onButtonClick,
            onCardClick = onCardClick,
            retryAction = retryAction,
            isFavorite = isFavorite,
            modifier = modifier
        )
    } else {
        NetworkBooksList(
            navigationType = navigationType,
            networkBookUiState = networkBookUiState,
            onButtonClick = onButtonClick,
            onCardClick = onCardClick,
            retryAction = retryAction,
            isFavorite = isFavorite,
            modifier = modifier
        )
    }
}