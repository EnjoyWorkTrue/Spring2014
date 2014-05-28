package com.example.chatprogram;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import utils.Interface.FCTA;
import android.app.Activity;
import android.util.Log;

public class ThreadHandler extends Thread {
    int n;
	private Socket socket;
	private DataInputStream streamIn;
	private MainActivity activity;

	ThreadHandler(Socket s) {
		socket = s;
        open();
        start();
        
        
    }
	private void open() {
		try {
			streamIn = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		Log.i("ThreadHandelr","running");
		while(true){
			try {
				String s = streamIn.readUTF();
				//comm.appendMessage(s);
				Log.i("ThreadHandelr",s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
