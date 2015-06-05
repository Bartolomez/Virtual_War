package frame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author seysn
 */
public class Menu extends JFrame {

    public JButton jouer, regles;

    public Menu() {
        setTitle("Menu");
        init();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void init() {
        JPanel p = new JPanel();
        jouer = new JButton("Jouer");
        p.add(jouer);

        regles = new JButton("Regles du jeu");
        p.add(regles);

        JButton quit = new JButton("Quitter");
        quit.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                System.exit(0);
            }
        });
        p.add(quit);

        this.add(p);
    }

}
