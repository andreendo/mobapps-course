package com.example.imcapp_kt2.model

import org.junit.Test

import org.junit.Assert.*

class UtilsTest {
    @Test
    fun test_calculateIMC() {
        assertEquals(24.22f, calculateIMC(1.70f, 70.0f), 0.01f)
    }
}