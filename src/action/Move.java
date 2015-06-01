package action;

import plateau.Axis;
import robot.Robot;

/**
 * La classe Move permet de creer un deplacement.
 *
 * @author boinc
 */

public class Move extends Action {

    /**
     * Construit un objet Move avec le robot passe en parametre.
     *
     * @param robot - Le robot qui se deplace.
     */
    public Move( Robot robot ) {
        super( robot );
    }

    public Move( Robot robot, Axis objective) {
        super(robot, objective);
    }

    @Override public void doSomething() {
        travelTo( getObjective() );
    }

    private void travelTo( Axis objective ) {
        this.getRobot().getView().revokeRobot( this.getRobot().getAxis() );
        this.getRobot().setAxis( objective );
        this.getRobot().getView().putRobot( objective, this.getRobot() );
        if( this.getRobot().getView().getPlateau().getCell( objective ).containsMine() > 0 ) {
            this.getRobot().suddenByMine();
            this.getRobot().getView().getPlateau().getCell( objective ).revokeMine();
            System.out.println( "Il y avait une mine dans cette cellule :-(" );
        }
    }
}
