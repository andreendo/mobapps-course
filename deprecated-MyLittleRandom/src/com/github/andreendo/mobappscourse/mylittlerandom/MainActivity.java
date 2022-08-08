package com.github.andreendo.mobappscourse.mylittlerandom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button bt_interval = (Button) findViewById(R.id.bt_interval);
        Button bt_manualList = (Button) findViewById(R.id.bt_manualList);
        Button bt_txtFile = (Button) findViewById(R.id.bt_txtFile);

        bt_interval.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, IntervalActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});
        
        bt_manualList.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ManualListActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});
        
        bt_txtFile.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, TextFileActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});
    }
}
