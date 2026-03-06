package com.example.one2nineappwithroom_kt2.compose

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.one2nineappwithroom_kt2.game.GameConfig
import com.example.one2nineappwithroom_kt2.game.Number
import com.example.one2nineappwithroom_kt2.repository.Score
import com.example.one2nineappwithroom_kt2.repository.ScoreRepository
import kotlinx.coroutines.launch
import java.lang.String
import java.text.SimpleDateFormat
import java.util.Date


class GameViewModel(private val scoreRepository: ScoreRepository) : ViewModel() {
    private val gameConfig = GameConfig()
    private var current = 1
    private var startTime : Long = 0
    private var yourTime = -1.0f
    var dialogTimeMessage = ""
    var markers = mutableStateListOf<Boolean>()
    var numbers = mutableStateListOf<Number>()
    var showEndGameDialog by mutableStateOf(false)
    var endGameDialogText by mutableStateOf("")
    var playerName by mutableStateOf("")

    init {
        startGame()
    }

    fun startGame() {
        gameConfig.reset()
        startTime = System.currentTimeMillis()
        startRound()
    }

    fun startRound() {
        current = 1
        markers.clear()
        repeat(9) {
            markers.add(false)
        }
        numbers.clear()
        gameConfig.getNextConfiguration().forEach { numbers.add(it) }
    }

    fun click(buttonIndex: Int, value: Int) {
        if (value == current) {
            // change to green
            current++
            markers[buttonIndex] = true
            Log.i("Game", "current: " + current)
            Log.i("Game", "" + markers.toString())
            if (current == 10) {
                if (gameConfig.hasNext()) {
                    startRound()
                } else {
                    endGame()
                }
            }
        }
    }

    fun endGame() {
        playerName = ""
        yourTime = (System.currentTimeMillis() - startTime) / 1000.0f
        viewModelScope.launch {
            val bestScore = scoreRepository.getBestScore()
            var isBest = ""
            if (bestScore == null || bestScore.time > yourTime)
                isBest = "NEW RECORD!!!\n"
            val message = isBest + String.format(dialogTimeMessage, yourTime)
            endGameDialogText = message
            showEndGameDialog = true
        }
    }

    fun closeDialog() {
        showEndGameDialog = false
        val whenPlayed = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Date())
        // save player data
        val player = playerName.takeIf { it.isNotEmpty() } ?: "Anonymous"
        val score = Score(
            playerName = player,
            time = yourTime,
            whenPlayed = whenPlayed)
        viewModelScope.launch {
            scoreRepository.insertScore(score)
        }
    }
}