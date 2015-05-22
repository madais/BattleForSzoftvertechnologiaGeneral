package game.map;

import java.lang.reflect.Array;
import java.util.Random;

import bfsztg_gui.GraphicCell;
import bfsztg_gui.GraphicCell.Terrain;
import bfsztg_gui.GraphicCell.Unit;
import bfsztg_settings.gameSettings;

/**
 * This class contains the fields and the units
 *
 */
public class Map {
	public Area map[][];//m�trixot k�ne
	
	/**
	 * Initializing map
	 */
	public Map(){
		super();
		map=new Area[gameSettings.MAP_SIZE_X][gameSettings.MAP_SIZE_Y];
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
	}
	
	/**
	 * Random map initializing
	 * @param random
	 */
	public Map(boolean random){
		//map= new Area{Water(1,1), };//fel is k�ne t�rlteni
		map=new Area[gameSettings.MAP_SIZE_X][gameSettings.MAP_SIZE_Y];
		//map[1][1]=new Field(1,1);//hosszadalmas lenne
		
		int waternum=15;//v�zfel�letek sz�ma
		int forestnum=15;//erd�k sz�ma
		int mountainnum=15;//hegyek sz�ma
		int desertnum=15;//sivatagok sz�ma
		int swampnum=15;//mocs�rok sz�ma
		
		
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
			if (column!=6 || (row!=6 && row!=12 && row!=18)){
				map[row-1][column-1]=new Water(row,column,null);
			}
		}
		
		for (int i=0; i<forestnum;i++){
			Random dec = new Random();
			int row=dec.nextInt(gameSettings.MAP_SIZE_X-2)+2;
			int column=dec.nextInt(gameSettings.MAP_SIZE_Y)+1;
			if (column!=6 || (row!=6 && row!=12 && row!=18)){
				map[row-1][column-1]=new Forest(row,column,null);
			}
		}
		
		for (int i=0; i<mountainnum;i++){
			Random dec = new Random();
			int row=dec.nextInt(gameSettings.MAP_SIZE_X-2)+2;
			int column=dec.nextInt(gameSettings.MAP_SIZE_Y)+1;
			if (column!=6 || (row!=6 && row!=12 && row!=18)){
				map[row-1][column-1]=new Mountain(row,column,null);
			}
		}
		
		for (int i=0; i<desertnum;i++){
			Random dec = new Random();
			int row=dec.nextInt(gameSettings.MAP_SIZE_X-2)+2;
			int column=dec.nextInt(gameSettings.MAP_SIZE_Y)+1;
			if (column!=6 || (row!=6 && row!=18 && row!=12)){
				map[row-1][column-1]=new Desert(row,column,null);
			}
		}
		
		for (int i=0; i<swampnum;i++){
			Random dec = new Random();
			int row=dec.nextInt(gameSettings.MAP_SIZE_X-2)+2;
			int column=dec.nextInt(gameSettings.MAP_SIZE_Y)+1;
			if (column!=6 || (row!=6 && row!=18 && row!=12)){
				map[row-1][column-1]=new Swamp(row,column,null);
			}
		}
		
//		for (int row=1 ; row <= gameSettings.MAP_SIZE_X ; row++ ){
//			for ( int column=1 ; column <= gameSettings.MAP_SIZE_Y ; column++ ){
//				findneighbour(row,column);
//			}
//		}
		
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
	
	public void printneigh(int row, int column){
		for (int i=0; i<map[row][column].getNeighnum(); i++){
				System.out.print(map[row][column].neighbours[i][0]);
				System.out.println(map[row][column].neighbours[i][1]);
		}
	}
	
	public void clearneighbour(){
		for (int i=0;i<gameSettings.MAP_SIZE_X;i++){
			for (int j=0;j<gameSettings.MAP_SIZE_Y;j++){
				if (map[i][j].neighnum!=0){
					map[i][j].delneigs();
				}				
			}
		}
	}
	
