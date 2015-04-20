package plateau;

/**
 * @author boinc
 */

public class Axis {
    private int x;
    private int y;

    public Axis(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Axis add(Axis axis) {
        return new Axis (axis.getX () + getX (), axis.getY () + getY ());
    }

    public Axis differenceBetween(Axis axis) {
        return new Axis (getX () - axis.getX (), getY () - axis.getY ());
    }

    @Override public boolean equals(Object o) {
        if ( this == o )
            return true;
        if ( o == null || getClass () != o.getClass () )
            return false;

        Axis axis = ( Axis ) o;

        if ( x != axis.x )
            return false;
        return y == axis.y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override public String toString() {
        return "Axis{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }
}
