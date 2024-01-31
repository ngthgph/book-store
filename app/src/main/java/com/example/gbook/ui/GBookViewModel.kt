package com.example.gbook.ui

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gbook.data.database.account.Account
import com.example.gbook.data.database.books.Book
import com.example.gbook.data.database.books.OfflineRepository
import com.example.gbook.data.database.collection.BookCollection
import com.example.gbook.data.database.layout.LayoutPreferencesRepository
import com.example.gbook.data.local.LocalSearchQueryProvider.categoryQuery
import com.example.gbook.data.local.LocalSearchQueryProvider.recommendedQuery
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.data.model.LayoutPreferencesUiState
import com.example.gbook.data.model.NetworkBookUiState
import com.example.gbook.data.model.OfflineBookUiState
import com.example.gbook.data.network.NetworkRepository
import com.example.gbook.data.database.books.SearchQuery
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NetworkFunction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class GBookViewModel(
    private val networkRepository: NetworkRepository,
    private val offlineRepository: OfflineRepository,
    private val layoutPreferencesRepository: LayoutPreferencesRepository
): ViewModel() {
    companion object {
        private const val TIME_MILLIS = 5000L
    }

    // GBookUiState
    private val _uiState = MutableStateFlow(GBookUiState())
    val uiState: StateFlow<GBookUiState> = _uiState
    init {
        initializeUiState()
    }
    private fun initializeUiState() {
        _uiState.value = GBookUiState()
        getRecommended()
    }
    private fun updateCurrentBook(book: Book?) {
        _uiState.update { currentState ->
            currentState.copy(currentBook = book)
        }
    }
    fun updateCurrentCollection(name: String?) {
        _uiState.update { currentState ->
            currentState.copy(currentCollectionName = name)
        }
    }
    //************************************************************************
    // NETWORK FUNCTIONS *****************************************************
    //************************************************************************
    var networkBookUiState: NetworkBookUiState by mutableStateOf(NetworkBookUiState.Loading)
    var recommendedUiState: NetworkBookUiState by mutableStateOf(NetworkBookUiState.Loading)
    fun handleOnNetworkFunction(networkFunction: NetworkFunction, searchQuery: SearchQuery?) {
        when(networkFunction) {
            NetworkFunction.Search -> TODO()
            NetworkFunction.Retry -> TODO()
            NetworkFunction.Recommended -> getRecommended()
            NetworkFunction.Category -> searchQuery?.let { getSubjectBookList(searchQuery = it) }
        }
    }
    //************************************************************************
    private fun getRecommended() {
        getRecommendedBookList(recommendedQuery)
    }
    // onCollectionClick - get collection data from internet
    private fun getSubjectBookList(searchQuery: SearchQuery) {
        val subject = searchQuery.query
        networkBookUiState = NetworkBookUiState.Loading
        onBackFromBookDetail()
        getNetworkBookList(subject.categoryQuery)
    }
    fun handleOnSearch(query: String) {

    }
    // Get data for Category Book List
    private fun getNetworkBookList(searchQuery: SearchQuery) {
        viewModelScope.launch {
            networkBookUiState = try {
                NetworkBookUiState
                    .Success(networkRepository.searchBookTerm(searchQuery))
            } catch (e: IOException) {
                NetworkBookUiState.Error
            } catch (e: retrofit2.HttpException) {
                NetworkBookUiState.Error
            }
        }
    }

    private fun getRecommendedBookList(searchQuery: SearchQuery) {
        viewModelScope.launch {
            recommendedUiState = try {
                NetworkBookUiState
                    .Success(networkRepository.searchBookTerm(searchQuery))
            } catch (e: IOException) {
                NetworkBookUiState.Error
            } catch (e: retrofit2.HttpException) {
                NetworkBookUiState.Error
            }
        }
    }
    //************************************************************************
    // END OF NETWORK FUNCTIONS **********************************************
    //************************************************************************
    val layoutPreferencesUiState: StateFlow<LayoutPreferencesUiState> =
        layoutPreferencesRepository.isGridLayout
            .map { LayoutPreferencesUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIME_MILLIS),
                initialValue = LayoutPreferencesUiState()
            )
    fun selectLayout(isGridLayout: Boolean) {
        viewModelScope.launch {
            layoutPreferencesRepository.saveLayoutPreferences(isGridLayout = isGridLayout)
        }
    }
    //************************************************************************
    // FUNCTIONS *************************************************************
    //************************************************************************

    // Get full book-database
    val offlineBooksUiState: StateFlow<OfflineBookUiState> =
        offlineRepository.getAllBooksStream()
            .map { OfflineBookUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIME_MILLIS),
                initialValue = OfflineBookUiState()
            )

    // Get books with specific collectionName
    var offlineCollectionUiState: OfflineBookUiState by mutableStateOf(OfflineBookUiState())
    fun getOfflineCollection(collectionName: String) {
        viewModelScope.launch {
            offlineCollectionUiState = offlineRepository.getCollectionStream(collectionName)
                .filterNotNull()
                .first()
                .toOfflineBookUiState()
        }
    }
    private fun List<Book>.toOfflineBookUiState(): OfflineBookUiState = OfflineBookUiState(bookList = this)
    fun handleOnFunction(
        function: Function,
        book: Book? = null,
        collection: BookCollection? = null,
        account: Account? = null,
        string: String? = null,
        context: Context? = null,
    ) {
        when(function) {
            Function.BookCard -> {
                book?.let { onCardClick(it) }
            }
            Function.AddToLibrary -> {
                book?.let { addBookToCollection(it, "Favorite") }
            }
            Function.Cart -> TODO()
            Function.Share -> book?.let {
                context?.shareBook(it)
            }
            Function.Cancel -> TODO()
            Function.IncreaseAmount -> TODO()
            Function.DecreaseAmount -> TODO()
            Function.Delete -> TODO()
            Function.Checkout -> TODO()
            Function.AddToCart -> TODO()
            Function.PreviousPage -> TODO()
            Function.NextPage -> TODO()
            Function.SignIn -> TODO()
            Function.ForgetPassword -> TODO()
            Function.SignUp -> TODO()
            Function.AddCollection -> TODO()
            Function.Search -> TODO()
            Function.Input -> TODO()
            Function.RemoveFromLibrary -> removeBookFromCollection(book!!,uiState.value.currentCollectionName!!)
        }
    }
    //************************************************************************
    // onCardClick
    private fun onCardClick(book: Book) {
        updateCurrentBook(book)
    }
    // get out of details book - reset currentBook to null
    fun onBackFromBookDetail() {
        updateCurrentBook(null)
    }
    // Data for Library Collection Book List
    private fun addBookToCollection(book: Book, collectionName: String) {
        val updatedBook = book.apply { book.collectionName = collectionName }
        viewModelScope.launch {
            offlineRepository.insertBook(updatedBook)
        }
    }
    private fun removeBookFromCollection(book: Book,collectionName: String) {
        viewModelScope.launch {
            offlineRepository.deleteBook(book)
        }
        getOfflineCollection(collectionName)
        onBackFromBookDetail()
    }
    fun handleOnInput(input: String) {

    }

    //************************************************************************
    // END OF FUNCTIONS ******************************************************
    //************************************************************************

}
