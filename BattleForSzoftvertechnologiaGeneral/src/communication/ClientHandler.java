package communication;

import java.beans.XMLEncoder;
import java.io.*;
import java.net.Socket;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.io.BufferedOutputStream;
import java.util.ArrayList;

/**
 * Multiple client handler object for server.
 * Creates a new client object for each connection request.
 * @author mdi
 *
 */
public class ClientHandler extends Communication implements Runnable{

	private static int number=0;
	private String name;
	private Socket clientsocket=null;
	public BufferedOutputStream out=null;
	public BufferedInputStream in=null;
	
	private static ArrayList<BufferedOutputStream> outlist=new ArrayList<BufferedOutputStream>();
	

	@Override
	/**
	 * Overrided run method for multithreading.
	 * Sends all incoming data to every listed client, including the original sender.  
	 */
	public void run() {
		if (DEBUG){
			System.out.println("Client connected to socket: " + clientsocket.getPort());
		}
		
		while (true){
			
			try{
				
				int b;
				while (in.available()>0 && number!=0){
					b=in.read();
					for (BufferedOutputStream kifele: outlist){
						kifele.write(b);
					}
					//out.write(b);
					//System.out.println(String.format(b, format, args));
				}
				out.flush();
			}
			catch (IOException ex){
				ex.printStackTrace();
			}
			try{
			Thread.sleep(100);
			}
			catch (InterruptedException ex){
				ex.printStackTrace();
			}
		}
		
	}

	/**
	 * Connects the streams to the clientports. Updates the list of clients.
	 * @param soc
	 */
	public ClientHandler(Socket soc){
		super();
		if (number<MAXCLIENT){
			this.clientsocket=soc;
			try {		
				this.out=new BufferedOutputStream(soc.getOutputStream());
				outlist.add(this.out);
				this.in=new BufferedInputStream(soc.getInputStream());
				
			}
			catch (IOException ex){
				ex.printStackTrace();
			}
			name="Clienthandler " + Integer.toString(number);
			number++;
			
		}
		
	}
	
}
