package game.game;

import java.util.Random;

import game.map.Map;
import game.unit.Team;
import game.unit.Unit;

public class GameOn {

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
			M.map[T1.units[i].poz1-1][T1.units[i].poz2-1].setunit(T1.units[i].team);
			M.map[T2.units[i].poz1-1][T2.units[i].poz2-1].setunit(T2.units[i].team);
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
		//inicializálás
		int victpoint=50;
		Map M=new Map();
		Team T1=new Team(1);
		Team T2=new Team(2);
		setunittomap(M,T1,T2);//inicializálás vége
		
		boolean fin=false;
		Random dec = new Random();
		int start = dec.nextInt(2) + 1;
		System.out.println("Player" + start + " start the game");
		int round=1;
		boolean endturn1=false;
		boolean endturn2=false;
		boolean endround=true;
		while(fin!=true){
			System.out.println(round + ".round started");
			while (endround!=true){
				System.out.println("Player 1 turn");
				if (start==1 || endturn2==true){
					while (endturn1!=true){
						if (round % 5 == 1){
							//setnewunit
							//T1.newstack(id, 1, poz1, poz2);
						}
					}
				}
				System.out.println("Player 2 turn");
				while (endturn2!=true){
					if (round % 5 == 1){
						//setnewunit
						//T2.newstack(id, 2, poz1, poz2);
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
}
