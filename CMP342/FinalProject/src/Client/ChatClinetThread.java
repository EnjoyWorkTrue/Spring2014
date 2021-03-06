package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClinetThread extends Thread {
	
	private ChatClient client;
	private Socket socket;
	private DataInputStream streamIn;
	private String nicknamelist;
	
	public ChatClinetThread(ChatClient client, Socket socket){
		this.client = client;
		this.socket = socket;
		open();
		getNickNameInfo();
		start();
	}

	private void getNickNameInfo() {
		try {
			client.pushNicknameList(streamIn.readUTF());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void run() {
		System.out.println("threading start");
		while(true){
		try {			
			String s = streamIn.readUTF();
			System.out.println(s);
			if(s.equals("hey new commerce!@#$")){
				String nickname = streamIn.readUTF();
				System.out.println(nickname);
				client.addClient(nickname);
				
			}
			else if(s.equals("deleteUSER!@#$")){
				String nickname = streamIn.readUTF();
				client.deleteClient(nickname);
			}
			else
			client.handle(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	public void open() {
		try {
			streamIn = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			close();
			client.stop();
		}
		
	}

	private void close() {
		 try{
	    	   if (streamIn != null) streamIn.close();
	      }
	      catch(IOException ioe){
	    	   System.out.println("Error closing input stream: " + ioe);
	      }
		
	}
}
