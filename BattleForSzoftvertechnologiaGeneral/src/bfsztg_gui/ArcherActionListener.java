package bfsztg_gui;

import game.game.GameOn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArcherActionListener implements ActionListener {

	private GameOn gameOn;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Archer button clicked.");
		gameOn.ArcherIsTheRecruit();
	}
	
	public ArcherActionListener(GameOn gameOn) {
		super();
		this.gameOn = gameOn;
	}

}

