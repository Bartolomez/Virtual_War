package plateau;

/**
 * @author boinc
 */

public class Base extends Cell {
    public Base(int x, int y, int team) {
        super(x, y);
        this.putBase (team);
    }
}
