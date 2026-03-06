package com.example.boardofmessageapp_kt2.repository.common

data class Board (
    val id: Int = 0,
    val name: String = "",
    val messages: List<Message>
)