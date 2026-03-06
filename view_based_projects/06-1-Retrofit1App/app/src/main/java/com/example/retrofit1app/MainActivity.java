package com.example.retrofit1app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;

import com.example.retrofit1app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getQuote().observe(this, binding.textView::setText);
        mainViewModel.getShowLoading().observe(this, show -> {
            binding.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        });

        binding.newButton.setOnClickListener(v -> mainViewModel.newQuote());
    }
}