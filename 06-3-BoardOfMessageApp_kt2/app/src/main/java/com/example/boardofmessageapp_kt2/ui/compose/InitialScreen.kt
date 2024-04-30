package com.example.boardofmessageapp_kt2.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boardofmessageapp_kt2.ui.theme.BoardOfMessageApp_kt2Theme

@Composable
fun InitialScreen(
    mainViewModel: MainViewModel = viewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = { mainViewModel.getBoards() }) {
            Text(text = "Get Boards")
        }
        Text(text = mainViewModel.numberOfBoardsMsg)
    }
}

@Preview(showBackground = true)
@Composable
fun InitialScreenPreview() {
    BoardOfMessageApp_kt2Theme {
        InitialScreen()
    }
}