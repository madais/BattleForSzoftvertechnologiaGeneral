package bfsztg_gui;

import game.game.GameOn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Called when the player activates the Cavalry button.
 * Sets the recruited unit type to Cavalry.
 * @author fhenrir
 *
 */
public class CavalryActionListener implements ActionListener {

	private GameOn gameOn;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Archer button clicked.");
		gameOn.CavalryIsTheRecruit();
	}
	
	public CavalryActionListener(GameOn gameOn) {
		super();
		this.gameOn = gameOn;
	}
}
