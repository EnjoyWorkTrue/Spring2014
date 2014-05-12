package chat_server_client_2;

import java.net.*;
import java.io.*;
/**  
 *  Version 2: 
 *  works with the same client as before
 *  Server handles 1 client connection at a time on a single thread
 *  Clients will have to wait for the current client to quit
 *  Server will remain 'open' for additional connection once a client has quit. 
 * */
public class ChatServer implements Runnable {

	private Socket socket = null;
	private ServerSocket server = null;
	private Thread thread = null;
	private DataInputStream streamIn = null;
	private boolean done = true;//setting flag to true, it will get set to false inside run
	
	public ChatServer(int port){
		try{
			 System.out.println("Binding to port " + port + " please wait...");
			 server = new ServerSocket(port);
			 System.out.println("Server started: " + server);
			 while(true){
					try{
						System.out.println("Waiting for a client...");
						socket = server.accept();
						System.out.println("client accepted on socket : "+ socket);
						open();
						done = false;
						while(!done){
							try{
								String line = streamIn.readUTF();
							 //	System.out.println("YOU SAID " + line);
								System.out.println("JOE JOE JOE JOE");
							 	 System.out.println(" Client on remote port "+socket.getPort()+" Said " + line);
								done = line.equals("bye");
							}
							catch(IOException ioe){
								done=true;
								System.out.println("error "+ioe.getMessage());
							}
							
						}
						System.out.println("EXIT NOW");
						close();	//method to close the socket and the stream
					}
					catch(IOException ioe){
						System.out.println("error accepting the client "+ioe.getMessage());
					}
				}
			 
			 
			 
		}
		catch(IOException ioe){
			   System.out.println("error encountered: "+ioe.getMessage());
		}
		
	}

	@Override
	public void run() {
		while(thread!=null){
			try{
				System.out.println("Waiting for a client...");
				socket = server.accept();
				System.out.println("client accepted on socket : "+ socket);
				open();
				done = false;
				while(!done){
					try{
						String line = streamIn.readUTF();
					 //	System.out.println("YOU SAID " + line);
					 	 System.out.println(" Client on remote port "+socket.getPort()+" Said " + line);
						done = line.equals("bye");
					}
					catch(IOException ioe){
						done=true;
						System.out.println("error "+ioe.getMessage());
					}
				}
				System.out.println("EXIT NOW");
				close();	//method to close the socket and the stream
			}
			catch(IOException ioe){
				System.out.println("error accepting the client "+ioe.getMessage());
			}
		}
	}
	
	public void start(){
		if(thread==null){
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void stop(){
		if(thread !=null){
			thread=null;
			done=true;//set flag instead of using thread.stop which is deprecated		
		}
	}
	//same as version 1
	public void open() throws IOException{
		streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	}
	
	//same as version 1
	public void close() throws IOException{
		if(streamIn != null){
			streamIn.close();
		}
		if(socket != null){
			socket.close();
		}
	}
	
	public static void main(String[] args){
		   if(args.length !=1){
			   System.out.println("To run the server you need to specify a port");
		   }
		   else{
			   ChatServer server = new ChatServer(Integer.parseInt(args[0]));
			   
		   }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

