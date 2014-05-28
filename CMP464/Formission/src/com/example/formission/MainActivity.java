package com.example.formission;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {
	ImageView image;
	Bitmap bitmap;
	private NotificationManager mNotificationManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		image = (ImageView) findViewById(R.id.imageView1);
		final String src="http://cubecityministry.com/files/2014/04/JM02052.jpg";
		
		(new Thread(new Runnable(){
			@Override
			public void run() {
				
					bitmap = getBitmap(src);
					MainActivity.this.runOnUiThread(new Runnable(){
						@Override
						public void run() {
							setup(bitmap);
						}

						
					});
				
			}
        })).start();
		
		//image.setImageDrawable(LoadImageFromWebOperations(url));
	}
	private void setup(Bitmap bitmap) {
		image.setImageBitmap(bitmap);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public Bitmap getBitmap(String src){
		try {
			URL url = new URL(src);			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBit = BitmapFactory.decodeStream(input);
			return myBit;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}return null;}

}
