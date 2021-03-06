package com.example.notepad;

import utils.NotesDbAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Description extends Activity {
	private TextView tv;
	private EditText et;
	private MenuItem editMenu_button;
	private MenuItem addMenu_button;
	private NotesDbAdapter mDbHelper;
	
	private String des;
	private String TableName;
	private String title;
	private long idN;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_description);
		// get Title and Description
		getInfo_from_previousActivity();
		// setText and getText
		tv = (TextView) findViewById(R.id.textView1);
		et = (EditText) findViewById(R.id.editText1);
		// EditText make it invisible firsttime
		et.setVisibility(TextView.INVISIBLE);
		tv.setText(des);
		// open database manager
		mDbHelper = new NotesDbAdapter(this);
		mDbHelper.open();
		
		//change title
		ActionBar ac = getActionBar();
		ac.setTitle(title);
		//
	}

	private void getInfo_from_previousActivity() {
		Intent intent = getIntent();
		title = intent.getStringExtra("title");
		idN = intent.getLongExtra("id", 0);
		des = intent.getStringExtra("description");
		TableName = intent.getStringExtra("tableName");		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.description, menu);
		editMenu_button = menu.findItem(R.id.edit);
		addMenu_button = menu.findItem(R.id.ok);
		addMenu_button.setVisible(false);
		return true;
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch(item.getItemId()){
		case R.id.edit:
			editText();
			break;
		
		case R.id.ok:
			addText();
		}
		return super.onMenuItemSelected(featureId, item);
	}

	private void addText() {
		String des = et.getText().toString();
		mDbHelper.updateNote(idN, title, des, TableName);
		tv.setText(des);
		tv.setVisibility(View.VISIBLE);
		et.setVisibility(View.INVISIBLE);
		editMenu_button.setVisible(true);
		addMenu_button.setVisible(false);
		
	}

	private void editText() {
		et.setText(tv.getText().toString());
		et.setVisibility(EditText.VISIBLE);
		tv.setVisibility(TextView.INVISIBLE);
		editMenu_button.setVisible(false);
		addMenu_button.setVisible(true);
	}

}
