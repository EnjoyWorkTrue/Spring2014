package com.example.chatprogram;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import utils.Interface.FCTA;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
	public static Activity activity;
	Socket socket = null;
	 DataOutputStream dataOutputStream = null;
	 DataInputStream dataInputStream = null;
EditText textOut;
TextView textIn;

 /** Called when the activity is first created. */
 @Override
 public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);
     activity = this;
     Log.i("main","before");
     Thread thread = 
     (new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					Log.i("runnable","running");
					socket = new Socket("148.84.128.235", 8000);
					dataOutputStream = new DataOutputStream(socket.getOutputStream());
					startReceivedThread();
				} catch (IOException e) {
					Log.i("main","printStackTrace");
					e.printStackTrace();
				}
			}
			
     }));
     Log.i("main","thread start");
     thread.start();
     Log.i("main","after");
     textOut = (EditText)findViewById(R.id.textout);
     textIn = (TextView)findViewById(R.id.textin);
 
 }
 public void startReceivedThread(){
	 ThreadHandler a = new ThreadHandler(socket);
	 
 }
 public synchronized void append(String s){
	 Toast.makeText(this, s, 0).show();
 }
 public void sendMessage(View v){
	 try {
		dataOutputStream.writeUTF(textOut.getText().toString());
		textIn.setText("");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
}
