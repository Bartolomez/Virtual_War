package team;

import config.Constants;
import plateau.Axis;

import java.util.List;

/**
 * @author seysn
 */
public class Team {
    private List<Robot> robots;
    private String nomPays;
    private Axis axisBase;

    public Team(Axis axisBase) {
        this.axisBase = axisBase;
        this.nomPays = choosePays();
    }

    private String choosePays() {
        // TODO Ajouter la liste des pays
        System.out.print("Choisissez un pays : ");
        return Constants.sc.nextLine();
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

    public void removeRobot(Robot robot){
        this.robots.remove(robot);
    }

    public boolean lose(){
        return robots.isEmpty();
    }
}
