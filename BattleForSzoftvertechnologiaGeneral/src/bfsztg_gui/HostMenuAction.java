package bfsztg_gui;

import game.game.GameOn;

import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import communication.CommTest;
import communication.Communication;
<<<<<<< HEAD
import game.game.BattleForSzoftvertechnologiaGeneral_v2;
=======

/**
 * Action listener for the host menu option.
 * Shows an input dialog, then prompts for a port string,
 * then valides the string. If it's not a valid port number it shows
 * and alert pop-up window.  If it's correct, then creates the
 * server and subscribes to the message stream.
 * @author fhenrir
 *
 */
>>>>>>> branch 'master' of https://github.com/madais/BattleForSzoftvertechnologiaGeneral.git
public class HostMenuAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5638988311864715609L;

	GUI parent;
	
	public HostMenuAction(GUI gui) {
        super("Host");
        this.parent = gui;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		
		
		String inputValue = JOptionPane.showInputDialog("Please input a port number:","1234"); 
		
		final String PORT_PATTERN = 
		        "[0-9]{4}";

	      Pattern pattern = Pattern.compile(PORT_PATTERN);
	      Matcher matcher = pattern.matcher(inputValue);
	      if(!matcher.matches()){
	    	  JOptionPane.showMessageDialog(null, "That does not seem to be a valid port number :(");
	      } else
	      {
	    	try{
	    	  Communication.start_server(inputValue);
	    	  Communication.subscribe_message(this.parent);
	    	  //Communication.subscribe_table(BattleForSzoftvertechnologiaGeneral_v2.getgame());
	    	  //Communication.send_message("Ez a cim: " + Communication.getlocalport());;
	    	  parent.getBtnSend().addActionListener(new SendChatActionListener(parent)); 
	    	}
	    	catch (Exception ex){
	    		ex.printStackTrace();
	    		
	    	}
	    	}
		
		
	}

	

}
