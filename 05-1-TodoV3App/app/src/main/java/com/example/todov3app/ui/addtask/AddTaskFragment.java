package com.example.todov3app.ui.addtask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.todov3app.databinding.FragmentAddTaskBinding;
import com.example.todov3app.ui.MainViewModel;

public class AddTaskFragment extends Fragment {

    private FragmentAddTaskBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MainViewModel mainViewModel =
                new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding = FragmentAddTaskBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.addNewButton.setOnClickListener(view -> {
            mainViewModel.addTask(
                    binding.taskNameEditText.getText().toString(),
                    binding.taskDescEditText.getText().toString()
            );
            getActivity().onBackPressed();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}