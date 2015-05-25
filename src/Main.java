import frame.Initialize;
import frame.Menu;
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
    public Plateau p;
    public Team    t1, t2;

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
                    System.out.println("ok");
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
