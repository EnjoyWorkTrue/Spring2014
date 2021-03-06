package com.example.assignment3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity2 extends Activity {
	private Button buttonNormal;
	private Button buttonAlternative;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity2);
		setButtonVisibility(MainActivity.switchActivity2);
					
	}
	
	private void setButtonVisibility(boolean b) {
		buttonNormal = (Button) findViewById(R.id.button2);
		buttonAlternative = (Button) findViewById(R.id.button2A);
		if(b){
			buttonNormal.setVisibility(buttonNormal.INVISIBLE);
			buttonAlternative.setVisibility(buttonAlternative.VISIBLE);
		}
		else{
			buttonNormal.setVisibility(buttonNormal.VISIBLE);
			buttonAlternative.setVisibility(buttonAlternative.INVISIBLE);
		}
		
	}

	public void letsGoAc3(View v){
		gotoActivity3();
	}
	private void gotoActivity3(){
		Intent intent = new Intent(Activity2.this,Activity3.class);
		startActivity(intent);
	}

}
