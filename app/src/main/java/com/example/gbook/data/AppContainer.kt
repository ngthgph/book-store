package com.example.gbook.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.gbook.data.database.books.BookDatabase
import com.example.gbook.data.database.books.OfflineBooksRepository
import com.example.gbook.data.database.books.OfflineRepository
import com.example.gbook.data.database.layout.LayoutPreferencesRepository
import com.example.gbook.data.network.BookApiService
import com.example.gbook.data.network.NetworkBooksRepository
import com.example.gbook.data.network.NetworkRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


interface AppContainer {
    val networkRepository: NetworkRepository
    val offlineRepository: OfflineRepository
    val layoutPreferencesRepository: LayoutPreferencesRepository
}

class DefaultAppContainer(context: Context): AppContainer {

    // Network Google books API
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
    override val networkRepository: NetworkRepository by lazy {
        NetworkBooksRepository(retrofitService)
    }

    // Offline books database
    override val offlineRepository: OfflineRepository by lazy {
        OfflineBooksRepository(BookDatabase.getDatabase(context).bookDao())
    }

    // Layout user preferences
    override val layoutPreferencesRepository: LayoutPreferencesRepository by lazy {
        LayoutPreferencesRepository(dataStore = context.dataStore)
    }
}

const val LAYOUT_PREFERENCES_NAME = "layout_preferences"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = LAYOUT_PREFERENCES_NAME)