package com.example.login_kt2

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory


/**
 * Provides Factory to create instance of ViewModel for the entire app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {

        // Initializer for MainViewModel
        initializer {
            MainViewModel(appApplication().container.loginTryRepository)
        }

        // Initializer for LogTriesViewModel
        initializer {
            LogTriesViewModel(appApplication().container.loginTryRepository)
        }

    }
}

fun CreationExtras.appApplication(): AppApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as AppApplication)