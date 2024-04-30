package com.example.one2nineappwithroom_kt2.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.one2nineappwithroom_kt2.R
import com.example.one2nineappwithroom_kt2.ui.theme.One2NineAppWithRoom_kt2Theme

@Composable
fun Init(
    onStartButtonClicked: () -> Unit = {},
    onViewScoresButtonClicked: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = stringResource(id = R.string.lbl_game),
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Button(onClick = onStartButtonClicked) {
            Text(text = stringResource(id = R.string.lbl_bt_start))
        }
        Button(onClick = onViewScoresButtonClicked) {
            Text(text = stringResource(id = R.string.lbl_bt_score))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InitPreview() {
    One2NineAppWithRoom_kt2Theme {
        Init()
    }
}