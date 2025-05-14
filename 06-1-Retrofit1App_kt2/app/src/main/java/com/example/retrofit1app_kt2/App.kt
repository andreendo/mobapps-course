package com.example.retrofit1app_kt2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retrofit1app_kt2.ui.theme.Retrofit1App_kt2Theme

@Composable
fun App() {
    val viewModel = viewModel<MainViewModel>()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Button(onClick = { viewModel.newQuote() }) {
            Text(text = "New Quote")
        }
        if (viewModel.loading)
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        else
            Text(
                style = MaterialTheme.typography.headlineMedium,
                text = viewModel.quote
            )
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    Retrofit1App_kt2Theme {
        App()
    }
}