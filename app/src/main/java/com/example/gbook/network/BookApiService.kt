package com.example.gbook.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApiService {
    @GET("v1/volumes")
    suspend fun searchBooks(
        @Query("q") query: String
    ): BookSearchResponse

    @GET("v1/volumes/{id}")
    suspend fun getBook(
        @Path("id") bookId: String
    ): BookItem
}