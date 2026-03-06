package com.example.one2nineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.one2nineapp.databinding.ActivityScoreBinding;

import java.text.DecimalFormat;

public class ScoreActivity extends AppCompatActivity {

    ActivityScoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences prefs = getSharedPreferences(Constants.PREFS_FILE_NAME, Context.MODE_PRIVATE);
        float bestTime = prefs.getFloat(Constants.PREFS_TIME, 0.0f);
        String player = prefs.getString(Constants.PREFS_PLAYER, getString(R.string.lbl_none_before));
        String when = prefs.getString(Constants.PREFS_WHEN, "");

        DecimalFormat df = new DecimalFormat("#.00");
        binding.bestTimeTextView.setText(df.format(bestTime));
        binding.playedByTextView.setText(player + "\n" + when);

        binding.closeButton.setOnClickListener(v -> ScoreActivity.this.finish());
    }
}