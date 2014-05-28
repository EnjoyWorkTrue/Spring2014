package com.example.hello;

import utils.singleNote;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class Activity2 extends Activity {
	singleNote hi;
	TextView x;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity2);
		x = (TextView) findViewById(R.id.two);
		hi = singleNote.getInstance();
		x.setText(hi.getName());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity2, menu);
		return true;
	}

}
