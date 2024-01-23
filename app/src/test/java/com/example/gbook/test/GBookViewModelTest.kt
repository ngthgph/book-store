package com.example.gbook.test

import com.example.gbook.data.model.NetworkBookUiState
import com.example.gbook.data.fake.FakeDataSource
import com.example.gbook.data.fake.FakeNetworkBooksRepository
import com.example.gbook.rules.TestDispatcherRule
import com.example.gbook.ui.GBookViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class GBookViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun gBookViewModelTest_getBookList_verifyNetworkBookUiStateSuccess() = runTest {
        val gBookViewModel = GBookViewModel(
            booksRepository = FakeNetworkBooksRepository()
        )
        assertEquals(
            NetworkBookUiState.Success(FakeDataSource.fakeBookList),
            gBookViewModel.networkBookUiState
        )
    }
}