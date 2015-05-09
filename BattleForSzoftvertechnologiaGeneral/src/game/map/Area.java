package game.map;

import game.unit.Unit;

public class Area {
	int poz1;//sor
	int poz2;//oszlop
	int team;//ha van rajta egység, akkor melyik csapat-é
	int neighbours[][];//szomszédok
	int neighnum;//hány szomszédja van
	int canshoot[][];
	int id;
	boolean shoot;//átlõhetõ-e
	Unit gameunit;
		
	void findtarget(){//azok a pontok ahova még ellõhet az íjász
		//lehet hogy a map-ban kéne megírni
	}
	
	void drawarea(){}

	public void setunit(int team) {
		this.team=team;
	}
	
	public void delunit(){
		this.team=0;
	}

	boolean isFree(){
		if (gameunit == null){
			return true;
		}
		else{
			return false;
		}
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

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public int[][] getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(int[][] neighbours) {
		this.neighbours = neighbours;
	}

	public int getNeighnum() {
		return neighnum;
	}

	public void setNeighnum(int neighnum) {
		this.neighnum = neighnum;
	}

	public int[][] getCanshoot() {
		return canshoot;
	}

	public void setCanshoot(int[][] canshoot) {
		this.canshoot = canshoot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	public Unit getGameunit() {
		return gameunit;
	}

	public void setGameunit(Unit gameunit) {
		this.gameunit = gameunit;
	}
	
	
	
	
}
