package bfsztg_gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import bfsztg_gui.GraphicCell.Marker;
import bfsztg_gui.GraphicCell.Terrain;
import bfsztg_gui.GraphicCell.Unit;
import bfsztg_settings.*;
/**
 * The class of the map in the center of the GUI window.
 * @author fhenrir
 *
 */
public class GameCanvasPanel extends JPanel{
	
	GUI parent;
	GameImages images;
	int[][] cellCornersX;
	int[][] cellCornersY;
	int[][] cellCentersX;
	int[][] cellCentersY;
	GraphicCell[][] cells;
	
	/**
	 * An enum containing the recruitable types of units.
	 * It's NONE if no unit type has been selected yet.
	 * @author fhenrir
	 *
	 */
	public enum Recruit {
	    NONE, SPEARMAN, ARCHER, CAVALRY	    
	}
	
	/**
	 * Callback method. It paints the terrain tiles, the units,
	 * and then the marker images on top of eachother.
	 */
	protected void paintComponent(Graphics g) {	
		super.paintComponent(g);
		PaintTerrain(g,cells);
		PaintUnits(g,cells);
		PaintMarkers(g,cells);
		//Uncomment the following lines to show the cell centers
//		for(int i=0; i<gameSettings.MAP_SIZE_X; i++) {
//			for(int j=0; j<gameSettings.MAP_SIZE_Y; j++) {
//				g.drawOval(cellCentersX[i][j], cellCentersY[i][j], 2, 2);
//			}
//		}
	}
	
