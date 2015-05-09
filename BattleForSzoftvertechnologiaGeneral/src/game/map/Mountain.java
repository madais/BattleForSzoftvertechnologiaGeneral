package game.map;

import game.unit.Unit;

public class Mountain extends Area{
	Mountain (int poz1, int poz2, Unit unit){
		this.id=4;
		this.poz1=poz1;
		this.poz2=poz2;
		this.shoot=false;
		this.gameunit=unit;
	}
}
