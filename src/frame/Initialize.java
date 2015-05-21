package frame;

import config.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * La fenetre permettant d'initialiser le plateau et les équipes
 * TODO : rendre la frame fonctionnelle
 *
 * @author seysn
 */
public class Initialize extends JFrame {

    public Initialize() {
        init();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Initialisation de la partie");
        pack();
        setVisible(true);
    }

    private void init(){
        setLayout(new BorderLayout());
        JPanel pageStart = new JPanel(new GridLayout(0, 2));
        pageStart.add(new JLabel("Largeur"));
        JTextField larg = new JTextField("10");
        pageStart.add(larg);
        pageStart.add(new JLabel("Hauteur"));
        JTextField haut = new JTextField("10");
        pageStart.add(haut);
        pageStart.add(new JLabel("% d'obstacles"));
        JTextField pourc = new JTextField("25");
        pageStart.add(pourc);
        pageStart.setBorder(BorderFactory
                .createTitledBorder(BorderFactory.createLineBorder(new Color(0)), "Plateau"));
        add(pageStart, BorderLayout.PAGE_START);

        JPanel team1 = new JPanel(new GridLayout(0, 2));
        JComboBox type = new JComboBox(new String[]{"Ordinateur","Joueur"});
        team1.add(new JLabel("Type"));
        team1.add(type);
        JComboBox pays1 = new JComboBox(Constants.NAME_COUNTRY);
        team1.add(new JLabel("Pays"));
        team1.add(pays1);
        team1.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Equipe 1"));
        JTextField nbTank1 = new JTextField("1");
        team1.add(new JLabel("Char"));
        team1.add(nbTank1);
        JTextField nbShooter1 = new JTextField("1");
        team1.add(new JLabel("Tireur"));
        team1.add(nbShooter1);
        JTextField nbScavenger1 = new JTextField("1");
        team1.add(new JLabel("Piegeur"));
        team1.add(nbScavenger1);

        add(team1, BorderLayout.LINE_START);

        JPanel team2 = new JPanel(new GridLayout(0, 2));
        JComboBox type2 = new JComboBox(new String[]{"Ordinateur","Joueur"});
        team2.add(new JLabel("Type"));
        team2.add(type2);
        JComboBox pays2 = new JComboBox(Constants.NAME_COUNTRY);
        team2.add(new JLabel("Pays"));
        team2.add(pays2);
        team2.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Equipe 2"));
        JTextField nbTank2 = new JTextField("1");
        team2.add(new JLabel("Char"));
        team2.add(nbTank2);
        JTextField nbShooter2 = new JTextField("1");
        team2.add(new JLabel("Tireur"));
        team2.add(nbShooter2);
        JTextField nbScavenger2 = new JTextField("1");
        team2.add(new JLabel("Piegeur"));
        team2.add(nbScavenger2);

        add(team2, BorderLayout.LINE_END);

        JPanel panelValider = new JPanel();
        JButton valider = new JButton("Valider");
        panelValider.add(valider);
        JButton retour = new JButton("Retour");
        retour.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                System.exit(0);
            }
        });
        panelValider.add(retour);
        add(panelValider, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        new Initialize();
    }
}
