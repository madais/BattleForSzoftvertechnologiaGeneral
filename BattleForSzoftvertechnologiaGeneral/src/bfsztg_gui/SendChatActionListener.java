package bfsztg_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import communication.Communication;
/**
 * Called when the Send button is clicked on the bottom of the chat panel.
 * Calls the send message method of the communication class.
 * @author fhenrir
 *
 */
public class SendChatActionListener implements ActionListener {

	GUI parent;
	String sentTextFromGUI = new String("");
	
	public SendChatActionListener(GUI thisGUI) {
		// TODO Auto-generated constructor stub
		parent = thisGUI;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		sentTextFromGUI = parent.getChatTextField().getText();
		Communication.send_message(sentTextFromGUI);
		//Communication.send_message(" kirsz vmit-e ");
		
	}

}