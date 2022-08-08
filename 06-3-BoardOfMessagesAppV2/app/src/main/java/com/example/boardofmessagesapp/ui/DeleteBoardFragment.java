package com.example.boardofmessagesapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.boardofmessagesapp.R;
import com.example.boardofmessagesapp.databinding.FragmentDeleteBoardBinding;
import com.example.boardofmessagesapp.databinding.FragmentPostMessageBinding;

public class DeleteBoardFragment extends Fragment {

    private FragmentDeleteBoardBinding binding;
    private MainViewModel mainViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mainViewModel.initDeleteBoardMessage();
        binding = FragmentDeleteBoardBinding.inflate(inflater, container, false);

        mainViewModel.getDeleteMessage().observe(getViewLifecycleOwner(), msg -> {
            if(!msg.equals(""))
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT)
                        .show();
        });

        binding.deleteButton.setOnClickListener(v -> {
            int iid = Integer.parseInt(binding.boardIdEditText.getText().toString());
            mainViewModel.deleteBoard(iid);

        });

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}