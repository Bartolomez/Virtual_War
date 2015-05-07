package plateau;

import robot.Robot;

/**
 * @author boinc
 */
public class Cell {
    private Axis axis;
    private Robot robot = null;
    private boolean obstacle;
    private int mine = 0, base = 0;

    public Cell(int x, int y) {
        this.axis = new Axis (x, y);
    }

    public Axis getAxis() {
        return this.axis;
    }

    public void setAxis(Axis axis) {
        this.axis = axis;
    }

    public Robot getRobot() {
        return this.robot;
    }

    public int isRobot() {
        if ( this.robot == null ) {
            return 0;
        }
        return this.robot.getNumberTeam();
    }

    public void putRobot(Robot robot) {
        this.robot = robot;
    }

    public void revokeRobot() {
        this.robot = null;
    }

    public boolean isObstacle() {
        return this.obstacle;
    }

    public void putObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }

    public void putMine(int equipe) {
        this.mine = equipe;
    }

    public int containsMine() {
        return this.mine;
    }

    public void revokeMine() {
        this.mine = 0;
    }

    public int getBase() {
        return this.base;
    }

    public void putBase(int equipe) {
        this.base = equipe;
    }

    public boolean isEmpty() {
        return !( this.robot == null || this.obstacle || this.mine == 0 || this.base == 0 );
    }
    
    public Robot getContent() {
    	return this.robot;
    }

    public void clearCell() {
        this.revokeRobot ();
        this.obstacle = false;
        this.revokeMine ();
        this.base = 0;
    }

    @Override public String toString() {
        return "Cell{" +
            "axis=" + axis +
            ", robot=" + robot +
            ", obstacle=" + obstacle +
            ", mine=" + mine +
            ", base=" + base +
            '}';
    }
}
