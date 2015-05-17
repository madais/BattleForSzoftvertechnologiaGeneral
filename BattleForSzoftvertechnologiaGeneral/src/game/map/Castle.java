package game.map;

import game.unit.Unit;

public class Castle extends Area{
	public Castle (int poz1, int poz2, Unit unit){
		this.id=5;
		this.poz1=poz1;
		this.poz2=poz2;
		this.shoot=false;
		this.gameunit=unit;
		this.moveable=true;
	}
	
	public Castle(){
		super();
	}
}