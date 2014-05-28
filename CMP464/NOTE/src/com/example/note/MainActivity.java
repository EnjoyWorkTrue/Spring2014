package com.example.note;

import interfaCe.notifyChange;
import utils.DbManager;
import utils.DialogButton;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements notifyChange{
	private DbManager mDb;
	private Cursor cursor;
	private ListView lv;
	private SimpleCursorAdapter madapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mDb = DbManager.getInstances(this);
		mDb.open();
		startListActivity();
		
	}
	
	private void startListActivity() {
		cursor = mDb.getData();
		String[] from = new String[]{DbManager.FeedEntry.COLUMN_NAME_TITLE};
		int[] to = new int[]{android.R.id.text1};
		madapter = new SimpleCursorAdapter(MainActivity.this,android.R.layout.simple_list_item_1,cursor,from,to);
		lv = (ListView) findViewById(R.id.titleList);
		lv.setAdapter(madapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Cursor c = mDb.getSpecificData(arg3);
				Toast.makeText(MainActivity.this, c.getString(1), 0).show();
				startActivity2(c.getString(1));
				
			}
			
		});
		
		
	}

	protected void startActivity2(String string) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this,MainActivity2.class);
		intent.putExtra("Title", string);
		startActivity(intent);
		
	}

	@Override
	protected void onDestroy() {
		mDb.close();
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.anction_add:
			addItems();
			return true;
			
		}
		return super.onOptionsItemSelected(item);
	}
	private void addItems() {
		DialogButton a = new DialogButton(this);
		a.addItem();
		a.show();
		
	}	
	@Override
	public void happened() {
		madapter.changeCursor(mDb.getData());
		
	}

}
