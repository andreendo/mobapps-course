package com.example.todov3app.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.todov3app.databinding.FragmentHomeBinding;
import com.example.todov3app.model.Task;
import com.example.todov3app.ui.MainViewModel;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MainViewModel mainViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.taskListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.taskListRecyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        TasksAdapter tasksAdapter = new TasksAdapter();
        binding.taskListRecyclerView.setAdapter(tasksAdapter);
        tasksAdapter.setClickListener((view, position) -> showDialog(position));

        // mainViewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> tasksAdapter.submitList(tasks));
        mainViewModel.getTasks().observe(getViewLifecycleOwner(), tasksAdapter::submitList);

        return root;
    }

    public void showDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeFragment.this.getContext());
        Task tTask = mainViewModel.getTask(position);
        String tMsg = "Name: " + tTask.getName() + "\n" + "Description: " + tTask.getDescription();
        builder.setTitle("Did you finish the task?");
        builder.setMessage(tMsg);
        builder.setPositiveButton("Yes", (dialog, which) -> mainViewModel.removeTask(position));
        builder.setNegativeButton("No", null);
        builder.create().show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}