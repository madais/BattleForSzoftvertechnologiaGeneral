package communication;

import game.game.GameOn;


import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
//import java.util.List;

import communication.MsgListener;
import game.map.*;
//import java.util.List;
/**
 * Client class for TCP/IP communication for the game
 * @author mdi
 *
 */
public class Client extends Communication implements Runnable{
	
	

	Socket client_socket=null;
	BufferedInputStream read_socket=null;
	BufferedOutputStream write_socket=null;
	
	XMLEncoder xml_out=null;
	XMLDecoder xml_in=null;
	
	int file_in_cnt=0;
	int file_out_cnt=0;
	
	ArrayList<MsgListener> msg_list=new ArrayList<MsgListener>();
	ArrayList<TableListener> table_list= new ArrayList<TableListener>();
	
	ArrayList<String> msg_out_list=new ArrayList<String>();
	ArrayList<String> msg_in_list=new ArrayList<String>();
	
	//ArrayList<GameOn> table_out_list=new ArrayList<GameOn>();
	//ArrayList<GameOn> table_in_list=new ArrayList<GameOn>();
	
	ArrayList<Map> table_out_list=new ArrayList<Map>();
	ArrayList<Map> table_in_list=new ArrayList<Map>();
	
	
	@Override
	/**
	 * Overrided run method for multithreading
	 * Checks if various messagequeues are empty, if not, sends or recieves the message, based on the type of the queue 
	 */
	public void run() {
		while(true){
				
			if (DEBUG){
				//System.out.println("Client-thread kezd");
			}
			
			if (!msg_in_list.isEmpty()){
					recieve_msg();
				}
				
				if (!table_in_list.isEmpty()){
					recieve_table();
				}
				
				
				if (!msg_out_list.isEmpty()){
					try{
					send_data(msg_out_list.remove(0));
					}
					catch (IOException ex){
						ex.printStackTrace();
					}
				}
				
				if (!table_out_list.isEmpty()){
					try{
					send_data(table_out_list.remove(0));
					}
					catch (IOException ex){
						ex.printStackTrace();
					}
				}
				
				try{
					read_data();
				}
				catch (IOException ex){
					ex.printStackTrace();
				}
			
/*
			try{
				Thread.sleep(100);
			}
			catch (InterruptedException ex){
				ex.printStackTrace();
			}*/
		}
	}
/**
 * Null parameter constructor
 * Creates a client, a socket for it, connects to the default server and binds its streams.
 * @throws IOException
 */
	public Client() throws IOException{
		super();
		this.client_socket=new Socket(DEFAULTHOST, DEFAULTPORT);
		
		this.write_socket=new BufferedOutputStream(client_socket.getOutputStream());
		this.read_socket=new BufferedInputStream(client_socket.getInputStream());
		
		
		if (DEBUG){
			System.out.println("Client socket created:" + client_socket.getPort());
		}
		
	} 
	/**
	 * Creates a client, a socket for it, connects to the specified host server and binds its streams.
	 * @param host
	 * @throws IOException
	 */
	public Client(String host) throws IOException{
		super();
		this.client_socket=new Socket(host, DEFAULTPORT);
		
		this.read_socket=new BufferedInputStream(client_socket.getInputStream());
		this.write_socket=new BufferedOutputStream(client_socket.getOutputStream());
		
		
		if (DEBUG){
			System.out.println("Client socket created:" + client_socket.getPort());
		}
	} 
	/**
	 * Creates a client, a socket for it, connects to the specified server on the specified port and binds its streams.
	 * @param host
	 * @param port
	 * @throws IOException
	 */
	public Client(String host, int port) throws IOException{
		super();
		this.client_socket=new Socket(host, port);
		
		this.read_socket=new BufferedInputStream(client_socket.getInputStream());
		this.write_socket=new BufferedOutputStream(client_socket.getOutputStream());
		
		if (DEBUG){
			System.out.println("Client socket created:" + client_socket.getPort());
		}
	} 
	
