package communication;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Communication {
	
	public boolean DEBUG=true;

	public static int DEFAULTPORT=5000;
	public static String DEFAULTHOST="localhost";
	public static int DEFAULTTIMEOUT=1000;
	public static int MAXCLIENT=4;
	
	public static Client client=null;
	
	public static boolean start_server() throws IOException{
		Runnable newserver= new Server();
		Thread serverthread=new Thread(newserver);
		
		serverthread.start();
		connect_server(DEFAULTHOST);
		return true;
	}
	
	public static boolean connect_server(String ip){
		try {
			client=new Client(ip);
			Runnable newclient= client;
			Thread clientthread=new Thread(newclient);
			
			clientthread.start();

			
		}
		catch (IOException ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void send_message(String str){
		client.send_msg(str);
	}
	/*
	public static void send_table(Table table){
		client.send_table(table);
	}
	*/
	
	public static void subscribe_message(MsgListener listener){
		client.subscribe_msg(listener);
	}

}
