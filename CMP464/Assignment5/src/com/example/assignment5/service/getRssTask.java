package com.example.assignment5.service;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import Utils.MyPullParser;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class getRssTask extends AsyncTask<Void, Void, String> {

	private String before;
	private String after;
	private Context ctx;
	private notifyInterface comm;
	private MyPullParser parser;

	public getRssTask(MyService myService,Context ctx) {
		this.ctx = ctx;
		comm = (notifyInterface) myService;
	}

	@Override
	protected String doInBackground(Void... arg0) {
		//checked every 15 minutes
		long How = 15*60*1000;
		while(true){			
				try {					
					Thread.sleep(How);
					parser = MyPullParser.getInstance();
					synchronized (parser) {
						//get date from original recent published date
						before = parser.getFirstPubDate();
						//delete all data from Database
						parser.clearInfo();
						parser.parse("http://rss.cnn.com/rss/cnn_topstories.rss");
						// get first published date after download info 
						after = parser.getFirstPubDate();	
					}
					
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//compare if original pub date is different from recent pubdate then notify
				if(!before.equals(after)){
					giveNotification();
				}
				
			
			
		}
	}

	private void giveNotification() {
		Log.i("getRssTask","notify message");
		comm.notifyMessage(parser.getTitle().get(0));
	}

}
