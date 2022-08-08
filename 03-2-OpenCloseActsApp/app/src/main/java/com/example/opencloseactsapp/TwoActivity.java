package com.example.opencloseactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.opencloseactsapp.databinding.ActivityTwoBinding;

public class TwoActivity extends AppCompatActivity {

    private ActivityTwoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTwoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.exitButton2.setOnClickListener(v -> TwoActivity.this.finish());

        binding.oneButton2.setOnClickListener(view -> {
            Intent i = new Intent(TwoActivity.this, OneActivity.class);
            i.putExtra("origin", getResources().getString(R.string.two_title));
            startActivity(i);
        });
    }
}