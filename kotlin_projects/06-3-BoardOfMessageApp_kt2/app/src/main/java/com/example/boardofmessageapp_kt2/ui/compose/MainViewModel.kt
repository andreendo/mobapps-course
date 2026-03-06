package com.example.boardofmessageapp_kt2.ui.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boardofmessageapp_kt2.repository.common.Board
import com.example.boardofmessageapp_kt2.repository.common.BoardRepository
import com.example.boardofmessageapp_kt2.repository.common.Message
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = BoardRepository()

    var numberOfBoardsMsg by mutableStateOf("")
    var boards = mutableStateListOf<Board>()
    var messages = mutableStateListOf<Message>()
    var showNetworkErrorSnackBar by mutableStateOf(false)

    var msg_boardID by mutableStateOf("")
    var msg_boardIdError by mutableStateOf(false)
    var errorMessage = ""

    fun getBoards() {
        viewModelScope.launch {
            try {
                val rBoards = repository.getBoards()
                numberOfBoardsMsg = "#Boards: " + rBoards.size
                boards.clear()
                rBoards.forEach { boards.add(it) }
            } catch (e: Exception) {
                numberOfBoardsMsg = ""
                boards.clear()
                showNetworkErrorSnackBar = true
            }
        }
        numberOfBoardsMsg = "loading..."
    }

    fun dismissNetworkErrorSnackBar() {
        showNetworkErrorSnackBar = false
    }

    fun updateMsg_boardID(s: String) {
        msg_boardID = s
        if (msg_boardID == "")
            return
        validateBoardId()
    }

    private fun validateBoardId(): Boolean {
        val boardId = runCatching { msg_boardID.toLong() }.getOrNull()
        if (boardId == null) {
            msg_boardIdError = true
            errorMessage = "Board ID should be an integer"
            return false
        } else {
            msg_boardIdError = false
            return true
        }
    }

    fun loadMessages() {
        if (validateBoardId()) {
            viewModelScope.launch {
                val rMessages = repository.getMessages(msg_boardID.toLong())
                messages.clear()
                rMessages.forEach {
                    messages.add(it)
                }
            }
        }
    }

    fun postMessage(boardId: String, from: String, to: String, text: String) {
        viewModelScope.launch {
            val newMsg = Message(from, to, text)
            repository.postMessage(boardId.toLong(), newMsg)
        }
    }
}