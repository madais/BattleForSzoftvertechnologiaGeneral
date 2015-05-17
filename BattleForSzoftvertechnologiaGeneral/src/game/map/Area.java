package game.map;

import game.unit.Unit;

public class Area {
	int poz1;//sor
	int poz2;//oszlop
	int team;//ha van rajta egys�g, akkor melyik csapat-�
	public int neighbours[][];//szomsz�dok
	public int neighnum;//h�ny szomsz�dja van
	public int targets[][];
	public int targetnum;
	int id;
	boolean shoot;//�tl�het�-e
	boolean moveable;
	Unit gameunit;
	
	
	public Area(){
		super();
	}
	
	void findtarget(){//azok a pontok ahova m�g ell�het az �j�sz
		//lehet hogy a map-ban k�ne meg�rni
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
	
	boolean getmoveable(){
		return moveable;
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
	
	int getneighbours(int i, int j){
//		System.out.println("lek�rdezett szomsz�d i:"+ i + " j: " + j);
		return neighbours[i][j];
	}

	public void delneigs() {
		for (int i=0;i<neighnum;i++){
			neighbours[i][0]=0;
			neighbours[i][1]=0;
		}
		neighnum=0;
	}

	public int[][] getTargets() {
		return targets;
	}

	public void setTargets(int[][] targets) {
		this.targets = targets;
	}

	public int getTargetnum() {
		return targetnum;
	}

	public void setTargetnum(int targetnum) {
		this.targetnum = targetnum;
	}

	public void setTargets(int i, int j) {
		targets[targetnum][0]=i;
		targets[targetnum][1]=j;
		this.targetnum++;
	}
	
	public int getTargets(int i, int j){
		return this.targets[i][j];
	}

	public boolean isneighbour(int clickedX, int clickedY) {
		for (int i=0;i<this.neighnum;i++){
			if (this.neighbours[i][0]==clickedX && this.neighbours[i][1]==clickedY){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
}
