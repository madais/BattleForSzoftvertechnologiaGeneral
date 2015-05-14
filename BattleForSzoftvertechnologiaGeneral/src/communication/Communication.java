package communication;

import game.game.GameOn;

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
	
	public static boolean start_server(String port) throws IOException{
		int port_int=Integer.parseInt(port);
		Runnable newserver= new Server(port_int);
		Thread serverthread=new Thread(newserver);
		
		serverthread.start();
		connect_server(DEFAULTHOST, port_int);
		client.send_msg("Szerver indult: " + String.valueOf(port) + "\n");
		return true;
	}
	
	public static boolean connect_server(String ip){
		try {
			client=new Client(ip);
			Runnable newclient= client;
			Thread clientthread=new Thread(newclient);
			
			clientthread.start();


			client.send_msg("Kliens indult: " + ip + "\n");
		}
		catch (IOException ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean connect_server(String ip, int port){
		try {
			client=new Client(ip, port);
			Runnable newclient= client;
			Thread clientthread=new Thread(newclient);
			
			clientthread.start();

			client.send_msg("Kliens indult: " + ip+ ":" + String.valueOf(port) + "\n");
		}
		catch (IOException ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	public static void send_message(String str){
		client.send_msg(str + "\n");
	}
	
	public static void send_table(GameOn table){
		client.send_table_client(table);
	}
	
	
	public static void subscribe_message(MsgListener listener){
		client.subscribe_msg(listener);
	}
	
	public static void subscribe_table(TableListener listener){
		client.subscribe_table(listener);
	}

	public static ArrayList<String> get_clients(){
		ArrayList<String> client_list=null;
		
		return client_list;
	}
	
	public static String getlocalport(){
		return client.client_socket.getLocalSocketAddress().toString();
	}
}
