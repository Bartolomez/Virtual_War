package plateau;

/**
 * @author boinc
 */

public class Plateau {
    private Cell[][] plateau;
    private int x;
    private int y;
    private int length;
    private int width;

    public Plateau(int length, int width, double percentageObstacles) {
        this.length = length;
        this.width = width;
        this.plateau = new Cell[ width ][ length ];

        initializePlateau (this.plateau);


    }

    private Cell[][] initializePlateau(Cell[][] tab) {
        for ( int i = 0; i < tab.length; i++ ) {
            for ( int j = 0; j < tab[0].length; j++ ) {
                tab[ i ][ j ] = new Cell (i, j);
            }
        }

        return tab;
    }
}
