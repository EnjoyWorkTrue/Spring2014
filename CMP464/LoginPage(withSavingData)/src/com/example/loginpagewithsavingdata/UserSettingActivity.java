package com.example.loginpagewithsavingdata;



import android.os.Bundle;
import android.preference.PreferenceActivity;


public class UserSettingActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.activity_user_setting);
	}

}

