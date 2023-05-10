package com.example.form1_app_kt1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val proceedBt = findViewById<Button>(R.id.proceedButton)
        val cancelBt = findViewById<Button>(R.id.cancelButton)
        val firstNameET = findViewById<EditText>(R.id.firstName)
        val statusTV = findViewById<TextView>(R.id.statusTextView)

        cancelBt.setOnClickListener {
            firstNameET.setText("")
            statusTV.setText("")
        }

        proceedBt.setOnClickListener {
            val name = firstNameET.text.toString()
            if (name.isEmpty()) {
                // firstNameET.error = "Fill it!"
                firstNameET.error = getString(R.string.fill_it)
                return@setOnClickListener
            }

            statusTV.setText(R.string.form_data_saved_msg)
        }
    }
}