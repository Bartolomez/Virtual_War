package plateau;

import config.Constants;
import robot.Robot;
import robot.Scavenger;
import robot.Shooter;
import robot.Tank;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Plateau permet de creer un plateau et de gerer ce dernier.
 *
 * @author boinc
 */

public class Plateau {
    /**
     * Le plateau de jeu
     */
    private Cell[][] plateau;
    /**
     * Les dimensions du tableau.
     */
    private int x, y, length, width;

    /**
     * Construit un objet Plateau avec la longueur, la largeur et le pourcentage d obstacle passes en parametre.
     *
     * @param length              - La longueur du plateau.
     * @param width               - La largeur du plateau.
     * @param percentageObstacles - Le pourcentage d obstacle.
     */
    public Plateau( int length, int width, double percentageObstacles ) {
        this.length = length;
        this.width = width;
        this.plateau = new Cell[ width ][ length ];
        this.plateau = initializePlateau( this.plateau );
        initializeObstacles( percentageObstacles );
    }

    public static void main( String[] args ) {
        Plateau p = new Plateau( 10, 5, 0 );
        System.out.println( p );
    }

    /**
     * Retourne une cellule du plateau.
     *
     * @param x - L abscisse de la cellule souhaitee.
     * @param y - L ordonnee de la cellule souhaitee.
     * @return Une instance de Cell, qui correspond a une Cellule du plateau.
     */
    public Cell getCell( int x, int y ) {
        return this.plateau[ x ][ y ];
    }

    /**
     * Retourne une cellule du plateau.
     *
     * @param axis - La coordonnee de la cellule souhaitee.
     * @return Une instance de Cell, qui correspond a une Cellule du plateau.
     */
    public Cell getCell( Axis axis ) {
        return plateau[ axis.getY() ][ axis.getX() ];
    }

    /**
     * Retourne la largeur du plateau.
     *
     * @return Un entier, qui correspond a la largeur du plateau.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Retourne la longueur du plateau.
     *
     * @return Un entier, qui correspond a la longueur du plateau.
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Retourne un boolean qui indique si la cellule est un obstacle ou non.
     *
     * @param x - L abscisse de la Cellule.
     * @param y - L ordonnee de la Cellule.
     * @return Un booleen, qui est vrai si la cellule est un obstacle, faux dans le cas contraire.
     */
    public boolean isObstacle( int x, int y ) {
        return this.plateau[ y ][ x ].isObstacle();
    }

    /**
     * Retourne un boolean qui indique si la cellule est une base ou non.
     *
     * @param x - L abscisse de la Cellule.
     * @param y - L ordonnee de la Cellule.
     * @return Un booleen, qui est vrai si la cellule est une base, faux dans le cas contraire.
     */
    public int isBase( int x, int y ) {
        return this.plateau[ y ][ x ].getBase();
    }

    /**
     * Retourne un boolean qui indique si la cellule est une mine ou non.
     *
     * @param x - L abscisse de la Cellule.
     * @param y - L ordonnee de la Cellule.
     * @return Un booleen, qui est vrai si la cellule est une mine, faux dans le cas contraire.
     */
    public int isMine( int x, int y ) {
        return this.plateau[ y ][ x ].containsMine();
    }

    /**
     * Retourne un boolean qui indique si la cellule contient un robot ou non.
     *
     * @param x - L abscisse de la Cellule.
     * @param y - L ordonnee de la Cellule.
     * @return Un booleen, qui est vrai si la cellule contient un robot, faux dans le cas contraire.
     */
    public int isRobot( int x, int y ) {
        return this.plateau[ y ][ x ].isRobot();
    }

    /**
     * Vide une cellule du plateau.
     *
     * @param x - L abscisse de la cellule.
     * @param y - L ordonnee de la cellule.
     */
    public void clearCell( int x, int y ) {
        this.plateau[ y ][ x ].clearCell();
    }

    /**
     * Ajoute un Robot dans une cellule du plateau.
     *
     * @param x     - l abscisse de la cellule ou l on souhaite ajouter un robot.
     * @param y     - l ordonnee de la cellule ou l on souhaite ajouter un robot.
     * @param robot - Le robot que l'on souhaite ajouter dans la cellule.
     */
    public void putRobot( int x, int y, Robot robot ) {
        if( !this.plateau[ y ][ x ].isObstacle() ) {
            this.plateau[ y ][ x ].putRobot( robot );
        } else {
            System.err.printf(
                "Erreur : La cellule ou vous voulez placer ce robot est un obstacle ! :-(\n" );
        }
    }

    /**
     * Enleve un robot d une Cellule.
     *
     * @param axis - La coordonnee de la cellule dans laquelle on souhaite enlever un robot.
     */
    public void revokeRobot( Axis axis ) {
        this.plateau[ axis.getY() ][ axis.getX() ].revokeRobot();
    }

    /**
     * Enleve une mine d une Cellule.
     *
     * @param axis - La coordonnee de la cellule dans laquelle on souhaite enlever une mine.
     */
    public void revokeMine( Axis axis ) {
        this.plateau[ axis.getY() ][ axis.getX() ].revokeMine();
    }

    private void initializeObstacles( double percentageObstacles ) {
        List<Axis> unusable =
            searchPath( new Axis( 0, 0 ), new Axis( this.length - 1, this.width - 1 ) );
        int x, y, obstacles = 0;
        int count = 0;
        while( obstacles != ( int ) ( ( this.width * this.length ) * percentageObstacles )
            && ( ++count ) <= Integer.MAX_VALUE ) {
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
            //System.out.printf( nodes.toString() + "\n" );
            if( tmp.getX() != dest.getX() && tmp.getY() != dest.getY() ) {
                switch( Constants.random.nextInt( 2 ) ) {
                    case 0:
                        tmp = tmp.add( new Axis( 0, 1 ) );
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
            } else if( tmp.getX() == dest.getX() ) {
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
        StringBuilder results = new StringBuilder( "    " );
        int cpt = 0;
        for( int i = 0; i < this.width; i++ )
            results.insert( results.length(), cpt++ + "   " );
        cpt = 0;
        results.insert( results.length(), "\n  +" );
        for( int i = 0; i < this.width; i++ )
            results.insert( results.length(), "---+" );
        for( int j = 0; j < this.length; j++ ) {
            results.insert( results.length(), "\n" + cpt++ + " | " );
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
            results.insert( results.length(), "\n  +" );
            for( int i = 0; i < this.width; i++ )
                results.insert( results.length(), "---+" );
        }
        results.insert( results.length(), "\n" );
        return results.toString();
    }
}
