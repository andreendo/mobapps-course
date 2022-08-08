package com.example.boardofmessagesapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.boardofmessagesapp.R;
import com.example.boardofmessagesapp.databinding.FragmentHomeBinding;
import com.example.boardofmessagesapp.repository.Board;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MainViewModel mainViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel.getBoards().observe(getViewLifecycleOwner(), boards -> {
            binding.numberOfBoardsTextView.setText("#Boards: " + boards.size());
            StringBuffer toPrint = new StringBuffer();
            for (Board board: boards) {
                toPrint.append(
                        String.format(
                                "%d - %s: %d messages.\n",
                                board.getId(),
                                board.getName(),
                                board.getMessages().size()
                        )
                );
            }
            binding.boardsTextView.setText(toPrint.toString());
        });

        binding.getBoardButton.setOnClickListener(v -> {
            mainViewModel.collectBoards();
        });

        binding.boardMsgsButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(HomeFragment.this)
                    .navigate(R.id.action_HomeFragment_to_showMessagesFragment);
        });
        binding.postMsgbutton.setOnClickListener(v -> {
            NavHostFragment.findNavController(HomeFragment.this)
                    .navigate(R.id.action_FirstFragment_to_postMessageFragment);
        });
        binding.delBoardButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(HomeFragment.this)
                    .navigate(R.id.action_HomeFragment_to_deleteBoardFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}