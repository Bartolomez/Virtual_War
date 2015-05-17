package team;

import plateau.Axis;
import plateau.Base;
import plateau.Plateau;
import robot.Robot;
import robot.Scavenger;
import robot.Shooter;
import robot.Tank;

/**
 * La classe View permet de creer une vue et de gerer celle ci.
 * 
 * @author seysn
 */
public class View {
	
	/** Le plateau sur laquelle intervient la vue. */
    private Plateau plateau;
    /** L equipe sur laquelle intervient la vue. */
    private int team;

    /**
     * Construit un objet View avec le plateau et l equipe sur lesquels la vue intervient passes en parametre.
     * @param plateau - Le plateau sur laquelle intervient la vue.
     * @param team - L equipe sur laquelle intervient la vue.
     */
    public View( Plateau plateau, int team ) {
        this.plateau = plateau;
        this.team = team;
    }

    /**
     * Permet d'ajouter un Robot a une vue.
     * @param axis - La coordonnee a laquelle on souhaite ajouter un robot.
     * @param robot - Le robot que l on souhaite ajouter a la vue.
     */
    public void putRobot( Axis axis, Robot robot ) {
        this.plateau.putRobot( axis.getX(), axis.getY(), robot );
    }

    /**
     * Retourne le plateau sur laquelle intervient la vue.
     * @return Une instance de Plateau, qui correspond au plateau sur laquelle la vue est effective.
     */
    public Plateau getPlateau() {
        return plateau;
    }

    @Override public String toString() {
        StringBuilder results = new StringBuilder("    ");
        int cpt = 0;
        for( int i = 0; i < this.plateau.getWidth(); i++)
            results.insert(results.length(), cpt++ +"   ");
        cpt = 0;
        results.insert(results.length(), "\n  +");
        for( int i = 0; i < this.plateau.getWidth(); i++ )
            results.insert( results.length(), "---+" );
        for( int j = 0; j < this.plateau.getLength(); j++ ) {
            results.insert( results.length(), "\n"+ cpt++ +" | " );
            for( int i = 0; i < this.plateau.getWidth(); i++ ) {
                if( this.plateau.getCell( i, j ) instanceof Base ) {
                    switch( this.plateau.getCell( i, j ).getBase() ) {
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
                } else if( this.plateau.getCell( i, j ).getContent() instanceof Shooter ) {
                    switch( this.plateau.getCell( i, j ).getContent().getTeam().getTeam() ) {
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
                } else if( this.plateau.getCell( i, j ).getContent() instanceof Tank ) {
                    switch( this.plateau.getCell( i, j ).getContent().getTeam().getTeam() ) {
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
                } else if( this.plateau.getCell( i, j ).getContent() instanceof Scavenger ) {
                    switch( this.plateau.getCell( i, j ).getContent().getTeam().getTeam() ) {
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
                } else if( this.plateau.getCell( i, j ).containsMine() == team ) {
                    results.insert( results.length(), "X" );
                } else if( this.plateau.getCell( i, j ).isObstacle() ) {
                    results.insert( results.length(), "O" );
                } else {
                    results.insert( results.length(), " " );
                }
                results.insert( results.length(), " | " );
            }
            results.insert( results.length(), "\n  +" );
            for( int i = 0; i < this.plateau.getWidth(); i++ )
                results.insert( results.length(), "---+" );
        }
        results.insert( results.length(), "\n" );
        return results.toString();
    }

    /**
     * Supprime un robot du plateau.
     * @param axis - La coordonnee ou l on souhaite enlever un robot.
     */
    public void revokeRobot( Axis axis ) {
        this.plateau.revokeRobot( axis );
    }
}
