package game.map;

import game.unit.Unit;

public class Water extends Area{
	public Water (int poz1, int poz2, Unit unit){
		this.id=2;
		this.poz1=poz1;
		this.poz2=poz2;
		this.shoot=true;
		this.gameunit=unit;
		this.moveable=false;
	}
	
	public Water(){
		super();
	}
}
