package com.github.andreendo.mobappscourse.mylittlerandom;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class IntervalActivity extends Activity {
    
	private EditText fromEditText, toEditText;
    private Button genButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interval);
		
		retrieveGuiObjects();
	}
	
    private void retrieveGuiObjects() {
        fromEditText = (EditText) findViewById(R.id.fromEditText);
        toEditText = (EditText) findViewById(R.id.toEditText);
        genButton = (Button) findViewById(R.id.intervalGenbutton);
        genButton.setOnClickListener(onClickGenerateButtonListener);
    }
    
    public String checkFields() {
        String from = fromEditText.getText().toString();
        String to = toEditText.getText().toString();
        if(from.trim().equals("") || to.trim().equals(""))
            return "empty fields";

        int fromNum = Integer.valueOf(from);
        int toNum = Integer.valueOf(to);

        if(fromNum > toNum)
            return "\'From number\' is smaller than \'to number\'.";

        return "";
    }
    
    public int getRandomNumber() {
        int fromNum = Integer.valueOf(fromEditText.getText().toString());
        int toNum = Integer.valueOf(toEditText.getText().toString());
        MyRandom myRandom = new MyRandom(fromNum, toNum);
        return myRandom.getRandomNumber();
    }   
    
    /**
     * Private anonymous class to handle onClick event
     */
    private OnClickListener onClickGenerateButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
            String msg = checkFields();
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(IntervalActivity.this);
            if(! msg.equals("")) {
                dialogBuilder.setTitle("Error");
                dialogBuilder.setMessage(msg);
                dialogBuilder.setPositiveButton("OK", null);
                dialogBuilder.show();
            }
            else {
                dialogBuilder.setTitle("Done!");
                dialogBuilder.setMessage("Your number is " + getRandomNumber() + "!");
                dialogBuilder.setPositiveButton("OK", null);
                dialogBuilder.show();

            }

		}
	};
}
