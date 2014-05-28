package com.example.note;

import utils.DbManager;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity2 extends Activity {
	private String title;
	private DbManager mDb;
	private Cursor cursor;
	private ListView lv;
	private SimpleCursorAdapter madapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity2);
		Intent intent = getIntent();
		title = intent.getStringExtra("Title");
		mDb = DbManager.getInstances();
		mDb.checkIfExist(title);
		startListActivity();
		
		
	}
	public void startListActivity(){
		cursor = mDb.getData(title);
		lv = (ListView) findViewById(R.id.titleList2);
		String[] from = new String[]{DbManager.FeedEntry.COLUMN_NAME_TITLE};
		int[] to = new int[]{android.R.id.text1};
		madapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor,from,to);
		lv.setAdapter(madapter);
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity2, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.anction_add1:
			add();
		}
		return super.onOptionsItemSelected(item);
	}
	private void add() {
		mDb.add_title(title,"hello");
		madapter.changeCursor(mDb.getData(title));
		
	}
	

}
