package com.example.class_notes;

import org.json.JSONException;
import org.json.JSONObject;

import utils.Downloader;
import utils.MyCursorAdapter;
import utils.SportsManager;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * list view displaying parsed json stored in an sqlite database
 * @author josh
 *
 */
public class MainActivity extends Activity implements Downloader.JSONDownloaderListener{
	MyCursorAdapter madapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Downloader.JSONAsyncDownloader(this).execute("http://data.nba.com/json/cms/noseason/scoreboard/20101229/games.json");
    }

	@Override
	public void onJSONRetrieved(JSONObject json) {
		try {
			//get new database object
			final SportsManager dbmanager = new SportsManager(this);
			//insert the sports json into the database
			dbmanager.insertSports(json);
			//display the database in a list using cursor adapter
			final ListView lv = (ListView) findViewById(R.id.arenalist);
			madapter = MyCursorAdapter.defaultImplementation(this,dbmanager);
			lv.setAdapter(madapter);
			//when clicking an item, remove it from the list
			lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					madapter.delete(id);
				}
			});
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch(item.getItemId()){
		case R.id.add:
			madapter.insert("item");
		}
		return super.onMenuItemSelected(featureId, item);
	}

}
