package com.example.pesquisadeinteresseapp_kt2.model

import org.junit.Test

import org.junit.Assert.*


class PreferenciaDAOTest {

    @Test
    fun getByFaixa() {
        val dao = PreferenciaDAO()
        val res = dao.getByFaixa(1)
        assertEquals(5, res.size)
        assertEquals("Hello Kitty", res[0].nome)
    }
}