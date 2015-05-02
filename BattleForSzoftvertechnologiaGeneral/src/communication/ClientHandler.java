package communication;

import java.beans.XMLEncoder;
import java.io.*;
import java.net.Socket;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.io.BufferedOutputStream;

public class ClientHandler extends Communication implements Runnable{

	private static int number=0;
	private String name;
	private Socket clientsocket=null;
	public BufferedOutputStream out=null;
	public BufferedInputStream in=null;
	
	
	@Override
	public void run() {
		if (DEBUG){
			System.out.println("Client connected to socket: " + clientsocket.getPort());
		}
		
		while (true){
			
			try{
				
				int b;
				while (in.available()>0) {	//&& number!=0){
					b=in.read();
					out.write(b);
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


	public ClientHandler(Socket soc){
		super();
		if (number<MAXCLIENT){
			this.clientsocket=soc;
			try {
				this.out=new BufferedOutputStream(soc.getOutputStream());
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
