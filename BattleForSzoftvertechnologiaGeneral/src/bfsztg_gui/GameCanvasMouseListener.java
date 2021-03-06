package bfsztg_gui;

import game.game.GameOn;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import bfsztg_settings.gameSettings;
/**
 * Listens on mouse click events on the Game Canvas
 * (which is in the middle of the screen with the hexagons),
 * then gives the processed information to the game logic.  
 * @author fhenrir
 *
 */
public class GameCanvasMouseListener implements MouseListener {
	
	GameCanvasPanel graphics;
	GUI parent;
	GameOn gameOn;
	
	/**
	 * Constructor with an appropriate game canvas, the canvas's panel,
	 * and a gameOn game logic class.
	 * @param gameCanvasPanel
	 * @param gui
	 * @param gameOn
	 */
	public GameCanvasMouseListener(GameCanvasPanel gameCanvasPanel, GUI gui, GameOn gameOn) {
		this.graphics = gameCanvasPanel;
		this.parent = gui;
		this.gameOn = gameOn;
	}

	/**
	 * Calls the callback method of the game logic class, with
	 * indexes of the map.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int[] clicked = getClickedCellID(e); // returns which number is the cell from the top left corner in (x,y) format
		int clickedX = clicked[0];
		int clickedY = clicked[1];
		System.out.println("Clicked X:" + clicked[0]);
		System.out.println("Clicked Y:" + clicked[1]);
		gameOn.thereWasAClick(clickedX,clickedY);			
	}
	
	/**
	 * Gets the X and Y coordinates from the MouseEvent,
	 * and runs a minimum search algorithm on the length of 
	 * the vector pointing from the MouseEvent and each cells center.
	 * @param e the MousEvent to be processed
	 * @return array of x and y coordinates of the cell
	 */
	private int[] getClickedCellID(MouseEvent e) {
		double distance;
		double minDistance = 999999999;
		int[] minCellCoord = new int[2];
		minCellCoord[0]=0;
		minCellCoord[1]=0;
		for(int i=0; i<24; i++) {
			for(int j=0; j<10; j++) {
				distance = Math.sqrt((e.getX()-graphics.getCellCentersX()[i][j])*(e.getX()-graphics.getCellCentersX()[i][j]) + (e.getY()-graphics.getCellCentersY()[i][j])*(e.getY()-graphics.getCellCentersY()[i][j]));			
				if(distance < minDistance){
					minDistance = distance;
					minCellCoord[0]=i;
					minCellCoord[1]=j;							
				}
			}
		}				
		return minCellCoord;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}		
	



}
