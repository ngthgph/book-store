package com.example.gbook.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gbook.network.BooksRepository
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.data.model.NetworkBookUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
        getNetworkBookList("search+term")
    }

    private fun getNetworkBookList(query: String) {
        viewModelScope.launch {
            networkBookUiState = try {
                NetworkBookUiState.Success(booksRepository.searchBookTerm(query))
            } catch (e: IOException) {
                NetworkBookUiState.Error
            } catch (e: retrofit2.HttpException) {
                NetworkBookUiState.Error
            }
        }
    }
}