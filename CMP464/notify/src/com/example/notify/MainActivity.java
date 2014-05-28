package com.example.notify;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private NotificationManager mNotificationManager;
	private int SIMPLE_NOTFICATION_ID;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mNotificationManager = 
        		(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        final Notification notifyDetails = 
        		new Notification(R.drawable.ic_launcher,
        		 "You've got a new notification!",System.currentTimeMillis());
		
        Button start = (Button)findViewById(R.id.notifyButton);
        Button cancel = (Button)findViewById(R.id.cancelButton);
        
        start.setOnClickListener(new OnClickListener() {
        	
        public void onClick(View v) {
          		
        		Context context = getApplicationContext();
        		CharSequence contentTitle = 
        				"Notification Details...";
        		CharSequence contentText = 
        				"Browse Android Official Site by clicking me";
        		Intent notifyIntent = 
        				new Intent(android.content.Intent.ACTION_VIEW,
				             Uri.parse("http://www.android.com"));
        		PendingIntent intent = 
        			PendingIntent.getActivity(MainActivity.this, 0, 
        			notifyIntent, android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
        		
        		notifyDetails.setLatestEventInfo(context, 
        				                       contentTitle, contentText, intent);
        		mNotificationManager.notify(SIMPLE_NOTFICATION_ID, notifyDetails);
        	}
        
        });
        
        cancel.setOnClickListener(new OnClickListener() {    	
        	public void onClick(View v) {     		
        		mNotificationManager.cancel(SIMPLE_NOTFICATION_ID);
        	}
        });
    }
}
