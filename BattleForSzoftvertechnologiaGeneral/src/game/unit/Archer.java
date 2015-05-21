package game.unit;

public class Archer extends Unit{
	public Archer(int team, int poz1, int poz2){
		this.hp=10;
		this.team=team;
		this.unitid=2;
		this.poz1=poz1;
		this.poz2=poz2;
	}
	//kett� mez�re tud l�ni
	public Archer(){
		super();
	}
}
