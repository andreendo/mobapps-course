package com.example.maps

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*

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
        modifier = Modifier.fillMaxSize()
    ) {

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
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Obter localização")
        }

        if (viewModel.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }

        viewModel.errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        }

        if (lat != null && lon != null) {

            val location = LatLng(lat, lon)

            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(location, 15f)
            }

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {

                Marker(
                    state = rememberUpdatedMarkerState(position = location),
                    title = "Você está aqui"
                )
            }

        } else {
            Text(
                text = "Localização não disponível",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}