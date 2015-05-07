package action;

import plateau.Axis;
import robot.Robot;

/**
 * @author boinc
 */

public class Move extends Action {

    public Move( Robot robot ) {
        super( robot );
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
            System.out.printf( "Il y avais une mine dans cette cellule :-(" );
        }
    }
}
