package game.unit;

public class Team {
	public int points;
	public int teamnum;
	public Unit units[];
	public Team (int teamnumber){
		units=new Unit[6];
		teamnum=6;
		if (teamnumber==1){
			units[0]=new Cavalary(teamnumber,1,1);
			units[1]=new Cavalary(teamnumber,10,1);
			units[2]=new Archer(teamnumber,2,1);
			units[3]=new Archer(teamnumber,9,1);
			units[4]=new Pikeman(teamnumber,3,1);
			units[5]=new Pikeman(teamnumber,8,1);
		}
		else{
			units[0]=new Cavalary(teamnumber,1,15);
			units[1]=new Cavalary(teamnumber,10,15);
			units[2]=new Archer(teamnumber,2,15);
			units[3]=new Archer(teamnumber,9,15);
			units[4]=new Pikeman(teamnumber,3,15);
			units[5]=new Pikeman(teamnumber,8,15);
		}
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
