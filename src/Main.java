import frame.Initialize;
import frame.Menu;
import ia.ArtificialIntelligence;
import plateau.Axis;
import plateau.Plateau;
import team.Team;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * La classe Main permet de lancer l application.
 *
 * @author boinc
 * @author seysn
 */
public class Main {
    public static Plateau p;
    public static Team[] teams = new Team[2];

    public static void main(String[] args) throws Exception {
        launchMenu();
    }

    static private void launchMenu() {
        Menu m = new Menu();
        m.jouer.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                launchInit();
                m.dispose();
            }
        });
    }

    static private void launchInit() {
        Initialize init = new Initialize();
        init.valider.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                if (init.canStart()) {
                    p = new Plateau(Integer.parseInt(init.haut.getText()),
                            Integer.parseInt(init.larg.getText()),
                            Integer.parseInt(init.pourc.getText()) / 100);
                    if (init.type1.getSelectedItem().equals("Ordinateur")) {
                        teams[0] = new ArtificialIntelligence(new Axis(0, 0), p, 1,
                                String.valueOf(init.pays1.getSelectedItem()));
                    } else {
                        teams[0] = new Team(new Axis(0, 0), p, 1,
                                String.valueOf(init.pays1.getSelectedItem()));
                    }

                    if (init.type2.getSelectedItem().equals("Ordinateur")) {
                        teams[1] = new ArtificialIntelligence(
                                new Axis(p.getLength() - 1, p.getWidth() - 1), p, 2,
                                String.valueOf(init.pays1.getSelectedItem()));
                    } else {
                        teams[1] = new Team(new Axis(p.getLength() - 1, p.getWidth() - 1), p, 2,
                                String.valueOf(init.pays1.getSelectedItem()));
                    }
                }
            }
        });
        init.retour.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                launchMenu();
                init.dispose();
            }
        });

    }

}
