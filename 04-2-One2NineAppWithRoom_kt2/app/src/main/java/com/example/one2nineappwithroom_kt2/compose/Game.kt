package com.example.one2nineappwithroom_kt2.compose

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.one2nineappwithroom_kt2.R
import com.example.one2nineappwithroom_kt2.ui.theme.One2NineAppWithRoom_kt2Theme

@Composable
fun Game(
    gameViewModel: GameViewModel = viewModel(),
    onNavigateUp: () -> Unit = {}
) {
    gameViewModel.dialogTimeMessage = stringResource(id = R.string.dlg_time_msg)

    GameScreen(
        gameViewModel.numbers,
        gameViewModel.markers,
        onClick = { index, value -> gameViewModel.click(index, value) }
    )
    if (gameViewModel.showEndGameDialog) {
        EndGameDialog(
            dialogTitle = stringResource(id = R.string.dlg_title),
            dialogText = gameViewModel.endGameDialogText,
            textFieldValue = gameViewModel.playerName,
            onTextFieldValueChanged = { gameViewModel.playerName = it },
            onConfirmation = {
                gameViewModel.closeDialog()
                onNavigateUp()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GamePreview(
    modifier: Modifier = Modifier.fillMaxSize()
) {
    One2NineAppWithRoom_kt2Theme {
        Game()
    }
}