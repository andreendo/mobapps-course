package com.example.todov1app;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.todov1app.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Task> tasks = new ArrayList<>();
    TaskRecyclerViewAdapter taskRecyclerViewAdapter;
    int currentPosition;
    ActionMode currentActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d("MainActivity", "onCreate");

        //se tiver uma instancia salva
        if(savedInstanceState != null) {
            tasks = (ArrayList<Task>) savedInstanceState.getSerializable("tasks");
            if (tasks == null) tasks = new ArrayList<>();
        }

        binding.addButton.setOnClickListener(view -> {
            //desabilita o action mode quando clica no botao add
            if(currentActionMode != null)
                currentActionMode.finish();

            Intent i = new Intent(MainActivity.this, AddTaskActivity.class);
            addTaskResultLauncher.launch(i);
        });

        binding.taskListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.taskListRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        taskRecyclerViewAdapter = new TaskRecyclerViewAdapter(MainActivity.this, tasks);
        binding.taskListRecyclerView.setAdapter(taskRecyclerViewAdapter);

        taskRecyclerViewAdapter.setLongClickListener((view, position) -> {
            if (currentActionMode != null)
                return;

            currentPosition = position;  // armazena o item atualmente selecionado
            currentActionMode = startActionMode(modeCallBack);
            view.setSelected(true);
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

    /**
     *
     * @param savedInstanceState
     *
     * Mudar a orientacao da tela nao perde os dados
     * comente este metodo e mude a orientacao com uma recyclerview preenchida
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("MainActivity", "onSaveInstanceState()");
        savedInstanceState.putSerializable("tasks", tasks);
    }

    /**
     * Classe anonima que implementa a interface ActionMode.Callback
     * usada para criar um context menu (usando o actionBar)
     */
    private ActionMode.Callback modeCallBack = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Actions");
            mode.getMenuInflater().inflate(R.menu.context_menu, menu);
            return true;
        }

        // chamado quando o usuario seleciona um item do contextual menu
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if(item.getItemId() == R.id.showItem) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                Task tTask = tasks.get(currentPosition);
                String tMsg = "Name: " + tTask.getName() + "\n" + "Description: " + tTask.getDescription();
                builder.setTitle("Task details");
                builder.setMessage(tMsg);
                builder.setPositiveButton("OK", null);
                builder.create().show();
                mode.finish();    //encerra o action mode
                return true;
            } else if (item.getItemId() == R.id.deleteItem) {
                tasks.remove(currentPosition);
                taskRecyclerViewAdapter.notifyDataSetChanged();
                mode.finish();    //encerra o action mode
                return true;
            } else if (item.getItemId() == R.id.toTopItem) {
                Task tTask = tasks.remove(currentPosition);
                tasks.add(0, tTask); // add it to the beginning of the list
                taskRecyclerViewAdapter.notifyDataSetChanged();
                mode.finish();    //encerra o action mode
                return true;
            } else if (item.getItemId() == R.id.toEndItem) {
                Task tTask = tasks.remove(currentPosition);
                tasks.add(tTask);
                taskRecyclerViewAdapter.notifyDataSetChanged();
                mode.finish();	//encerra o action mode
                return true;
            } else {
                return false;
            }
        }

        //chamado to vez que o action mode eh apresentado
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        //chamado quando o usuario sai do action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            currentActionMode = null; //limpa o current mode atual
        }
    };
}