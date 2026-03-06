package com.example.jetpacktest_kt2

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun ImageList() {
    //https://github.com/Foso/Jetpack-Compose-Playground/blob/master/app/src/main/res/drawable/composelogo.png
    repeat(3) {
        Image(
            painter = painterResource(id = R.drawable.composelogo),
            contentDescription = ""
        )
    }
}