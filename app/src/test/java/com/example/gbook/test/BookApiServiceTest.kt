package com.example.gbook.test

import com.example.gbook.fake.FakeDataSource
import com.example.gbook.network.BookApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.mockito.Mockito.mock
import org.junit.Test
import org.mockito.Mockito.`when`


class BookApiServiceTest {
    private val bookApiService = mock(BookApiService::class.java)

    @Test
    fun bookApiService_searchBooks_returnBooksSearchResponse() = runBlocking {
        val query = "Fake Book"
        val fakeResponse = FakeDataSource.fakeBookResponse

        `when`(bookApiService.searchBooks(query)).thenReturn(fakeResponse)
        val result = bookApiService.searchBooks(query)

        assertEquals(fakeResponse, result)
    }

    @Test
    fun bookApiService_getBook_returnBookItem() = runBlocking {
        val bookId = "123456789"
        val fakeResponse = FakeDataSource.fakeBookItem1

        `when`(bookApiService.getBook(bookId)).thenReturn(fakeResponse)
        val result = bookApiService.getBook(bookId)

        assertEquals(fakeResponse, result)
    }
}