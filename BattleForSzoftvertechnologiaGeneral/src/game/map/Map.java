package game.map;

import java.lang.reflect.Array;
import java.util.Random;

import bfsztg_gui.GraphicCell;
import bfsztg_gui.GraphicCell.Terrain;
import bfsztg_gui.GraphicCell.Unit;
import bfsztg_settings.gameSettings;

public class Map {
	public Area map[][];//mátrixot kéne
	
	public Map(){
		//map= new Area{Water(1,1), };//fel is kéne törlteni
		map=new Area[gameSettings.MAP_SIZE_X][gameSettings.MAP_SIZE_Y];
		//map[1][1]=new Field(1,1);//hosszadalmas lenne
		
		int waternum=15;//vízfelületek száma
		int forestnum=15;//erdõk száma
		int mountainnum=15;//hegyek száma
		int desertnum=15;//sivatagok száma
		int swampnum=15;//mocsárok száma
		
		for ( int row=1 ; row <= gameSettings.MAP_SIZE_X ; row++ ){
			for ( int column=1 ; column <= gameSettings.MAP_SIZE_Y ; column++ ){
				if (column==6 && (row==6 || row==12 || row==18)){
					map[row-1][column-1]=new Castle(row,column,null);
				}
				else{
					map[row-1][column-1]=new Field(row,column,null);
				}
			} 
		}
		
		for (int i=0; i<waternum;i++){
			Random dec = new Random();
			int row=dec.nextInt(gameSettings.MAP_SIZE_X-2)+2;
			int column=dec.nextInt(gameSettings.MAP_SIZE_Y)+1;
			if (column!=6 || (row!=4 && row!=8 && row!=12)){
				map[row-1][column-1]=new Water(row,column,null);
			}
		}
		
		for (int i=0; i<forestnum;i++){
			Random dec = new Random();
			int row=dec.nextInt(gameSettings.MAP_SIZE_X-2)+2;
			int column=dec.nextInt(gameSettings.MAP_SIZE_Y)+1;
			if (column!=6 || (row!=4 && row!=8 && row!=12)){
				map[row-1][column-1]=new Forest(row,column,null);
			}
		}
		
		for (int i=0; i<mountainnum;i++){
			Random dec = new Random();
			int row=dec.nextInt(gameSettings.MAP_SIZE_X-2)+2;
			int column=dec.nextInt(gameSettings.MAP_SIZE_Y)+1;
			if (column!=6 || (row!=4 && row!=8 && row!=12)){
				map[row-1][column-1]=new Mountain(row,column,null);
			}
		}
		
		for (int i=0; i<desertnum;i++){
			Random dec = new Random();
			int row=dec.nextInt(gameSettings.MAP_SIZE_X-2)+2;
			int column=dec.nextInt(gameSettings.MAP_SIZE_Y)+1;
			if (column!=6 || (row!=4 && row!=8 && row!=12)){
				map[row-1][column-1]=new Desert(row,column,null);
			}
		}
		
		for (int i=0; i<swampnum;i++){
			Random dec = new Random();
			int row=dec.nextInt(gameSettings.MAP_SIZE_X-2)+2;
			int column=dec.nextInt(gameSettings.MAP_SIZE_Y)+1;
			if (column!=6 || (row!=4 && row!=8 && row!=12)){
				map[row-1][column-1]=new Swamp(row,column,null);
			}
		}
		
		for (int row=1 ; row <= gameSettings.MAP_SIZE_X ; row++ ){
			for ( int column=1 ; column <= gameSettings.MAP_SIZE_Y ; column++ ){
				findneighbour(row,column);
			}
		}
		
		for ( int row=1 ; row <= gameSettings.MAP_SIZE_X ; row++ ){
			for ( int column=1 ; column <= gameSettings.MAP_SIZE_Y ; column++ )	{
					if (column!=gameSettings.MAP_SIZE_Y){
						System.out.print(map[row-1][column-1].id);
					}
					else {
						System.out.println(map[row-1][column-1].id);
					}
			} 
		}
		
	}
	
