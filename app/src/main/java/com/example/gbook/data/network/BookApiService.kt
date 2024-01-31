package com.example.gbook.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApiService {
    @GET("v1/volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("filter") filter: String? = null,
        @Query("startIndex") startIndex: Int? = null,
        @Query("maxResults") maxResults: Int? = null,
        @Query("printType") printType: String? = null,
        @Query("projection") projection: String? = null,
        @Query("orderBy") orderBy: String? = null,
    ): BookSearchResponse

    @GET("v1/volumes/{networkId}")
    suspend fun getBook(
        @Path("networkId") networkId: String
    ): BookItem
}