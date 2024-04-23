package com.example.jetpacktest_kt2

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val f1 = { a: Int ->
            val c = a + 10
            c
        }

        val f2 = { a: Int, b: Int ->
            val c = a + b
            val msg = "Hello %d"
            String.format(msg, c)
        }

        val f4 = { a: Int, b: Int ->
            val c = a + b
            if (c >= 10) {
                val msg = "Hello %d"
                String.format(msg, c)
            } else {
                c
            }
        }

        val res: Any? = f4(4, 5)
        println("F4")
        if (res is Int) {
            val total = 100 + res
            println("Res: $total")
        }

        fun f3(): String {
            return "KKK"
        }

        assertEquals(12, f1(2))
        assertEquals("Hello 5", f2(2, 3))
        assertEquals("KKK", f3())
    }


}