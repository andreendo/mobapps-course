package com.example.pesquisadeinteresseapp_kt2.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pesquisadeinteresseapp_kt2.R
import com.example.pesquisadeinteresseapp_kt2.ui.theme.PesquisaDeInteresseApp_kt2Theme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pesquisadeinteresseapp_kt2.viewmodels.MyViewModel

@Composable
fun MainScreen(
    viewModel: MyViewModel = viewModel(),
    onGravarPesquisaClicked: () -> Unit = {}
) {
    val context = LocalContext.current
    val regioes = context.resources.getStringArray(R.array.regiao_array).toList()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(5.dp)
    ) {
        Text(
            text = "Pesquisa de Interesse",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = uiState.nome,
            onValueChange = { viewModel.updateNome(it) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "nome") }
        )
        Spinner(
            regioes,
            uiState.regiao,
            onItemSelected = { viewModel.updateRegiao(it) }
        )
        RadioGroup(
            viewModel.faixas,
            indexOfSelected = uiState.faixaEtaria-1,
            onItemIndexSelected = { viewModel.updateFaixa(it+1) }
        )
        Row(
            Modifier.weight(1f)
        ) {
            ListOfCheckboxes(
                options = uiState.preferencias,
                checked = uiState.preferenciasMarcadas,
                onCheckedChange = { viewModel.updatePreferenciaMarcada(it) }
            )
        }

        Button(onClick = onGravarPesquisaClicked) {
            Text(text = "Gravar Pesquisa")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    PesquisaDeInteresseApp_kt2Theme {
        MainScreen()
    }
}