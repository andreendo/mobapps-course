package com.example.actionbarapp;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.actionbarapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // recupera o navController para o host
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        // configura as modificações na actionBar enquanto navega-se entre os fragments
        // usa "android:label" em nav_graph.xml
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "selecionou settings", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(id == R.id.action_item1) {
//            Toast.makeText(this, "selecionou item 1", Toast.LENGTH_SHORT).show();
            NavController navController = Navigation
                    .findNavController(this, R.id.nav_host_fragment_content_main);
            if (navController.getCurrentDestination().getId() == R.id.FirstFragment)
                Toast.makeText(this, "Already in first.", Toast.LENGTH_SHORT).show();
            else
                navController.navigate(R.id.FirstFragment);
            return true;
        }
        else if(id == R.id.action_item2) {
//            Toast.makeText(this, "selecionou item 2", Toast.LENGTH_SHORT).show();
            NavController navController = Navigation
                    .findNavController(this, R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.SecondFragment);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // trata o up and back button
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}