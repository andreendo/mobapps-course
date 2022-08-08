package com.example.form1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button proceedBt;
    Button cancelBt;
    EditText firstNameET;
    TextView statusTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        proceedBt = (Button) findViewById(R.id.proceedButton);
        cancelBt = (Button) findViewById(R.id.cancelButton);
        firstNameET = (EditText) findViewById(R.id.firstName);
        statusTV = (TextView) findViewById(R.id.statusTextView);

        cancelBt.setOnClickListener(view -> {
            firstNameET.setText("");
        });

        proceedBt.setOnClickListener(view -> {
            String name = firstNameET.getText().toString();
            if (name.equals("")) {
                firstNameET.setError("Fill it!");
                return;
            }

            statusTV.setText(R.string.form_data_saved_msg);
        });
    }
}