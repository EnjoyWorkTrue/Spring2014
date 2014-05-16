package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import Application.ClientApp;
import Enc_Dec.Encryption;
import Interface.FCTA;

public class ChatClient implements Runnable{

	private Socket socket;
	private DataOutputStream streamOut;
	private Thread thread;
	private boolean done;
	private ChatClinetThread client;
	FCTA comm;
	
	
	public ChatClient(String serverName, int serverPort, ClientApp clientApp){
		
		try{
			socket = new Socket(serverName, serverPort);
			//System.out.println("Connected to socket: " + socket);
			comm = (FCTA) clientApp;
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
	public void sendEncrypptedMessage(int portNumber, String text) throws IOException {
		Encryption enc = new Encryption(text);
		if(enc.isEncryptable()){
			
			streamOut.writeUTF("Encrypted!!**");
			streamOut.writeUTF(portNumber+"");
			streamOut.writeUTF(enc.getEncryptionKey());
			streamOut.writeUTF(enc.getEncrypted());
			streamOut.flush();
			comm.appendMessage("encrypted value: " + enc.getEncrypted());
		}
		else{
			comm.appendMessage("-->symbol or number cannot be used for encryption");
		}
		
		}
		
	
	private String encryptedMessage(String text, String key) {
		String s = "abcdefghijklmnopqrstuvwxynz ";
		String enc = "";
		for(int i=0;i<text.length();i++){
			
			
		}
		return null;
	}
	private String getKey(int length) {
		String s = "abcdefghijklmnopqrstuvwxynz ";
		String key="";
		for(int i=0;i < length; i++){
			key = key + s.charAt((int)(Math.random()*28));
		}
		return key;
	}
	
	

}
