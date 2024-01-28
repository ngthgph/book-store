package com.example.gbook.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.gbook.network.BookApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

const val LAYOUT_PREFERENCES_NAME = "layout_preferences"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = LAYOUT_PREFERENCES_NAME)
interface AppContainer {
    val booksRepository: BooksRepository
    val layoutPreferencesRepository: LayoutPreferencesRepository
}

class DefaultAppContainer(context: Context): AppContainer {
    private val baseUrl =
        "https://www.googleapis.com/books/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()
    private val retrofitService: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }
    override val booksRepository: BooksRepository by lazy {
        NetworkBooksRepository(retrofitService)
    }

    override val layoutPreferencesRepository: LayoutPreferencesRepository by lazy {
        LayoutPreferencesRepository(dataStore = context.dataStore)
    }
}