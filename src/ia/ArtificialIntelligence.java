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
 * @author seysn
 * @author boinc.
 */

public class ArtificialIntelligence extends Team {

    public static int nbRobots;

    public ArtificialIntelligence(Axis axisBase, Plateau plateau, int team) {
        super(axisBase, plateau, team);
        super.setNomPays(choseNomPays());
        robots = chooseRobots();
        setIsIa(true);
    }

    public ArtificialIntelligence(Axis axisBase, Plateau plateau, int team, String nomPays) {
        super(axisBase, plateau, team, nomPays);
        robots = chooseRobots();
        setIsIa(true);
    }

    private List<Robot> chooseRobots() {
        List<Robot> robots = new ArrayList<>();
        int t = 0, sc = 0, sh = 0;
        while (robots.size() != nbRobots) {
            switch (Constants.random.nextInt(3)) {
                case 0:
                    if (t < 2) {
                        robots.add(new Tank(getView(), this));
                        t++;
                    }
                    break;
                case 1:
                    if (sc < 2) {
                        robots.add(new Scavenger(getView(), this));
                        sc++;
                    }
                    break;
                case 2:
                    if (sh < 2) {
                        robots.add(new Shooter(getView(), this));
                        sh++;
                    }
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

    public boolean lose() {
        return robots.isEmpty();
    }

    public String toString() {
        return "IA " + super.toString();
    }
}
