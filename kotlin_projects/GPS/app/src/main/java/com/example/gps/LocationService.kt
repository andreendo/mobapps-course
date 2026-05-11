package com.example.gps

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices

object LocationService {

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(
        context: Context,
        onSuccess: (Double, Double) -> Unit,
        onError: (String) -> Unit
    ) {

        val client = LocationServices.getFusedLocationProviderClient(context)

        client.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    onSuccess(location.latitude, location.longitude)
                } else {
                    onError("Localização não disponível")
                }
            }
            .addOnFailureListener {
                onError("Erro ao obter localização")
            }
    }

}
