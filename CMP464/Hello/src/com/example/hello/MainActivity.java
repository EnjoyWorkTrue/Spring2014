package com.example.hello;

import utils.singleNote;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	singleNote h;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		h = singleNote.getInstance();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void go(View view){
		EditText name = (EditText) findViewById(R.id.editText1);
		h.setName(name.getText().toString());		
		Intent intent = new Intent(this,Activity2.class);
		startActivity(intent);
		
	}

}
