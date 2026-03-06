package com.example.checkweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.example.checkweatherapp.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.getCity().observe(this, cityName -> {
            if (cityName.equals("")) {
                binding.cityTextView.setText("");
                binding.tempTextView.setText("");
            } else {
                binding.cityTextView.setText(String.format(getString(R.string.city_label), cityName));
            }
        });
        viewModel.getTemperature().observe(this, temp -> {
            binding.tempTextView.setText(String.format(getString(R.string.city_temp), temp));
        });
        viewModel.getErrorMessage().observe(this, errorMsg -> {
            Snackbar.make(binding.constraintLayout,"Error: " + errorMsg, Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", v -> {})
                    .show();
        });

        binding.getButton.setOnClickListener(v -> {
            viewModel.getWeatherFor(MainActivity.this.isNetworkAvailable(), "São Carlos");
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null
                && activeNetworkInfo.isConnectedOrConnecting();
    }
}