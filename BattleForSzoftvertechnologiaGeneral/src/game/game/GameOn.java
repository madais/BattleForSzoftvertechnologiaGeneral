package game.game;

import java.awt.event.MouseListener;
import java.util.Random;

import javax.crypto.AEADBadTagException;
import javax.swing.JOptionPane;

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

public class GameOn {
	
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
	boolean weCanMove;
	
	public enum RecruitType {
	    NONE, ARCHER, CAVALRY, INFANTRY 
	}
	
	static int dmgcalc() {
		Random dec = new Random();
		int dmg = dec.nextInt(6) + 1;
		if (dmg == 6) {
			dmg = dmg + dec.nextInt(6) + 1;
		}
		return dmg;
	}

	static void move(Map M, Unit A, int poz1, int poz2) {
		M.map[A.poz1-1][A.poz2-1].delunit();
		A.setpoz(poz1,poz2);
		M.map[A.poz1-1][A.poz2-1].setunit(A.team);
	}

	static void attack(Map M, Unit A, Unit B) {
		// ha a kõ-papír-olló játék teljesül
		// lovas üti az íjász
		// íjász üti a gyalogost
		// gyalogos üti a lovast
		if ((A.unitid == 1 && B.unitid == 2)
				|| (A.unitid == 2 && B.unitid == 3)
				|| (A.unitid == 3 && B.unitid == 1)) {
			// getClass vagy gettype megkeresése, olvashatóság miatt
			// equals-al kellene stringet hasonlítani
			int dmg = dmgcalc();
			B.decreasehp(dmg);
			if (B.gethp() != 0) {
				dmg = dmgcalc();
				A.decreasehp(dmg);
			}
			return;
		}
		// ha egyik sem élvez elõnyt a másikkal szemben
		int dmg1 = dmgcalc();
		int dmg2 = dmgcalc();
		A.decreasehp(dmg1);
		B.decreasehp(dmg2);
		if (A.hp==0){
			M.map[A.poz1-1][A.poz2-1].delunit();
		}
		if (B.hp==0){
			M.map[B.poz1-1][B.poz2-1].delunit();
		}
	}
	
	public static void setunittomap(Map M,Team T1,Team T2){
		for (int i=0; i<6;i++){
			M.map[T1.units[i].poz2-1][T1.units[i].poz1-1].setunit(T1.units[i].team);
			M.map[T2.units[i].poz2-1][T2.units[i].poz1-1].setunit(T2.units[i].team);
		}
	}

