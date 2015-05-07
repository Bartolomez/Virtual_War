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
        System.out.print("Largeur du plateau : ");
        int x = Integer.parseInt(Constants.sc.nextLine());
        System.out.print("Longeur du plateau : ");
        int y = Integer.parseInt( Constants.sc.nextLine() );
        double obstacle;
        do {
            System.out.print("Pourcentage d'obstacle (de 0.1 à 0.99): ");
            obstacle = Double.parseDouble(Constants.sc.nextLine());
        } while(!isValide( obstacle ));
        Plateau plateau = new Plateau(x, y, obstacle);
        Team[] teams = new Team[2];
        teams[0] = new Team(new Axis(0, 0), plateau, 1);
        int count = 0;
        do {
            if( count > 0 ) {
                System.err.println( "Vous ne pouvez pas faire de coup d'état !!" );
            }
            teams[ 1 ] =
                new Team( new Axis( plateau.getLength() - 1, plateau.getWidth() - 1 ), plateau, 2 );
            count += 1;
        } while (sameCountry(teams[0].getNomPays(), teams[1].getNomPays()));
        for (Team t : teams) {
            t.addRobot(new Tank(t.getView(), t));
            t.addRobot(new Scavenger(t.getView(), t));
            t.addRobot(new Shooter(t.getView(), t));
        }
        boolean end = false;
        Robot robot;
        Action action;
        ArrayList<Robot> deadRobot = new ArrayList<Robot>();
        count = 0;
        do {
            System.out.println(teams[count % 2].getView().getPlateau() + "\n" + teams[count % 2]
                    .getNomPays() + " c'est à votre tour ! ");
            robot = teams[count % 2].chooseRobot();
            // Bug
            System.out.printf(robot.getView().toString());
            action = robot.selectedAction();
            if (action != null) {
                action.doSomething();
            }
            count += 1;
            for (Team t : teams) {
                if (t.lose()) {
                    end = !end;
                }
                for (Robot r : t.getRobots()) {
                    if (r.isDead()) {
                        deadRobot.add(r);
                    }
                }
            }
            for (Robot r : teams[count % 2].getRobots()) {
                if (r.isBased()) {
                    r.isHeals();
                }
            }
            if (!deadRobot.isEmpty()) {
                for (Robot r : deadRobot) {
                    r.revoke();
                }
                deadRobot.clear();
            }
        } while (!end);
    }

    private static boolean sameCountry( String nomPays, String nomPays1 ) {
        return nomPays.equals( nomPays1 );
    }

    private static boolean isValide( double obstacle ) {
        return obstacle >= 0.1 && obstacle <= 0.99;
    }

}
