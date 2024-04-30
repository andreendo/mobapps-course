package com.example.myshoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myshoppinglist.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        binding.progressBar.setVisibility(View.VISIBLE);

        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        myViewModel.getShoppingList().observe(this, shoppingList -> {
            myRecyclerViewAdapter = new MyRecyclerViewAdapter(
                    MainActivity.this,
                    shoppingList
            );
            myRecyclerViewAdapter.setClickListener((view, position) -> {
                Toast.makeText(
                        this,
                        "You clicked " + myRecyclerViewAdapter.getItem(position) + " on row number " + position,
                        Toast.LENGTH_SHORT)
                        .show();
            });
            binding.recyclerView.setAdapter(myRecyclerViewAdapter);
//            binding.progressBar.setVisibility(View.GONE);
        });

        myViewModel.getShowProgressBar().observe(this, show -> {
            if (show)
                binding.progressBar.setVisibility(View.VISIBLE);
            else
                binding.progressBar.setVisibility(View.GONE);

        });
    }
}