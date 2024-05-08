package com.example.boardofmessageapp_kt2.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boardofmessageapp_kt2.ui.theme.BoardOfMessageApp_kt2Theme

@OptIn
@Composable
fun BoardMessages(
    mainViewModel: MainViewModel = viewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)
    ) {
        OutlinedTextField(
            value = mainViewModel.msg_boardID,
            label = { Text(text = "Board ID") },
            onValueChange = { mainViewModel.updateMsg_boardID(it) },
            isError = mainViewModel.msg_boardIdError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if (mainViewModel.msg_boardIdError) {
            Text(
                text = mainViewModel.errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Button(onClick = { mainViewModel.loadMessages() }) {
            Text(text = "Show Messages")
        }
        Spacer(modifier = Modifier.size(10.dp))
        LazyColumn {
            itemsIndexed(mainViewModel.messages) {index, it ->
                MessageCard(
                    position =  index + 1,
                    from = it.from,
                    to = it.from,
                    text = it.text,
                    timestamp = it.timestamp
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun BoardMessagesPreview() {
    BoardOfMessageApp_kt2Theme {
        BoardMessages()
    }
}