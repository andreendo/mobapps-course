package com.example.toastdialogapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.toastdialogapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // documentation: https://developer.android.com/topic/libraries/view-binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View rootView = binding.getRoot();
        setContentView(rootView);

        setupToastButtons();
        setupDialogButtons();
    }

    private void setupToastButtons() {
        binding.toast1Button.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this,
                    R.string.short_msg, Toast.LENGTH_SHORT).show();
        });

        binding.toast2Button.setOnClickListener(view -> {
            Toast toast = Toast.makeText(this,
                    R.string.long_msg, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        });
    }

    private void setupDialogButtons() {
        binding.exitButton.setOnClickListener(view -> {
            Builder alertDialogBuilder = new Builder(MainActivity.this);
            alertDialogBuilder.setTitle(R.string.exit_dialog_title);
            alertDialogBuilder.setMessage(R.string.exit_dialog_msg);
            alertDialogBuilder.setCancelable(false);

            alertDialogBuilder.setPositiveButton(R.string.yes, (dialog, which) -> {
                MainActivity.this.finish();
            });

            alertDialogBuilder.setNegativeButton(R.string.no, (dialog, which) -> {
                dialog.cancel();
            });

//            alertDialogBuilder.setOnCancelListener()

            alertDialogBuilder.create().show();
        });

        binding.emptyDialogButton.setOnClickListener(view -> {
            Builder alertDialogBuilder = new Builder(MainActivity.this);
            alertDialogBuilder.setTitle(R.string.error_title);
            alertDialogBuilder.setMessage(R.string.error2_dialog_msg);
            alertDialogBuilder.setPositiveButton(R.string.ok, null);
            alertDialogBuilder.show();
        });
    }
}