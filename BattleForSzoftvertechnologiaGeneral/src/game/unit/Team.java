package game.unit;

public class Team {
	public int points;
	public int teamnum;
	public Unit units[];
	public Team (int teamnumber){
		units=new Unit[6];
		teamnum=6;
	}
	
	public void setpoint(int newpoint){
		this.points=this.points+newpoint;
	}
	
	public void newstack(int id, int teamnumber,int poz1, int poz2){
		if (id==1){
			units[teamnum]=new Cavalary(teamnumber,poz1, poz2);
		}
		if (id==2){
			units[teamnum]=new Archer(teamnumber,poz1, poz2);
		}
		if (id==3){
			units[teamnum]=new Pikeman(teamnumber,poz1, poz2);
		}
		teamnum=teamnum+1;
	}
}
