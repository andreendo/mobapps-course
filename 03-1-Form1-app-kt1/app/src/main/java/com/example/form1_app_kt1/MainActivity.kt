package com.example.form1_app_kt1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

// use ':' instead of (Java) extends
class MainActivity : AppCompatActivity() {

    // use 'fun' to declare a method or function. Param1: Type1
    // Type? - use '?' to define a nullable type: a variable may be of Type or null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // val used to declare immutable variables in Kotlin
        val proceedBt = findViewById<Button>(R.id.proceedButton)
        val cancelBt = findViewById<Button>(R.id.cancelButton)
        val firstNameET = findViewById<EditText>(R.id.firstName)
        val statusTV = findViewById<TextView>(R.id.statusTextView)

        cancelBt.setOnClickListener {
            Log.i("MainActivity", "clicked in Cancel.")
            // 'it' is implicitly declared as var name for lambda with one parameter
            Log.i("MainActivity", "clicked in Cancel." + it.toString())
            firstNameET.setText("")
            statusTV.setText("")
        }

        proceedBt.setOnClickListener {
            Log.i("MainActivity", "clicked in Proceed.")
            val name = firstNameET.text.toString()
            if (name.isEmpty()) {
                firstNameET.error = getString(R.string.fill_it)
                return@setOnClickListener   // return for the outer lambda setOnClickListener
            }

            statusTV.setText(R.string.form_data_saved_msg)
        }
    }
}