	/**
	 * Draws the unit images depending on the two dimensional
	 * GraphicCell array it gets as the second parameter.
	 * @param g
	 * @param cells2
	 */
	private void PaintUnits(Graphics g, GraphicCell[][] cells2) {
		for(int i=0; i<gameSettings.MAP_SIZE_X; i++) {
			for(int j=0; j<gameSettings.MAP_SIZE_Y; j++) {
				switch (cells[i][j].getUnit()){
				case RED_ARCHER:
					g.drawImage(images.getRedArcher(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				case BLUE_ARCHER:
					g.drawImage(images.getBlueArcher(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				case RED_INFANTRY:
					g.drawImage(images.getRedInfantry(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				case BLUE_INFANTRY:
					g.drawImage(images.getBlueInfantry(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				case RED_CAVALRY:
					g.drawImage(images.getRedCavalry(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				case BLUE_CAVALRY:
					g.drawImage(images.getBlueCavalry(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				default:
					break;
				
				}				
			}
		}		
	}

	/**
	 * Draws the marker images depending on the two dimensional
	 * GraphicCell array it gets as the second parameter.
	 * @param g
	 * @param cells2
	 */
	private void PaintMarkers(Graphics g, GraphicCell[][] cells2) {
		for(int i=0; i<gameSettings.MAP_SIZE_X; i++) {
			for(int j=0; j<gameSettings.MAP_SIZE_Y; j++) {
				switch (cells[i][j].getMarker()){
				case SELECTED:
					g.drawImage(images.getSelected(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				case MOVEABLE:
					g.drawImage(images.getMoveable(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				case ATTACKABLE:
					g.drawImage(images.getAttackable(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				default:
					break;
				
				}						
			}
		}		
	}
	
	
	/**
	 * Draws the terrain tile images depending on the two dimensional
	 * GraphicCell array it gets as the second parameter.
	 * @param g
	 * @param cells2
	 */
	private void PaintTerrain(Graphics g, GraphicCell[][] cells2) {
		for(int i=0; i<gameSettings.MAP_SIZE_X; i++) {
			for(int j=0; j<gameSettings.MAP_SIZE_Y; j++) {
				switch (cells[i][j].getTerrain()){
				case DESERT:
					g.drawImage(images.getDesert(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				case FIELDS:
					g.drawImage(images.getFields(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				case FOREST:
					g.drawImage(images.getForest(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				case HILLS:
					g.drawImage(images.getHills(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				case MOUNTAINS:
					g.drawImage(images.getMountains(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				case SEA:
					g.drawImage(images.getSea(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				case SNOW:
					g.drawImage(images.getSnow(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				case SWAMP:
					g.drawImage(images.getSwamp(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				case CASTLE:
					g.drawImage(images.getCastle(), cellCornersX[i][j], cellCornersY[i][j] , null);
					break;
				}				
			}
		}		
	}

	/**
	 * Constructor of the GameCanvasPanel:
	 * Initializes the GameImages class, which contains the game images.
	 * Calculates the cell corners and centers, and creates a new GraphicCell matrix.
	 * @param parent
	 */
	public GameCanvasPanel(GUI parent){
		   	this.parent = parent;
			images = new GameImages();
			cellCornersX = new int[gameSettings.MAP_SIZE_X][gameSettings.MAP_SIZE_Y];
			cellCornersY = new int[gameSettings.MAP_SIZE_X][gameSettings.MAP_SIZE_Y];
			cellCentersX = new int[gameSettings.MAP_SIZE_X][gameSettings.MAP_SIZE_Y];
			cellCentersY = new int[gameSettings.MAP_SIZE_X][gameSettings.MAP_SIZE_Y];
			int a = 65; //egy cella szélessége
			int b = 57; //magassága
			cells = new GraphicCell[gameSettings.MAP_SIZE_X][gameSettings.MAP_SIZE_Y];
			for(int i=0; i<gameSettings.MAP_SIZE_X; i++) {
				for(int j=0; j<gameSettings.MAP_SIZE_Y; j++) {
					if (i%2 == 0)
					{
						cellCornersX[i][j] = i*a - (a/4 + 1) * i;
						cellCornersY[i][j] = j* + b;
					}
					else
					{
						cellCornersX[i][j] = i*a - (a/4 + 1) * i;
						cellCornersY[i][j] = j*b + b/2;
					}
					cellCentersX[i][j] = cellCornersX[i][j] + a/2; 
					cellCentersY[i][j] = cellCornersY[i][j] + b/2;
					TestCellInit(i,j);
				}
			}
			
			
	}

	/**
	 * Can be used to create a GraphicCell matrix for debug and graphical demonstration.
	 * @param i
	 * @param j
	 */
	private void TestCellInit(int i, int j) {
		if(i == 3 && j ==6){
			cells[i][j]=new GraphicCell(Terrain.MOUNTAINS, Unit.NONE, Marker.NONE);
		}
		else if(i == 8 && j ==9){
			cells[i][j]=new GraphicCell(Terrain.SNOW, Unit.RED_ARCHER, Marker.NONE);
		}
		else if(i == 4 && j ==7){
			cells[i][j]=new GraphicCell(Terrain.SWAMP, Unit.BLUE_ARCHER, Marker.NONE);
		}
		else if(i == 6 && j ==2){
			cells[i][j]=new GraphicCell(Terrain.SEA, Unit.BLUE_INFANTRY, Marker.NONE);
		}
		else if(i == 6 && j ==3){
			cells[i][j]=new GraphicCell(Terrain.FOREST, Unit.BLUE_CAVALRY, Marker.NONE);
		}
		else if(i == 6 && j ==4){
			cells[i][j]=new GraphicCell(Terrain.FIELDS, Unit.RED_INFANTRY, Marker.NONE);
		}
		else if(i == 6 && j ==5){
			cells[i][j]=new GraphicCell(Terrain.MOUNTAINS, Unit.RED_CAVALRY, Marker.NONE);
		}
		else if(i == 2 && j ==2){
			cells[i][j]=new GraphicCell(Terrain.MOUNTAINS, Unit.RED_CAVALRY, Marker.SELECTED);
		}
		else if(i == 2 && j ==3){
			cells[i][j]=new GraphicCell(Terrain.MOUNTAINS, Unit.RED_CAVALRY, Marker.MOVEABLE);
		}
		else if(i == 2 && j ==4){
			cells[i][j]=new GraphicCell(Terrain.MOUNTAINS, Unit.RED_CAVALRY, Marker.ATTACKABLE);
		}
		else{
			cells[i][j]=new GraphicCell();				
		}		
	}

	

	public GameImages getImages() {
		return images;
	}

	public void setImages(GameImages images) {
		this.images = images;
	}

	public int[][] getCellCornersX() {
		return cellCornersX;
	}

	public void setCellCornersX(int[][] cellCornersX) {
		this.cellCornersX = cellCornersX;
	}

	public int[][] getCellCornersY() {
		return cellCornersY;
	}

	public void setCellCornersY(int[][] cellCornersY) {
		this.cellCornersY = cellCornersY;
	}

	public int[][] getCellCentersX() {
		return cellCentersX;
	}

	public void setCellCentersX(int[][] cellCentersX) {
		this.cellCentersX = cellCentersX;
	}

	public int[][] getCellCentersY() {
		return cellCentersY;
	}

	public void setCellCentersY(int[][] cellCentersY) {
		this.cellCentersY = cellCentersY;
	}

	public GraphicCell[][] getCells() {
		return cells;
	}

	/**
	 * Setter of the GraphicCell array. Automatically redraws the
	 * hex map, using the new GraphicCell matrix.
	 * @param cells
	 */
	public void setCells(GraphicCell[][] cells) {
		this.cells = cells;
		GameCanvasPanel.this.repaint();
	}

	/**
	 * Clears the markers from the map.
	 */
	public void ClearMarkers(){
		for (int i=0;i<gameSettings.MAP_SIZE_X;i++){
			 for (int j=0;j<gameSettings.MAP_SIZE_Y;j++){
				 cells [i][j].setMarker(Marker.NONE);
			 }
		 }
		GameCanvasPanel.this.repaint();
	}
	
	/**
	 * Puts moveable markers on the left or right column of
	 * hexagons. Use it for the recruit phase.
	 * @param leftside
	 */
	public void RecruitMarkers(boolean leftside){
		int columnToMark;
		if (leftside){
			columnToMark = 0;
		}
		else {
			columnToMark = 23;
		}
		for (int i=0;i<gameSettings.MAP_SIZE_Y;i++) {
			cells[columnToMark][i].setMarker(Marker.MOVEABLE);
		}
		GameCanvasPanel.this.repaint();
	}
	
	/**
	 * Puts a single marker (first parameter) on the hexagon indexed by posX and posY.
	 * @param marker
	 * @param posX
	 * @param posY
	 */
	public void setSingleMarker(Marker marker, int posX, int posY){		
		cells[posX][posY].setMarker(marker);		
		GameCanvasPanel.this.repaint();
		System.out.println("Cell X " + posX + "Y " + posY + "set to marker"  + marker);
	}
	
	
	}


	