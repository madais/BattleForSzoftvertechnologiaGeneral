package game.unit;

import game.map.Map;

public class Team {
	public int points;
	public int teamnum;
	public Unit units[];
	public Team (int teamnumber){
		units=new Unit[150];
		teamnum=0;
	}
	
	public void setpoint(int newpoint){
		this.points=this.points+newpoint;
	}
	
	public void newstack(Map m, int id, int teamnumber,int poz1, int poz2){
		if (id==1){
			units[teamnum]=new Cavalary(teamnumber,poz1, poz2);
			m.map[poz1][poz2].setGameunit(units[teamnum]);
			teamnum++;
		}
		if (id==2){
			units[teamnum]=new Archer(teamnumber,poz1, poz2);
			m.map[poz1][poz2].setGameunit(units[teamnum]);
			teamnum++;
		}
		if (id==3){
			units[teamnum]=new Pikeman(teamnumber,poz1, poz2);
			m.map[poz1][poz2].setGameunit(units[teamnum]);
			teamnum++;
		}
		teamnum=teamnum+1;
	}
	
	public int getpoints(){
		return this.points;
	}
}
