package com.example.boardofmessageapp_kt2.ui.compose

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boardofmessageapp_kt2.repository.common.BoardRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = BoardRepository()

    var numberOfBoardsMsg by mutableStateOf("")

    fun getBoards() {
        viewModelScope.launch {
            val boards = repository.getBoards()
            var msg = "#Boards: " + boards.size
            boards.forEach {
                msg += "\n" + it.name + ", messages: " + it.messages.size
            }

            numberOfBoardsMsg = msg
        }
        numberOfBoardsMsg = "loading..."
    }
}