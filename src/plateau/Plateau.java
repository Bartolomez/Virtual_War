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

    public Cell getCell(int x, int y) {
        return this.plateau[x][y];
    }

    public int getWidth() {
        return this.width;
    }

    public int getLength() {
        return this.length;
    }

    public boolean isObstacle(int x, int y) {
        return this.plateau[x][y].isObstacle ();
    }

    public int isBase(int x, int y) {
        return this.plateau[x][y].getBase ();
    }

    public int isMine(int x, int y) {
        return this.plateau[x][y].isMine();
    }

    public int isRobot(int x, int y) {
        return this.plateau[x][y].isRobot();
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
