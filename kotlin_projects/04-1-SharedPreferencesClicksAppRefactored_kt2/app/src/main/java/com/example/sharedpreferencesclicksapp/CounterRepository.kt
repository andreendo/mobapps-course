package com.example.sharedpreferencesclicksapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CounterRepository(context: Context) {
    private val Context.dataStore by preferencesDataStore("app_preferences")
    private val dataStore: DataStore<Preferences> = context.dataStore
    private val COUNTER = intPreferencesKey("counter")
    private val counterFlow: Flow<Int> = dataStore.data.map { preferences ->
        preferences[COUNTER] ?: 0
    }

    suspend fun observeCounter(onCounterChange: (Int) -> Unit) {
        counterFlow.collect { counterVal ->
            if (counterVal != 0)
                onCounterChange(counterVal)
        }
    }

    suspend fun incrementCounter() {
        // Start editing the preferences
        dataStore.edit { preferences ->
            val currentCounter = preferences[COUNTER] ?: 0
            preferences[COUNTER] = currentCounter + 1
        }
    }
}