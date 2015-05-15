package bfsztg_gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
/**
 * When the player selects the about option from the menu bar, it 
 * shows a short information about the authors.
 * @author fhenrir
 *
 */
public class AboutMenuAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5304488078941272848L;

	public AboutMenuAction() {
        super("About");
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Battle for Szoftvertechnologia General 1.0, made by:\n"
				+ "\nDániel, Marton"
				+ "\nJános, Tóth"
				+ "\nZoltán, Elek");
	}

}
