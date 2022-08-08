package com.example.todov1app;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.todov1app.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Task> tasks = new ArrayList<>();
    TaskRecyclerViewAdapter taskRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addButton.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, AddTaskActivity.class);
            addTaskResultLauncher.launch(i);
        });

        binding.taskListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.taskListRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        taskRecyclerViewAdapter = new TaskRecyclerViewAdapter(MainActivity.this, tasks);
        binding.taskListRecyclerView.setAdapter(taskRecyclerViewAdapter);
        taskRecyclerViewAdapter.setClickListener((view, position) -> {
            Builder builder = new Builder(MainActivity.this);
            Task tTask = tasks.get(position);
            String tMsg = "Name: " + tTask.getName() + "\n" + "Description: " + tTask.getDescription();
            builder.setTitle("Did you finish the task?");
            builder.setMessage(tMsg);
            builder.setPositiveButton("Yes", (dialog, which) -> {
                tasks.remove(position);
                taskRecyclerViewAdapter.notifyDataSetChanged();
            });
            builder.setNegativeButton("No", null);
            builder.create().show();
        });
    }

    ActivityResultLauncher<Intent> addTaskResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Task tTask = (Task) data.getSerializableExtra("taskAdded");
                        Log.d("MainActivity", tTask.toString());
                        // update recycler view
                        tasks.add(tTask);
                        taskRecyclerViewAdapter.notifyDataSetChanged();
                    }
                }
            }
    );
}