	public void printmap(){
		for ( int row=1 ; row <= gameSettings.MAP_SIZE_X ; row++ ){
			for ( int column=1 ; column <= gameSettings.MAP_SIZE_Y ; column++ )	{
				if (map[row-1][column-1].isFree()==true){
					if (column!=gameSettings.MAP_SIZE_Y){
						System.out.print(map[row-1][column-1].id);
					}
					else {
						System.out.println(map[row-1][column-1].id);
					}
				}
				else {
					if (column!=gameSettings.MAP_SIZE_Y){
						System.out.print(map[row-1][column-1].team+6);
					}
					else {
						System.out.println(map[row-1][column-1].team+6);
					}
				}
			} 
		}
	}
	
	void printneigh(int row, int column){
		System.out.print("masik fv");
		for (int i=0; i<6; i++){
				System.out.print(map[row-1][column-1].neighbours[i][0]);
				System.out.println(map[row-1][column-1].neighbours[i][1]);
		}
	}
	
	public void clearneighbour(){
		for (int i=0;i<gameSettings.MAP_SIZE_X;i++){
			for (int j=0;j<gameSettings.MAP_SIZE_Y;j++){
				map[i][j].neighnum=0;
			}
		}
	}
	
	public void findneighbour(int row, int column){
		int neigh[][];
		int cavnum=0;
//		if(map[row-1][column-1].gameunit!=null){
//			if (map[row-1][column-1].gameunit.getUnitid()!=3){
//				neigh = new int [6][2];
//			}
//			else{
//				neigh = new int [18][2];
//			}
//		}else{
//			neigh=new int[6][2];
//		}
		neigh=new int[18][2];
		int neighnum=0;
		if (row!=1 && column!=1){
			if (map[row-2][column-2].getmoveable()==true){
				neigh[neighnum][0]=row-1;
				neigh[neighnum][1]=column-1;
				neighnum++;
			}
		}
		if (row!=1 && column!=gameSettings.MAP_SIZE_Y){
			if (map[row-2][column-1].getmoveable()==true){
				neigh[neighnum][0]=row-1;
				neigh[neighnum][1]=column;
				neighnum++;
			}
		}
		if (column!=gameSettings.MAP_SIZE_Y){
			if (map[row-1][column].getmoveable()==true){
				neigh[neighnum][0]=row;
				neigh[neighnum][1]=column+1;
				neighnum++;
			}
		}
		if (row!=gameSettings.MAP_SIZE_X && column!=gameSettings.MAP_SIZE_Y){
			if (map[row][column-1].getmoveable()==true){
				neigh[neighnum][0]=row+1;
				neigh[neighnum][1]=column;
				neighnum++;
			}
		}
		if (row!=gameSettings.MAP_SIZE_X && column!=1){
			if (map[row][column-2].getmoveable()==true){
				neigh[neighnum][0]=row+1;
				neigh[neighnum][1]=column-1;
				neighnum++;
			}
		}
		if (column!=1){
			if (map[row-1][column-2].getmoveable()==true){
				neigh[neighnum][0]=row;
				neigh[neighnum][1]=column-1;
				neighnum++;
			}
		}
		if (map[row-1][column-1].gameunit!=null){
			if (map[row-1][column-1].gameunit.getUnitid()!=3){
				for (int i=0;i<map[row-1][column-1].getNeighnum();i++){
					for (int j=0;j<map[map[row-1][column-1].getneighbours(i, 0)][map[row-1][column-1].getneighbours(i, 1)].getNeighnum();j++){
						neigh[neighnum][0]=row;
						neigh[neighnum][1]=column-1;
						neighnum++;
						cavnum++;
					}
				}
			}
			if (map[row-1][column-1].gameunit.getUnitid()==1){
				map[row-1][column-1].neighbours=new int [neighnum][2];
				map[row-1][column-1].setNeighnum(neighnum);
				for (int i=0;i<neighnum;i++){
					map[row-1][column-1].neighbours[i][0]=neigh[i][0]-1;
					map[row-1][column-1].neighbours[i][1]=neigh[i][1]-1;
				}
			}else{
				map[row-1][column-1].neighbours=new int [neighnum-cavnum][2];
				map[row-1][column-1].setNeighnum(neighnum-cavnum);
				for (int i=0;i<neighnum-cavnum;i++){
					map[row-1][column-1].neighbours[i][0]=neigh[i][0]-1;
					map[row-1][column-1].neighbours[i][1]=neigh[i][1]-1;
				}
			}
			if (map[row-1][column-1].gameunit.getUnitid()==2){
				map[row-1][column-1].targets=new int [neighnum][2];
				for (int i=0;i<neighnum;i++){
					if (map[map[row-1][column-1].getneighbours(i, 0)][map[row-1][column-1].getneighbours(i, 1)].isShoot()==true){
						map[row-1][column-1].targets[i][0]=neigh[i][0]-1;
						map[row-1][column-1].targets[i][1]=neigh[i][1]-1;
					}
				}
			}
		}
	}
	
	
	void drawmap(){
		for ( int row=1 ; row <= gameSettings.MAP_SIZE_X ; row++ ){
			for ( int column=1 ; column <= gameSettings.MAP_SIZE_Y ; column++ ){
				map[row-1][column-1].drawarea();
			}
		}
	}
	
