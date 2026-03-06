package com.example.opencloseactsapp_kt2

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.opencloseactsapp_kt2.ui.theme.OpenCloseActsApp_kt2Theme

@Composable
fun ScreenTwo(
    onOpenScreen1Clicked: () -> Unit = {},
    onExitClicked: () -> Unit = {}
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Screen 2",
            style = MaterialTheme.typography.headlineMedium
        )
        Button(onClick = onOpenScreen1Clicked) {
            Text(text = "Open Screen 1")
        }
        Button(onClick = onExitClicked) {
            Text(text = "Exit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenTwoPreview() {
    OpenCloseActsApp_kt2Theme {
        ScreenTwo()
    }
}