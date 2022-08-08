package com.example.one2nineapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.one2nineapp.databinding.ActivityScoreBinding;
import com.example.one2nineapp.persistence.Score;
import com.example.one2nineapp.persistence.ScoreDao;
import com.example.one2nineapp.persistence.ScoreDatabase;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ScoreActivity extends AppCompatActivity {

    ActivityScoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ScoreDao scoreDao = ScoreDatabase.getInstance(this).scoreDao();
        scoreDao.getAllScores()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(scores -> {
                    binding.container.removeAllViews();
                    for (Score score : scores) {
                        TextView textView = new TextView(ScoreActivity.this);
                        textView.setText("  * " + score.toString());
                        binding.container.addView(textView);
                    }
                });

        binding.closeButton.setOnClickListener(v -> ScoreActivity.this.finish());
        binding.clearAllButton.setOnClickListener(v -> {
            scoreDao.deleteAllScores()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(rows -> {
                        Snackbar.make(
                                binding.layout,
                                String.format(getString(R.string.lbl_clear_all), rows),
                                Snackbar.LENGTH_SHORT)
                                .show();
                    }, error -> {
                        // may deal with an exception here.
                    });
        });
    }
}