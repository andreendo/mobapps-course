package com.example.retrofit1app_kt2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.retrofit1app_kt2.ui.theme.Retrofit1App_kt2Theme
import org.conscrypt.Conscrypt
import java.security.Security

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // fix "Trust anchor not found" error for older Android versions
        // Not enough, it needs to download and include certificate
        // For now, use newer Android versions to test
        Security.insertProviderAt(Conscrypt.newProvider(), 1)

        setContent {
            Retrofit1App_kt2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

