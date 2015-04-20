package plateau;

/**
 * @author boinc
 */

public class Case extends Cell {
    public Case(int mine, int base) {
        super (mine, base);
    }

    public Case(int mine, int base, boolean obstacle) {
        this (mine, base);
        putObstacle (obstacle);
    }
}
