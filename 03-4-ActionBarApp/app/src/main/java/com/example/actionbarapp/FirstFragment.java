package com.example.actionbarapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.actionbarapp.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Log.d("FirstFragment", "onCreateView");
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("FirstFragment", "onViewCreated");
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.buttonSecond.setOnClickListener(v -> {
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_thirdFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d("FirstFragment", "onDestroyView");
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d("FirstFragment", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("FirstFragment", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d("FirstFragment", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d("FirstFragment", "onStop");
    }
}