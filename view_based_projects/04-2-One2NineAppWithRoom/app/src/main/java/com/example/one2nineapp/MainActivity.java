package com.example.one2nineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.one2nineapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startButton.setOnClickListener(v -> openGame());
        binding.scoreButton.setOnClickListener(v -> openViewScore());
    }

    public void openViewScore() {
        Intent i = new Intent(MainActivity.this, ScoreActivity.class);
        startActivity(i);
    }

    public void openGame() {
        Intent i = new Intent(MainActivity.this, GameActivity.class);
        startActivity(i);
    }
}