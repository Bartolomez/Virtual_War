package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author seysn
 */
public class Menu extends JFrame {

    JFrame regle;

    public Menu() {
        regle = new Regles();
        setTitle("Menu");
        init();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void init() {
        JPanel p = new JPanel();
        JButton jouer = new JButton("Jouer");
        jouer.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                // TODO
            }
        });
        p.add(jouer);

        JButton regles = new JButton("Regles du jeu");
        regles.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                regle.setVisible(true);
                setVisible(false);
                dispose();
            }
        });
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

class Regles extends JFrame {

    public Regles() {
        setTitle("Regles du jeu");
        init();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(false);
    }

    private void init() {
        this.setLayout(new GridLayout(0, 1));

        JPanel buttons = new JPanel();
        JLabel text = new JLabel();
        buttons.setLayout(new GridLayout(1, 0));
        JButton b1 = new JButton("Regles générales");
        b1.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                text.setText("TODO1");
            }
        });
        buttons.add(b1);
        JButton b2 = new JButton("Déroulement du tour");
        b2.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                text.setText("TODO2");
            }
        });
        buttons.add(b2);
        JButton b3 = new JButton("Les types de robots");
        b3.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                text.setText("TODO3");
            }
        });
        buttons.add(b3);
        JButton b4 = new JButton("Modes de jeu");
        b4.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                text.setText("TODO4");
            }
        });
        buttons.add(b4);

        this.add(buttons);
        this.add(text);

        JPanel p2 = new JPanel();
        JButton retour = new JButton("Retour au Menu");
        retour.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                setVisible(false);
                dispose();
                new Menu();
            }
        });
        p2.add(retour);

        this.add(p2);
    }
}
