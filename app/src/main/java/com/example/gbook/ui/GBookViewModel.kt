package com.example.gbook.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gbook.data.local.BookFilter
import com.example.gbook.data.local.OrderBy
import com.example.gbook.data.local.PrintType
import com.example.gbook.data.local.Projection
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
import java.util.Locale

class GBookViewModel(private val booksRepository: BooksRepository): ViewModel() {

    private val _uiState = MutableStateFlow(GBookUiState())
    val uiState: StateFlow<GBookUiState> = _uiState

    var networkBookUiState: NetworkBookUiState by mutableStateOf(NetworkBookUiState.Loading)
    var recommendedUiState: NetworkBookUiState by mutableStateOf(NetworkBookUiState.Loading)
    var bookUiState: NetworkBookUiState by mutableStateOf(NetworkBookUiState.Loading)

    init {
        innitializeUiState()
    }

    fun innitializeUiState() {
        _uiState.value = GBookUiState()
        getRecommended()
    }

    fun getRecommended() {
        getRecommendedBookList(
            query = "search term",
            maxResults = 40,
            filter = BookFilter.PAID_EBOOKS,
            orderBy = OrderBy.NEWEST
        )
    }

    fun handleOnButtonClick(function: Function) {
        when(function) {
            Function.Library -> TODO()
            Function.Cart -> TODO()
            Function.Share -> TODO() // Done on each book card
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
            Function.PreviousPage -> TODO()
            Function.NextPage -> TODO()
        }
    }
    // onCardClick
    fun handleOnCardClick(book: Book) {
        bookUiState = NetworkBookUiState.Loading
        getNetworkBookItem(book.networkId)
        updateCurrentBook(book)
    }
    // get out of details book - reset currentBook to null
    fun onBackFromBookDetail() {
        updateCurrentBook(null)
    }
    // onCollectionClick - get collection data from internet
    fun getSubjectBookList(subject: String) {
        networkBookUiState = NetworkBookUiState.Loading
        onBackFromBookDetail()
        getNetworkBookList(
            query = "",
            subject = subject.lowercase(Locale.getDefault()),
            maxResults = 40,
            filter = BookFilter.PAID_EBOOKS,
            orderBy = OrderBy.NEWEST
        )
    }

    // PART OF HANDLING ON BUTTON CLICK FOR EACH FUNCTIONS ***************


    // END OF HANDLE ON BUTTON CLICK FOR EACH FUNCTIONS PART *************

    fun handleOnSearch(query: String) {

    }

    fun handleOnInput(input: String) {

    }

    private fun getNetworkBookList(
        query: String,
        intitle: String? = null,
        inauthor: String? = null,
        inpublisher: String? = null,
        subject: String? = null,
        isbn: String? = null,
        lccn: String? = null,
        oclc: String? = null,
        filter: BookFilter? = null,
        startIndex: Int? = null,
        maxResults: Int? = null,
        printType: PrintType? = null,
        projection: Projection? = null,
        orderBy: OrderBy? = null,
    ) {
        viewModelScope.launch {
            networkBookUiState = try {
                NetworkBookUiState
                    .Success(booksRepository.searchBookTerm(
                        query,
                        intitle, inauthor, inpublisher, subject, isbn, lccn, oclc,
                        filter,
                        startIndex,
                        maxResults,
                        printType,
                        projection,
                        orderBy
                    ))
            } catch (e: IOException) {
                NetworkBookUiState.Error
            } catch (e: retrofit2.HttpException) {
                NetworkBookUiState.Error
            }
        }
    }

    private fun getRecommendedBookList(
        query: String,
        intitle: String? = null,
        inauthor: String? = null,
        inpublisher: String? = null,
        subject: String? = null,
        isbn: String? = null,
        lccn: String? = null,
        oclc: String? = null,
        filter: BookFilter? = null,
        startIndex: Int? = null,
        maxResults: Int? = null,
        printType: PrintType? = null,
        projection: Projection? = null,
        orderBy: OrderBy? = null,
    ) {
        viewModelScope.launch {
            recommendedUiState = try {
                NetworkBookUiState
                    .Success(booksRepository.searchBookTerm(
                        query,
                        intitle, inauthor, inpublisher, subject, isbn, lccn, oclc,
                        filter,
                        startIndex,
                        maxResults,
                        printType,
                        projection,
                        orderBy
                    ))
            } catch (e: IOException) {
                NetworkBookUiState.Error
            } catch (e: retrofit2.HttpException) {
                NetworkBookUiState.Error
            }
        }
    }

    private fun getNetworkBookItem(networkId: String) {
        viewModelScope.launch {
            bookUiState = try {
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