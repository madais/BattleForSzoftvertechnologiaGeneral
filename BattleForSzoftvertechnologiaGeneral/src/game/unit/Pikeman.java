package game.unit;

public class Pikeman extends Unit{	
	public Pikeman(int team, int poz1, int poz2){//pikeman-re lehet �t kell �rni
		this.hp=20;
		this.team=team;
		this.unitid=3;
		this.poz1=poz1;
		this.poz2=poz2;
	}
	//k�tszerannyi �lete van
	//mini balance sz�ks�ges lesz!!!!!!!!!!!!
}
