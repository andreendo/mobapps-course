package com.example.one2nineappwithroom_kt2.compose

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.one2nineappwithroom_kt2.repository.Score
import com.example.one2nineappwithroom_kt2.repository.ScoreRepository
import kotlinx.coroutines.launch

class ScoreViewModel(private val scoreRepository: ScoreRepository) : ViewModel() {

    var scores = mutableStateListOf<Score>()

    init {
        viewModelScope.launch {
            scoreRepository.getAllScores().collect {
                scores.clear()
                it.forEach { s -> scores.add(s) }
            }
        }
    }

    fun clearAllRecords() {
        viewModelScope.launch {
            scoreRepository.deleteAllScores()
        }
    }
}