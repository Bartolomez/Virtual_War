package team;

import plateau.Plateau;

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
}
