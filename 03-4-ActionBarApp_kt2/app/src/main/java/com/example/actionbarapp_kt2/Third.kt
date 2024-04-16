package com.example.actionbarapp_kt2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.actionbarapp_kt2.ui.theme.ActionBarApp_kt2Theme

@Composable
fun Third(
    onPreviousButtonClicked: () -> Unit = {},
    onCloseButtonClicked: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Third Screen",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Button(onClick = onPreviousButtonClicked) {
            Text(text = "Previous")
        }
        Button(onClick = onCloseButtonClicked) {
            Text(text = "Close")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThirdPreview() {
    ActionBarApp_kt2Theme {
        Third()
    }
}