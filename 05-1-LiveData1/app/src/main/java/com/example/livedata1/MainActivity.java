package com.example.livedata1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.livedata1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // recupera uma instância do viewModel
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.getCounter().observe(this, inc -> {
            binding.incTextView.setText("Counter: " + inc);
        });

        binding.twoButton.setOnClickListener(v -> viewModel.twoPoints());
        binding.threeButton.setOnClickListener(v -> viewModel.threePoints());
        binding.freeButton.setOnClickListener(v -> viewModel.freeThrow());
        binding.zeroButton.setOnClickListener(v -> viewModel.restart());
        binding.rollbackButton.setOnClickListener(v -> viewModel.rollback());
    }
}