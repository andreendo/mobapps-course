package com.example.boardofmessageapp_kt2.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boardofmessageapp_kt2.ui.theme.BoardOfMessageApp_kt2Theme

@Composable
fun InitialScreen(
    mainViewModel: MainViewModel = viewModel(),
    onBoardMessagesClicked: () -> Unit = {},
    onPostMessageClicked: () -> Unit = {},
    onDeleteBoardClicked: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            Button(onClick = { mainViewModel.getBoards() }) {
                Text(text = "Get Boards")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = onBoardMessagesClicked) {
                Text(text = "Board Messages")
            }
        }
        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            Button(onClick = onPostMessageClicked) {
                Text(text = "Post Message")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = onDeleteBoardClicked) {
                Text(text = "Delete Board")
            }
        }
        Text(text = mainViewModel.numberOfBoardsMsg)
        LazyColumn {
            items(mainViewModel.boards) {
                Row {
                    Text(text = "ID: " + it.id)
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(text = it.name)
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(text = "(${it.messages.size} message(s))")
                }
                
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InitialScreenPreview() {
    BoardOfMessageApp_kt2Theme {
        InitialScreen()
    }
}