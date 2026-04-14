package com.example.sharedpreferencesclicksapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sharedpreferencesclicksapp.ui.theme.SharedPreferencesClicksAppTheme

@Composable
fun App(viewModel: AppViewModel = viewModel()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        CounterLabel(counter = viewModel.counter)
        Button(onClick = viewModel::click) {
            Text(text = stringResource(id = R.string.click_here))
        }
    }
}

@Composable
fun CounterLabel(counter: Int) {
    val text = if (counter % 10 == 0) {
        stringResource(id = R.string.no_clicked_msg)
    } else {
        stringResource(id = R.string.clicked_msg, counter)
    }

    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        textAlign = TextAlign.Center
    )
 }


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    SharedPreferencesClicksAppTheme {
        App()
    }
}