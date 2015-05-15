package bfsztg_gui;

import game.game.GameOn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Called when the player activates the Archer button.
 * Sets the recruited unit type to Archer.
 * @author fhenrir
 *
 */
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

