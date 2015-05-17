package game.game;

import java.awt.event.MouseListener;
import java.util.Random;

import javax.crypto.AEADBadTagException;
import javax.swing.JOptionPane;

import communication.Communication;
import communication.TableListener;
import bfsztg_gui.ArcherActionListener;
import bfsztg_gui.CavalryActionListener;
import bfsztg_gui.EndTurnActionListener;
import bfsztg_gui.GUI;
import bfsztg_gui.GameCanvasMouseListener;
import bfsztg_gui.GraphicCell;
import bfsztg_gui.GraphicCell.Marker;
import bfsztg_gui.InfantryActionListener;
import bfsztg_settings.gameSettings;
import game.map.Map;
import game.unit.Archer;
import game.unit.Cavalary;
import game.unit.Pikeman;
import game.unit.Team;
import game.unit.Unit;

public class GameOn implements TableListener, Runnable {
	
	public static GameOn lastgame=null;
	GUI gui;
	Map M;
	int victpoint;
	Team T1;
	Team T2;
	int poz1;
	int poz2;
	int round;
	boolean recruiting;
	RecruitType recruitType;
	boolean p1turn;
	boolean p2turn;
	boolean endturn1;
	boolean endturn2;
	boolean movephase;
	int movepoz1;
	int movepoz2;
	boolean alreadychoose;
	boolean notrecruited;
	boolean alreadychoosenonestack;
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.mainGameMethod();
	}

	public enum RecruitType{
	    NONE, ARCHER, CAVALRY, INFANTRY 
	}
	
	static int dmgcalc(){
		Random dec = new Random();
		int dmg = dec.nextInt(6) + 1;
		if (dmg == 6) {
			dmg = dmg + dec.nextInt(6) + 1;
		}
		return dmg;
	}

	static void move(Map M, Unit A, int poz1, int poz2) {
		M.map[A.poz1][A.poz2].setGameunit(null);
		A.setpoz(poz1,poz2);
		//M.map[A.poz1][A.poz2].setunit(A.team);
		M.map[poz1][poz2].setGameunit(A);
		if (A.getUnitid()==1 && A.isMoved()==true){
			A.setdoublemove(true);
		}
		A.moved();
		System.out.println("elmozdult :)");
		Communication.send_table(M);
	}

	void attack(Map M, Unit A, Unit B, boolean longshoot) {
		// ha a k�-pap�r-oll� j�t�k teljes�l
		// lovas �ti az �j�sz
		// �j�sz �ti a gyalogost
		// gyalogos �ti a lovast
		if (A.unitid==2 && longshoot==true){
			int dmg=dmgcalc();
			B.decreasehp(dmg);
			System.out.println("T�mad� egys�g sebz�se:" + dmg);
			gui.appendToChat("Archer make:" + dmg + "damage\n");
//			A.moved();
//			return;
		}else if ((A.unitid == 1 && B.unitid == 2)
				|| (A.unitid == 2 && B.unitid == 3)
				|| (A.unitid == 3 && B.unitid == 1)) {
			// getClass vagy gettype megkeres�se, olvashat�s�g miatt
			// equals-al kellene stringet hasonl�tani
			int dmg = dmgcalc();
			B.decreasehp(dmg);
			System.out.println("T�mad� egys�g sebz�se:" + dmg);
			gui.appendToChat("Defender take:" + dmg + "damage\n");
			if (B.gethp() != 0) {
				dmg = dmgcalc();
				A.decreasehp(dmg);
				System.out.println("V�dekez� egys�g sebz�se:" + dmg);
				gui.appendToChat("Attacker take:" + dmg + "damage\n");
			}
//			A.moved();
//			return;
		}else{
			// ha egyik sem �lvez el�nyt a m�sikkal szemben
			int dmg1 = dmgcalc();
			int dmg2 = dmgcalc();
			A.decreasehp(dmg1);
			B.decreasehp(dmg2);
			System.out.println("buny� volt :)");
			System.out.println("T�mad� egys�g sebz�se:" + dmg1);
			gui.appendToChat("Attacker make:" + dmg2 + "damage\n");
			System.out.println("v�dekez� egys�g sebz�se:" + dmg2);
			gui.appendToChat("Defender make:" + dmg1 + "damage\n");
		}
		A.moved();
		if (A.hp==0){	
			M.map[A.poz1][A.poz2].setGameunit(null);
			if (p1turn==true){
				gui.appendToChat("Player 1 lost one stack\n");	
			}
			if (p2turn==true){
				gui.appendToChat("Player 2 lost one stack\n");
			}
		}
		if (B.hp==0){		
			M.map[B.poz1][B.poz2].setGameunit(null);
			if (p1turn==true){
				gui.appendToChat("Player 2 lost one stack\n");	
			}
			if (p2turn==true){
				gui.appendToChat("Player 1 lost one stack\n");
			}
		}
		M.clearneighbour();
		freshthemap();
		Communication.send_table(M);
	}
	
