package game.unit;

import game.map.Area;

/**
 * This class contains the units parameters
 *
 */

public class Unit {
	public int hp;//életpontok
	public int team;//csapat
	public int unitid;//azonosító
	public int poz1;//sor
	public int poz2;//oszlop
	public boolean moved;
	public Area area;
	
	public void decreasehp(int dmg){
		this.hp=this.hp-dmg;
		if (this.hp<=0) {
			this.hp=0;
			this.team=0;
		}
	}
	
	public int getteam(){
		return this.team;
	}
	
	public int gethp(){
		return this.hp;
	}
	
	public int getpoz1(){
		return this.poz1;
	}
	
	public int getpoz2(){
		return this.poz2;
	}
	
	
	
	public void setpoz(int poz1, int poz2){
		this.poz1=poz1;
		this.poz2=poz2;
		this.moved=true;
	}
	
	public void moved(){
		this.moved=true;
	}
	
	public void newround(){
		this.moved=false;
	}
	
	int getPosition1(){
		return area.getPoz1();
	}
	
	int getPosition2(){
		return area.getPoz2();
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public int getUnitid() {
		return unitid;
	}

	public void setUnitid(int unitid) {
		this.unitid = unitid;
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

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public void setdoublemove(boolean b){}

	
	
}