package team;

import plateau.Base;
import plateau.Plateau;
import robot.Scavenger;
import robot.Shooter;
import robot.Tank;

/**
 * @author seysn
 */
public class View {
    private Plateau plateau;
    private int team;

    public View(Plateau plateau, int team) {
        this.plateau = plateau;
        this.team = team;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    @Override public String toString() {
        StringBuilder results = new StringBuilder ("+");
        for ( int i = 0; i < this.plateau.getWidth(); i++ )
            results.insert (results.length (), "---+");
        for ( int j = 0; j < this.plateau.getLength(); j++ ) {
            results.insert (results.length (), "\n| ");
            for ( int i = 0; i < this.plateau.getWidth(); i++ ) {
                if ( this.plateau.getCell( i, j ) instanceof Base ) {
                    switch ( this.plateau.getCell( i, j ).getBase() ) {
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
                } else if ( this.plateau.getCell( i, j ).getContent() instanceof Shooter ) {
                    switch ( this.plateau.getCell( i, j ).getContent().getTeam ().getTeam () ) {
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
                } else if ( this.plateau.getCell( i, j ).getContent() instanceof Tank ) {
                    switch ( this.plateau.getCell( i, j ).getContent().getTeam ().getTeam () ) {
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
                } else if ( this.plateau.getCell( i, j ).getContent() instanceof Scavenger ) {
                    switch ( this.plateau.getCell( i, j ).getContent().getTeam ().getTeam () ) {
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
                } else if ( this.plateau.getCell( i, j ).containsMine() == 1 ) {
                    results.insert (results.length (), "X");
                } else if ( this.plateau.getCell( i, j ).containsMine() == 2 ) {
                    results.insert (results.length (), "x");
                } else if ( this.plateau.getCell( i, j ).isObstacle () ) {
                    results.insert (results.length (), "O");
                } else {
                    results.insert (results.length (), " ");
                }
                results.insert (results.length (), " | ");
            }
            results.insert (results.length (), "\n+");
            for ( int i = 0; i < this.plateau.getWidth(); i++ )
                results.insert (results.length (), "---+");
        }
        results.insert (results.length (), "\n");
        return results.toString ();
    }
}