//	public static void setunittomap(Map M,Team T1,Team T2){
//		for (int i=0; i<6;i++){
//			M.map[T1.units[i].poz2-1][T1.units[i].poz1-1].setunit(T1.units[i].team);
//			M.map[T2.units[i].poz2-1][T2.units[i].poz1-1].setunit(T2.units[i].team);
//		}
//	}

	public static void setpoints(Map M, Team T1, Team T2){
		int t1=0;
		int t2=0;
		if (M.map[5][5].getGameunit()!=null){
			if (M.map[5][5].getGameunit().getTeam()==1){
				t1++;
			}else{
				t2++;
			}
		}
		if (M.map[11][5].getGameunit()!=null){
			if (M.map[11][5].getGameunit().getTeam()==1){
				t1++;
			}else{
				t2++;
			}
		}
		if (M.map[17][5].getGameunit()!=null){
			if (M.map[17][5].getGameunit().getTeam()==1){
				t1++;
			}else{
				t2++;
			}
		}
		T1.setpoint(t1);
		System.out.println("P1 get " + t1 + " point(s)");
		T2.setpoint(t2);
		System.out.println("P2 get " + t2 + " point(s)");
	}
	
	public static boolean victorycond(Team T1, Team T2, int victpoint){
		if (T1.points>=victpoint || T2.points>=victpoint){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void victory(Team T1, Team T2, int victpoint){
		if (T1.points>=victpoint){
			System.out.println("P" + 1 + " is the winner");
			JOptionPane.showMessageDialog(null, "P" + 1 + " is the winner");	
		}
		if (T2.points>=victpoint){
			System.out.println("P" + 2 + " is the winner");
			JOptionPane.showMessageDialog(null, "P" + 2 + " is the winner");	
		}
	}	
	
	public static void newround(Map M, Team T1, Team T2){
		for (int i=0;i<gameSettings.MAP_SIZE_X;i++){
			for (int j=0;j<gameSettings.MAP_SIZE_Y;j++){
				if (M.map[i][j].getGameunit()!=null){
					M.map[i][j].getGameunit().setMoved(false);
				}
			}
		}
	}

	public GameOn(GUI gui) {
		super();
		GameOn.lastgame=this;
		this.gui = gui;
		//this.initGame();
		//this.mainGameMethod();
	}
	
	public void mainGameMethod() {
		boolean fin=false;
		Random dec = new Random();
		int start = dec.nextInt(2) + 1;
		System.out.println("Player" + start + " start the game");
		round=1;
		if (start==1){
			p1turn=true;
			p2turn=false;
		}else{
			p2turn=true;
			p1turn=false;
		}
		endturn1=false;
		endturn2=false;
		boolean endround=false;
		notrecruited=true;
		while(fin!=true){
			System.out.println(round + ".round started");
			gui.appendToChat(round + ".round started\n");
			while (endround!=true){
			//	freshthemap();
				while (p1turn==true && endturn1==false){
					
					try{
						Thread.sleep(100);
					}
					catch (InterruptedException ex){
						ex.printStackTrace();
					}
					
					gui.appendToChat("Player 1's turn" + "\n");
					p1turn=true;
					p2turn=false;
					if (round%5==0){
						notrecruited=true;
					}
					while (endturn1!=true){
						
						try{
							Thread.sleep(100);
						}
						catch (InterruptedException ex){
							ex.printStackTrace();
						}
						
						if (round % 5 == 0 && notrecruited==true){
							recruiting = true;
							movephase=false;		
							gui.getGameCanvasPanel().RecruitMarkers(true);
							if (alreadychoose=false){
								recruitType = RecruitType.NONE;
								gui.getGameCanvasPanel().RecruitMarkers(true);
							}
							gui.enableUnitRecruiting();					
							//A t�bla sz�nez�se:
						}else {
							recruiting = false;
							gui.disableUnitRecruiting();
							movephase=true;
							
						}
					}
				}
				while (p2turn==true && endturn2==false){
					
					try{
						Thread.sleep(100);
					}
					catch (InterruptedException ex){
						ex.printStackTrace();
					}
					
					gui.appendToChat("Player 2's turn" + "\n");
					p2turn=true;
					p1turn=false;
					if (round%5==0){
						notrecruited=true;
					}
					while(endturn2!=true){
						
						try{
							Thread.sleep(100);
						}
						catch (InterruptedException ex){
							ex.printStackTrace();
						}
						
						if (round % 5 == 0  && notrecruited==true){
							recruiting = true;						
							movephase=false;
							gui.getGameCanvasPanel().RecruitMarkers(false);
							if (alreadychoose=false){
								recruitType = RecruitType.NONE;
								gui.getGameCanvasPanel().RecruitMarkers(false);
							}
							gui.enableUnitRecruiting();					
						}else {
							recruiting = false;
							gui.disableUnitRecruiting();
							movephase=true;
						}
					}
				}
				if (endturn1==true && endturn2==true){
					endround=true;
				}
			}
			newround(M,T1,T2);
			setpoints(M, T1, T2);
			gui.appendToChat("Player 1 score:" + T1.getpoints() + "\n");
			gui.appendToChat("Player 2 score:" + T2.getpoints() + "\n");
			fin=victorycond(T1, T2, victpoint);
			round++;
			if (round%5==1){
				notrecruited=true;
			}
			System.out.println("pontok kioszt�sa");
			M.clearneighbour();
			freshthemap();
			System.out.println("t�rk�p friss�t�se");
			if (start==1){
				p1turn=true;
				p2turn=false;
			}else{
				p2turn=true;
				p1turn=false;
			}
			endround=false;
			endturn1=false;
			endturn2=false;
		}
		victory(T1, T2, victpoint);
		
	}

	public void initGame() {
		//inicializ�l�s
		victpoint=50;
		M=new Map(true);
		T1=new Team(1);
		T2=new Team(2);
		initTeams();
		//setunittomap(M,T1,T2);//inicializ�l�s v�ge
		gui.getGameCanvasPanel().addMouseListener(new GameCanvasMouseListener(gui.getGameCanvasPanel(), gui, this));
		gui.getBtnArcher().addActionListener(new ArcherActionListener(this));
		gui.getBtnInfantry().addActionListener(new InfantryActionListener(this));
		gui.getBtnCavalry().addActionListener(new CavalryActionListener(this));
		gui.getBtnEndTurn().addActionListener(new EndTurnActionListener(this));
		gui.getGameCanvasPanel().setCells(M.toGraphicCellArray()); 
		//		
	}
	
	void initTeams(){
		if(M.map[1][1] == null){
			System.out.println("THIS BULLCRAP IS NULL!!!");
		}
		
//		// TEAM1
		
	//	M.map[0][2].setGameunit(T1.units[1])); 
		T1.newstack(M, 1, 1, 0, 2);
		T1.newstack(M, 1, 1, 0, 7);
		T1.newstack(M, 2, 1, 0, 1);
		T1.newstack(M, 2, 1, 0, 8);
		T1.newstack(M, 3, 1, 0, 0);
		T1.newstack(M, 3, 1, 0, 9);
		
		T2.newstack(M, 1, 2, 23, 2);
		T2.newstack(M, 1, 2, 23, 7);
		T2.newstack(M, 2, 2, 23, 1);
		T2.newstack(M, 2, 2, 23, 8);
		T2.newstack(M, 3, 2, 23, 0);
		T2.newstack(M, 3, 2, 23, 9);
		
		T1.newstack(M, 1, 1, 10, 5);
		T2.newstack(M, 2, 2, 13, 6);
		
//		System.out.println("0 7 szomsz�sai"+M.map[23][7].getNeighnum());
//		System.out.println("0 8 szomsz�sai"+M.map[23][8].getNeighnum());
//		System.out.println("0 9 szomsz�sai"+M.map[23][9].getNeighnum());
		freshthemap();
		//freshthemap();
//		for (int i=0;i<24;i++){
//			for (int j=0;j<10;j++){
//				M.findneighbour(i+1, j+1);
//			}
//		}
//		System.out.println("0 7 szomsz�sai" +M.map[23][7].getNeighnum());
//		System.out.println("0 8 szomsz�sai" +M.map[23][8].getNeighnum());
//		System.out.println("0 9 szomsz�sai" +M.map[23][9].getNeighnum());
		
		
//		M.map[0][7].setGameunit(new Cavalary(1, 7, 0)); 
//		GraphicCell[][] cells= gui.getGameCanvasPanel().getCells();
//		for (int i=0;i<M.map[1][9].getNeighnum();i++){
//			cells [M.map[1][9].getNeighbours()[i][0]][M.map[1][9].getNeighbours()[i][1]].setMarker(Marker.MOVEABLE);
//		}
//		gui.getGameCanvasPanel().setCells(cells);
//		M.map[0][1].setGameunit(new Archer(1, 1, 0)); 
//		M.map[0][8].setGameunit(new Archer(1, 8, 0)); 
//		M.map[0][0].setGameunit(new Pikeman(1, 0, 0)); 
//		for (int i=0;i<M.map[0][8].getNeighnum();i++){
//			cells [M.map[0][8].getNeighbours()[i][0]][M.map[1][8].getNeighbours()[i][1]].setMarker(Marker.MOVEABLE);
//		}
//		gui.getGameCanvasPanel().setCells(cells);
//		M.map[0][9].setGameunit(new Pikeman(1, 9, 0)); 
//		
//		//TEAM2
//		M.map[23][7].setGameunit(new Cavalary(2, 7, gameSettings.MAP_SIZE_X-1)); 
//		M.map[23][2].setGameunit(new Cavalary(2, 2, gameSettings.MAP_SIZE_X-1)); 
//		M.map[23][1].setGameunit(new Archer(2, 1, gameSettings.MAP_SIZE_X-1)); 
//		M.map[23][8].setGameunit(new Archer(2, 8, gameSettings.MAP_SIZE_X-1)); 
//		for (int i=0;i<M.map[23][8].getNeighnum();i++){
//			cells [M.map[23][8].getNeighbours()[i][0]][M.map[23][8].getNeighbours()[i][1]].setMarker(Marker.ATTACKABLE);
//		}
//		gui.getGameCanvasPanel().setCells(cells);
//		M.map[23][0].setGameunit(new Pikeman(2, 0, gameSettings.MAP_SIZE_X-1)); 
//		M.map[23][9].setGameunit(new Pikeman(2, 9, gameSettings.MAP_SIZE_X-1)); 
	}
	
	
	public void thereWasAClick(int clickedX, int clickedY){
		System.out.println("There was a click.");
		if (recruiting){
			if(recruitType == RecruitType.NONE){
				JOptionPane.showMessageDialog(null, "First choose recruit type!");	
			}
			else{
				switch (recruitType) {
				case ARCHER:
					if (p1turn==true){
						T1.newstack(M,2, 1, clickedX, clickedY);	
						notrecruited=false;
					}
					if (p2turn==true){
						T2.newstack(M,2, 2, clickedX, clickedY);						
						notrecruited=false;						
					}
					break;
				case CAVALRY:
					if (p1turn==true){
						T1.newstack(M,1, 1, clickedX, clickedY);
						notrecruited=false;
					}
					if (p2turn==true){
						T2.newstack(M,1, 2, clickedX, clickedY);
						notrecruited=false;
					}
					break;
				case INFANTRY:
					if (p1turn==true){
						T1.newstack(M,3, 1, clickedX, clickedY);
						notrecruited=false;
					}
					if (p2turn==true){
						T2.newstack(M,3, 2, clickedX, clickedY);
						notrecruited=false;
					}
					break;					
				default:
					break;
				}
				gui.getGameCanvasPanel().ClearMarkers();
				alreadychoose=false;
				recruiting=false;
				gui.getGameCanvasPanel().setCells(M.toGraphicCellArray()); 
				GraphicCell[][] cells= gui.getGameCanvasPanel().getCells();
				for (int i=0;i<10;i++) {					
					cells[i][0].setMarker(Marker.NONE);
				}
				gui.getGameCanvasPanel().setCells(cells);
			}
			gui.disableUnitRecruiting();
		} else if(movephase==true && alreadychoosenonestack==false){
//			if(M.getArea(clickedX, clickedY).getGameunit()!= null){
//				System.out.println(M.getArea(clickedX, clickedY).getGameunit());
//			}
//			else {
//				System.out.println("ez bizony null");
//			}
	//		System.out.println(M.getArea(clickedX, clickedY).getGameunit().getTeam());
			 if (M.getArea(clickedX, clickedY).getGameunit()!=null){
				 if ((M.getArea(clickedX, clickedY).getGameunit().isMoved()==false) && (((p1turn==true && M.getArea(clickedX, clickedY).getGameunit().getTeam()==1)) || (p2turn==true && M.getArea(clickedX, clickedY).getGameunit().getTeam()==2))){
					System.out.println("kattint�s ut�n: " + M.getArea(clickedX, clickedY).getGameunit().isMoved());
					 GraphicCell[][] cells= gui.getGameCanvasPanel().getCells();
					cells [clickedX][clickedY].setMarker(Marker.SELECTED);
					System.out.println("szomsz�dok sz�ma: " + M.map[clickedX][clickedY].getNeighnum());
					for (int i=0;i<M.map[clickedX][clickedY].getNeighnum();i++){
						if (M.map[M.map[clickedX][clickedY].getNeighbours()[i][0]][M.map[clickedX][clickedY].getNeighbours()[i][1]].getGameunit()==null){//marker==move
							cells [M.map[clickedX][clickedY].getNeighbours()[i][0]][M.map[clickedX][clickedY].getNeighbours()[i][1]].setMarker(Marker.MOVEABLE);
						}else if (M.map[M.map[clickedX][clickedY].getNeighbours()[i][0]][M.map[clickedX][clickedY].getNeighbours()[i][1]].getGameunit()!=null && (M.map[M.map[clickedX][clickedY].getNeighbours()[i][0]][M.map[clickedX][clickedY].getNeighbours()[i][1]].getGameunit().getTeam()!=1 && p1turn==true) || (M.map[M.map[clickedX][clickedY].getNeighbours()[i][0]][M.map[clickedX][clickedY].getNeighbours()[i][1]].getGameunit().getTeam()!=2 && p2turn==true)){
							//market==attack
						//	if (M.map[M.map[clickedX][clickedY].getNeighbours()[i][0]][M.map[clickedX][clickedY].getNeighbours()[i][1]].getGameunit()!=null){
								cells [M.map[clickedX][clickedY].getNeighbours()[i][0]][M.map[clickedX][clickedY].getNeighbours()[i][1]].setMarker(Marker.ATTACKABLE);
//							}
//							else{
//								cells [M.map[clickedX][clickedY].getNeighbours()[i][0]][M.map[clickedX][clickedY].getNeighbours()[i][1]].setMarker(Marker.MOVEABLE);
//							}
						}
					}
					if (M.map[clickedX][clickedY].getGameunit().getUnitid()==2){
						for (int i=0;i<M.map[clickedX][clickedY].getTargetnum();i++){
							if (M.map[M.map[clickedX][clickedY].getTargets(i,0)][M.map[clickedX][clickedY].getTargets(i,1)].getGameunit()!=null) {
								if ((M.map[M.map[clickedX][clickedY].getTargets(i,0)][M.map[clickedX][clickedY].getTargets(i,1)].getGameunit().getteam()==1 && p2turn==true) || (M.map[M.map[clickedX][clickedY].getTargets(i,0)][M.map[clickedX][clickedY].getTargets(i,1)].getGameunit().getteam()==2 && p1turn==true)){
									cells [M.map[clickedX][clickedY].getTargets(i,0)][M.map[clickedX][clickedY].getTargets(i,1)].setMarker(Marker.ATTACKABLE);
								}
							}
						}
					}
					movepoz1=clickedX;
					movepoz2=clickedY;
					alreadychoosenonestack=true;
					M.printneigh(clickedX,clickedY);
					gui.getGameCanvasPanel().setCells(cells);
				 }
			 }
			 else{
				 JOptionPane.showMessageDialog(null, "Choose one stack of your army!");	
			 }
		} else if (movephase==true && alreadychoosenonestack==true){
			if (movepoz1==clickedX && movepoz2==clickedY){
			//	GraphicCell[][] cells= gui.getGameCanvasPanel().getCells();
				movepoz1=0;
				movepoz2=0;
				alreadychoosenonestack=false;
				gui.getGameCanvasPanel().ClearMarkers();
			}else if (M.getArea(clickedX, clickedY).getGameunit()==null){
				 GraphicCell[][] cells= gui.getGameCanvasPanel().getCells();
				 if (cells [clickedX][clickedY].getMarker()==Marker.MOVEABLE){
					 move(M,M.map[movepoz1][movepoz2].getGameunit(),clickedX,clickedY);
				 }
				 gui.getGameCanvasPanel().ClearMarkers();
			//	 gui.getGameCanvasPanel().setCells(cells);
				 alreadychoosenonestack=false;
				 gui.getGameCanvasPanel().setCells(M.toGraphicCellArray()); 
//				 cells= gui.getGameCanvasPanel().getCells();
//				 gui.getGameCanvasPanel().setCells(cells);
				 //refres gui
			 }else if (M.getArea(clickedX, clickedY).getGameunit()!=null){
				 //ha p1turn �s p1 c�me a szerver akkor ...
				 if (((p1turn==true && M.getArea(clickedX, clickedY).getGameunit().getTeam()==2)) || (p2turn==true && M.getArea(clickedX, clickedY).getGameunit().getTeam()==1)){
					 GraphicCell[][] cells= gui.getGameCanvasPanel().getCells();
					 if (cells [clickedX][clickedY].getMarker()==Marker.ATTACKABLE){
						 if (M.map[movepoz1][movepoz2].getGameunit().getUnitid()==2){
							 if (M.map[movepoz1][movepoz2].isneighbour(clickedX,clickedY)){
								 attack(M,M.map[movepoz1][movepoz2].getGameunit(),M.map[clickedX][clickedY].getGameunit(),false);
							 }else{
								 attack(M,M.map[movepoz1][movepoz2].getGameunit(),M.map[clickedX][clickedY].getGameunit(),true);
							 }
						 }else{
							 attack(M,M.map[movepoz1][movepoz2].getGameunit(),M.map[clickedX][clickedY].getGameunit(),false);
						 }
					 }
					 gui.getGameCanvasPanel().ClearMarkers();
//					 M.map[movepoz1][movepoz2].getGameunit().moved=true;
//					 System.out.println("t�mad�s ut�n:" + M.getArea(movepoz1, movepoz2).getGameunit().isMoved());
					 //refresh gui
					 alreadychoosenonestack=false;
					 gui.getGameCanvasPanel().setCells(M.toGraphicCellArray()); 
//					 cells= gui.getGameCanvasPanel().getCells();
//					 gui.getGameCanvasPanel().setCells(cells);
				//	 gui.getGameCanvasPanel().setCells(cells);
				 }
			 }
		}
		System.out.println("End of click, redrawing cells.");
	}
	public GUI getGui() {
		return gui;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}

	public Map getM() {
		return M;
	}

	public void setM(Map m) {
		M = m;
	}

	public int getVictpoint() {
		return victpoint;
	}

	public void setVictpoint(int victpoint) {
		this.victpoint = victpoint;
	}

	public Team getT1() {
		return T1;
	}

	public void setT1(Team t1) {
		T1 = t1;
	}

	public Team getT2() {
		return T2;
	}

	public void setT2(Team t2) {
		T2 = t2;
	}

	public int getPoz1() {
		return poz1;
	}

	public void setPoz1(int poz1) {
		this.poz1 = poz1;
	}

	public int getPoz2() {
		return poz2;
	}

	public void setPoz2(int poz2) {
		this.poz2 = poz2;
	}	

	public void InfrantryIsTheRecruit() {
		if (recruiting == true){
			recruitType = RecruitType.INFANTRY;	
			alreadychoose=true;
			gui.appendToChat("Choosen recruit type: pikeman\n");
		}
		else{
			recruitType = RecruitType.NONE;	
		}
	}

	public void CavalryIsTheRecruit() {
		if (recruiting == true){
			recruitType = RecruitType.CAVALRY;	
			alreadychoose=true;
			gui.appendToChat("Choosen recruit type: cavalry\n");
		}
		else{
			recruitType = RecruitType.NONE;	
		}
	}

	public void ArcherIsTheRecruit() {
		if (recruiting == true){
			recruitType = RecruitType.ARCHER;	
			alreadychoose=true;
			gui.appendToChat("Choosen recruit type: archer\n");
		}
		else{
			recruitType = RecruitType.NONE;	
		}
	}

	public void EndTurn() {
		p1turn=!p1turn;
		if (p1turn==false){
			endturn1=true;
			//Communication.send_table(M);
		}
		p2turn=!p2turn;
		if (p2turn==false){
			endturn2=true;
			//Communication.send_table(M);
		}
	}
	
	void freshthemap(){
		for (int i=0;i<gameSettings.MAP_SIZE_X;i++){
			for (int j=0;j<gameSettings.MAP_SIZE_Y;j++){
//				if (M.map[i][j].getGameunit()!=null){
					M.findneighbour(i, j);
//				}
			}
		}
	}

	@Override
	public void recieveTable(Map table) {
		// TODO Auto-generated method stub
		M=table;
		gui.appendToChat("Table recieved");
		freshthemap(); 
		gui.getGameCanvasPanel().setCells(M.toGraphicCellArray());
	}
	
	
	
}
