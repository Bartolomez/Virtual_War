package team;

import plateau.Plateau;

/**
 * @author seysn
 */
public class Vue {
    private Plateau plateau;
    private Team team;

    public Vue(Plateau plateau, Team team) {
        this.plateau = plateau;
        this.team = team;
    }

    public Plateau getPlateau() {
        return plateau;
    }
}
