package com.example.pesquisadeinteresseapp_kt2.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pesquisadeinteresseapp_kt2.model.Preferencia
import com.example.pesquisadeinteresseapp_kt2.model.PreferenciaDAO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyViewModel : ViewModel() {
    val faixas = listOf("0 a 17 anos", "18 a 34 anos", "Acima de 35 anos")
    val dao = PreferenciaDAO()
    private var _prefs: List<Preferencia> = ArrayList()
    // Expose screen UI state
    private val _uiState = MutableStateFlow(MyUiState())
    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()

    init {
        updateRegiao("--")
        updateFaixa(1)
    }

    fun reset() {
        _uiState.update {
            MyUiState()
        }
        updateRegiao("--")
        updateFaixa(1)
    }

    fun updateNome(nome: String) {
        _uiState.update { currentState ->
            currentState.copy(nome= nome)
        }
    }

    fun updateRegiao(regiao: String) {
        _uiState.update { currentState ->
            currentState.copy(regiao = regiao)
        }
    }

    fun updateFaixa(faixa: Int) {
        _prefs = dao.getByFaixa(faixa)
        val prefs = _prefs.map { it.nome }
        val checked = List(_prefs.size) { false }
        _uiState.update { currentState ->
            currentState.copy(
                faixaEtaria = faixa,
                preferencias = prefs,
                preferenciasMarcadas = checked
            )
        }
    }

    fun updatePreferencias(prefs: List<String>) {
        _uiState.update { currentState ->
            currentState.copy(preferencias = prefs)
        }
    }

    fun updatePreferenciaMarcada(indexDaPreferencia: Int) {
        val checked = mutableListOf<Boolean>()
        _uiState.value.preferenciasMarcadas.forEach { checked.add(it) }
        checked[indexDaPreferencia] = !checked[indexDaPreferencia]

        _uiState.update { currentState ->
            currentState.copy(
                preferenciasMarcadas = checked
            )
        }
    }

    fun gravarPesquisa() {
        Log.d("UiState", uiState.value.toString())
        reset()
    }
}