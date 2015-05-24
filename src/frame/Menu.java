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

    public static void main(String[] args) {
        new Menu();
    }
}


class Regles extends JFrame {
    final static String REGLE1 =
            "Deux équipes doivent s'affronter dans un plateau où ils auront droit à un maximum de 5 robots. "
                    + "Les robots de chaque équipe devront d'attaquer et "
                    + "pourront regenerer leurs points de vie à la base où ils ne pourront pas être attaqués."
                    + "La partie se termine au moment où l'une des deux équipes n'a plus de robot,"
                    + "l'autre équipe est alors déclarée gagnante.";
    final static String REGLE2 = "Lorsque c'est son tour, le joueur doit selectionner un robot."
            + "S'il selectionne une base contenant plusieurs robots,"
            + "une liste de tout les robots est affichée."
            + "Une fois le robot selectionné, une liste de toutes les actions"
            + "possibles est affichée puis termine son tour une fois l'action effectuée.";
    final static String REGLE3 =
            "- Le Tireur :" + "\nIl peut se déplacer d'une case dans toutes les directions,"
                    + "et peut attaquer un ennemi directement qui se trouve à une distance de "
                    + "3 cases maximum." + "\n- Le Piegeur : "
                    + "\nIl peut se déplacer d'une case dans toutes les directions,"
                    + "et peut poser une mine à une distance d'une case" + "\n- Le Char : "
                    + "\nIl peut se déplacer de deux "
                    + "cases maximum de façon verticale et horizontale. "
                    + "Si un obstacle lui empeche d'effectuer ce déplacement, il s'arrête juste avant."
                    + "Il peut attaquer un ennemi uniquement de façon verticale et"
                    + "horizontale avec une portée de dix cases.";
    final static String REGLE4 = "Il existe trois modes de jeu :" + "\n- Ordinateur VS Ordinateur :"
            + "\nCe mode permet de visualiser deux ordinateurs se combattre automatiquement."
            + "\n- Joueur VS Ordinateur :"
            + "\nCe mode est destiné à ếtre joué seul contre un ordinateur effectuant ses "
            + "actions automatiquement." + "\n- Joueur VS Joueur :"
            + "\n Ce mode est destiné a être joué à deux joueurs humains qui se combattent "
            + "l'un contre l'autre.";

    public Regles() {
        setTitle("Regles du jeu");
        init();
        setPreferredSize(new Dimension(800,300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(false);
    }

    private void init() {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gridbag);

        JTextArea text = textAreaProperties(new JTextArea(REGLE1));

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        JButton b1 = new JButton("Regles générales");
        b1.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                text.setText(REGLE1);
            }
        });
        gridbag.setConstraints(b1, c);
        add(b1);
        JButton b2 = new JButton("Déroulement du tour");
        b2.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                text.setText(REGLE2);
            }
        });
        gridbag.setConstraints(b2, c);
        add(b2);
        JButton b3 = new JButton("Les types de robots");
        b3.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                text.setText(REGLE3);
            }
        });
        gridbag.setConstraints(b3, c);
        add(b3);
        c.gridwidth = GridBagConstraints.REMAINDER;
        JButton b4 = new JButton("Modes de jeu");
        b4.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                text.setText(REGLE4);
            }
        });
        gridbag.setConstraints(b4, c);
        add(b4);

        c.weightx = 0.0;
        c.weighty = 1.0;
        gridbag.setConstraints(text, c);
        add(text);

        JButton retour = new JButton("Retour au Menu");
        retour.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                setVisible(false);
                dispose();
                new Menu();
            }
        });
        c.weighty = 0.0;
        gridbag.setConstraints(retour,c);
        add(retour);

    }

    private JTextArea textAreaProperties(JTextArea textArea) {
        textArea.setEditable(false);
        textArea.setCursor(null);
        textArea.setOpaque(false);
        textArea.setFocusable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(10, 10, 10, 10));
        return textArea;
    }
}
