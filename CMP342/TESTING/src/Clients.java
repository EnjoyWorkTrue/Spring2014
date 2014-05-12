import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Clients {
	private Socket socket;
	BufferedReader console;
	private DataInputStream streamIn;
	private String line="";
	
	public Clients(String serverName,int serverPort){
		System.out.println("Establishing connection");
		
		try {
			socket = new Socket(serverName,serverPort);
			start();
			getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getMessage() throws IOException {
		while(!line.equals("bye")){
			line = streamIn.readUTF();
			System.out.println(line);
			
		}
		
	}

	private void start() throws IOException {
		streamIn = new DataInputStream(socket.getInputStream());
		
	}
	
	public static void main(String[] args){
		Clients c = new Clients("0.0.0.0",8080);
	}
	

}
