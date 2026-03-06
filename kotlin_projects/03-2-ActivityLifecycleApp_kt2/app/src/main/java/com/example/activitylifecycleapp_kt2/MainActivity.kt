package com.example.activitylifecycleapp_kt2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.activitylifecycleapp_kt2.ui.theme.ActivityLifecycleApp_kt2Theme
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("MainActivity", "Metodo onCreate chamado.")

        setContent {
            ActivityLifecycleApp_kt2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("MainActivity", "Metodo onStart chamado.")
    }

    override fun onResume() {
        super.onResume()

        Log.d("MainActivity", "Metodo onResume chamado.")
    }

    override fun onPause() {
        super.onPause()

        Log.d("MainActivity", "Metodo onPause chamado.");
    }
}

@Composable
fun MainScreen() {
    var numberOfClicks by remember {
        mutableStateOf(0)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ClickCounter(clicks = numberOfClicks) {
            numberOfClicks++
        }
    }
}

@Composable
fun ClickCounter(clicks: Int, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("I've been clicked $clicks times")
    }
}


@Preview(showBackground = true)
@Composable
fun ClickCounterPreview() {
    ActivityLifecycleApp_kt2Theme {
        MainScreen()
    }
}