import action.Action;
import config.Input;
import plateau.Axis;
import plateau.Plateau;
import robot.Robot;
import robot.Scavenger;
import robot.Shooter;
import robot.Tank;
import team.Team;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        int x = Input.readInt( "Largeur du plateau : " );
        int y = Input.readInt( "Longeur du plateau : " );
        double obstacle;
        obstacle = Input.readDouble("Pourcentage d'obstacle (de 0.1 à 0.99): ", 0.0, 0.99);
        Plateau plateau = new Plateau(x, y, obstacle);
        Team[] teams = new Team[2];
        teams[0] = new Team(new Axis(0, 0), plateau, 1);
        int count = 0;
        do {
            if( count > 0 ) {
                System.err.printf( "Vous ne pouvez pas faire de coup d'état !! \n" );
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
        ArrayList<Robot> deadRobot = new ArrayList<>();
        count = 0;
        do {
            System.out.printf(teams[count % 2].getView().getPlateau() + "\n" + teams[count % 2]
                    .getNomPays() + " c'est à votre tour ! \n");
            robot = teams[count % 2].chooseRobot();
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
                deadRobot.addAll( t.getRobots().stream().filter( r -> r.isDead() )
                    .collect( Collectors.toList() ) );
            }
            teams[ count % 2 ].getRobots().stream().filter( r -> r.isBased() ).forEach( r -> r.isHeals() );
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


}
