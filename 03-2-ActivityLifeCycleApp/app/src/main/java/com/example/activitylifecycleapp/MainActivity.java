package com.example.activitylifecycleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "Method onCreate called.");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("MainActivity", "Method onStart called.");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("MainActivity", "Method onResume called.");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("MainActivity", "Method onPause called.");
    }
}