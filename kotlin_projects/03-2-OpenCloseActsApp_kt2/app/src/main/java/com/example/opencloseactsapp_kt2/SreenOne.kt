package com.example.opencloseactsapp_kt2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.opencloseactsapp_kt2.ui.theme.OpenCloseActsApp_kt2Theme

@Composable
fun ScreenOne(
    originScreen: String = "unknown",
    onExitClicked: () -> Unit = {}
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Screen One",
            style = MaterialTheme.typography.headlineMedium
        )
        Button(onClick = onExitClicked) {
            Text(text = "Exit")
        }
        Row {
            Text(
                text = "Come from: ",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = originScreen,
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ScreenOnePreview() {
    OpenCloseActsApp_kt2Theme {
        ScreenOne()
    }
}