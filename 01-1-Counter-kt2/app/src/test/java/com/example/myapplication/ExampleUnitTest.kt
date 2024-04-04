package com.example.myapplication

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    fun greet() {
        println("Hello!")
    }

    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun greetPerson(name: String = "Guest") {
        println("Hello $name!")
    }

    // Infere o tipo de retorno
    fun multiply(a: Int, b: Int) = a * b

    val greetLambda: () -> Unit = {
        println("Hello from lambda!")
        println("Hello from lambda!")
        println("Hello from lambda!")
    }

    val addLambda: (Int, Int) -> Int = { a, b ->
        val sum = a + b
        sum
    }

    val squareLambda: (Int) -> Int = { it * it }

    fun f1() {
        val a1 = 5

        StringBuffer("return a1")
    }

    @Test
    fun addition_isCorrect() {
        var res = f1()
        println(res)
        assertEquals(4, 2 + 2)
    }
}