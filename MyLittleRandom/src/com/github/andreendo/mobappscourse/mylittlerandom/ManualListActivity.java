package com.github.andreendo.mobappscourse.mylittlerandom;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ManualListActivity extends Activity {
	
	ListView itensListView;
	ArrayList<String> itens;
	ArrayAdapter<String> aAdapter;
	public static int REQUEST_CODE = 20;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manual_list);
		
		itensListView = (ListView) findViewById(R.id.itensListView);
		itensListView.setOnItemClickListener(onItemListener);
		
		Button bt_add = (Button) findViewById(R.id.bt_add);
		bt_add.setOnClickListener(btAddListener);
		
		Button bt_randomSelect = (Button) findViewById(R.id.bt_randomSelect);
		bt_randomSelect.setOnClickListener(btSelectRandomListener);
		
		itens = new ArrayList<String>();
		aAdapter = new ArrayAdapter<String>(ManualListActivity.this, android.R.layout.simple_list_item_1, itens);
		itensListView.setAdapter(aAdapter);
	}
	
	private OnClickListener btSelectRandomListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ManualListActivity.this);
			String title = "", msg = "";
			
			if(itens.isEmpty()) {
				title = "Error";
				msg = "No item to select.";
			}
			else {
				MyRandom random = new MyRandom(0, itens.size()-1);
				int index = random.getRandomNumber();
				msg = "item: " + itens.get(index);
			}
			
            dialogBuilder.setTitle(title);
            dialogBuilder.setMessage(msg);
            dialogBuilder.setPositiveButton("OK", null);
            dialogBuilder.show();			
		}
	};	
	
	private OnClickListener btAddListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(ManualListActivity.this, ManualListAddActivity.class);
			startActivityForResult(intent, REQUEST_CODE);
		}
	};	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
			String newItem = data.getExtras().getString("newItem");
			itens.add(newItem);
			aAdapter.notifyDataSetChanged();			
		}
	}
	
	private OnItemClickListener onItemListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
				long id) {
			TextView clickedView = (TextView) view;
			itens.remove(position);
			aAdapter.notifyDataSetChanged();
			Toast.makeText(ManualListActivity.this, "Item ["+clickedView.getText()+"] removed", Toast.LENGTH_SHORT).show();
		}
	};
}
