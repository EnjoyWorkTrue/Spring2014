package com.example.notepad;

import java.util.ArrayList;

import utils.DialogButton;
import utils.NotesDbAdapter;
import utils.getCursor;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements getCursor{
	int count = 1;
	Cursor cursor;
	private ListView lv;
	private NotesDbAdapter mDbHelper;
	private static SimpleCursorAdapter notes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mDbHelper = new NotesDbAdapter(this);
		mDbHelper.open();
		startListActivity();
	}

	private void startListActivity() {
		cursor = mDbHelper.fetchAllNotes();
		String[] from = new String[]{NotesDbAdapter.KEY_TITLE};
		int[] to = new int[] { android.R.id.text1 };
		notes = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor,from, to);
		lv = (ListView) findViewById(R.id.titleList);
		lv.setAdapter(notes);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
								Cursor c = mDbHelper.fetchNote(arg3);								
								startActivity2(c.getString(1));
			}
		});
		 lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				delete(arg3);
				return false;
			}
		});
			
	}
	protected void startActivity2(String s) {
		Intent intent = new Intent(this,Activity2.class);
		intent.putExtra("Title", s);
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
		DialogButton a = new DialogButton(this);
		a.createTable();
		a.show();
		
	}
	
	private void delete(long arg3) {
		Cursor c = mDbHelper.fetchNote(arg3);		
		mDbHelper.deleteNote(arg3);
		mDbHelper.deleteTable(c.getString(1));
		c = mDbHelper.fetchAllNotes();
		notes.changeCursor(c);
	}
	@Override
	public void getNewAdapter() {
		notes.changeCursor(mDbHelper.fetchAllNotes());
		
	}
	@Override
	protected void onDestroy() {
		mDbHelper.close();
		super.onDestroy();
	}

}
