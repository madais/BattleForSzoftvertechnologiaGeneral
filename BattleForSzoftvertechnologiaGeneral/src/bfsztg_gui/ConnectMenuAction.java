package bfsztg_gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import communication.Communication;
import game.game.BattleForSzoftvertechnologiaGeneral_v2;

/**
 * Action listener for the connect menu option.
 * Shows an input dialog, then prompts for an IP number and a port string,
 * then valides the string. If it's not a valid IP&port number it shows
 * and alert pop-up window.  If it's correct, then connects to the
 * server and subscribes to the message stream.
 * @author fhenrir
 *
 */
public class ConnectMenuAction extends AbstractAction {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7083397607741696583L;

GUI parent;
	
	public ConnectMenuAction(GUI gui) {
        super("Connect");
        parent = gui;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String inputValue = JOptionPane.showInputDialog("Please input an IP with a port number:","100.000.000.000:1234");
		final String IP_AND_PORT_PATTERN = 
		        "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5]):[0-9]{4}";//FIXME

	      Pattern pattern = Pattern.compile(IP_AND_PORT_PATTERN);
	      Matcher matcher = pattern.matcher(inputValue);
	      if(!matcher.matches()){
	    	  JOptionPane.showMessageDialog(null, "That does not seem to be a valid ip and port number :(");
	      Communication.connect_server(inputValue);
	      } else {
	    	  String[] cim = inputValue.split(":");
	    	  Communication.connect_server(cim[0], Integer.parseInt(cim[1]));
	    	  parent.getBtnSend().addActionListener(new SendChatActionListener(parent));
	    	  Communication.subscribe_message(parent);
	    	  Communication.subscribe_table(BattleForSzoftvertechnologiaGeneral_v2.getgame());
	      }
	
	}

}
