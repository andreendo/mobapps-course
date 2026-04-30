package com.example.maps

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
        try {
            val client = LocationServices.getFusedLocationProviderClient(context)

            client.getCurrentLocation(
                com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
                null
            ).addOnSuccessListener { location ->

                if (location != null) {
                    onSuccess(location.latitude, location.longitude)
                } else {
                    onError("Não foi possível obter localização atual")
                }

            }.addOnFailureListener {
                onError("Erro ao obter localização")
            }

        } catch (e: SecurityException) {
            onError("Permissão não concedida")
        }
    }
}