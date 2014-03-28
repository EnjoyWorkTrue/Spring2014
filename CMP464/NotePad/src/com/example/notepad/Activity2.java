package com.example.notepad;

import utils.DialogButton;
import utils.NotesDbAdapter;
import utils.getCursor;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Activity2 extends Activity implements getCursor {
	private String intent_title = "title";
	private String intent_des = "description";
	int count = 0;
	NotesDbAdapter mDbHelper;
	private static SimpleCursorAdapter notes;
	private ListView lv;
	private String TableName;
	Context ctx; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ctx = this;
		setContentView(R.layout.activity_activity2);
		Intent intent = getIntent();
		TableName = intent.getStringExtra("Title");
		//change Title Name
		ActionBar ac = getActionBar();
		ac.setTitle(TableName);
		//////
		mDbHelper = new NotesDbAdapter(this);
		mDbHelper.open();
		mDbHelper.getTable(TableName);
		startListActivity();
		
	}
	private void startListActivity() {
		Cursor c = mDbHelper.fetchAllNotes(TableName);
		String[] from = new String[]{NotesDbAdapter.KEY_TITLE};
		int[] to = new int[] {android.R.id.text1 };
		notes = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,c,from, to);
		
		lv = (ListView) findViewById(R.id.titleList2);
		lv.setAdapter(notes);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
								Cursor c = mDbHelper.fetchNote(TableName,arg3);
								startActivity(c.getString(1),c.getString(2),arg3);
			}
		});
		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				DialogButton a = new DialogButton(ctx,TableName,arg3);
				a.createEdit();
				a.show();				
				return false;
			}
		
			
		});
	}
	private void startActivity(String title,String des,long idNum) {
		Intent intent = new Intent(this,Description.class);
		intent.putExtra(intent_title, title);
		intent.putExtra(intent_des, des);
		intent.putExtra("tableName", TableName);
		intent.putExtra("id", idNum);
		startActivity(intent);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch(item.getItemId()){
		case R.id.add:
			add();
		}
		return super.onMenuItemSelected(featureId, item);
	}
	private void add() {
		DialogButton a = new DialogButton(ctx,TableName);
		a.createAdd();
		a.show();		
	}
	@Override
	public void getNewAdapter() {
		notes.changeCursor(mDbHelper.fetchAllNotes(TableName));
		
	}

}
