package communication;

import java.io.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Server class, accepts connection request and starts a client handler thread for each.
 * @author mdi
 *
 */
public class Server extends Communication implements Runnable{
	
	

	ServerSocket serversocket=null;
	
	
	BufferedInputStream server_in=null;
	BufferedOutputStream server_out=null;
	
	ArrayList<Runnable> run_list=new ArrayList<Runnable>();
	ArrayList<String> client_list=new ArrayList<String>();
	/**
	 * Overrided method
	 * Listens on the server port, and starts a new client thread in case of connection request.
	 */
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			
			if (DEBUG){
				System.out.println("Server_thread");
			}
			
			try{	
				run_list.add(new ClientHandler(serversocket.accept()));
				new Thread (run_list.get(run_list.size()-1)).start();;
			
			
		}	
		catch (IOException ex){
			ex.printStackTrace();
		}
		
		
		try{
			Thread.sleep(10);
		}
		catch (InterruptedException ex){
			ex.printStackTrace();
		}
		}
	}

	/**
	 * Null parameter constructor
	 * Starts a server on the default port
	 */
	public Server() throws IOException{
		super();
		this.serversocket = new java.net.ServerSocket(DEFAULTPORT);
		if (DEBUG){
			System.out.println("Server socket created:" + this.serversocket.getLocalPort());
			client_list.add(String.valueOf(this.serversocket.getLocalPort()));
		}
	}
	
	/**
	 * Start a server listening on a specified port.
	 * @param port
	 * @throws IOException
	 */
	public Server(int port) throws IOException{
		super();
		this.serversocket= new java.net.ServerSocket(port);
		
		if (DEBUG){
			System.out.println("Server socket created:" + this.serversocket.getLocalPort());
			client_list.add(String.valueOf(this.serversocket.getLocalPort()));
		}
	}
	
	
}
