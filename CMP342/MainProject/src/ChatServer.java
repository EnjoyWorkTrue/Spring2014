
import java.net.*;
import java.io.*;
/**
 * 
 * Version 1: A simple server that will accept a single client connection and display what the client says. 
 * If the client user types "bye", the client and the server will both quit.
 * NO THREADS
 *
 */
public class ChatServer{
 
   private Socket socket = null;
   private ServerSocket server = null;
   private DataInputStream streamIn = null;

   public ChatServer(int port){
  try{
  System.out.println("Binding to port " + port + " please wait...");
  server = new ServerSocket(port);
  System.out.println("Server started: " + server+ " waiting for a client...");
  socket = server.accept();
  System.out.println("Client accepted: " + socket);
  open();// method to open a new inputstream for the socket
boolean done = false;
  while(!done){
  try {
  String line = streamIn.readUTF();
//   System.out.println("Socket is: " + socket+" , localport is : "+ port +" Client Said " + line);
  System.out.println(" Client on remote port "+socket.getPort()+" Said " + line);
  done = line.equals("bye");//evaluating if text equals bye 
  }
  catch(IOException ioe){
  System.out.println("error encountered: "+ioe.getMessage());
  done=true;
  }
  }
  System.out.println("EXIT NOW");
  close();//method to close the socket and the stream
  }catch(IOException ioe){
  System.out.println(ioe);
  }
   }
  
   public void open() throws IOException{
  streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
   }
   public void close() throws IOException{
  if(streamIn !=null){
  streamIn.close();
  }
  if(socket !=null){
  socket.close();
  }
  
   }

   public static void main(String[] args){
  ChatServer server = null;
  if(args.length !=1){
  System.out.println("To run the server you need to specify a single port");
  }
  else{
  server = new ChatServer(Integer.parseInt(args[0]));
  
  }
   }
  
  
      
   
   
   
}