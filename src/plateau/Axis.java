package plateau;

/**
 * @author boinc
 */

public class Axis {
    private int x, y;

    public Axis(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Axis add(Axis axis) {
        return new Axis (axis.getX () + getX (), axis.getY () + getY ());
    }

    public Axis differenceBetween(Axis axis) {
        return new Axis (axis.getX() - getX(), axis.getY() - getY());
    }

    public boolean equals(Object o) {
        if( this == o )
            return true;
        if( o == null || getClass() != o.getClass() )
            return false;
        Axis axis = ( Axis ) o;
        return x == axis.x && y == axis.y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override public String toString() {
        return "vers [" + x + ", " + y +"] ";
    }
}
