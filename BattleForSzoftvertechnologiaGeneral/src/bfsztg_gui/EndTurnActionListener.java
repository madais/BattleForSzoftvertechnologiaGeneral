package bfsztg_gui;

import game.game.GameOn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Called when the player activates the end turn button on the 
 * right button bar. Notifies the game logic about the end of the round.
 * @author fhenrir
 *
 */
public class EndTurnActionListener implements ActionListener {

	private GameOn gameOn;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("End turn button clicked.");
		gameOn.EndTurn();
	}
	
	public EndTurnActionListener(GameOn gameOn) {
		super();
		this.gameOn = gameOn;
	}

}
