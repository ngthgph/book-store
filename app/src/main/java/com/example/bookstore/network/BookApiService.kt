package com.example.bookstore.network

import retrofit2.http.GET

interface BookApiService {
    @GET("math")
    suspend fun getBooks(): List<BookInfo>
}