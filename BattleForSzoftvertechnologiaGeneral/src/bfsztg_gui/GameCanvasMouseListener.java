package bfsztg_gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameCanvasMouseListener implements MouseListener {
	
	GameCanvasPanel graphics;
	GUI parent;

	public GameCanvasMouseListener(GameCanvasPanel gameCanvasPanel, GUI gui) {
		graphics = gameCanvasPanel;
		parent = gui;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int[] clicked = getClickedCellID(e); // returns which number is the cell from the top left corner in (x,y) format
		int clickedX = clicked[0];
		int clickedY = clicked[1];
		System.out.println("Clicked X:" + clicked[0]);
		System.out.println("Clicked Y:" + clicked[1]);
		//place of game logic event handler
		
		//graphics.setCells();
		//vagy
		//janiFuggvenye(clickedX,clickedY,parent,graphics) ahol parent a gui az ikonokkal
		// a graphics pedig a panel ahol a hatszogletu cuccok vannak
		//graphics.setCells() allitgatas utan pedig automatikusan ujra rajzolja
		graphics.repaint();	
	}
	
	private int[] getClickedCellID(MouseEvent e) {
		double distance;
		double minDistance = 999999999;
		int[] minCellCoord = new int[2];
		minCellCoord[0]=0;
		minCellCoord[1]=0;
		for(int i=0; i<24; i++) {
			for(int j=0; j<10; j++) {
				distance = Math.sqrt((e.getX()-graphics.getCellCentersX()[i][j])*(e.getX()-graphics.getCellCentersX()[i][j]) + (e.getY()-graphics.getCellCentersX()[i][j])*(e.getY()-graphics.getCellCentersX()[i][j]));						
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
