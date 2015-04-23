package team;

import plateau.Plateau;

/**
 * @author seysn
 */
public class View {
    private Plateau plateau;
    private Team team;

    public View(Plateau plateau, Team team) {
        this.plateau = plateau;
        this.team = team;
    }

    public Plateau getPlateau() {
        return plateau;
    }
}
