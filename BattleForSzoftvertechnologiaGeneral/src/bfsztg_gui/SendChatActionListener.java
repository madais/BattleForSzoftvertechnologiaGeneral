package bfsztg_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import communication.Communication;

public class SendChatActionListener implements ActionListener {

	String sentTextFromGUI = new String("");
	
	public SendChatActionListener(GUI thisGUI) {
		// TODO Auto-generated constructor stub
		sentTextFromGUI = thisGUI.getChatTextField().getText();
		Communication.send_message(sentTextFromGUI);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
