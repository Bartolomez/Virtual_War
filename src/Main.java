import action.Action;
import config.Constants;
import plateau.Axis;
import plateau.Plateau;
import robot.Robot;
import robot.Scavenger;
import robot.Shooter;
import robot.Tank;
import team.Team;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.printf ("Largeur du plateau : ");
        int x = Constants.sc.nextInt ();
        System.out.printf ("Longeur du plateau : ");
        int y = Constants.sc.nextInt ();
        System.out.printf ("Pourcentage d'obstacle");
        double obstacle = Constants.sc.nextDouble ();
        Plateau plateau = new Plateau (x, y, obstacle);
        Team[] teams = new Team[2];
        teams[1] = new Team (new Axis (0, 0), plateau, 1);
        teams[2] = new Team (new Axis (plateau.getLength () - 1, plateau.getWidth () - 1),
            plateau, 2);
        for ( Team t : teams ) {
            t.addRobot (new Tank (t.getView (), t));
            t.addRobot (new Scavenger (t.getView (), t));
            t.addRobot (new Shooter (t.getView (), t));
        }
        boolean end = false;
        Robot robot;
        Action action;
        ArrayList<Robot> deadRobot = new ArrayList<Robot> ();
        int count = 0;
        do {
            System.out.printf (teams[count % 2].getView ().toString () + "\n" + teams[count % 2]
                .getNomPays () + " c'est a votre toure ! ");
            robot = teams[count % 2].chooseRobot ();
            System.out.printf (robot.getView ().toString ());
            action = robot.selectedAction ();
            if ( action != null ) {
                action.doSomething ();
            }
            count += 1;
            for ( Team t : teams ) {
                if ( t.lose () ) {
                    end = !end;
                }
                for ( Robot r : t.getRobots () ) {
                    if ( r.isDead () ) {
                        deadRobot.add (r);
                    }
                }
            }
            for ( Robot r : teams[count % 2].getRobots () ) {
                if ( r.isBased () ) {
                    r.isHeals ();
                }
            }
            if ( !deadRobot.isEmpty () ) {
                for ( Robot r : deadRobot ) {
                    r.revoke ();
                }
                deadRobot.clear ();
            }
        } while (!end);
    }

}
