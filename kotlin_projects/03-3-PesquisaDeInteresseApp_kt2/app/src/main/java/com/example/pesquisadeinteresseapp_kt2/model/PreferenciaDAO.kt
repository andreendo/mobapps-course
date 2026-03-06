package com.example.pesquisadeinteresseapp_kt2.model

class PreferenciaDAO {
    val listOfPrefs: MutableList<Preferencia> = mutableListOf()

    init {
        // faixa 1
        listOfPrefs.add(Preferencia(1, "Hello Kitty", 1))
        listOfPrefs.add(Preferencia(2, "Ben 10", 1))
        listOfPrefs.add(Preferencia(3, "Rebeldes", 1))
        listOfPrefs.add(Preferencia(4, "Chiquititas", 1))
        listOfPrefs.add(Preferencia(5, "Transformers", 1))

        // faixa 2
        listOfPrefs.add(Preferencia(6, "Game of Thrones", 2))
        listOfPrefs.add(Preferencia(7, "Dragon Ball", 2))
        listOfPrefs.add(Preferencia(8, "Jaspion", 2))

        // faixa 3
        listOfPrefs.add(Preferencia(9, "Pantanal", 3))
        listOfPrefs.add(Preferencia(10, "Faustao", 3))
        listOfPrefs.add(Preferencia(11, "Zorra Total", 3))
        listOfPrefs.add(Preferencia(12, "Ta no Ar", 3))
    }

    fun getByFaixa(faixa: Int): List<Preferencia> {
        val res = listOfPrefs.filter { it.faixaEtaria == faixa }
        return res.toList()
    }
}