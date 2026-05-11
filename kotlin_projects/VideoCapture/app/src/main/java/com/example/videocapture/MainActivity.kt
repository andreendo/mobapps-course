package com.example.videocapture

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = remember { AppViewModel() }

            CameraVideoScreen(viewModel)
        }
    }
}