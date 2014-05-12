
import java.net.*;
import java.io.*;
/**
 * 
 * Chat Client Version 1
 * Works with ChatServer Versions 1,2,3
 *
 */
public class ChatClient {

private Socket socket = null;
BufferedReader console = null; // = new BufferedReader(new InputStreamReader(in))
private DataOutputStream streamOut = null;
 
public ChatClient(String serverName, int serverPort){
System.out.println("Establishing connection to server "+serverName+ " on port "+ serverPort + " please wait...");
try{
socket = new Socket(serverName, serverPort);
System.out.println("Connected to socket: " + socket);
start();//method to create a new buffered reader for the console, and a new data output stream
}
catch(UnknownHostException uhe){
System.out.println("Error Unknown Host: "+ uhe.getMessage());
}
catch(IOException ioe){
System.out.println("Unexpected exception: "+ ioe.getMessage());
}
String line="";
while(!line.equals("bye")){
try{
line=console.readLine();
streamOut.writeUTF(line);
streamOut.flush();
 
}
catch(IOException ioe){
System.out.println("There was an error : "+ ioe.getMessage());
}
}
}
 
public void start()throws IOException{
console = new BufferedReader(new InputStreamReader(System.in));
streamOut = new DataOutputStream(socket.getOutputStream());
}
 
 
public void stop(){
try{
if (console   != null)  console.close();
       if (streamOut != null)  streamOut.close();
       if (socket    != null)  socket.close();
}
catch(IOException ioe){
System.out.println("Error during closing  : "+ ioe.getMessage());
}
}
 
 
public static void main(String[] args) {
// TODO Auto-generated method stub
ChatClient client = null;
if(args.length !=2){
System.out.println("To chat you must specify both a host and a port ");
}
else{
client = new ChatClient(args[0],Integer.parseInt(args[1]));
}

}

}