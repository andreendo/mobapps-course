package com.example.pesquisadeinteresseapp_kt2.viewmodels

data class MyUiState (
    val nome: String = "",
    val regiao: String = "",
    val faixaEtaria: Int = 0,
    val preferencias: List<String> = ArrayList(),
    val preferenciasMarcadas: List<Boolean> = ArrayList()
)