package team;

import config.Constants;
import config.ShowFrame;
import plateau.Axis;
import plateau.Plateau;
import robot.Robot;

import java.util.List;

/**
 * @author seysn
 * @author boinc
 */
public class Team {
    private List<Robot> robots;
    private String nomPays;
    private Axis axisBase;
    private int team;
    private View view;

    public Team(Axis axisBase, Plateau plateau, int team) {
        this.axisBase = axisBase;
        this.nomPays = choosePays();
        this.team = team;
        this.view = new View (plateau, team);
    }
    
    public Robot chooseRobot() {
    	int count = 0, chosen;
    	for (Robot robot : this.robots) {
			System.out.println("Choix ["+count+"] \t"+robot.toString());
			count += 1;
		}
    	do {
    		chosen = Constants.sc.nextInt();
    	} while (chosen < 1 && chosen > count);
    	return this.robots.get(chosen);
    }

	private String choosePays() {
		ShowFrame.showFrame("Choisir pays", Constants.NAME_COUNTRY);
		System.out.print("Choisissez un pays : ");
        return Constants.sc.nextLine ();
    }
    

    public int getTeam() {
		return this.team;
	}

	public String getNomPays() {
        return nomPays;
    }

    public List<Robot> getRobots() {
        return robots;
    }

    public Axis getAxisBase() {
        return axisBase;
    }

    public void addRobot(Robot robot) {
        this.robots.add(robot);
    }

    public void revokeRobot(Robot robot){
        this.robots.remove (robot);
    }

    public boolean lose(){
        return robots.isEmpty();
    }

    public View getView() { return this.view; }

	@Override
	public String toString() {
		return "Team [robots=" + robots + ", nomPays=" + nomPays
				+ ", axisBase=" + axisBase + ", team=" + team + "]";
	}
}
