package com.example.popupmenuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.popupmenuapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.filterButton.setOnClickListener(view -> {
            // it attaches the popup with the UI component associated with variable 'view'
            PopupMenu popup = new PopupMenu(this, view);
            popup.getMenuInflater().inflate(R.menu.popup_filter, popup.getMenu());

            popup.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.item1) {
                    Toast.makeText(MainActivity.this, "Filter by keyword!!", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.item2) {
                    Toast.makeText(MainActivity.this, "Filter by popularity!!", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    return false;
                }
            });
            popup.show();
        });
    }
}