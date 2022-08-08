package com.example.one2nineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.one2nineapp.databinding.ActivityGameBinding;
import com.example.one2nineapp.game.GameConfig;
import com.example.one2nineapp.game.Number;
import com.example.one2nineapp.persistence.Score;
import com.example.one2nineapp.persistence.ScoreDao;
import com.example.one2nineapp.persistence.ScoreDatabase;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;
    private List<Button> buttons;
    GameConfig gameConfig = new GameConfig();
    List<Number> currentListOfNumbers;
    int nextNumberMustBe;
    long startTime;
    ScoreDao scoreDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        scoreDao = ScoreDatabase.getInstance(this).scoreDao();

        retrieveButtons();
        startNewRound();
        startTime = System.currentTimeMillis();
    }

    private void retrieveButtons() {
        buttons = Arrays.asList(
                binding.button1, binding.button2, binding.button3,
                binding.button4, binding.button5, binding.button6,
                binding.button7, binding.button8, binding.button9
        );
        buttons.forEach(b -> b.setOnClickListener(buttonClickListener));
    }

    private void startNewRound() {
        currentListOfNumbers = gameConfig.getNextConfiguration();
        for (int i = 0; i < buttons.size(); i++) {
            Button bt = buttons.get(i);
            bt.setTextColor(Color.BLACK);
            bt.setBackgroundColor(Color.WHITE);
            bt.setText( currentListOfNumbers.get(i).getLabel() );
        }

        nextNumberMustBe = 1;
    }

    private View.OnClickListener buttonClickListener = v -> {
        //identify which button (index) was clicked
        int clickedButton = 0;
        for (int i = 0; i < buttons.size(); i++) {
            Button bt = buttons.get(i);
            if (v.getId() == bt.getId()) {
                clickedButton = i;
                break;
            }
        }

        //check value
        Number number = currentListOfNumbers.get(clickedButton);
        Button btCurrent = buttons.get(clickedButton);
        if (number.getValue() == nextNumberMustBe) {
            btCurrent.setBackgroundColor(Color.CYAN);
            nextNumberMustBe++;
        }

        //clicked correctly 9 times, phase ends
        if (nextNumberMustBe == 10) {
            checkEnd();
        }
    };

    private void checkEnd() {
        if(gameConfig.hasNext()) {
            startNewRound();
        }
        else {
            endGame();
        }
    }

    private void endGame() {
        long endTime = System.currentTimeMillis();
        float yourTime = (endTime - startTime) / 1000.0f;
        // say that is a new record
        scoreDao.getBestScore()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(score -> {
                    showDialog(yourTime, yourTime < score.getTime());
                }, error -> {
                    // empty
                    showDialog(yourTime, true);
                });
    }

    private void showDialog(float yourTime, boolean newRecord) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(GameActivity.this);
        alertBuilder.setTitle(R.string.dlg_title);
        String message = String.format(getString(R.string.dlg_time_msg), yourTime);
        message += newRecord ? "\nNEW RECORD!!!!" : "";
        alertBuilder.setMessage(message);
        alertBuilder.setCancelable(false);
        final EditText editText = new EditText(this); //
        alertBuilder.setView(editText); //
        alertBuilder.setPositiveButton(R.string.dlg_ok, (dialog, which) -> {
            String playerName = editText.getText().toString();
            if (playerName.trim().equals(""))
                playerName = "anonymous";
            String when = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
            saveScore(playerName, yourTime, when);
        });
        alertBuilder.create().show();
    }

    private void saveScore(String playerName, double time, String when) {
        Score newScore = new Score(playerName, time, when);
        scoreDao.insertScore(newScore)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    // close it when the insertion is finished
                    GameActivity.this.finish();
                });
    }
}