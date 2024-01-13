package com.example.bookstore.ui.screens.cart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.data.local.MockData
import com.example.bookstore.data.model.Book
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun BookItem(
    book: Book,
    modifier: Modifier = Modifier,
) {

}

@Preview
@Composable
fun BookItemPreview() {
    BookStoreTheme {
        BookItem(book = MockData.bookUiState.currentBook!!)
    }
}