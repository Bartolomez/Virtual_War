package team;

import config.Constants;
import config.Input;
import config.ShowFrame;
import plateau.Axis;
import plateau.Plateau;
import robot.Robot;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Team permet de creer une equipe et de gerer celle ci.
 *
 * @author seysn
 * @author boinc
 */

public class Team {

    /**
     * La liste des robots de l equipe.
     */
    protected List<Robot> robots;
    /**
     * Le nom du pays de l equipe.
     */
    private String nomPays;
    /**
     * La coordonnee de la base de l equipe.
     */
    private Axis axisBase;
    /**
     * Le numero correspondant a l equipe.
     */
    private int team;
    /**
     * La vue de l equipe.
     */
    private View view;
    /**
     * Un booleen pour savoir si l equipe est geree ou non par un ordinateur.
     */
    private boolean isIa;

    /**
     * Construit un objet Team avec la coordonnee de la base, le plateau et le numero de l equipe passes en parametre.
     *
     * @param axisBase - La coordonnee de la base de l equipe.
     * @param plateau  - Le plateau sur laquelle l equipe va jouer.
     * @param team     - Le numero de l equipe.
     */
    public Team( Axis axisBase, Plateau plateau, int team ) {
        this.axisBase = axisBase;
        this.nomPays = choosePays();
        this.team = team;
        this.view = new View( plateau, team );
        this.robots = new ArrayList<>();
        this.isIa = false;
    }

    /**
     * Construit un objet Team avec la coordonnee de la base, le plateau, le numero et le pays de l equipe passes en parametre.
     *
     * @param axisBase - La coordonnee de la base de l equipe.
     * @param plateau  - Le plateau sur laquelle l equipe va jouer.
     * @param team     - Le numero de l equipe.
     * @param nomPays  - Le pays que represente l equipe.
     */
    public Team( Axis axisBase, Plateau plateau, int team, String nomPays ) {
        this.axisBase = axisBase;
        this.nomPays = nomPays;
        this.team = team;
        this.view = new View( plateau, team );
        this.robots = new ArrayList<>();
        this.isIa = false;
    }

    /**
     * Permet au joueur de choisir un Robot de l equipe.
     *
     * @return Une instance de Robot, qui correspond au Robot choisis par le joueur.
     */
    public Robot chooseRobot() {
        int count = 0, chosen;
        for( Robot robot : this.robots ) {
            System.out.println( ( ++count ) + ": \t" + robot.toString() );
        }
        do {
            chosen = Input.readInt( "Votre choix : " ) - 1;
            if( chosen < 0 || chosen >= count ) {
                ShowFrame.showErr( "Erreur : Valeur incorrecte !" );
            }
        } while( chosen < 0 || chosen >= count );

        return this.robots.get( chosen );
    }

    private String choosePays() {
        ShowFrame.showFrame( "Choisir pays", Constants.NAME_COUNTRY );
        String pays;
        do {
            System.out.print( "Choisissez un pays : " );
            pays = Constants.sc.nextLine();
        } while( !isInList( pays ) );
        return pays;
    }

    private boolean isInList( String pays ) {
        for( String str : Constants.NAME_COUNTRY ) {
            if( str.toLowerCase().equals( pays.toLowerCase() ) ) {
                return true;
            }
        }
        System.err.printf( "Le nom que vous avez choisi n'est pas dans la liste\n" );
        return false;
    }

    /**
     * Retourne le numero de l equipe.
     *
     * @return Un entier, qui correspond au numero de l equipe.
     */
    public int getTeam() {
        return this.team;
    }

    /**
     * Retourne le nom du pays que represente l equipe.
     *
     * @return Une chaine de caracteres, qui correspond au pays que represente l equipe.
     */
    public String getNomPays() {
        return nomPays;
    }

    /**
     * Met a jour le nom du pays que represente l equipe.
     *
     * @param nomPays - Le nouveau nom du pays que represente l equipe.
     */
    public void setNomPays( String nomPays ) {
        this.nomPays = nomPays;
    }

    /**
     * Retourne la liste des Robots de l equipe.
     *
     * @return Une liste de Robot, qui correspond a la liste des Robots de l equipe.
     */
    public List<Robot> getRobots() {
        return robots;
    }

    /**
     * Retourne les coordonnees de la Base de l equipe.
     *
     * @return Une instance de Axis, qui correspond aux coordonnees de la base de l equipe.
     */
    public Axis getAxisBase() {
        return axisBase;
    }

    /**
     * Ajoute un Robot a l equipe.
     *
     * @param robot - Le Robot a ajouter a l equipe.
     */
    public void addRobot( Robot robot ) {
        this.robots.add( robot );
        this.view.getPlateau().putRobot( getAxisBase().getX(), getAxisBase().getY(), robot );
    }

    /**
     * Supprime un robot de l equipe.
     *
     * @param robot - Le Robot a supprimer de l equipe.
     */
    public void revokeRobot( Robot robot ) {
        this.robots.remove( robot );
    }

    /**
     * Retourne un booleen qui est vrai si l equipe a perdu, c est a dire qu elle n a plus de robot en vie, faux dans le cas contraire.
     *
     * @return Un boolean, qui est vrai si l equipe a perdu.
     */
    public boolean lose() {
        return robots.isEmpty();
    }

    /**
     * Retourne la vue de l equipe.
     *
     * @return Une instance de View, qui correspond a la vue de l equipe.
     */
    public View getView() {
        return this.view;
    }

    @Override public String toString() {
        return "Team " + team + "[ robots=" + robots + ", nomPays=" + nomPays + ", axisBase="
            + axisBase + "]";
    }

    /**
     * Retourne un booleen qui est vrai si l equipe est geree par un ordinateur, faux dans le cas contraire.
     *
     * @return Un boolean, qui est vrai si l equipe est geree par un ordinateur.
     */
    public boolean isIa() {
        return this.isIa;
    }

    /**
     * Met a jour le boolean pour savoir si l equipe est gerer ou non par un ordinateur.
     *
     * @param isIa - La nouvelle valeur de ce boolean.
     */
    public void setIsIa( boolean isIa ) {
        this.isIa = isIa;
    }
}
