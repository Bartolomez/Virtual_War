package action;

import robot.Robot;

/**
 * La classe Attack permet de creer une attaque.
 *
 * @author boinc
 */

public class Attack extends Action {

    /**
     * Construit un objet Attack avec le robot passe en parametre.
     *
     * @param robot - Le robot qui realise l attaque.
     */
    public Attack( Robot robot ) {
        super( robot );
    }

    @Override public void doSomething() {
        System.out.println(
            "Vous allez prendre pour cible : " + this.getRobot().getView().getPlateau()
                .getCell( getObjective() ).getRobot() + " avec une puissance de " + this.getRobot()
                .getDamageByShoot() );
        this.getRobot().getView().getPlateau().getCell( getObjective() ).getRobot()
            .suddenByShoot( this.getRobot() );
        System.out.println(
            "Après votre attaque la cible est tombé à " + this.getRobot().getView().getPlateau()
                .getCell( getObjective() ).getRobot().getEnergy() + " pv" );
        this.getRobot().losesEnergyAfterAction();
    }

}
