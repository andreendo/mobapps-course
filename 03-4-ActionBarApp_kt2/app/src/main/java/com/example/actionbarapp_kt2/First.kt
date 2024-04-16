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
fun First(
    onNext1Clicked: () -> Unit = {},
    onNext2Clicked: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "First Screen",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Button(onClick = onNext1Clicked) {
            Text(text = "Next")
        }
        Button(onClick = onNext2Clicked) {
            Text(text = "Next 2")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstPreview() {
    ActionBarApp_kt2Theme {
        First()
    }
}