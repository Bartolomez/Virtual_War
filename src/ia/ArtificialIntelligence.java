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
 * La classe ArtificialIntelligence permet de creer une IA et de gerer celle ci.
 * 
 * @author seysn
 * @author boinc.
 */

public class ArtificialIntelligence extends Team {

	/** Le nombre de robot gerer par l IA */
    public static int nbRobots;

    /**
     * Construit un objet ArtificialIntelligence avec la coordonnee de la base, le plateau et le numero de l equipe passee en parametre.
     * @param axisBase - La coordonnee de la base de l equipe geree par l IA.
     * @param plateau - Le plateau sur lequel joue l IA.
     * @param team - Le numero de l equipe geree par l IA.
     */
    public ArtificialIntelligence(Axis axisBase, Plateau plateau, int team) {
        super(axisBase, plateau, team);
        robots = chooseRobots();
        setIsIa(true);
    }

    /**
     * Construit un objet ArtificialIntelligence avec la coordonnee de la base, le plateau, le numero et le nom de pays de l equipe passee en parametre.
     * @param axisBase - La coordonnee de la base de l equipe geree par l IA.
     * @param plateau - Le plateau sur lequel joue l IA.
     * @param team - Le numero de l equipe geree par l IA.
     * @param nomPays - Le pays de l equipe geree par l IA.
     */
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

    /**
     * Choisis un Robot pour l IA.
     * @return Une instance de Robot, qui correspond au Robot choisis aleatoirement pour l IA.
     */
    public Robot chooseRobot() {
        return this.robots.get(Constants.random.nextInt(robots.size() - 1));
    }

    /**
     * Choisis une action pour l IA.
     * @param borderA - Un entier.
     * @param borderB - Un deuxieme entier.
     * @return Une entier qui correspond a une action choisie aleatoirement pour l IA.
     */
    public int chooseAction(int borderA, int borderB) {
        return Constants.random.nextInt(borderB - borderA);
    }

    /**
     * Retourne la liste des Robots de l equipe geree par l IA.
     * @return Une liste de Robot, qui correspond a la liste des robots de l equipe geree par l IA.
     */
    public List<Robot> getRobots() {
        return robots;
    }

    /**
     * Indique si l IA a perdu.
     * @return Un booleen, qui est vrai si l IA a perdu, faux dans le cas contraire.
     */
    public boolean lose() {
        return robots.isEmpty();
    }

    /**
     * Retourne une chaine de caractere pour l objet ArtificialIntelligence.
     * @return Une chaine de caractere, pour l objet ArtificialIntelligence.
     */
    public String toString() {
        return "IA " + super.toString();
    }
}
