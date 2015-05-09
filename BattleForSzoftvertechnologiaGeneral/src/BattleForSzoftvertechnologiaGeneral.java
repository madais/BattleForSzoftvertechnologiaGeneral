import game.game.GameOn;

import java.io.IOException;

import javax.swing.JOptionPane;

import bfsztg_gui.ArcherActionListener;
import bfsztg_gui.DefendActionListener;
import bfsztg_gui.EndTurnActionListener;
import bfsztg_gui.GUI;
import bfsztg_gui.WaitActionListener;
import bfsztg_gui.GameCanvasMouseListener;
import bfsztg_gui.InfantryActionListener;
import communication.*;


public class BattleForSzoftvertechnologiaGeneral {


	
	
	public static void main(String[] args) {
		// Instantiating the GUI
		try {
			GUI window = new GUI();
			window.getFrmBattleForSzoftvertechnolgia().setVisible(true);
			window.disableUnitRecruiting();
			//Game map mouse listener
			GameOn game = new GameOn(window);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
//Here be dragons.
//                      ^    ^
//                     / \  //\
//       |\___/|      /   \//  .\
//       /O  O  \__  /    //  | \ \
//      /     /  \/_/    //   |  \  \
//      @___@'    \/_   //    |   \   \ 
//         |       \/_ //     |    \    \ 
//         |        \///      |     \     \ 
//        _|_ /   )  //       |      \     _\
//       '/,_ _ _/  ( ; -.    |    _ _\.-~        .-~~~^-.
//       ,-{        _      `-.|.-~-.           .~         `.
//       '/\      /                 ~-. _ .-~      .-~^-.  \
//          `.   {            }                   /      \  \
//        .----~-.\        \-'                 .~         \  `. \^-.
//       ///.----..>    c   \             _ -~             `.  ^-`   ^-_
//         ///-._ _ _ _ _ _ _}^ - - - - ~                     ~--,   .-~

		
		//JOptionPane.showMessageDialog(null, "A buddhista zen telev�zi� f�rfi bemond�n�je vagyok. A Grzegorz Brz�czyszczykiewicz eml�kcsoportr�l tud�s�tunk. ");
		/*
		try{
		Communication.start_server();
		}
		catch (IOException ex){
			ex.printStackTrace();
		}*/
		
		try{
			CommTest.main(args);
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
	}

}