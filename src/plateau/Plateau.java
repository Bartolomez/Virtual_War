package plateau;

import config.Constants;

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

        this.plateau = initializePlateau (this.plateau);
        initializeObstacles (percentageObstacles);


    }

    private void initializeObstacles(double percentageObstacles) {
        int obstacles = 0, x, y;

        while ( obstacles != ( int ) ( ( this.width * this.length ) * percentageObstacles ) ) {
            x = Constants.random.nextInt (this.width);
            y = Constants.random.nextInt (this.length);

            if ( this.plateau[ x ][ y ].getBase () == 0 && !this.plateau[ x ][ y ].isObstacle () ) {
                this.plateau[ x ][ y ].setObstacle (true);
                obstacles += 1;
            }

            if ( x == 0 && this.plateau[ x ][ y ].isObstacle () ) {
                this.plateau[ x ][ y ].setObstacle (false);
                obstacles -= 1;
            } else if ( y == this.plateau.length - 1 && this.plateau[ x ][ y ].isObstacle () ) {
                this.plateau[ x ][ y ].setObstacle (false);
                obstacles -= 1;
            }
        }
    }

    private Cell[][] initializePlateau(Cell[][] tab) {
        for ( int i = 0; i < tab.length; i++ ) {
            for ( int j = 0; j < tab[ 0 ].length; j++ ) {
                tab[ i ][ j ] = new Cell (i, j);
            }
        }

        return tab;
    }
}
