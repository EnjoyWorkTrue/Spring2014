package com.example.assignment5.service;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.assignment5.MainActivity;

public class MyService extends Service implements notifyInterface{

	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	@Override
	public void onCreate() {
		Log.i("service","service created");
		new getRssTask(this,getBaseContext()).execute();
	}
	@Override
	public void onStart(Intent intent, int startId) {
		Log.i("service","service started");
		
	}
	@Override
	public void onDestroy() {
		
	}
	@Override
	public void notifyMessage(String title) {
		Log.i("MyService Java","notifyMessage");
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_dialog_alert,"get New News!",System.currentTimeMillis());
		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent, 0);
		notification.setLatestEventInfo(this, "You got new News", title, pendingIntent);
		notificationManager.notify(10,notification);
	}

}
