package com.example.retrofit1app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.retrofit1app.databinding.ActivityMainBinding;
import com.example.retrofit1app.service.AnimeQuote;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getShowLoading().observe(this, show -> {
            binding.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        });
        mainViewModel.getQuotes().observe(this, animeQuotes -> {
            for (AnimeQuote q : animeQuotes) {
                TextView tv = new TextView(MainActivity.this);
                tv.setText(q.getQuote() + "\n");
                binding.container.addView(tv);
            }
        });

        binding.newButton.setOnClickListener(v -> {
            String characterName = binding.personNameEditText.getText().toString();
            binding.container.removeAllViews();
            mainViewModel.getQuotesFor(characterName);
        });
    }
}