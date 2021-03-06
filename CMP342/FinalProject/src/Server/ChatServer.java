package Server;

import java.net.*;
import java.io.*;

/**
 * 
 *Version 4:
 *Group Chat
 *This time it sends all text received from any of the connected clients to all clients. 
 *This means that the server has to receive and send, and the client has to send as well as receive
 *
 */
public class ChatServer implements Runnable{
	private ChatServerThread clients[] = new ChatServerThread[50];
	private ServerSocket server = null;
	private Thread thread = null;
	private int clientCount = 0;
	private String[] nickname = new String[50];


	
	public ChatServer(int port){
		try{
			System.out.println("Binding to port " + port + " please wait...");
			server = new ServerSocket(port);
			System.out.println("Server started: " + server);
			start();
		}
		catch(IOException ioe){
			System.out.println("Cannot bind to port " + port + ": " +ioe.getMessage());
		}
	}

	//same as previous versions (i.e. same as version 3 and 2)	
	public void start(){
		if(thread==null){
			thread = new Thread(this);
			thread.start();
		}
	}	
	//same as previous versions (i.e. same as version 3 and 2)	
	public void stop(){
		if(thread !=null){
			thread=null;
		}
	}
	//same as previous version (i.e. same as version 3)
	public void run() {
		// TODO Auto-generated method stub
		while(thread != null){
			try{
				System.out.println("Waiting for a client...");
				addThread(server.accept());
			}
			catch(IOException ioe){

				System.out.println("error accepting the client "+ioe.getMessage());
			}
		}
		
		
		
		
	}
	
	public synchronized void handle(int ID, String input, int pos){
		System.out.println(input);
			for(int i=0; i<clientCount; i++){
				if(i!=pos)
				clients[i].send(ID+" "+input);		
											}
			if(input.equals("bye")){
			remove(ID);
		}
		
	}
	public synchronized void sendByeNotification(String input, int pos){
		for(int i=0; i<clientCount; i++){
			if(i!=pos){
			clients[i].send("deleteUSER!@#$");
			clients[i].send(input);	
			}
										}
	
	}

	public synchronized void sendNewcommerceNotification(String input,int pos) {
		for(int i=0; i<clientCount; i++){
			if(i!=pos){
			clients[i].send("hey new commerce!@#$");
			clients[i].send(input);	
			}
										}
	}
	
	public synchronized void remove(int pos){
		removeNickname(pos);
		try {
			clients[pos].close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int c = 0;
		for(int i = 0; i<clientCount;i++){
			if(i==pos){
				
			}
			else{
				clients[c] = clients[i];
				clients[c].setPosition(c);
				c++;
			}
		}
		clientCount--;
			
	}
	public void removeNickname(int position){
		int n = 0;
		for (int i = 0; i < clientCount; i++){
			if(i==position)
				n++;
			nickname[i] = nickname[n];
			n++;
		}
	}

	
	public int findClient(int ID){
		for(int i=0; i<clientCount; i++){
			if(clients[i].getID() == ID){
				return i;
			}
		}
		return -1; //if ID not found in array	
	}
	
	private synchronized void addThread(Socket socket){
		System.out.println("addThread before" + clientCount);
		if(clientCount < clients.length){
			System.out.println("Client "+ clientCount + " accepted on : " + socket);
			clients[clientCount] = new ChatServerThread(this, socket,clientCount);
				try {
					clients[clientCount].open();
					nickname[clientCount] = null;
					if(nickname[0]==null){
						clients[clientCount].send("you are first");
					}
					else{
						clients[clientCount].send(getListofNickname());
					}
					clients[clientCount].setNickName();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				clients[clientCount].start();
				
				clientCount++;
				System.out.println("addThread After" + clientCount);
			}
						
		}
		
	

	private String getListofNickname() {
		System.out.println(clientCount);
		if(clientCount!=0){
		String s = "";
		for(int i=0;i<clientCount-1;i++){
			if(nickname[i] != null)
			s = s+nickname[i]+",";
		}
		if(nickname[clientCount-1] != null){
		s = s+nickname[clientCount-1];
		return s;
		}
		}
		return null;
	}

	//same as previous version
		public static void main(String[] args){
			   ChatServer server = null;
			   if(args.length !=1){
				   System.out.println("To run the server you need to specify a port");
			   }
			   else{
				   server = new ChatServer(8000);
				   
			   }
		}

		public void addonNicknamelist(String name,int pos) {
			nickname[pos] = name;
			
		}

		public synchronized boolean sendPrivateMessage(int port, String message) {
			for(int i=0; i<clientCount; i++){
				if(clients[i].getID() == port){
					clients[i].send(message);
					return true;
				}
			}
			return false;
		}

	
	
	
	
	
	

}
