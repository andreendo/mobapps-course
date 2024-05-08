package com.example.boardofmessageapp_kt2.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boardofmessageapp_kt2.ui.theme.BoardOfMessageApp_kt2Theme

@Composable
fun PostMessage(
    mainViewModel: MainViewModel = viewModel()
) {
    var boardId by rememberSaveable { mutableStateOf("") }
    var from by rememberSaveable { mutableStateOf("") }
    var to by rememberSaveable { mutableStateOf("") }
    var text by rememberSaveable { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)
    ) {
        OutlinedTextField(
            value = boardId,
            label = { Text(text = "Board ID") },
            onValueChange = { boardId = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        OutlinedTextField(
            value = from,
            label = { Text(text = "From") },
            onValueChange = { from = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        OutlinedTextField(
            value = to,
            label = { Text(text = "To") },
            onValueChange = { to = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        OutlinedTextField(
            value = text,
            label = { Text(text = "Message here") },
            onValueChange = { text = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = false,
            maxLines = 3
        )
        Button(onClick = { mainViewModel.postMessage(boardId, from, to, text) }) {
            Text(text = "Post Message")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostMessagePreview() {
    BoardOfMessageApp_kt2Theme {
        PostMessage()
    }
}