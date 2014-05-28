package utils;



import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import utils.Interface.FCTA;
import android.app.Activity;

public class ChatClient implements Runnable{

	private Socket socket;
	private DataOutputStream streamOut;
	private Thread thread;
	private boolean done;
	private ChatClinetThread client;
	FCTA comm;
	
	
	public ChatClient(String serverName, int serverPort){
		
		try{
			socket = new Socket(serverName, serverPort);
			//System.out.println("Connected to socket: " + socket);
		//	comm = (FCTA) clientApp;
		}
		catch(UnknownHostException uhe){
			//System.out.println("Error Unknown Host: "+ uhe.getMessage());
		}
		catch(IOException ioe){
			//System.out.println("Unexpected exception: "+ ioe.getMessage());
		}
			
		
	}
	public void start() throws IOException {
		streamOut = new DataOutputStream(socket.getOutputStream());
		if(thread == null){
			client = new ChatClinetThread(this,socket);
			thread = new Thread(this);			
		}
		
	}
	
	
	public void run(){
	}
	

	
	public void stop() {
		done = true;
		if(thread!=null){
			thread =null;
		}
	
		if(streamOut != null)
			try {
				if(streamOut != null)streamOut.close();
				if(socket != null) socket.close();
				if(client != null) client = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
	}
	public void addClient(String name){
		comm.addClient(name);
	}
	public void handle(String message) {
		//Receiving Messages
		if(message.equalsIgnoreCase("bye")){
		}
		else{
			comm.appendMessage(message);
			// Here is the Part send Message to Application
			//comm.appendMessage(message);			
		}
		
	}
	public void sendMessage(String message) throws IOException{
		streamOut.writeUTF(message);
		streamOut.flush();
	}
	public void pushNicknameList(String readUTF) {
		comm.setNicknameList(readUTF);
		
	}
	public void deleteClient(String nickname) {
		System.out.println(nickname);
		comm.deleteUser(nickname);
		
	}
	public void sendPrivateMessage(int portNumber,String text) throws IOException {
		streamOut.writeUTF("PRIVATE!!**");
		streamOut.writeUTF(portNumber+"");
		streamOut.writeUTF(text);
		streamOut.flush();
		
		
	}

	

}
