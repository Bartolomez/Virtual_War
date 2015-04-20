package plateau;

/**
 * @author boinc
 */
public class Cell {
    private Axis axis;
    private Robot robot;
    private boolean isObstacle;
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
        this.robot = robot;
    }

    public int isRobot() {
        if ( this.robot == null ) {
            return 0;
        }

        return this.robot.getTeam();
    }

    public void putRobot(Robot robot) {
        this.robot = robot;
    }

    public void revokeRobot() {
        this.robot = null;
    }

    public int getBase() {
        return 0;
    }

    public boolean isObstacle() {
        return fasle;
    }
}
