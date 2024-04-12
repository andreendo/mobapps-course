package com.example.pesquisadeinteresseapp_kt2.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pesquisadeinteresseapp_kt2.ui.theme.PesquisaDeInteresseApp_kt2Theme

@Composable
fun ListOfCheckboxes(
    options: List<String>,
    checked: List<Boolean>,
    onCheckedChange: (index: Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        options.forEachIndexed { index, option ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checked[index],
                    onCheckedChange = { onCheckedChange(index) })
                Text(text = option)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListOfCheckboxesPreview() {
    PesquisaDeInteresseApp_kt2Theme {
        ListOfCheckboxes(
            options = listOf("Mango","Banana","Apple","Peach"),
            checked = listOf(true, true, false, true)
        )
    }
}