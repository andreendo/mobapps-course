package com.example.roomapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.roomapp1.databinding.ActivityMainBinding;
import com.example.roomapp1.persistence.repo.ICategory;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = (new ViewModelProvider(this)).get(MainViewModel.class);
        mainViewModel.insertCategory("category 1");
        mainViewModel.insertCategory("category 2");

        mainViewModel.getCategories().observe(this, cats -> {
            StringBuffer toPrint = new StringBuffer();
            for(ICategory iCat : cats) {
                toPrint.append(iCat.toString() + "\n");
            }
            binding.textView.setText(toPrint.toString());
        });
    }
}