	public Area getArea(int posX, int posY){
		
		return map[posX][posY];
	}
	
	public Area setArea(int posX, int posY){
		return map[posX][posY];
	}
	
	public GraphicCell[][] toGraphicCellArray(){
		GraphicCell result[][] = null;
		result = new GraphicCell[gameSettings.MAP_SIZE_X][gameSettings.MAP_SIZE_Y];
		for ( int row=0 ; row < gameSettings.MAP_SIZE_X ; row++ ){
			for ( int column=0 ; column < gameSettings.MAP_SIZE_Y ; column++ ){
						
				
				// Setting the terrain
				switch (map[row][column].getId()) {				
				case 1: 					
					result[row][column] = new GraphicCell(Terrain.FIELDS);
					break;
				case 2: result[row][column] = new GraphicCell(Terrain.SEA);
						break;
				case 3: result[row][column] = new GraphicCell(Terrain.FOREST);
						break;
				case 4: result[row][column] = new GraphicCell(Terrain.MOUNTAINS);
						break;
				case 5: result[row][column] = new GraphicCell(Terrain.SNOW);
						break;
				case 6: result[row][column] = new GraphicCell(Terrain.DESERT);
						break;
				case 7: result[row][column] = new GraphicCell(Terrain.SWAMP);
						break;
				default:
					result[row][column] = new GraphicCell(Terrain.SEA);
					break;
				}
				
				if(map[row][column].gameunit != null){
					int unitId = map[row][column].gameunit.getUnitid();
					int unitTeam = map[row][column].gameunit.getTeam();
				
				if (unitId == 1 && unitTeam == 1){
					result[row][column].setUnit(Unit.RED_CAVALRY);
				} else if (unitId == 2 && unitTeam == 1){
					result[row][column].setUnit(Unit.RED_ARCHER);
				} else if (unitId == 3 && unitTeam == 1){
					result[row][column].setUnit(Unit.RED_INFANTRY);
				} else if (unitId == 1 && unitTeam == 2){
					result[row][column].setUnit(Unit.BLUE_CAVALRY);
				} else if (unitId == 2 && unitTeam == 2){
					result[row][column].setUnit(Unit.BLUE_ARCHER);
				} else if (unitId == 3 && unitTeam == 2){
					result[row][column].setUnit(Unit.BLUE_INFANTRY);
				}
				
				}
				
			} 
		}
		
		return result;
	}
	

}

