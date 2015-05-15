package ia;

import config.Constants;
import plateau.Axis;
import plateau.Plateau;
import robot.Robot;
import robot.Scavenger;
import robot.Shooter;
import robot.Tank;
import team.Team;
import team.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author boinc.
 */

public class ArtificialIntelligence {
    private String      nomPays;
    private Axis        axisBase;
    private View        view;
    private Team        team;
    private List<Robot> robots;

    public ArtificialIntelligence(Axis axisBase, Plateau plateau, int team) {
        this.nomPays = choseNomPays();
        this.axisBase = axisBase;
        this.view = new View(plateau, team);
        this.team = new Team(axisBase, plateau, team);
        this.robots = choseRobots();
    }

    public ArtificialIntelligence(Axis axisBase, Plateau plateau, int team, String nomPays) {
        this.nomPays = choseNomPays(nomPays);
        this.axisBase = axisBase;
        this.view = new View(plateau, team);
        this.team = new Team(axisBase, plateau, team, nomPays);
        this.robots = choseRobots();
    }

    private List<Robot> choseRobots() {
        List<Robot> robots = new ArrayList<>();
        while (robots.size() != 5) {
            switch (Constants.random.nextInt(3)) {
                case 0:
                    robots.add(new Tank(this.view,this.team));
                    break;
                case 1:
                    robots.add(new Scavenger(this.view,this.team));
                    break;
                case 2:
                    robots.add(new Shooter(this.view,this.team));
                    break;
            }
        }
        return robots;
    }

    private String choseNomPays() {
        return Constants.NAME_COUNTRY[Constants.random.nextInt(Constants.NAME_COUNTRY.length - 1)];
    }

    private String choseNomPays(String nomPays) {
        List<String> pays = new ArrayList<>(Arrays.asList(Constants.NAME_COUNTRY));
        pays.remove(nomPays);
        return pays.get(Constants.random.nextInt(pays.size() - 1));
    }

    public Robot chooseRobot() {
        return this.robots.get(Constants.random.nextInt(robots.size() - 1));
    }

    public int chooseAction(int borderA, int borderB) {
        return Constants.random.nextInt(borderB - borderA);
    }

    public List<Robot> getRobots() {
        return robots;
    }

    public String getNomPays() {
        return nomPays;
    }

    public boolean lose() {
        return robots.isEmpty();
    }

    public View getView() {
        return this.view;
    }
}
