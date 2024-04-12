package com.example.pesquisadeinteresseapp_kt2.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pesquisadeinteresseapp_kt2.ui.theme.PesquisaDeInteresseApp_kt2Theme

@Composable
fun RadioGroup(
    radioOptions: List<String>,
    indexOfSelected: Int = 0,
    onItemIndexSelected: (Int) -> Unit = {}
) {
    var selectedOption by remember { mutableStateOf(indexOfSelected) }

    Column(
        modifier = Modifier.padding(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        radioOptions.forEachIndexed { index, radioOption ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = index == selectedOption,
                    onClick = {
                        selectedOption = index
                        onItemIndexSelected(index)
                    }
                )
                Text(
                    text = radioOption,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RadioGroupPreview() {
    PesquisaDeInteresseApp_kt2Theme {
        RadioGroup(radioOptions = listOf("Mango","Banana","Apple","Peach"))
    }
}