package com.example.gbook.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gbook.data.model.Book
import com.example.gbook.data.model.BookCollection
import com.example.gbook.network.BooksRepository
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.data.model.NetworkBookUiState
import com.example.gbook.ui.utils.Function
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class GBookViewModel(private val booksRepository: BooksRepository): ViewModel() {

    private val _uiState = MutableStateFlow(GBookUiState())
    val uiState: StateFlow<GBookUiState> = _uiState

    var networkBookUiState: NetworkBookUiState by mutableStateOf(NetworkBookUiState.Loading)

    init {
        innitializeUiState()
    }

    fun innitializeUiState() {
        _uiState.value = GBookUiState()
        getNetworkBookList("search terms")
    }

    fun handleOnButtonClick(function: Function) {
        when(function) {
            Function.Library -> TODO()
            Function.Cart -> TODO()
            Function.Share -> TODO()
            Function.Cancel -> TODO()
            Function.Add -> TODO()
            Function.Decline -> TODO()
            Function.Delete -> TODO()
            Function.Checkout -> TODO()
            Function.AddToCart -> TODO()
            Function.Configuration -> TODO()
            Function.SignIn -> TODO()
            Function.ForgetPassword -> TODO()
            Function.SignUp -> TODO()
            Function.Retry -> TODO()
        }
    }
    fun handleOnCardClick(book: Book) {
        getNetworkBookItem(book.networkId)
        updateCurrentBook(book)
    }
    fun onBackFromBookDetail() {
        updateCurrentBook(null)
    }
    fun handleOnCollectionClick(collection: BookCollection) {

    }

    fun handleOnSearch(query: String) {

    }

    fun handleOnInput(input: String) {

    }

    fun getNetworkBookList(query: String) {
        viewModelScope.launch {
            networkBookUiState = try {
                NetworkBookUiState
                    .Success(booksRepository.searchBookTerm(query))
            } catch (e: IOException) {
                NetworkBookUiState.Error
            } catch (e: retrofit2.HttpException) {
                NetworkBookUiState.Error
            }
        }
    }

    fun getNetworkBookItem(networkId: String) {
        viewModelScope.launch {
            networkBookUiState = try {
                NetworkBookUiState.Success(List(1){booksRepository.searchBookItem(networkId)})
            } catch (e: IOException) {
                NetworkBookUiState.Error
            } catch (e: retrofit2.HttpException) {
                NetworkBookUiState.Error
            }
        }
    }

    fun updateCurrentBook(book: Book?) {
        _uiState.update { currentState ->
            currentState.copy(currentBook = book)
        }
    }
    fun updateCurrentBookCollection(query: String, bookList: List<Book>) {
        _uiState.update { currentState ->
            currentState.copy(currentBookCollection =
            BookCollection(query,currentState.currentBookCollection?.image,bookList))
        }
    }
}