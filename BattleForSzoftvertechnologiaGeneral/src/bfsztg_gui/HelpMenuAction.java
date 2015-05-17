package bfsztg_gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 * Shows a help window when the help option is selected
 * from the menu bar.
 * @author fhenrir
 *
 */
public class HelpMenuAction extends AbstractAction {

	public HelpMenuAction() {
		super("Help");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null,
				"Welcome to the help of Battle for Szoftvertechnologia General 1.0\n"
				+ ""
				+ ""
				+ ""
				+ ""
				+ ""
				+ ""
				+ ""
				+ ""
				+ ""
				+ "");
	}

}
