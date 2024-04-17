package com.example.sharedpreferencesclicksapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sharedpreferencesclicksapp.ui.theme.SharedPreferencesClicksAppTheme

@Composable
fun App() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val fMessage = stringResource(id = R.string.clicked_msg)
        val viewModel = viewModel<AppViewModel>()
        viewModel.fMessage = fMessage

        Text(text = viewModel.currentMessage)
        Button(onClick = { viewModel.click() }) {
            Text(text = stringResource(id = R.string.click_here))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    SharedPreferencesClicksAppTheme {
        App()
    }
}