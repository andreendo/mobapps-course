package com.example.gps

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

@Composable
fun LocationScreen(viewModel: AppViewModel) {

    val context = LocalContext.current

    var hasPermission by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasPermission = granted
    }

    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    val lat = viewModel.latitude
    val lon = viewModel.longitude

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "GPS Demo",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                if (!hasPermission) {
                    viewModel.setError("Permissão não concedida")
                    return@Button
                }

                viewModel.setLoadingState(true)

                LocationService.getCurrentLocation(
                    context,
                    onSuccess = { lat, lon ->
                        viewModel.setLocation(lat, lon)
                    },
                    onError = {
                        viewModel.setError(it)
                    }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Obter localização")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.isLoading) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
        }

        viewModel.errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (lat != null && lon != null) {

            Text("Latitude: $lat")
            Text("Longitude: $lon")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    openGoogleMaps(context, lat, lon)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Abrir no Google Maps")
            }

        } else {
            Text("Localização não disponível")
        }
    }
}

fun openGoogleMaps(context: Context, lat: Double, lon: Double) {

    val uri = "geo:$lat,$lon?q=$lat,$lon(Localização)".toUri()

    val intent = Intent(Intent.ACTION_VIEW, uri)

    intent.setPackage("com.google.android.apps.maps")

    context.startActivity(intent)
}

