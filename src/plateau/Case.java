package plateau;

/**
 * La classe Case permet de creer une cellule contenant une mine ou un obstacle.
 * 
 * @author boinc
 */

public class Case extends Cell {
	/**
	 * Construit un objet Case qui contient ou non une mine ou une base.
	 * @param mine - l entier qui correspond ou non a une mine.
	 * @param base - l entier qui correspond ou non a une base. 
	 */
    public Case(int mine, int base) {
        super (mine, base);
    }

    /**
     * Construit un objet Case qui contient ou non une mine, une base ou un obstacle.
     * @param mine - l entier qui correspond ou non a une mine.
     * @param base - l entier qui correspond ou non a une base.
     * @param obstacle - le booleen qui indique si la celleule contient ou non un obstacle.
     */
    public Case(int mine, int base, boolean obstacle) {
        this (mine, base);
        putObstacle (obstacle);
    }
}
