package com.example.imcapp_kt2

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.imcapp_kt2.model.calculateIMC
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ImcViewModel : ViewModel() {
    // UI state
    private val _uiState = MutableStateFlow(PersonUiState())
    val uiState: StateFlow<PersonUiState> = _uiState.asStateFlow()  // make _uiState as read-only state flow
    var dialogMessage by mutableStateOf("")

    fun updateName(name: String) {
        _uiState.update { currentState ->
            currentState.copy(name=name)
        }
    }

    fun updateAge(age: String) {
        _uiState.update { currentState ->
            currentState.copy(age=age)
        }
    }

    fun updateHeight(height: String) {
        _uiState.update { currentState ->
            currentState.copy(height=height)
        }
    }

    fun updateWeight(weight: String) {
        _uiState.update { currentState ->
            currentState.copy(weight=weight)
        }
    }

    fun calculateImc() {
        val imc = calculateIMC(uiState.value.height.toFloat(), uiState.value.weight.toFloat())
        dialogMessage = String.format("IMC value is %.2f.", imc)
    }
}