	public static void setpoints(Map M, Team T1, Team T2){
		int t1=0;
		int t2=0;
		for (int i=0;i<6;i++){
			if (T1.units[i].poz1==6 && (T1.units[i].poz2==4 || T1.units[i].poz2==8 || T1.units[i].poz2==12)){
				t1++;
			}
			if (T2.units[i].poz1==6 && (T2.units[i].poz2==4 || T2.units[i].poz2==8 || T2.units[i].poz2==12)){
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
		}
		if (T2.points>=victpoint){
			System.out.println("P" + 2 + " is the winner");
		}
	}	
	
	public static void newround(Team T1, Team T2){
		for (int i=0;i<6;i++){
			T1.units[i].newround();
			T2.units[i].newround();
		}
	}
	
	public static void main(String[] args) {
		
		
		
	}

	public GameOn(GUI gui) {
		super();
		this.gui = gui;
		this.initGame();
		this.mainGameMethod();
	}
	
	
	
	private void mainGameMethod() {
		boolean fin=false;
		Random dec = new Random();
		int start = dec.nextInt(2) + 1;
		System.out.println("Player" + start + " start the game");
		round=1;
		boolean endturn1=false;
		boolean endturn2=false;
		boolean endround=false;
		int id;
		
		boolean newstackdone=false;
		while(fin!=true){
			System.out.println(round + ".round started");
			while (endround!=true){
				System.out.println("Player 1's turn");
				if (start==1 || endturn2==true){
					p1turn=true;
					p2turn=false;
					while (endturn1!=true){
						if (round % 5 == 1){
							recruiting = true;
							recruitType = RecruitType.NONE;
							gui.enableUnitRecruiting();					
							//A tábla színezése:
							GraphicCell[][] cells= gui.getGameCanvasPanel().getCells();
							for (int i=0;i<gameSettings.MAP_SIZE_Y;i++) {
								cells[0][i].setMarker(Marker.MOVEABLE);
							}
							gui.getGameCanvasPanel().setCells(cells);
						}else {
							recruiting = false;
							gui.disableUnitRecruiting();
							
						}
					}
				}
				System.out.println("Player 2's turn");
				while (endturn2!=true){
					p2turn=true;
					p1turn=false;
					if (round % 5 == 1){
						recruiting = true;
						recruitType = RecruitType.NONE;
						gui.enableUnitRecruiting();
						GraphicCell[][] cells= gui.getGameCanvasPanel().getCells();
						for (int i=0;i<10;i++) {
							cells[gameSettings.MAP_SIZE_X-1][i].setMarker(Marker.MOVEABLE);
						}
						gui.getGameCanvasPanel().setCells(cells);
					}else {
						recruiting = false;
						gui.disableUnitRecruiting();
					}
				}
				if (endturn1==true && endturn2==true){
					endround=true;
				}
			}
			newround(T1,T2);
		//	move(M,T1.units[2],6,4);
		//	move(M,T1.units[3],6,8);
		//	move(M,T1.units[1],6,12);
		//	attack(M,T1.units[0],T2.units[1]);
			setpoints(M, T1, T2);
			fin=victorycond(T1, T2, victpoint);
			round++;
		}
		victory(T1, T2, victpoint);
		
	}

	private void initGame() {
		//inicializálás
		victpoint=50;
		M=new Map();
		T1=new Team(1);
		T2=new Team(2);
		initTeams();
		//setunittomap(M,T1,T2);//inicializálás vége
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
		M.map[1][1].setGameunit(new Cavalary(1, 1, 1)); 
		M.map[1][9].setGameunit(new Cavalary(1, 9, 1)); 
		M.map[1][2].setGameunit(new Archer(1, 2, 1)); 
		M.map[1][9].setGameunit(new Archer(1, 9, 1)); 
		M.map[1][3].setGameunit(new Pikeman(1, 3, 1)); 
		M.map[1][8].setGameunit(new Pikeman(1, 8, 1)); 
		//TEAM2
		M.map[1][9].setGameunit(new Cavalary(2, 1, gameSettings.MAP_SIZE_X)); 
		M.map[10][9].setGameunit(new Cavalary(2, 10, gameSettings.MAP_SIZE_X)); 
		M.map[2][8].setGameunit(new Archer(2, 2, gameSettings.MAP_SIZE_X)); 
		M.map[9][7].setGameunit(new Archer(2, 9, gameSettings.MAP_SIZE_X)); 
		M.map[3][6].setGameunit(new Pikeman(2, 3, gameSettings.MAP_SIZE_X)); 
		M.map[8][5].setGameunit(new Pikeman(2, 8, gameSettings.MAP_SIZE_X)); 
	}
	
	
	public void thereWasAClick(int clickedX, int clickedY){
		if (recruiting){
			if(recruitType == RecruitType.NONE){
				JOptionPane.showMessageDialog(null, "WTF DO YO WANT TO PUT DOWN???");	
			}
			else{
				switch (recruitType) {
				case ARCHER:
					if (p1turn==true){
						T1.newstack(2, 1, clickedX, clickedY);
					}
					if (p2turn==true){
						T2.newstack(2, 2, clickedX, clickedY);
					}
					break;
				case CAVALRY:
					if (p1turn==true){
						T1.newstack(1, 1, clickedX, clickedY);
					}
					if (p2turn==true){
						T2.newstack(1, 2, clickedX, clickedY);
					}
					break;
				case INFANTRY:
					if (p1turn==true){
						T1.newstack(3, 1, clickedX, clickedY);
					}
					if (p2turn==true){
						T2.newstack(3, 2, clickedX, clickedY);
					}
					break;					
				default:
					break;
				}
				GraphicCell[][] cells= gui.getGameCanvasPanel().getCells();
				for (int i=0;i<10;i++) {					
					cells[i][0].setMarker(Marker.NONE);
				}
				gui.getGameCanvasPanel().setCells(cells);
			}
			gui.disableUnitRecruiting();
		} else if( weCanMove){
			 if (M.getArea(clickedX, clickedY).getGameunit()!=null){
				GraphicCell[][] cells= gui.getGameCanvasPanel().getCells();
				cells [clickedX][clickedY].setMarker(Marker.SELECTED);
				for (int i=0;i<M.map[clickedX][clickedY].getNeighnum();i++){
					cells [M.map[clickedX][clickedY].getNeighbours()[i][0]][M.map[clickedX][clickedY].getNeighbours()[i][1]].setMarker(Marker.MOVEABLE);
				}
				gui.getGameCanvasPanel().setCells(cells);
			 }
			
		}
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
		}
		else{
			recruitType = RecruitType.NONE;	
		}
	}

	public void CavalryIsTheRecruit() {
		if (recruiting == true){
			recruitType = RecruitType.CAVALRY;	
		}
		else{
			recruitType = RecruitType.NONE;	
		}
	}

	public void ArcherIsTheRecruit() {
		if (recruiting == true){
			recruitType = RecruitType.ARCHER;	
		}
		else{
			recruitType = RecruitType.NONE;	
		}
	}

	public void EndTurn() {
		p1turn=!p1turn;
		p2turn=!p2turn;
	}
	
}
