package com.example.sharedpreferencesclicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.sharedpreferencesclicksapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public static String CLICK_VALUE = "clicks";
    public static String CLICK_FILE_NAME = "click_file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences preferences = getSharedPreferences(CLICK_FILE_NAME, Context.MODE_PRIVATE);
        updateCounterTextView(preferences.getInt(CLICK_VALUE, 0));

        binding.button1.setOnClickListener(v -> {
            int clicks = preferences.getInt(CLICK_VALUE, 0) + 1;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(CLICK_VALUE, clicks);
            editor.apply();
            updateCounterTextView(clicks);
        });
    }

    public void updateCounterTextView(int counter) {
        binding.textView1.setText(
                String.format(getString(R.string.clicks_label), counter)
        );
    }
}