	/**
	 * Find one field's neighbours
	 * @param row
	 * @param column
	 */
	public void findneighbour(int row, int column){
		map[row][column].setNeighnum(0);
		map[row][column].neighbours=new int[48][2];
		if (row%2==1){
			if (map[row][column].gameunit!=null){
				if (row!=0){
					if (map[row-1][column].getmoveable()==true){
						map[row][column].neighbours[map[row][column].getNeighnum()][0]=row-1;
						map[row][column].neighbours[map[row][column].getNeighnum()][1]=column;
						map[row][column].neighnum++;
					}
				}
				if (row!=0 && column!=(gameSettings.MAP_SIZE_Y-1)){
					if (map[row-1][column+1].getmoveable()==true){
						map[row][column].neighbours[map[row][column].getNeighnum()][0]=row-1;
						map[row][column].neighbours[map[row][column].getNeighnum()][1]=column+1;
						map[row][column].neighnum++;
					}
				}
				if (column!=0){
					if (map[row][column-1].getmoveable()==true){
						map[row][column].neighbours[map[row][column].getNeighnum()][0]=row;
						map[row][column].neighbours[map[row][column].getNeighnum()][1]=column-1;
						map[row][column].neighnum++;
					}
				}
				if (column!=(gameSettings.MAP_SIZE_Y-1)){
					if (map[row][column+1].getmoveable()==true){
						map[row][column].neighbours[map[row][column].getNeighnum()][0]=row;
						map[row][column].neighbours[map[row][column].getNeighnum()][1]=column+1;
						map[row][column].neighnum++;
					}
				}
				if (row!=(gameSettings.MAP_SIZE_X-1)){
					if (map[row+1][column].getmoveable()==true){
						map[row][column].neighbours[map[row][column].getNeighnum()][0]=row+1;
						map[row][column].neighbours[map[row][column].getNeighnum()][1]=column;
						map[row][column].neighnum++;
					}
				}
				if (row!=(gameSettings.MAP_SIZE_X-1) && column!=(gameSettings.MAP_SIZE_Y-1)){
					if (map[row+1][column+1].getmoveable()==true){
						map[row][column].neighbours[map[row][column].getNeighnum()][0]=row+1;
						map[row][column].neighbours[map[row][column].getNeighnum()][1]=column+1;
						map[row][column].neighnum++;
					}
				}
			}
		}
		else{
			if (row!=0){
				if (map[row-1][column].getmoveable()==true){
					map[row][column].neighbours[map[row][column].getNeighnum()][0]=row-1;
					map[row][column].neighbours[map[row][column].getNeighnum()][1]=column;
					map[row][column].neighnum++;
				}
			}
			if (row!=0 && column!=0){
				if (map[row-1][column-1].getmoveable()==true){
					map[row][column].neighbours[map[row][column].getNeighnum()][0]=row-1;
					map[row][column].neighbours[map[row][column].getNeighnum()][1]=column-1;
					map[row][column].neighnum++;
				}
			}
			if (column!=0){
				if (map[row][column-1].getmoveable()==true){
					map[row][column].neighbours[map[row][column].getNeighnum()][0]=row;
					map[row][column].neighbours[map[row][column].getNeighnum()][1]=column-1;
					map[row][column].neighnum++;
				}
			}
			if (column!=(gameSettings.MAP_SIZE_Y-1)){
				if (map[row][column+1].getmoveable()==true){
					map[row][column].neighbours[map[row][column].getNeighnum()][0]=row;
					map[row][column].neighbours[map[row][column].getNeighnum()][1]=column+1;
					map[row][column].neighnum++;
				}
			}
			if (row!=(gameSettings.MAP_SIZE_X-1)){
				if (map[row+1][column].getmoveable()==true){
					map[row][column].neighbours[map[row][column].getNeighnum()][0]=row+1;
					map[row][column].neighbours[map[row][column].getNeighnum()][1]=column;
					map[row][column].neighnum++;
				}
			}
			if (row!=(gameSettings.MAP_SIZE_X-1) && column!=0){
				if (map[row+1][column-1].getmoveable()==true){
					map[row][column].neighbours[map[row][column].getNeighnum()][0]=row+1;
					map[row][column].neighbours[map[row][column].getNeighnum()][1]=column-1;
					map[row][column].neighnum++;
				}
			}
		}
		if (map[row][column].getGameunit()!=null){
			if (map[row][column].getGameunit().getUnitid()==1){
				findcavneighb(row, column, map[row][column].getNeighnum());
			}
			if (map[row][column].getGameunit().getUnitid()==2){
				findarchtarger(row, column);
			}
		}
	}
	
