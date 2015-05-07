package action;

import robot.Robot;

/**
 * @author boinc
 */

public class Attack extends Action {

    public Attack( Robot robot ) {
        super( robot );
    }

    @Override public void doSomething() {
        System.out.printf(
            "Vous allez prendre pour cible : " + this.getRobot().getView().getPlateau()
                .getCell( getObjective() ).getRobot() + " avec une puissance de " + this.getRobot()
                .getDamageByShoot() );
        this.getRobot().getView().getPlateau().getCell( getObjective() ).getRobot().suddenByShoot
            ( this.getRobot() );
        System.out.printf(
            "Après votre attaque la cible est tombé à " + this.getRobot().getView().getPlateau()
                .getCell( getPosition() ).getRobot().getEnergy() + " pv" );
        this.getRobot().losesEnergyAfterAction();
    }

}
