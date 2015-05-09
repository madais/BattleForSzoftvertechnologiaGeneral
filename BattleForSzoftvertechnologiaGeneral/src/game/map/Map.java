package game.map;

import java.util.Random;

public class Map {
	public Area map[][];//mátrixot kéne
	
	public Map(){
		//map= new Area{Water(1,1), };//fel is kéne törlteni
		map=new Area[10][15];
		//map[1][1]=new Field(1,1);//hosszadalmas lenne
		
		int waternum=10;//vízfelületek száma
		int forestnum=10;//erdõk száma
		int mountainnum=10;//hegyek száma
		
		for ( int row=1 ; row <= 10 ; row++ ){
			for ( int column=1 ; column <= 15 ; column++ ){
				if (row==6 && (column==4 || column==8 || column==12)){
					map[row-1][column-1]=new Castle(row,column,null);
				}
				else{
					map[row-1][column-1]=new Field(row,column,null);
				}
			} 
		}
		
		for (int i=1; i<waternum;i++){
			Random dec = new Random();
			int row=dec.nextInt(10)+1;
			int column=dec.nextInt(13)+2;
			if (row!=6 || (column!=4 && column!=8 && column!=12)){
				map[row-1][column-1]=new Water(row,column,null);
			}
		}
		
		for (int i=1; i<forestnum;i++){
			Random dec = new Random();
			int row=dec.nextInt(10)+1;
			int column=dec.nextInt(13)+2;
			if (row!=6 || (column!=4 && column!=8 && column!=12)){
				map[row-1][column-1]=new Forest(row,column,null);
			}
		}
		
		for (int i=1; i<mountainnum;i++){
			Random dec = new Random();
			int row=dec.nextInt(10)+1;
			int column=dec.nextInt(13)+2;
			if (row!=6 || (column!=4 && column!=8 && column!=12)){
				map[row-1][column-1]=new Mountain(row,column,null);
			}
		}
		
		for (int row=1 ; row <= 10 ; row++ ){
			for ( int column=1 ; column <= 15 ; column++ ){
				//map[row-1][column-1].findtarget();
				findneighbour(row,column);
					//printneigh(6,4);
			}
		}
		
		for ( int row=1 ; row <= 10 ; row++ ){
			for ( int column=1 ; column <= 15 ; column++ )	{
					if (column!=15){
						System.out.print(map[row-1][column-1].id);
					}
					else {
						System.out.println(map[row-1][column-1].id);
					}
			} 
		}	
		
		//findneighbour(1,4);
		//printneigh(6,4);
		
	}
	
	public void printmap(){
		for ( int row=1 ; row <= 10 ; row++ ){
			for ( int column=1 ; column <= 15 ; column++ )	{
				if (map[row-1][column-1].isFree()==true){
					if (column!=15){
						System.out.print(map[row-1][column-1].id);
					}
					else {
						System.out.println(map[row-1][column-1].id);
					}
				}
				else {
					if (column!=15){
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
			//if (map[row-1][column-1].neighbours[i][0] != 0){
				
				System.out.print(map[row-1][column-1].neighbours[i][0]);
				System.out.println(map[row-1][column-1].neighbours[i][1]);
			//}
		}
	}
	
	void findneighbour(int row, int column){
		int neigh[][];
		neigh = new int [6][2];
		int neighnum=0;
		if (row!=1 && column!=1){
			if (map[row-2][column-2].isFree()==true){
				neigh[neighnum][0]=row-1;
				neigh[neighnum][1]=column-1;
//				System.out.print("bal fel");
	//			System.out.print(neigh[neighnum][0]);
		//		System.out.println(neigh[neighnum][1]);
				neighnum++;
			}
		}
		if (row!=1 && column!=15){
			if (map[row-2][column-1].isFree()==true){
				neigh[neighnum][0]=row-1;
				neigh[neighnum][1]=column;
//				System.out.print("jobb fel");
	//			System.out.print(neigh[neighnum][0]);
		//		System.out.println(neigh[neighnum][1]);
				neighnum++;
			}
		}
		if (column!=15){
			if (map[row-1][column].isFree()==true){
				neigh[neighnum][0]=row;
				neigh[neighnum][1]=column+1;
//				System.out.print("jobbra");
	//			System.out.print(neigh[neighnum][0]);
		//		System.out.println(neigh[neighnum][1]);
				neighnum++;
			}
		}
		if (row!=10 && column!=15){
			if (map[row][column-1].isFree()==true){
				neigh[neighnum][0]=row+1;
				neigh[neighnum][1]=column;
//				System.out.print("jobb le");
	//			System.out.print(neigh[neighnum][0]);
		//		System.out.println(neigh[neighnum][1]);
				neighnum++;
			}
		}
		if (row!=10 && column!=1){
			if (map[row][column-2].isFree()==true){
				neigh[neighnum][0]=row+1;
				neigh[neighnum][1]=column-1;
//				System.out.print("bal le");
	//			System.out.print(neigh[neighnum][0]);
		//		System.out.println(neigh[neighnum][1]);
				neighnum++;
			}
		}
		if (column!=1){
			if (map[row-1][column-2].isFree()==true){
				neigh[neighnum][0]=row;
				neigh[neighnum][1]=column-1;
//				System.out.print("balra");
	//			System.out.print(neigh[neighnum][0]);
		//		System.out.println(neigh[neighnum][1]);
				neighnum++;
			}
		}
	//	System.out.println("behelyetesites");
		for (int i=0;i<neighnum;i++){
			map[row-1][column-1].neighbours=new int [neighnum][2];
			map[row-1][column-1].neighbours[i][0]=neigh[i][0]-1;
			map[row-1][column-1].neighbours[i][1]=neigh[i][1]-1;
//			System.out.print(neigh[i][0]);
	//		System.out.println(neigh[i][1]);
		//	System.out.print(map[row-1][column-1].neighbours[i][0]);
			//System.out.println(map[row-1][column-1].neighbours[i][1]);
			//System.out.println("kovi");
		}
	}
	
	
	void drawmap(){
		for ( int row=1 ; row <= 10 ; row++ ){
			for ( int column=1 ; column <= 15 ; column++ ){
				map[row-1][column-1].drawarea();
			}
		}
	}	
}
