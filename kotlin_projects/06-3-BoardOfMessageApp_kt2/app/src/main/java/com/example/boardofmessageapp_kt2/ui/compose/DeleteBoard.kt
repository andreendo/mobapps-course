package com.example.boardofmessageapp_kt2.ui.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boardofmessageapp_kt2.ui.theme.BoardOfMessageApp_kt2Theme

@Composable
fun DeleteBoard(
    mainViewModel: MainViewModel = viewModel()
) {
    Text(text = "Delete Board")
}

@Preview(showBackground = true)
@Composable
fun DeleteBoardPreview() {
    BoardOfMessageApp_kt2Theme {
        DeleteBoard()
    }
}