	/**
	 * Find the second neighbours
	 * @param row
	 * @param column
	 * @param neignum
	 */
	void findcavneighb(int row, int column, int neignum){
		for (int i=0;i<neignum;i++){
//			if (map[map[row][column].getneighbours(i, 0)][map[row][column].getneighbours(i, 1)].getGameunit()!=null){
				if (map[row][column].getneighbours(i, 0) % 2 == 1){
					if (map[row][column].getneighbours(i, 0)!=0){
						if (map[map[row][column].getneighbours(i, 0)-1][map[row][column].getneighbours(i, 1)].getmoveable()==true){
							map[row][column].neighbours[map[row][column].getNeighnum()][0]=map[row][column].getneighbours(i, 0)-1;
							map[row][column].neighbours[map[row][column].getNeighnum()][1]=map[row][column].getneighbours(i, 1);
							map[row][column].neighnum++;
						}
					}
					if (map[row][column].getneighbours(i, 0)!=0 && map[row][column].getneighbours(i, 1)!=gameSettings.MAP_SIZE_Y-1){
						if (map[map[row][column].getneighbours(i, 0)-1][map[row][column].getneighbours(i, 1)+1].getmoveable()==true){
							map[row][column].neighbours[map[row][column].getNeighnum()][0]=map[row][column].getneighbours(i, 0)-1;
							map[row][column].neighbours[map[row][column].getNeighnum()][1]=map[row][column].getneighbours(i, 1)+1;
							map[row][column].neighnum++;
						}
					}
					if (map[row][column].getneighbours(i, 1)!=gameSettings.MAP_SIZE_Y-1){
						if (map[map[row][column].getneighbours(i, 0)][map[row][column].getneighbours(i, 1)+1].getmoveable()==true){
							map[row][column].neighbours[map[row][column].getNeighnum()][0]=map[row][column].getneighbours(i, 0);
							map[row][column].neighbours[map[row][column].getNeighnum()][1]=map[row][column].getneighbours(i, 1)+1;
							map[row][column].neighnum++;
						}
					}
					if (map[row][column].getneighbours(i, 0)!=gameSettings.MAP_SIZE_X-1 && map[row][column].getneighbours(i, 1)!=gameSettings.MAP_SIZE_Y-1){
						if (map[map[row][column].getneighbours(i, 0)+1][map[row][column].getneighbours(i, 1)+1].getmoveable()==true){
							map[row][column].neighbours[map[row][column].getNeighnum()][0]=map[row][column].getneighbours(i, 0)+1;
							map[row][column].neighbours[map[row][column].getNeighnum()][1]=map[row][column].getneighbours(i, 1)+1;
							map[row][column].neighnum++;
						}
					}
					if (map[row][column].getneighbours(i, 0)!=gameSettings.MAP_SIZE_X-1){
						if (map[map[row][column].getneighbours(i, 0)+1][map[row][column].getneighbours(i, 1)].getmoveable()==true){
							map[row][column].neighbours[map[row][column].getNeighnum()][0]=map[row][column].getneighbours(i, 0)+1;
							map[row][column].neighbours[map[row][column].getNeighnum()][1]=map[row][column].getneighbours(i, 1);
							map[row][column].neighnum++;
						}
					}
					if (map[row][column].getneighbours(i, 1)!=0){
						if (map[map[row][column].getneighbours(i, 0)][map[row][column].getneighbours(i, 1)-1].getmoveable()==true){
							map[row][column].neighbours[map[row][column].getNeighnum()][0]=map[row][column].getneighbours(i, 0);
							map[row][column].neighbours[map[row][column].getNeighnum()][1]=map[row][column].getneighbours(i, 1)-1;
							map[row][column].neighnum++;
						}
					}
				}else{
					if (map[row][column].getneighbours(i, 0)!=0 && map[row][column].getneighbours(i, 1)!=0){
						if (map[map[row][column].getneighbours(i, 0)-1][map[row][column].getneighbours(i, 1)-1].getmoveable()==true){
							map[row][column].neighbours[map[row][column].getNeighnum()][0]=map[row][column].getneighbours(i, 0)-1;
							map[row][column].neighbours[map[row][column].getNeighnum()][1]=map[row][column].getneighbours(i, 1)-1;
							map[row][column].neighnum++;
						}
					}
					if (map[row][column].getneighbours(i, 0)!=0){
						if (map[map[row][column].getneighbours(i, 0)-1][map[row][column].getneighbours(i, 1)].getmoveable()==true){
							map[row][column].neighbours[map[row][column].getNeighnum()][0]=map[row][column].getneighbours(i, 0)-1;
							map[row][column].neighbours[map[row][column].getNeighnum()][1]=map[row][column].getneighbours(i, 1);
							map[row][column].neighnum++;
						}
					}
					if (map[row][column].getneighbours(i, 1)!=gameSettings.MAP_SIZE_Y-1){
						if (map[map[row][column].getneighbours(i, 0)][map[row][column].getneighbours(i, 1)+1].getmoveable()==true){
							map[row][column].neighbours[map[row][column].getNeighnum()][0]=map[row][column].getneighbours(i, 0);
							map[row][column].neighbours[map[row][column].getNeighnum()][1]=map[row][column].getneighbours(i, 1)+1;
							map[row][column].neighnum++;
						}
					}
					if (map[row][column].getneighbours(i, 0)!=gameSettings.MAP_SIZE_X-1){
						if (map[map[row][column].getneighbours(i, 0)+1][map[row][column].getneighbours(i, 1)].getmoveable()==true){
							map[row][column].neighbours[map[row][column].getNeighnum()][0]=map[row][column].getneighbours(i, 0)+1;
							map[row][column].neighbours[map[row][column].getNeighnum()][1]=map[row][column].getneighbours(i, 1);
							map[row][column].neighnum++;
						}
					}
					if (map[row][column].getneighbours(i, 0)!=gameSettings.MAP_SIZE_X-1 && map[row][column].getneighbours(i, 1)!=0){
						if (map[map[row][column].getneighbours(i, 0)+1][map[row][column].getneighbours(i, 1)-1].getmoveable()==true){
							map[row][column].neighbours[map[row][column].getNeighnum()][0]=map[row][column].getneighbours(i, 0)+1;
							map[row][column].neighbours[map[row][column].getNeighnum()][1]=map[row][column].getneighbours(i, 1)-1;
							map[row][column].neighnum++;
						}
					}
					if (map[row][column].getneighbours(i, 1)!=0){
						if (map[map[row][column].getneighbours(i, 0)][map[row][column].getneighbours(i, 1)-1].getmoveable()==true){
							map[row][column].neighbours[map[row][column].getNeighnum()][0]=map[row][column].getneighbours(i, 0);
							map[row][column].neighbours[map[row][column].getNeighnum()][1]=map[row][column].getneighbours(i, 1)-1;
							map[row][column].neighnum++;
						}
					}
				}
			}
		}
//	}
	
