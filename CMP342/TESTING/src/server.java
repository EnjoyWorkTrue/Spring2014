import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class server {
	
	private Socket socket;
	private ServerSocket server;
	private DataOutputStream streamOut;
	private BufferedReader console;
	
	
	public server(int port){
		try {
			server = new ServerSocket(port);
			System.out.println("waiting for client");
			socket = server.accept();
			open();
			start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void start() throws IOException {
		// TODO Auto-generated method stub
		while(true){
			String s = console.readLine();
			streamOut.writeUTF(s);
			streamOut.flush();
			
		}
	}

	private void open() throws IOException {
		console = new BufferedReader(new InputStreamReader(System.in));
		streamOut = new DataOutputStream(socket.getOutputStream());
		
	}
	
	public static void main(String[] args) {
		server s = new server(8080);
		
	}
	

}
