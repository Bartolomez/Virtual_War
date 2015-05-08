package action;

import plateau.Axis;
import robot.Robot;

/**
 * @author boinc
 */

public abstract class Action {
    private Axis axis;
    private Axis objective;
    private Robot robot;

    public Action() { }

    public Action(Robot robot, Axis objective) {
        this.robot = robot;
        this.objective = objective;
        this.axis = robot.getAxis ();
    }

    public Action(Robot robot) {
        this.robot = robot;
        this.objective = robot.getObjective();
    }

    public Robot getRobot() {
        return this.robot;
    }

    public Axis getPosition() {
        return this.axis;
    }

    public Axis getObjective() {
        return this.objective;
    }

    public abstract void doSomething();
}
