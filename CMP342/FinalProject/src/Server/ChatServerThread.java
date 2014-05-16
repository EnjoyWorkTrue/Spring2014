package Server;
/**
 * 
 *Version 4:
 *Thread for Group Chat Server
 *This time it sends all text received from any of the connected clients to all clients. 
 *This means that the server has to receive and send, and the client has to send as well as receive
 *
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatServerThread extends Thread{
	
	private ChatServer server = null; 
	private Socket socket = null;
	private int ID = -1;
	private DataInputStream streamIn = null;
	private DataOutputStream streamOut = null;
	private boolean done = true;
	private String Nickname;
	private int pos;
	
	
	public ChatServerThread(ChatServer _server, Socket _socket, int clientCount){
		super();
		pos = clientCount;
		server = _server;
		socket = _socket;
		ID = socket.getPort();
		System.out.println("Chat Server Thread Info: server"+ server + " socket "+ socket + " ID "+ ID);
	}
	
	public void send(String msg){
		try{
			streamOut.writeUTF(msg);
			streamOut.flush();
		}
		catch(IOException ioe){
			System.out.println(ID + " ERROR sending: " + ioe.getMessage());
			server.remove(ID);
			ID=-1;//set ID -1 for the thread...
		}
	}
	
	public int getID(){
		return ID;
	}
	public void setNickName(){
		try {
			Nickname = streamIn.readUTF();
			server.sendNewcommerceNotification(ID+": "+Nickname,pos);
			server.handle(ID, Nickname + " join the room", pos);
			server.addonNicknamelist(ID+": "+Nickname, pos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run(){
		System.out.println("threading start");
		String line;
		while(ID!=-1){
			try{
				line = streamIn.readUTF();
				if(line.equals("PRIVATE!!**")){
					int port = Integer.parseInt(streamIn.readUTF());
					String message = streamIn.readUTF();
					if(!
					server.sendPrivateMessage(port,"private message from "+
					+	ID + " " +
							Nickname+": "+message)){
						send("could not find ID: "+port);
					}
				}
				else if(line.equals("Encrypted!!**")){
					int port = Integer.parseInt(streamIn.readUTF());
					String deckey = streamIn.readUTF();
					String encValue = streamIn.readUTF();
					server.sendPrivateMessage(port, port+" "+Nickname+" send encryption message");
					server.sendPrivateMessage(port, "Decription key: " + deckey);
					server.sendPrivateMessage(port, "Encription Value: " + encValue);
				}
				else
				server.handle(ID, Nickname + " said: " + line,pos);
			}
			catch(IOException ioe){
				//System.out.println(ID + "ERROR reading: " + ioe.getMessage());
				server.sendByeNotification(ID+": " + Nickname, pos);
				server.handle(ID, Nickname+" left the room", pos);
				server.remove(pos);
				ID=-1;//set ID to -1 so it will not enter the loop again instead of deprecated stop()
			}
		}
	}
	public void open() throws IOException{
		streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	}
	
	public void close() throws IOException{
		if(socket != null){
			socket.close();
		}
		if(streamIn != null){
			streamIn.close();
		}
		if(streamOut != null){
			streamOut.close();
		}
	}
	
	public String getNickname(){
		return Nickname;
	}

	public void setPosition(int c) {
		System.out.println("not working ");
		pos = c;
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
