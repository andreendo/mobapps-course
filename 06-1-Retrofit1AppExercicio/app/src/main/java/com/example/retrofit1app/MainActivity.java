package com.example.retrofit1app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit1app.databinding.ActivityMainBinding;
import com.example.retrofit1app.service.AnimeQuote;
import com.example.retrofit1app.utils.CloseKeyboard;

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

        mainViewModel.getErrorMessage().observe(this, message -> showError(message));

        mainViewModel.getQuotes().observe(this, animeQuotes -> {
            for (AnimeQuote q : animeQuotes) {
                TextView tv = new TextView(MainActivity.this);
                tv.setText(q.getQuote() + "\n");
                binding.container.addView(tv);
            }
        });

        binding.newButton.setOnClickListener(v -> {
            String characterName = binding.personNameEditText.getText().toString();
            CloseKeyboard.closeKeyboard(MainActivity.this, binding.newButton);
            binding.container.removeAllViews();
            mainViewModel.getQuotesFor(characterName);
        });
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}