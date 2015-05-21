package action;

import plateau.Axis;
import robot.Robot;

/**
 * La classe Action permet de creer une Action et de gerer celle ci.
 *
 * @author boinc
 */

public abstract class Action {
    // TODO : ArrayOutOfBounds si on entre une action non prédefinie

    /**
     * La coordonnee initiale de l action.
     */
    private Axis axis;
    /**
     * La coordonne objective de l action.
     */
    private Axis objective;
    /**
     * Le robot qui effectue l action.
     */
    private Robot robot;

    /**
     * Construit un objet Action avec le robot et la coordonnee objective passes en parametre.
     *
     * @param robot     - Le robot qui effectue l action.
     * @param objective - La coordonnee objective de l action.
     */
    public Action( Robot robot, Axis objective ) {
        this.robot = robot;
        this.objective = objective;
        this.axis = robot.getAxis();
    }

    /**
     * Construit un objet Action avec le robot passe en parametre.
     *
     * @param robot - Le robot qui effectue l action.
     */
    public Action( Robot robot ) {
        this.robot = robot;
        this.objective = robot.getObjective();
    }

    /**
     * Retourne le robot qui effectue l action.
     *
     * @return Une instance de Robot, qui correspond au robot qui effectue l action.
     */
    public Robot getRobot() {
        return this.robot;
    }

    /**
     * Retourne la coordonnee initiale de l action.
     *
     * @return Une instance de Axis, qui correspond a la coordonnee initiale de l action.
     */
    public Axis getPosition() {
        return this.axis;
    }

    /**
     * Retourne la coordonnee objective de l action.
     *
     * @return Une instance de Axis, qui correspond a la coordonnee objective de l action.
     */
    public Axis getObjective() {
        return this.objective;
    }

    /**
     * Realise une action.
     */
    public abstract void doSomething();
}
