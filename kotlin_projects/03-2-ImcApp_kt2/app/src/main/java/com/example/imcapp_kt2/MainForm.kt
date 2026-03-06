package com.example.imcapp_kt2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imcapp_kt2.ui.theme.ImcApp_kt2Theme

@Composable
fun MainForm(
    name: String = "",
    age: String = "",
    height: String = "",
    weight: String = "",
    onNameChanged: (String) -> Unit = {},
    onAgeChanged: (String) -> Unit = {},
    onHeightChanged: (String) -> Unit = {},
    onWeightChanged: (String) -> Unit = {},
    onCalculateButtonClicked: () -> Unit = {}
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.main_form),
            style = MaterialTheme.typography.headlineMedium
        )
        Divider(thickness = 2.dp)
        OutlinedTextField(
            value = name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            onValueChange = onNameChanged,
            label = { Text(text = stringResource(id = R.string.name)) }
        )
        OutlinedTextField(
            value = age,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            onValueChange = onAgeChanged,
            label = { Text(text = stringResource(id = R.string.age)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = height,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            onValueChange = onHeightChanged,
            label = { Text(text = stringResource(id = R.string.height)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = weight,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            onValueChange = onWeightChanged,
            label = { Text(text = stringResource(id = R.string.weight)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = onCalculateButtonClicked) {
            Text(text = stringResource(id = R.string.calculate))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainFormPreview() {
    ImcApp_kt2Theme {
        MainForm()
    }
}