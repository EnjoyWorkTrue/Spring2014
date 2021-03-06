package com.example.loginpagewithsavingdata;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final String PREFS_NAME = "MyPrefsFile";
	private static final int SETTINGS_RESULT = 1;
	private EditText edit1;
	private EditText edit2;
	private boolean autoLogin=false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit1 = (EditText) findViewById(R.id.editText1);
        edit2 = (EditText) findViewById(R.id.editText2);
        getPreviousData();
        displayUserSettings();
        if(autoLogin){
        	if(loginSuccess())
        	gotoActivity2();
        }
        
    }
    
    private void getPreviousData() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		edit1.setText(settings.getString("username", ""));
		edit2.setText(settings.getString("password", ""));
		
	}

	@Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop();
    	
    	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
    	Editor editor = settings.edit();
    	editor.putString("username",getUserId());
    	editor.putString("password",getPassword());
    	editor.commit();
    }
	/////////////BUTTON
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()){
    	case R.id.action_settings:
    		startPrefActivity();
    		return true;
    	}
    	
    	return super.onOptionsItemSelected(item);
    }
    
    private void startPrefActivity(){
    	Intent i = new Intent(getApplicationContext(),UserSettingActivity.class);
    	startActivityForResult(i,SETTINGS_RESULT);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	if(requestCode==SETTINGS_RESULT)
    	{
    		displayUserSettings();    		
   		
    	}
    }

	private void displayUserSettings() {
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    	this.autoLogin = sharedPrefs.getBoolean("switch", false);
    	
        
		
	}

	public void button_goActivityTwo(View v){
    	if (loginSuccess())
    	gotoActivity2();
    	else{
    		Toast.makeText(this, "wrong User Id or Password", 0).show();
    	}
    }


	private boolean loginSuccess() {
		String id = edit1.getText().toString();
		String password = edit2.getText().toString();
		if(id.equals("dldytpq234") && password.equals("dlfdlf11"))return true;
		return false;
	}


	private void gotoActivity2() {
		Intent intent = new Intent(MainActivity.this,Activity2.class);
		startActivity(intent);
		
		
	}
	private String getUserId(){
		return edit1.getText().toString();
	}
	private String getPassword(){
		return edit2.getText().toString();
	}
    
}
