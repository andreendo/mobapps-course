package com.example.sharedpreferencesclicksapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CounterRepository(context: Context, private val scope: CoroutineScope) {
    private val Context.dataStore by preferencesDataStore("app_preferences")
    private val dataStore: DataStore<Preferences> = context.dataStore
    private val COUNTER = intPreferencesKey("counter")
    private val counterFlow: Flow<Int> = dataStore.data.map { preferences ->
        preferences[COUNTER] ?: 0
    }

    fun observeCounter(onCounterChange: (Int) -> Unit) {
        scope.launch {
            counterFlow.collect { counterVal ->
                if (counterVal != 0)
                    onCounterChange(counterVal)
            }
        }
    }

    fun incrementCounter() {
        scope.launch {
            // Start editing the preferences
            dataStore.edit { preferences ->
                val currentCounter = preferences[COUNTER] ?: 0
                preferences[COUNTER] = currentCounter + 1
            }
        }
    }
}