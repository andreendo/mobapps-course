package com.example.imcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.imcapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.calcButton.setOnClickListener(view -> {
            String name = binding.nameEditText.getText().toString();
            int age = Integer.parseInt(
                    binding.ageEditText.getText().toString()
            );
            float height = Float.parseFloat(
                    binding.heightEditText.getText().toString()
            );
            float weight = Float.parseFloat(
                    binding.weightEditText.getText().toString()
            );
            PersonData personData = new PersonData(
                    name,
                    age,
                    height,
                    weight
            );

            Intent i = new Intent(MainActivity.this, ConfirmActivity.class);
            i.putExtra("personData", personData);
            startActivity(i);
        });
    }
}