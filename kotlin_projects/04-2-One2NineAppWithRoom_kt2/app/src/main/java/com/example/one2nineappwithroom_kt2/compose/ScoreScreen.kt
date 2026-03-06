package com.example.one2nineappwithroom_kt2.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.one2nineappwithroom_kt2.R
import com.example.one2nineappwithroom_kt2.ui.theme.One2NineAppWithRoom_kt2Theme

@Composable
fun ScoreScreen(
    scoreViewModel: ScoreViewModel = viewModel(),
    onNavigateUp: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            Button(onClick = onNavigateUp) {
                Text(text = stringResource(id = R.string.lbl_bt_close))
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = { scoreViewModel.clearAllRecords() }) {
                Text(text = stringResource(id = R.string.lbl_bt_clear_all))
            }
        }
        Text(
            text = stringResource(id = R.string.lbl_l_bestTime),
            style = MaterialTheme.typography.headlineMedium
        )
        if (scoreViewModel.scores.size == 0)
            Text(
                text = stringResource(id = R.string.lbl_none_before),
                style = MaterialTheme.typography.bodyLarge
            )
        else
            LazyColumn {
                itemsIndexed(scoreViewModel.scores) {index, item ->
                    ScoreCard(
                        position = index+1,
                        name = item.playerName,
                        gameTime = item.time,
                        whenPlayed = item.whenPlayed
                    )
                }
            }
    }

}

@Preview(showBackground = true)
@Composable
fun ScorePreview() {
    One2NineAppWithRoom_kt2Theme {
        ScoreScreen()
    }
}