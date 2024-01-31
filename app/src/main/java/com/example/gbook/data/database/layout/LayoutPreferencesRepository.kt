package com.example.gbook.data.database.layout

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class LayoutPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val IS_GRID_LAYOUT = booleanPreferencesKey("is_grid_layout")
        const val TAG = "LayoutPreferencesRepo"
    }

    val isGridLayout: Flow<Boolean> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[IS_GRID_LAYOUT] ?: true
        }

    suspend fun saveLayoutPreferences(isGridLayout: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_GRID_LAYOUT] = isGridLayout
        }
    }
}