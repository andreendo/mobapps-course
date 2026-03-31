package com.example.jetpacktest_kt2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImageList() {
    // https://github.com/Foso/Jetpack-Compose-Playground/blob/master/app/src/main/res/drawable/composelogo.png
    repeat(3) {
        Image(
            painter = painterResource(id = R.drawable.composelogo),
            contentDescription = "",
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
        )
    }
}