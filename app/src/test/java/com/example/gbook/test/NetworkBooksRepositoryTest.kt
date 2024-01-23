package com.example.gbook.test

import com.example.gbook.data.fake.FakeBookApiService
import com.example.gbook.data.fake.FakeDataSource
import com.example.gbook.network.NetworkBooksRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkBooksRepositoryTest {
    @Test
    fun networkBooksRepository_getBookList_verifyBookList() = runTest {
        val repository = NetworkBooksRepository(
            bookApiService = FakeBookApiService()
        )
        assertEquals(FakeDataSource.fakeBookList,repository.searchBookTerm(""))
    }
}