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

import com.example.one2nineapp.databinding.ActivityGameBinding;
import com.example.one2nineapp.game.GameConfig;
import com.example.one2nineapp.game.Number;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;
    private List<Button> buttons;
    GameConfig gameConfig = new GameConfig();
    List<Number> currentListOfNumbers;
    int nextNumberMustBe;
    long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        String msg = String.format(getString(R.string.dlg_time_msg), yourTime);

        //verify if it is a new high score
        SharedPreferences myPreferences = getSharedPreferences(Constants.PREFS_FILE_NAME, Context.MODE_PRIVATE);
        float bestTime = myPreferences.getFloat(Constants.PREFS_TIME, Float.MAX_VALUE);

        if(yourTime < bestTime) {	//update best score
            SharedPreferences.Editor editor = myPreferences.edit();
            editor.putFloat(Constants.PREFS_TIME, yourTime);
            editor.putString(Constants.PREFS_PLAYER, "Anonymous");	// to improve - user can write her name
            String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
            editor.putString(Constants.PREFS_WHEN, currentDate);
            editor.apply();
            msg += "\n" + getString(R.string.dlg_new_record_msg);
        }

        showDialog(msg);
    }

    private void showDialog(String message) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(GameActivity.this);
        alertBuilder.setTitle(R.string.dlg_title);
        alertBuilder.setMessage(message);
        alertBuilder.setCancelable(false);
        final EditText editText = new EditText(this); //
        alertBuilder.setView(editText); //
        alertBuilder.setPositiveButton(R.string.dlg_ok, (dialog, which) -> {
            String playerName = editText.getText().toString();
            GameActivity.this.finish();
        });
        alertBuilder.create().show();
    }
}