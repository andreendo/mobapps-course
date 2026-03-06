package com.example.opencloseactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.opencloseactsapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.oneButton.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, OneActivity.class);
            i.putExtra("origin", getResources().getString(R.string.main_title));
            startActivity(i);
        });

        binding.twoButton.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, TwoActivity.class);
            startActivity(i);
        });

        binding.exitButton.setOnClickListener(view -> {
            MainActivity.this.finish();
        });
    }
}