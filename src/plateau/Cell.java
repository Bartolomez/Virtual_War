package plateau;

import robot.Robot;

/**
 * La classe Cell permet de creer une cellule et de realiser des actions sur cette derniere.
 *
 * @author boinc
 */
public class Cell {
    /**
     * La coordonnee de la cellule.
     */
    private Axis axis;
    /**
     * Le Robot qui se trouve dans la cellule.
     */
    private Robot robot = null;
    /**
     * Indique si la cellule est un obstacle.
     */
    private boolean obstacle;
    /**
     * Indique si la cellule est une mine ou une base.
     */
    private int mine = 0, base = 0;

    /**
     * Construit un objet Cell avec l abscisse et l ordonnee passees en parametre.
     *
     * @param x - L abscisse de la cellule.
     * @param y - L ordonnee de la cellule.
     */
    public Cell( int x, int y ) {
        this.axis = new Axis( x, y );
    }

    /**
     * Retourne la coordonnee de la Cellule.
     *
     * @return Une instance de Axis, qui correspond a la coordonnee de la Cellule.
     */
    public Axis getAxis() {
        return this.axis;
    }

    /**
     * Met a jour la coordonnee de la Cellule.
     *
     * @param axis - La nouvelle coordonnee de la Cellule.
     */
    public void setAxis( Axis axis ) {
        this.axis = axis;
    }

    /**
     * Retourne le Robot qui se trouve dans la Cellule.
     *
     * @return Une instance de Robot, qui correspond au Robot se trouvant dans la Cellule.
     */
    public Robot getRobot() {
        return this.robot;
    }

    /**
     * Retourne le numero de l equipe du Robot se trouvant dans la Cellule, s il y en a un.
     *
     * @return Un entier qui correspond au numero de l equipe se trouvant dans la Cellule, 0 s il y en a pas.
     */
    public int isRobot() {
        if( this.robot == null ) {
            return 0;
        }
        return this.robot.getNumberTeam();
    }

    /**
     * Ajoute un Robot dans la Cellule.
     *
     * @param robot - Le Robot que l on souhaite ajouter a la Cellule.
     */
    public void putRobot( Robot robot ) {
        this.robot = robot;
    }

    /**
     * Enleve un Robot de la Cellule.
     */
    public void revokeRobot() {
        this.robot = null;
    }

    /**
     * Retourne un booleen qui est vrai si la Cellule est un obstacle, faux dans le cas contraire.
     *
     * @return Un boolean, qui est vrai si la Cellule est un obstacle.
     */
    public boolean isObstacle() {
        return this.obstacle;
    }

    /**
     * Ajoute un ostacle dans la Cellule.
     *
     * @param obstacle - Le booleen qui est vrai si l on ajoute un obstacle.
     */
    public void putObstacle( boolean obstacle ) {
        this.obstacle = obstacle;
    }

    /**
     * Ajoute une mine dans la Cellule.
     *
     * @param equipe - Le numero de l equipe a laquelle appartient la Cellule.
     */
    public void putMine( int equipe ) {
        this.mine = equipe;
    }

    /**
     * Indique si la Cellule contient une mine.
     *
     * @return Un entier, qui correspond au numero de l equipe qui a pose la mine s il y en a une, 0 dans le cas contraire.
     */
    public int containsMine() {
        return this.mine;
    }

    /**
     * Supprime une mine de la Cellule.
     */
    public void revokeMine() {
        this.mine = 0;
    }

    /**
     * Retourne un entier qui indique si la Cellule est une base ou non.
     *
     * @return Un entier, qui correspond au numero de l equipe si la cellule est une base, 0 dans le cas contraire.
     */
    public int getBase() {
        return this.base;
    }

    /**
     * Ajoute une base dans une Celulle.
     *
     * @param equipe - Le numero de l equipe a laquelle appartient la base.
     */
    public void putBase( int equipe ) {
        this.base = equipe;
    }

    /**
     * Retourne un booleen qui est vrai si la cellule est vide, faux dans le cas contraire.
     *
     * @return Un booleen, qui est vrai si la cellule est vide.
     */
    public boolean isEmpty() {
        return !( this.robot == null || this.obstacle || this.mine == 0 || this.base == 0 );
    }

    /**
     * Retourne le Robot contenu dans la Cellule.
     *
     * @return Une instance de Robot, qui correspond au Robot qui se trouve dans la cellule.
     */
    public Robot getContent() {
        return this.robot;
    }

    /**
     * Vide la cellule.
     */
    public void clearCell() {
        this.revokeRobot();
        this.obstacle = false;
        this.revokeMine();
        this.base = 0;
    }

    @Override public String toString() {
        return "Cell{" +
            "axis=" + axis +
            ", robot=" + robot +
            ", obstacle=" + obstacle +
            ", mine=" + mine +
            ", base=" + base +
            '}';
    }
}
