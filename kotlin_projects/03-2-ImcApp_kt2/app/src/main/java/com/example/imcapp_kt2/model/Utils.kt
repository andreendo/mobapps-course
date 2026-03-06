package com.example.imcapp_kt2.model

fun calculateIMC(height: Float, weight: Float): Float {
    return weight / (height * height)
}
