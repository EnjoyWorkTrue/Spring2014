package com.example.assignment3;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;

public class UserSettingActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.activity_user_setting);
	}

}
