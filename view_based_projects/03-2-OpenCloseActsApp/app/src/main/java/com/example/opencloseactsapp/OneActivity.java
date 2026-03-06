package com.example.opencloseactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.opencloseactsapp.databinding.ActivityOneBinding;

public class OneActivity extends AppCompatActivity {

    ActivityOneBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.exitButton3.setOnClickListener(view -> OneActivity.this.finish());

        String comeFrom = getResources().getString(R.string.come_from_label);
        String originAct = getIntent().getStringExtra("origin");
        if (originAct == null)
            originAct = getResources().getString(R.string.unknow_act);

        binding.comeFromTextView.setText(comeFrom + " " + originAct);
    }
}