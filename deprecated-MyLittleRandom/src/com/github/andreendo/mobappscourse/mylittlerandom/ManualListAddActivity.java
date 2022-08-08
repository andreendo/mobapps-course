package com.github.andreendo.mobappscourse.mylittlerandom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManualListAddActivity extends Activity {
	EditText newItemEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manual_list_add);
		
		Button bt_add = (Button) findViewById(R.id.bt_addAdd);
		bt_add.setOnClickListener(onClickAddListener);
		Button bt_cancel = (Button) findViewById(R.id.bt_addCancel);
		bt_cancel.setOnClickListener(onClickCancelListener);
		
		newItemEditText = (EditText) findViewById(R.id.newItemEditText);
	}
	
	private OnClickListener onClickCancelListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();			
		}
	};
	
	private OnClickListener onClickAddListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String newItem = newItemEditText.getText().toString().trim();
			if(newItem.equals("")){
				Toast.makeText(ManualListAddActivity.this, "Invalid empty item", Toast.LENGTH_SHORT).show();
				newItemEditText.setText("");
			}
			else {
				Intent intent = getIntent();
				intent.putExtra("newItem", newItem);
				setResult(RESULT_OK, intent);
				finish();
			}
		}
	};

}
