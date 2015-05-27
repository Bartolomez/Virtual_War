import frame.Initialize;
import frame.Menu;
import frame.ShowPlateau;
import ia.ArtificialIntelligence;
import plateau.Axis;
import plateau.Plateau;
import robot.Robot;
import team.Team;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

    private static void launchMenu() {
        Menu m = new Menu();
        m.jouer.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                launchInit();
                m.dispose();
            }
        });
    }

    private static void launchInit() {
        Initialize init = new Initialize();
        init.valider.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                if (init.canStart()) {
                    p = new Plateau(Integer.parseInt(init.haut.getText()),
                            Integer.parseInt(init.larg.getText()),
                            Double.parseDouble(init.pourc.getText())/ 100);
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
                    launchGame();
                    init.dispose();
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

    private static void launchGame() {
        int count = 0;
        boolean end = false;
        ShowPlateau pane = new ShowPlateau(p);
        ArrayList<Robot> deadRobot = new ArrayList<>();

        do {
            pane.changeTitle(count + 1, teams[count % 2].getNomPays());
            pane.setTeamCourante(teams[count%2]);
            count += 1;
            for (Team t : teams) {
                if (t.lose()) {
                    end = true;
                }
                deadRobot.addAll(t.getRobots().stream().filter(r -> r.isDead())
                        .collect(Collectors.toList()));
            }
            teams[count % 2].getRobots().stream().filter(r -> r.isBased())
                    .forEach(r -> r.isHeals());
            if (!deadRobot.isEmpty()) {
                for (Robot r : deadRobot) {
                    r.revoke();
                }
                deadRobot.clear();
            }
        } while (!end);
    }

}
