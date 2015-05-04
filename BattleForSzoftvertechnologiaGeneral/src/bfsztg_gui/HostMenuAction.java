package bfsztg_gui;

import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class HostMenuAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5638988311864715609L;

	public HostMenuAction() {
        super("Host");
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
	    	  //place of hosting method
	      }
		
		
	}

	

}
