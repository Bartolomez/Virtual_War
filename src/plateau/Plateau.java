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
        return this.plateau[ x ][ y ];
    }

    public int getWidth() {
        return this.width;
    }

    public int getLength() {
        return this.length;
    }

    public boolean isObstacle(int x, int y) {
        return this.plateau[ x ][ y ].isObstacle ();
    }

    public int isBase(int x, int y) {
        return this.plateau[ x ][ y ].getBase ();
    }

    public int isMine(int x, int y) {
        return this.plateau[ x ][ y ].isMine ();
    }

    public int isRobot(int x, int y) {
        return this.plateau[ x ][ y ].isRobot ();
    }

    public void clearCell(int x, int y) {
        this.plateau[ x ][ y ].clearCell ();
    }

    public void putRobot(int x, int y, Robot robot) {
        if ( !this.plateau[ x ][ y ].isObstacle () ) {
            this.plateau[ x ][ y ].putRobot (robot);
        } else {
            System.err.println (
                "Erreur : La cellule ou vous voulez placer ce robot est un " + "obstacle ! :-(");
        }
    }

    public void revokeRobot(Axis axis) {
        this.plateau[ axis.getHeight () ][ axis.getWidth () ].revokeRobot ();
    }

    public void revokeMine(Axis axis) {
        this.plateau[ axis.getHeight () ][ axis.getWidth () ].revokeMine ();
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

    @Override public String toString() {
        StringBuilder results = new StringBuilder ("+");
        for ( int i = 0; i < this.width; i++ )
            results.insert (results.length (), "---+");
        for ( int j = 0; j < this.length; j++ ) {
            results.insert (results.length (), "\n| ");
            for ( int i = 0; i < this.width; i++ ) {
                if ( this.plateau[ i ][ j ] instanceof Base ) {
                    switch ( this.plateau[ i ][ j ].getBase () ) {
                        case 1:
                            results.insert (results.length (), "B");
                            break;
                        case 2:
                            results.insert (results.length (), "b");
                            break;
                        default:
                            results.insert (results.length (), "");
                            break;
                    }
                } else if ( this.plateau[ i ][ j ].getContent () instanceof Shooter ) {
                    switch ( this.plateau[ i ][ j ].getContent ().getTeam () ) {
                        case 1:
                            results.insert (results.length (), "T");
                            break;
                        case 2:
                            results.insert (results.length (), "t");
                            break;
                        default:
                            results.insert (results.length (), "");
                            break;
                    }
                } else if ( this.plateau[ i ][ j ].getContent () instanceof Char ) {
                    switch ( this.plateau[ i ][ j ].getContent ().getTeam () ) {
                        case 1:
                            results.insert (results.length (), "C");
                            break;
                        case 2:
                            results.insert (results.length (), "c");
                            break;
                        default:
                            results.insert (results.length (), "");
                            break;
                    }
                } else if ( this.plateau[ i ][ j ].getContent () instanceof Piegeur ) {
                    switch ( this.plateau[ i ][ j ].getContent ().getTeam () ) {
                        case 1:
                            results.insert (results.length (), "P");
                            break;
                        case 2:
                            results.insert (results.length (), "p");
                            break;
                        default:
                            results.insert (results.length (), "");
                            break;
                    }
                } else if ( this.plateau[ i ][ j ].getMine () == 1 ) {
                    results.insert (results.length (), "X");
                } else if ( this.plateau[ i ][ j ].getMine () == 2 ) {
                    results.insert (results.length (), "x");
                } else if ( this.plateau[ i ][ j ].getObstacle () ) {
                    results.insert (results.length (), "O");
                } else {
                    results.insert (results.length (), " ");
                }
                results.insert (results.length (), " | ");
            }
            results.insert (results.length (), "\n+");
            for ( int i = 0; i < this.largeur; i++ )
                results.insert (results.length (), "---+");
        }
        results.insert (results.length (), "\n");
        return results.toString ();
    }
}
