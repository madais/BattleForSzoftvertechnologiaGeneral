package bfsztg_gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * Used for the close menu option.
 * Closes the program.
 * @author fhenrir
 *
 */
public class ExitMenuAction extends AbstractAction{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6875565736827287482L;

	public ExitMenuAction() {
        super("Exit");
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave us behind, my liege?", "Close?",  JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION)
		{
		   System.exit(0);
		}
	}
	
	
}
