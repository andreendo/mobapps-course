package com.example.imcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.imcapp.databinding.ActivityConfirmBinding;
import com.example.imcapp.databinding.ActivityMainBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ConfirmActivity extends AppCompatActivity {

    private ActivityConfirmBinding binding;
    private PersonData personData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        personData = (PersonData) getIntent().getSerializableExtra("personData");
        Log.d("ConfirmActivity", personData.toString());
        binding.providedDataTextView.setText(personData.toString());

        binding.confirmButton.setOnClickListener(view -> {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(ConfirmActivity.this);
            builder.setTitle(R.string.imc);
            builder.setMessage("IMC = " + personData.getIMC());
            builder.show();
        });
    }
}