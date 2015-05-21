package game.map;

import game.unit.Unit;

//import game.map.Water;

public class Field extends Area{	

	public Field (int poz1, int poz2, Unit unit){
		super();
		this.id=1;
		this.poz1=poz1;
		this.poz2=poz2;
		this.shoot=true;
		this.gameunit=unit;
		this.moveable=true;
	}
	
	public Field (){
		super();
	}
}
