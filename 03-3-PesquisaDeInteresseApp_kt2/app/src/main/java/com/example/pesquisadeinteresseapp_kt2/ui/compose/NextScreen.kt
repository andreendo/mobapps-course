package com.example.pesquisadeinteresseapp_kt2.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pesquisadeinteresseapp_kt2.ui.theme.PesquisaDeInteresseApp_kt2Theme
import com.example.pesquisadeinteresseapp_kt2.viewmodels.MyViewModel

@Composable
fun NextScreen(
    viewModel: MyViewModel = viewModel(),
    onCancelarClicked: () -> Unit = {},
    onFinalizarClicked: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val preferencias = preferenciasMarcadas(
        uiState.preferencias,
        uiState.preferenciasMarcadas
    ).joinToString(separator = ", ")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Nome: ",
                fontWeight = FontWeight.Bold
            )
            Text(text = uiState.nome)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Região: ",
                fontWeight = FontWeight.Bold
            )
            Text(text = uiState.regiao)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Faixa etária: ",
                fontWeight = FontWeight.Bold
            )
            Text(text = viewModel.faixas[uiState.faixaEtaria-1])
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Preferências: ",
                fontWeight = FontWeight.Bold
            )
            Text(text = preferencias)
        }
        Row() {
            Button(onClick = onCancelarClicked) {
                Text(text = "Cancelar")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = onFinalizarClicked) {
                Text(text = "Finalizar")
            }
        }
    }
}

fun preferenciasMarcadas(
    prefs: List<String>,
    checked: List<Boolean>
) : List<String> {
    return prefs.filterIndexed{ index, it ->
        checked[index]
    }
}

@Preview(showBackground = true)
@Composable
fun NextScreenPreview() {
    PesquisaDeInteresseApp_kt2Theme {
        NextScreen()
    }
}