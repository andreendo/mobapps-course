package com.example.boardofmessagesapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.boardofmessagesapp.databinding.FragmentPostMessageBinding;

public class PostMessageFragment extends Fragment {

    private FragmentPostMessageBinding binding;
    private MainViewModel mainViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = FragmentPostMessageBinding.inflate(inflater, container, false);

        mainViewModel.initAddPostMessage();
        mainViewModel.getAddMessageError().observe(getViewLifecycleOwner(), error -> {
            if (error.equals(""))
                return;

            if (error.equals("success")) {
                requireActivity().onBackPressed();
            } else {
                binding.boardIdEditText.setError(error);
            }
        });

        binding.postButton.setOnClickListener(v -> {
            int iid = -1;
            if (!binding.boardIdEditText.getText().toString().trim().equals(""))
                iid = Integer.parseInt(binding.boardIdEditText.getText().toString());

            String from = binding.fromEditText.getText().toString();
            String to = binding.toEditText.getText().toString();
            String msg = binding.msgEditText.getText().toString();
            mainViewModel.postMessage(iid, from, to, msg);
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