package com.example.assignment4;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;
import utils.MyPullParser;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class MainActivity extends Activity{
	ArrayAdapter<String> mAdapter;
	ListView lv;
	String sw = "hihi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        (new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					final MyPullParser parser = new MyPullParser();
					parser.parse("http://rss.cnn.com/rss/cnn_topstories.rss");
					MainActivity.this.runOnUiThread(new Runnable(){
						@Override
						public void run() {
							setUpUiAfterParse(parser);
						}
					});
				} catch (XmlPullParserException e) {
					Log.i("main","XmlPullParserException");
					e.printStackTrace();
				} catch (IOException e) {
					Log.i("main","printStackTrace");
					e.printStackTrace();
				}
			}
        })).start();
       
    }
 
    private void setUpUiAfterParse(final MyPullParser parser){
		
		mAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, 
				new ArrayList<String>(parser.getTitle()));
        lv = (ListView) findViewById(R.id.list_view);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Intent intent = new Intent(MainActivity.this,DescriptionActivity.class);
				intent.putExtra("des", parser.getDescription()[position]);
				intent.putExtra("title",parser.getTitle().get(position));
				startActivity(intent);
				
			}
        	
		});
    }
}
