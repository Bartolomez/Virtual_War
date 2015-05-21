package plateau;

/**
 * La classe Axis permet de creer une coordonnee et d effectuer des actions sur cette derniere.
 *
 * @author boinc
 */

public class Axis {
    /**
     * La valeur de l abscisse et de l ordonnee.
     */
    private int x, y;

    /**
     * Construit un objet Axis avec l abscisse et l ordonnee passees en parametre.
     *
     * @param x - L abscisse de la coordonnee.
     * @param y - L ordonnee de la coordonnee.
     */
    public Axis( int x, int y ) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne une coordonnee qui correspond a la somme de la coordonnee active et celle passee en parametre.
     *
     * @param axis - La coordonnee que l on souhaite additionner.
     * @return Une instance de Axis, qui correspond au resultat de la somme.
     */
    public Axis add( Axis axis ) {
        return new Axis( axis.getX() + getX(), axis.getY() + getY() );
    }

    /**
     * Retourne une coordonnee qui correspond a la difference de la coordonnee active et celle passee en parametre.
     *
     * @param axis - La coordonne dont on veut calculer la difference.
     * @return Une instance de Axis, qui correspond au resultat de la difference.
     */
    public Axis differenceBetween( Axis axis ) {
        return new Axis( axis.getX() - getX(), axis.getY() - getY() );
    }

    /**
     * Compare deux coordonnees.
     *
     * @param o - L objet que l on souhaite comparer.
     * @return Un boolean, qui est vrai si les deux coordonnees sont les memes, faux dans le cas contraire.
     */
    public boolean equals( Object o ) {
        if( this == o )
            return true;
        if( o == null || getClass() != o.getClass() )
            return false;
        Axis axis = ( Axis ) o;
        return x == axis.x && y == axis.y;
    }

    /**
     * Retourne l abscisse de la coordonnee.
     *
     * @return Un entier, qui correspond a l abscisse de la coordonnee.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Retourne l ordonnee de la coordonnee.
     *
     * @return Un entier, qui correspond a l ordonnee de la coordonnee.
     */
    public int getY() {
        return this.y;
    }

    @Override public String toString() {
        return "[" + x + ", " + y + "] ";
    }
}
