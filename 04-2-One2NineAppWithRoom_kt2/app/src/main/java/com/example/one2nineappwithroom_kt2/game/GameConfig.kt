package com.example.one2nineappwithroom_kt2.game


class GameConfig {
    private val configurations: MutableList<List<Number>> = mutableListOf()
    private var current: Int = 0

    init {
        configurations.add(getNumberNumber())
//        configurations.add(getNumberRoman())
        configurations.add(getNumberEnglish())
        reset()
    }

    public fun reset() {
        current = 0
    }

    private fun getNumberNumber(): List<Number> {
        return listOf(
            Number(1, "1"),
            Number(2, "2"),
            Number(3, "3"),
            Number(4, "4"),
            Number(5, "5"),
            Number(6, "6"),
            Number(7, "7"),
            Number(8, "8"),
            Number(9, "9")
        )
    }

    private fun getNumberRoman(): List<Number> {
        return listOf(
            Number(1, "I"),
            Number(2, "II"),
            Number(3, "III"),
            Number(4, "IV"),
            Number(5, "V"),
            Number(6, "VI"),
            Number(7, "VII"),
            Number(8, "VIII"),
            Number(9, "IX")
        )
    }

    private fun getNumberEnglish(): List<Number> {
        return listOf(
            Number(1, "one"),
            Number(2, "two"),
            Number(3, "three"),
            Number(4, "four"),
            Number(5, "five"),
            Number(6, "six"),
            Number(7, "seven"),
            Number(8, "eight"),
            Number(9, "nine")
        )
    }

    fun getNextConfiguration(): List<Number> {
        val ret = configurations[current].toMutableList()
        ret.shuffle()
        current++
        return ret
    }

    fun hasNext(): Boolean {
        return current < configurations.size
    }
}