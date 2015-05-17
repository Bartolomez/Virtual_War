package plateau;

/**
 * La classe Base permet de creer une Base.
 *
 * @author boinc
 */

public class Base extends Cell {

    /**
     * Construit un objet Base avec l abscisse, l ordonnee et l equipe passees en parametre.
     *
     * @param x    - l abscisse de la base.
     * @param y    - l ordonnee de la base.
     * @param team - l equipe de la base.
     */
    public Base( int x, int y, int team ) {
        super( x, y );
        this.putBase( team );
    }
}
