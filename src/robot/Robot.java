package robot;

import action.Action;
import config.Constants;
import plateau.Axis;
import team.Team;
import team.View;

import java.util.List;

/**
 * La classe Robot permet de creer un Robot et d effectuer des actions sur ce dernier.
 *
 * @author boinc
 */

public abstract class Robot {

    /**
     * Les coordonnees du robot et celle de l objectif de son deplacement.
     */
    private Axis axis, objective;
    /**
     * La vue du Robot.
     */
    private View view;
    /**
     * L equipe du Robot.
     */
    private Team team;
    /**
     * L energie du Robot.
     */
    private int energy;

    /**
     * Construit un objet Robot avec la vue et l equipe passees en parametre.
     *
     * @param view - La vue du Robot.
     * @param team - L equipe du Robot.
     */
    public Robot( View view, Team team ) {
        this.view = view;
        this.team = team;
        this.axis = new Axis( calculateAxisXByTeam(), calculateAxisYByTeam() );
    }

    /**
     * Retourne les coordonnees du Robot.
     *
     * @return Une instance de Axis, qui correspond a la coordonnees du Robot.
     */
    public Axis getAxis() {
        return this.axis;
    }

    /**
     * Met a jour la coordonnee du Robot.
     *
     * @param axis - La nouvelle coordonnee du Robot.
     */
    public void setAxis( Axis axis ) {
        this.axis = axis;
    }

    /**
     * Retourne la coordonnee objectif du Robot.
     *
     * @return Une instance de Axis, qui correspond a la coordonnees objectif du Robot.
     */
    public Axis getObjective() {
        return this.objective;
    }

    /**
     * Met a jour la coordonnee qui est l objectif de l action du Robot.
     *
     * @param objective - La nouvelle coordonnee objectif du Robot.
     */
    public void setObjective( Axis objective ) {
        this.objective = objective;
    }

    /**
     * Retourne la vue du Robot.
     *
     * @return Une instance de View, qui correspond a la vue du Robot.
     */
    public View getView() {
        return this.view;
    }

    /**
     * Met a jour la vue du Robot.
     *
     * @param view - La nouvelle vue du Robot.
     */
    public void setView( View view ) {
        this.view = view;
    }

    /**
     * Retourne l equipe du Robot.
     *
     * @return Une instance de Team, qui correspond a l equipe du Robot.
     */
    public Team getTeam() {
        return this.team;
    }

    /**
     * Retourne le numero de l equipe du Robot.
     *
     * @return Un entier, qui correspond au numero de l equipe du Robot.
     */
    public int getNumberTeam() {
        return this.team.getTeam();
    }

    /**
     * Retourne l energie du Robot.
     *
     * @return Un entier, qui correspond a l energie du Robot.
     */
    public int getEnergy() {
        return this.energy;
    }

    /**
     * Met a jour l energie du Robot.
     *
     * @param energy - La nouvelle energie du Robot.
     */
    public void setEnergy( int energy ) {
        this.energy = energy;
    }

    /**
     * Met a jour l energie du Robot apres qu il est subit une action.
     */
    public void losesEnergyAfterAction() {
        this.energy = this.energy - getDamageByAction();
    }

    /**
     * Met a jour l energie du Robot apres qu il ai effectue un deplacement.
     */
    public void losesEnergyAfterMove() {
        this.energy = this.energy - getDamageByMove();
    }

    /**
     * Supprime le Robot du terrain de jeu et le supprime de son equipe.
     */
    public void revoke() {
        this.view.getPlateau().getCell( this.axis ).clearCell();
        this.team.revokeRobot( this );
    }

    /**
     * Retourne un boolean qui est vrai si le Robot est mort, faux dans le cas contraire.
     *
     * @return Un booleen, qui est vrai si le Robot est mort.
     */
    public boolean isDead() {
        return this.energy <= 0;
    }

    /**
     * Retourne un boolean qui est vrai si le Robot est sur sa base, faux dans le cas contraire.
     *
     * @return Un booleen, qui est vrai si le Robot est sur sa base.
     */
    public boolean isBased() {
        return this.axis.equals( new Axis( calculateAxisXByTeam(), calculateAxisYByTeam() ) );
    }

    /**
     * Met a jour l energie du Robot apres qu il ait subit les degats d un ennemi.
     *
     * @param robot - Le Robot qui inflige des degats au Robot cible.
     */
    public abstract void suddenByShoot( Robot robot );

    /**
     * Met a jour l energie du Robot apres qu il ait subit les degats d une mine.
     */
    public abstract void suddenByMine();

    /**
     * Retoune le Type du Robot.
     *
     * @return Une chaine de caracteres, qui correspond au Type du Robot.
     */
    public abstract String getType();

    /**
     * Retourne les dommages qu inflige un Robot.
     *
     * @return Un entier, qui correspond aux dommages infliges par un Robot.
     */
    public abstract int getDamageByAction();

    /**
     * Retourne les dommages qu inflige un Robot quand il tire.
     *
     * @return Un entier, qui correspond aux dommages infliges par un Robot quand il tire.
     */
    public abstract int getDamageByShoot();

    /**
     * Retourne les dommages infliges a un Robot quand il se deplace.
     *
     * @return Un entier, qui correspond aux dommages que s inflige un Robot quand il se deplace.
     */
    public abstract int getDamageByMove();

    /**
     * Retourne les dommages que s inflige un Robot quand il passe sur une Mine.
     *
     * @return Un entier, qui correspond aux dommages infliges au Robot quand il passe sur une Mine.
     */
    public abstract int getDamageByMine();

    /**
     * Retourne la liste des deplacements possibles pour un Robot.
     *
     * @return Une liste de coordonnees, qui correspond au deplacement possible du Robot.
     */
    public abstract List<Axis> getMoves();

    /**
     * Soigne le Robot s il est en base.
     */
    public abstract void isHeals();

    /**
     * Retourne l action selectionnee par le joueur.
     *
     * @return Une instance de Action, qui correspond a l Action selectionnee par le Joueur.
     */
    public abstract Action selectedAction();

    /**
     * Retourne une action qui est choisie aleatoirement pour le Robot.
     *
     * @return Une instance de Action, qui correspond a l Action selectionnee aleatoirement pour le Robot.
     */
    public abstract Action selectActionForIa();

    private int calculateAxisXByTeam() {
        switch( this.team.getTeam() ) {
            case Constants.FIRST_TEAM:
                return 0;
            case Constants.SECOND_TEAM:
                return view.getPlateau().getWidth() - 1;
            default:
                return -1;
        }
    }

    private int calculateAxisYByTeam() {
        switch( this.team.getTeam() ) {
            case Constants.FIRST_TEAM:
                return 0;
            case Constants.SECOND_TEAM:
                return view.getPlateau().getLength() - 1;
            default:
                return -1;
        }
    }

    /**
     * Choisis un deplacement pour le Robot.
     *
     * @param displacement - La liste des deplacements possibles pour le Robot.
     * @return Une instance de Action, qui correspond au deplacement choisis par le Joueur.
     */
    public abstract Action chosesDisplacement( List<Axis> displacement );

    public abstract List<Axis> searchMoves();

    public abstract List<Axis> getAxisAction();
}
