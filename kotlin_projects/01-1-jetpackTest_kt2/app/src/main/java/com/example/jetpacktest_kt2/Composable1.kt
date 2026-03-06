package com.example.jetpacktest_kt2

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Composable1() {
    var counter = 0
    var counter2 by rememberSaveable { mutableStateOf(0) }
    var enabled by rememberSaveable { mutableStateOf(false) }
    val configuration = LocalConfiguration.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyButton(
            label = "Hello",
            onClicked = {
                counter++
                Log.i("Composable1", "counter: " + counter)
                counter2++
                Log.i("Composable1", "counter2: " + counter2)
            }
        )
        MyButton(
            label = "Enable/Disable",
            hasIcon = true,
            marked = enabled,
            onClicked = { enabled = !enabled }
        )
        Text(
            text = ":: " + counter2
        )
        if (enabled) {
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                Column {
                    ImageList()
                }
            } else {
                Row {
                    ImageList()
                }
            }

        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Composable1Preview() {
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
        Composable1()
//    }
}