	/**
	 * Reads incoming XML, saves it in a file, and updates the approriate messagequeue
	 * @return
	 * @throws IOException
	 */
	private synchronized boolean read_data() throws IOException{
		if (read_socket.available()>0){
			
			File dir = new File("msg");

			// if the directory does not exist, create it
			if (!dir.exists()) {
				try{
			        dir.mkdir();  
			    } 
			    catch(SecurityException ex){
			        ex.printStackTrace();
			    }        
			    
			}
			
			File file_in= new File("./msg/recieved_" + String.valueOf(file_in_cnt) + ".xml");
			xml_in=new XMLDecoder(read_socket);
			Object o=xml_in.readObject();
			
			FileOutputStream file_ir=new FileOutputStream(file_in);
			XMLEncoder file_xml= new XMLEncoder(file_ir);
			file_xml.writeObject(o);
			file_xml.flush();
			file_xml.close();
			
			System.out.println(o.getClass());
			if (o instanceof String){
				msg_in_list.add((String)o);
			}
			
			if (o instanceof Map){
				//table_in_list.add((GameOn)o);
				table_in_list.add((Map)o);
			}
			
			file_in_cnt++;
			
		}
		return true;
	}
	/**
	 * Send a generic object over the network, and save it in XML
	 * @param o
	 * @return 
	 * @throws IOException
	 */
	public synchronized boolean send_data(Object o) throws IOException{
		
		File dir = new File("msg");

		// if the directory does not exist, create it
		if (!dir.exists()) {
			try{
		        dir.mkdir();  
		    } 
		    catch(SecurityException ex){
		        ex.printStackTrace();
		    }        
		    
		}
		
		File file_out=new File("./msg/sent_" + String.valueOf(file_out_cnt) + ".xml");
		FileOutputStream file_ir= new FileOutputStream(file_out);
		XMLEncoder file_xml= new XMLEncoder(file_ir);
		file_xml.writeObject(o);
		file_xml.flush();
		file_xml.close();
		file_ir.close();
		FileInputStream file_olvas= new FileInputStream(file_out);
		
		
		while	(file_olvas.available()>0){ 
			write_socket.write(file_olvas.read());
		}
		write_socket.write(0);	//lezáró 0;
		write_socket.flush();
		
		file_olvas.close();
		file_out_cnt++;
		
		return true;
	}
	/**
	 * Add a String message to the sendable queue
	 * Utility function for other classes
	 * @param msg
	 * @return
	 */
	public boolean send_msg(String msg){
		//msg_out_list.add(msg);
		msg_out_list.add(msg);
		return true;
	}
	
	/**
	 * Callback for the subscribed listeners. Call each of them with the recieved message
	 */
	public void recieve_msg(){
		String msg=msg_in_list.remove(0);
		for (MsgListener subscriber :msg_list){
			subscriber.recieveMsg(msg);
		}
		return ;
	}
/**
 * Add a gamemap message to the sendable queue
 * Utility function for other classes
 * @param table
 * @return
 */
 
	public boolean send_table_client(Map table){
		
		table_out_list.add(table);
		return true;
	}
	/**
	 * Callback for the subscribed listeners. Call each of them with the recieved message
	 */
	public void recieve_table(){
		//GameOn table=table_in_list.remove(0);
		Map table=table_in_list.remove(0);
		for (TableListener subscriber :table_list){
			subscriber.recieveTable(table);
		}
		return ;
	}
	/**
	 * Subscribe service for String messages. Updates the subsciberlist.
	 * @param listener
	 */
	public void subscribe_msg(MsgListener listener){
		msg_list.add(listener);
	}
/**
 * Subscribe service for gamemap messages. Updates the subsciberlist.
 * @param listener
 */
	public void subscribe_tbl(TableListener listener){
		table_list.add(listener);
	}

	/**
	 * Close all open sockets.
	 */
	public void close_all(){
		try {
		client_socket.close();
		read_socket.close();
		write_socket.close();
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
		
		xml_out.close();
		xml_in.close();
		
	}
	
}