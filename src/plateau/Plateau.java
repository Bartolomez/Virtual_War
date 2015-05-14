package plateau;

import config.Constants;
import robot.Robot;
import robot.Scavenger;
import robot.Shooter;
import robot.Tank;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boinc
 */

public class Plateau {
    private Cell[][] plateau;
    private int x, y, length, width;

    public Plateau( int length, int width, double percentageObstacles ) {
        this.length = length;
        this.width = width;
        this.plateau = new Cell[ width ][ length ];
        this.plateau = initializePlateau( this.plateau );
        initializeObstacles( percentageObstacles );
    }

    public Cell getCell( int x, int y ) {
        return this.plateau[ x ][ y ];
    }

    public Cell getCell( Axis axis ) {
        return plateau[ axis.getY() ][ axis.getX() ];
    }

    public int getWidth() {
        return this.width;
    }

    public int getLength() {
        return this.length;
    }

    public boolean isObstacle( int x, int y ) {
        return this.plateau[ y ][ x ].isObstacle();
    }

    public int isBase( int x, int y ) {
        return this.plateau[ y ][ x ].getBase();
    }

    public int isMine( int x, int y ) {
        return this.plateau[ y ][ x ].containsMine();
    }

    public int isRobot( int x, int y ) {
        return this.plateau[ y ][ x ].isRobot();
    }

    public void clearCell( int x, int y ) {
        this.plateau[ y ][ x ].clearCell();
    }

    public void putRobot( int x, int y, Robot robot ) {
        if( !this.plateau[ y ][ x ].isObstacle() ) {
            this.plateau[ y ][ x ].putRobot( robot );
        } else {
            System.err.printf(
                "Erreur : La cellule ou vous voulez placer ce robot est un obstacle ! :-(\n" );
        }
    }

    public void revokeRobot( Axis axis ) {
        this.plateau[ axis.getY() ][ axis.getX() ].revokeRobot();
    }

    public void revokeMine( Axis axis ) {
        this.plateau[ axis.getY() ][ axis.getX() ].revokeMine();
    }

    private void initializeObstacles( double percentageObstacles ) {
        List<Axis> unusable =
            searchPath( new Axis( 0, 0 ), new Axis( this.length - 1, this.width - 1 ) );
        int x, y, obstacles = 0;
        int count = 0;
        while( obstacles != ( int ) ( ( this.width * this.length ) * percentageObstacles ) &&
            (++count) <= Integer.MAX_VALUE ) {
            x = Constants.random.nextInt( this.width );
            y = Constants.random.nextInt( this.length );
            if( this.plateau[ y ][ x ].getBase() == 0 && !this.plateau[ y ][ x ].isObstacle() &&
                !unusable.contains( new Axis( x, y ) ) ) {
                this.plateau[ y ][ x ].putObstacle( true );
                obstacles += 1;
            }
        }

    }

    private List<Axis> searchPath( Axis source, Axis dest ) {
        List<Axis> nodes = new ArrayList<>();
        nodes.add( source );
        Axis tmp = source;
        while( !tmp.equals( dest ) ) {
            System.out.printf( nodes.toString() + "\n" );
            if( tmp.getX() != dest.getX() && tmp.getY() != dest.getY() ) {
                switch( Constants.random.nextInt(2) ) {
                    case 0:
                        tmp = tmp.add( new Axis( 0, 1 ));
                        nodes.add( tmp );
                        break;
                    case 1:
                        tmp = tmp.add( new Axis( 1, 0 ) );
                        nodes.add( tmp );
                        break;
                    default:
                        System.err.println( "OMG TES NUL" );
                        break;
                }
            } else if (tmp.getX() == dest.getX() ) {
                tmp = tmp.add( new Axis( 0, 1 ) );
                nodes.add( tmp );
            } else {
                tmp = tmp.add( new Axis( 1, 0 ) );
                nodes.add( tmp );
            }
        }
        return nodes;
    }


    private Cell[][] initializePlateau( Cell[][] tab ) {
        for( int i = 0; i < tab.length; i++ ) {
            for( int j = 0; j < tab[ 0 ].length; j++ ) {
                tab[ i ][ j ] = new Cell( i, j );
            }
        }
        tab[ 0 ][ 0 ] = new Base( 0, 0, 1 );
        tab[ tab.length - 1 ][ tab[ 0 ].length - 1 ] =
            new Base( tab.length - 1, tab[ 0 ].length - 1, 2 );
        return tab;
    }

    @Override public String toString() {
        StringBuilder results = new StringBuilder( "+" );
        for( int i = 0; i < this.width; i++ )
            results.insert( results.length(), "---+" );
        for( int j = 0; j < this.length; j++ ) {
            results.insert( results.length(), "\n| " );
            for( int i = 0; i < this.width; i++ ) {
                if( this.plateau[ i ][ j ] instanceof Base ) {
                    switch( this.plateau[ i ][ j ].getBase() ) {
                        case 1:
                            results.insert( results.length(), "B" );
                            break;
                        case 2:
                            results.insert( results.length(), "b" );
                            break;
                        default:
                            results.insert( results.length(), "" );
                            break;
                    }
                } else if( this.plateau[ i ][ j ].getContent() instanceof Shooter ) {
                    switch( this.plateau[ i ][ j ].getContent().getTeam().getTeam() ) {
                        case 1:
                            results.insert( results.length(), "T" );
                            break;
                        case 2:
                            results.insert( results.length(), "t" );
                            break;
                        default:
                            results.insert( results.length(), "" );
                            break;
                    }
                } else if( this.plateau[ i ][ j ].getContent() instanceof Tank ) {
                    switch( this.plateau[ i ][ j ].getContent().getTeam().getTeam() ) {
                        case 1:
                            results.insert( results.length(), "C" );
                            break;
                        case 2:
                            results.insert( results.length(), "c" );
                            break;
                        default:
                            results.insert( results.length(), "" );
                            break;
                    }
                } else if( this.plateau[ i ][ j ].getContent() instanceof Scavenger ) {
                    switch( this.plateau[ i ][ j ].getContent().getTeam().getTeam() ) {
                        case 1:
                            results.insert( results.length(), "P" );
                            break;
                        case 2:
                            results.insert( results.length(), "p" );
                            break;
                        default:
                            results.insert( results.length(), "" );
                            break;
                    }
                } else if( this.plateau[ i ][ j ].containsMine() == 1 ) {
                    results.insert( results.length(), "X" );
                } else if( this.plateau[ i ][ j ].containsMine() == 2 ) {
                    results.insert( results.length(), "x" );
                } else if( this.plateau[ i ][ j ].isObstacle() ) {
                    results.insert( results.length(), "O" );
                } else {
                    results.insert( results.length(), " " );
                }
                results.insert( results.length(), " | " );
            }
            results.insert( results.length(), "\n+" );
            for( int i = 0; i < this.width; i++ )
                results.insert( results.length(), "---+" );
        }
        results.insert( results.length(), "\n" );
        return results.toString();
    }
}