	/**
	 * Find targets for archer
	 * @param row
	 * @param column
	 */
	void findarchtarger(int row, int column){
		//c�lpontok keres�se l�t�von bel�l
		map[row][column].setTargetnum(0);;
		map[row][column].targets= new int[12][2];
		if (row>1){
			if (map[row-2][column].getmoveable()==true){
				map[row][column].setTargets(row-2,column);
			}
		}
		if (column>1){
			if (map[row][column-2].getmoveable()==true){
				map[row][column].setTargets(row,column-2);
			}
		}
		if (row<gameSettings.MAP_SIZE_X-2){
			if (map[row+2][column].getmoveable()==true){
				map[row][column].setTargets(row+2,column);
			}
		}
		if (column<gameSettings.MAP_SIZE_Y-2){
			if (map[row][column+2].getmoveable()==true){
				map[row][column].setTargets(row,column+2);
			}
		}
		if (row>1 && column!=0){
			if (map[row-2][column-1].getmoveable()==true){
				map[row][column].setTargets(row-2,column-1);
			}
		}
		if (row>1 && column!=gameSettings.MAP_SIZE_Y-1){
			if (map[row-2][column+1].getmoveable()==true){
				map[row][column].setTargets(row-2,column+1);
			}
		}
		if (row<gameSettings.MAP_SIZE_X-2 && column!=gameSettings.MAP_SIZE_Y-1){
			if (map[row+2][column+1].getmoveable()==true){
				map[row][column].setTargets(row+2,column+1);
			}
		}
		if (row<gameSettings.MAP_SIZE_X-2 && column!=0){
			if (map[row+2][column-1].getmoveable()==true){
				map[row][column].setTargets(row+2,column-1);
			}
		}
		if (row%2==1){
			if (row!=0 && column!=0){
				if (map[row-1][column-1].getmoveable()==true){
					map[row][column].setTargets(row-1,column-1);
				}
			}
			if (row!=0 && column<gameSettings.MAP_SIZE_Y-3){
				if (map[row-1][column+2].getmoveable()==true){
					map[row][column].setTargets(row-1,column+2);
				}
			}
			if (row!=gameSettings.MAP_SIZE_X-1 && column<gameSettings.MAP_SIZE_Y-3){
				if (map[row+1][column+2].getmoveable()==true){
					map[row][column].setTargets(row+1,column+2);
				}
			}
			if (row!=gameSettings.MAP_SIZE_X-1 && column!=0){
				if (map[row+1][column-1].getmoveable()==true){
					map[row][column].setTargets(row+1,column-1);
				}
			}
		}else{
			if (row!=0 && column>1){
				if (map[row-1][column-2].getmoveable()==true){
					map[row][column].setTargets(row-1,column-2);
				}
			}
			if (row!=0 && column<gameSettings.MAP_SIZE_Y-2){
				if (map[row-1][column+1].getmoveable()==true){
					map[row][column].setTargets(row-1,column+1);
				}
			}
			if (row<gameSettings.MAP_SIZE_Y-1 && column<gameSettings.MAP_SIZE_Y-1){
				if (map[row+1][column+1].getmoveable()==true){
					map[row][column].setTargets(row+1,column+1);
				}
			}
			if (row<gameSettings.MAP_SIZE_Y-1 && column>1){
				if (map[row+1][column-2].getmoveable()==true){
					map[row][column].setTargets(row+1,column-2);
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
	
	/**
	 * Convert Map to GraphicCell
	 * @return GraphicCell
	 */
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
				case 5: result[row][column] = new GraphicCell(Terrain.CASTLE);
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

