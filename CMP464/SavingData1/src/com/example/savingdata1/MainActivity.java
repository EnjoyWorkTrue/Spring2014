package com.example.savingdata1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static final String PREFS_NAME = "MyPrefsFile";
	private EditText edit;
	private TextView textV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.editText1);
        edit.setText("hello");
        textV = (TextView) findViewById(R.id.textView);
        //Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String silent = settings.getString("savedString","hello world");
        textV.setText(silent);
        		
        		
    }
    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop();
    	
    	SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
    	
    	SharedPreferences.Editor editor = settings.edit();
    	editor.putString("savedString",getEditText());
    	
    	//need to commit the edits
    	editor.commit();
    }
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void Send_Button(View v){
    	textV.setText(edit.getText().toString());
    }
    private String getEditText(){
    	return edit.getText().toString();
    }
    
}
