package com.github.andreendo.mobappscourse.mylittlerandom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class TextFileActivity extends Activity {
	public static int FILE_SELECT_CODE = 20;
	
	ListView itensListView;
	ArrayList<String> itens;
	ArrayAdapter<String> aAdapter;
	Button bt_selectRandom;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_file);
		
		Button bt_selectFile = (Button) findViewById(R.id.addFilebutton);
		bt_selectFile.setOnClickListener(onBtSelectFileClickListener);
		
		itensListView = (ListView) findViewById(R.id.fileListView);
		itens = new ArrayList<String>();
		aAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens);
		itensListView.setAdapter(aAdapter);
		
		bt_selectRandom = (Button) findViewById(R.id.addSelectRandomButton);
		bt_selectRandom.setOnClickListener(onBtSelectRandomFileListener);
		bt_selectRandom.setEnabled(false);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == FILE_SELECT_CODE && resultCode == RESULT_OK) {
			Uri uri = data.getData();
			String path = uri.getPath();
			File file = new File(path);
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine()) != null) {
					itens.add(line);
				}
				br.close();
				//update listView
				aAdapter.notifyDataSetChanged();
				bt_selectRandom.setEnabled(true);
			} 
			catch (FileNotFoundException e) {
				Toast.makeText(this, "file not found", Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				Toast.makeText(this, "Error reading the file.", Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}
	
	private OnClickListener onBtSelectRandomFileListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			MyRandom random = new MyRandom(0, itens.size()-1);
			int index = random.getRandomNumber();
			AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TextFileActivity.this);
			dialogBuilder.setTitle("");
            dialogBuilder.setMessage("item: " + itens.get(index));
            dialogBuilder.setPositiveButton(R.string.lbl_ok, null);
            dialogBuilder.show();			
		}
	};
	
	private OnClickListener onBtSelectFileClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("*/*");
			intent.setType("text/plain");
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			
			//clear the listView
			itens.clear();
			aAdapter.notifyDataSetChanged();
			bt_selectRandom.setEnabled(false);
			
		    try {
		        startActivityForResult(Intent.createChooser(intent, "Select txt file"), FILE_SELECT_CODE);
		    } catch (android.content.ActivityNotFoundException ex) {
		        Toast.makeText(TextFileActivity.this, "Install a file manager, please.", Toast.LENGTH_SHORT).show();
		    }			
		}
	};
	

}
