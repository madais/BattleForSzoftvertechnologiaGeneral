package communication;

import game.game.GameOn;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.util.ArrayList;
import game.map.*;

/**
 * Abstarct class to wrap the network services application specificly.
 * @author mdi
 *
 */

public abstract class Communication {
	
	public boolean DEBUG=true;

	public static int DEFAULTPORT=5000;
	public static String DEFAULTHOST="localhost";
	public static int DEFAULTTIMEOUT=1000;
	public static int MAXCLIENT=4;
	
	public static Client client=null;
	
	/**
	 * Create and start a server on the default port in its own thread and connect a local client to it.  
	 * @return
	 * @throws IOException
	 */
	public static boolean start_server() throws IOException{
		Runnable newserver= new Server();
		Thread serverthread=new Thread(newserver);
		
		serverthread.start();
		connect_server(DEFAULTHOST);
		return true;
	}
	
	/**
	 * Create and start a server on the specified port in its own thread and connect a local client to it.
	 * @param port
	 * @return
	 * @throws IOException
	 */
	public static boolean start_server(String port) throws IOException{
		int port_int=Integer.parseInt(port);
		Runnable newserver= new Server(port_int);
		Thread serverthread=new Thread(newserver);
		
		serverthread.start();
		connect_server(DEFAULTHOST, port_int);
		client.send_msg("Szerver indult: " + String.valueOf(port) + "\n");
		return true;
	}
	
	/**
	 * Connect to a server on the specified IP and default port, both the client and server receives a message to notify
	 * the user of a succesful connection
	 * @param ip
	 * @return
	 */
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

	/**
	 * Connect to a server on the specified IP and specified port, both the client and server receives a message to notify
	 * the user of a succesful connection
	 * @param ip
	 * @param port
	 * @return
	 */
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
	
	/**
	 * Send a message over the network, put it into the clients message queue.
	 * @param str
	 */
	public static void send_message(String str){
		client.send_msg(str + "\n");
	}
	/**
	 * Send a gamemap over the network, put it into the clients map queue.
	 * @param table
	 */
	public static void send_table(Map table){
		client.send_table_client(table);
	}
	
	
	/**
	 * Subscribe for message notification, with the listener.
	 * @param listener
	 */
	public static void subscribe_message(MsgListener listener){
		client.subscribe_msg(listener);
	}
	
	/**
	 * Subscribe for gamemap notification, with the listener.
	 * @param listener - the input parameter
	 */
	public static void subscribe_table(TableListener listener){
		client.subscribe_tbl(listener);
	}

	/**
	 * not implemented
	 * Get the currently connected users from the server.
	 * @return - returns the client_list
	 */
	public static ArrayList<String> get_clients(){
		ArrayList<String> client_list=null;
		
		return client_list;
	}
	/**
	 * Return the locally used client port.
	 * @return String
	 */
	public static String getlocalport(){
		return client.client_socket.getLocalSocketAddress().toString();
	}
}
