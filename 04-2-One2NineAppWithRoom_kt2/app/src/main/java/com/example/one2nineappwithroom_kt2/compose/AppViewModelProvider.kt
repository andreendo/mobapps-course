package com.example.one2nineappwithroom_kt2.compose

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.one2nineappwithroom_kt2.GameApplication


/**
 * Provides Factory to create instance of ViewModel for the entire Game app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {

        // Initializer for GameViewModel
        initializer {
            GameViewModel(gameApplication().container.scoreRepository)
        }

        // Initializer for ScoreViewModel
        initializer {
            ScoreViewModel(gameApplication().container.scoreRepository)
        }

    }
}

fun CreationExtras.gameApplication(): GameApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as GameApplication)