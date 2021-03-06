package com.example.assignment3;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
	private static final int SETTINGS_RESULT = 1;
	private boolean switchMainActivity = false;
	public static boolean switchActivity2 = false;
	private Button buttonNormal;
	private Button buttonAlternative;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Log.i("MainActivity","Oncrezate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayUserSettings();
        buttonAlternative = (Button) findViewById(R.id.button1A);
        buttonNormal = (Button) findViewById(R.id.button1);
        setButtonVisibility(switchMainActivity);
		
	}
    
    public void letGoActwo(View v){
    	gotoActivity2();
    }
    private void setButtonVisibility(boolean switchMainActivity2) {
		if(switchMainActivity2){
			buttonAlternative.setVisibility(buttonAlternative.VISIBLE);
			buttonNormal.setVisibility(buttonNormal.INVISIBLE);
		}
		else{
			buttonAlternative.setVisibility(buttonAlternative.INVISIBLE);
			buttonNormal.setVisibility(buttonNormal.VISIBLE);
		}
		
	}
	private void gotoActivity2(){
    	Intent intent = new Intent(MainActivity.this,Activity2.class);
    	startActivity(intent);
    }
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
    		setButtonVisibility(switchMainActivity);    		
    	}
    }
    
    private void displayUserSettings(){
    	SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    	this.switchMainActivity = sharedPrefs.getBoolean("styleForMainActivity", false);
        this.switchActivity2 = sharedPrefs.getBoolean("styleForActivity2", false);
 
    }

    
}
