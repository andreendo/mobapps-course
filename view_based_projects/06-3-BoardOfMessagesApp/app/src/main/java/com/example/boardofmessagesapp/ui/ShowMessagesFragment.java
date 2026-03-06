package com.example.boardofmessagesapp.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boardofmessagesapp.R;
import com.example.boardofmessagesapp.databinding.FragmentDeleteBoardBinding;
import com.example.boardofmessagesapp.databinding.FragmentShowMessagesListBinding;
import com.example.boardofmessagesapp.ui.placeholder.PlaceholderContent;

public class ShowMessagesFragment extends Fragment {

    private FragmentShowMessagesListBinding binding;
    private MainViewModel mainViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentShowMessagesListBinding.inflate(inflater, container, false);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        MessageRecyclerViewAdapter messageAdapter = new MessageRecyclerViewAdapter();
        binding.recyclerView.setAdapter(messageAdapter);

        mainViewModel.getMessages().observe(getViewLifecycleOwner(), messageAdapter::submitList);
        mainViewModel.getCurrentBoardId().observe(getViewLifecycleOwner(), iid -> {
            if (iid != null) {
                binding.boardIdEditText.setText(iid.toString());
            }
        });

        binding.showMessagesButton.setOnClickListener(v -> {
            int iid = Integer.parseInt(binding.boardIdEditText.getText().toString());
            mainViewModel.showMessagesFor(iid);
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