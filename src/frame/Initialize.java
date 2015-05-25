package frame;

import config.Constants;

import javax.swing.*;
import java.awt.*;


/**
 * La fenetre permettant d'initialiser le plateau et les équipes
 *
 * @author seysn
 */
public class Initialize extends JFrame {
    public JTextField larg, haut, pourc, nbTank1, nbTank2, nbShooter1, nbShooter2, nbScavenger1,
            nbScavenger2;
    public JComboBox type1, type2, pays1, pays2;
    public JButton valider, retour;

    public Initialize() {
        init();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Initialisation de la partie");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void init() {
        setLayout(new BorderLayout());
        JPanel pageStart = new JPanel(new GridLayout(0, 2));
        pageStart.add(new JLabel("Largeur"));
        larg = new JTextField("10");
        pageStart.add(larg);
        pageStart.add(new JLabel("Hauteur"));
        haut = new JTextField("10");
        pageStart.add(haut);
        pageStart.add(new JLabel("% d'obstacles"));
        pourc = new JTextField("25");
        pageStart.add(pourc);
        pageStart.setBorder(BorderFactory
                .createTitledBorder(BorderFactory.createLineBorder(new Color(0)), "Plateau"));
        add(pageStart, BorderLayout.PAGE_START);

        JPanel team1 = new JPanel(new GridLayout(0, 2));
        type1 = new JComboBox(new String[] {"Ordinateur", "Joueur"});
        team1.add(new JLabel("Type"));
        team1.add(type1);
        pays1 = new JComboBox(Constants.NAME_COUNTRY);
        team1.add(new JLabel("Pays"));
        team1.add(pays1);
        team1.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Equipe 1"));
        nbTank1 = new JTextField("1");
        team1.add(new JLabel("Char"));
        team1.add(nbTank1);
        nbShooter1 = new JTextField("1");
        team1.add(new JLabel("Tireur"));
        team1.add(nbShooter1);
        nbScavenger1 = new JTextField("1");
        team1.add(new JLabel("Piegeur"));
        team1.add(nbScavenger1);

        add(team1, BorderLayout.LINE_START);

        JPanel team2 = new JPanel(new GridLayout(0, 2));
        type2 = new JComboBox(new String[] {"Ordinateur", "Joueur"});
        team2.add(new JLabel("Type"));
        team2.add(type2);
        pays2 = new JComboBox(Constants.NAME_COUNTRY);
        pays2.setSelectedIndex(1);
        team2.add(new JLabel("Pays"));
        team2.add(pays2);
        team2.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Equipe 2"));
        nbTank2 = new JTextField("1");
        team2.add(new JLabel("Char"));
        team2.add(nbTank2);
        nbShooter2 = new JTextField("1");
        team2.add(new JLabel("Tireur"));
        team2.add(nbShooter2);
        nbScavenger2 = new JTextField("1");
        team2.add(new JLabel("Piegeur"));
        team2.add(nbScavenger2);

        add(team2, BorderLayout.LINE_END);

        JPanel panelValider = new JPanel();
        valider = new JButton("Valider");
        panelValider.add(valider);
        retour = new JButton("Retour");
        panelValider.add(retour);
        add(panelValider, BorderLayout.SOUTH);

    }

    /**
     * Liste toutes les erreurs possibles pour les valeurs rentrées par l'utilisateur
     *
     * @return boolean
     */
    public boolean canStart() {
        boolean b = true;
        if (Integer.parseInt(this.haut.getText()) < 3
                || Integer.parseInt(this.larg.getText()) < 3) {
            JOptionPane.showMessageDialog(null,
                    "La hauteur et la largeur du plateau doivent être supérieures ou égales à 3",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            b = false;
        } else if (Integer.parseInt(this.pourc.getText()) < 0
                || Integer.parseInt(this.pourc.getText()) > 100) {
            JOptionPane.showMessageDialog(null, "La valeur du pourcentage d'obstacle est incorrect",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            b = false;
        } else if (Integer.parseInt(this.nbScavenger1.getText()) < 0
                || Integer.parseInt(this.nbScavenger1.getText()) > 2) {
            JOptionPane.showMessageDialog(null,
                    "Le nombre de piegeur pour l'équipe 1 doit être compris entre 0 et 2", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            b = false;
        } else if (Integer.parseInt(this.nbScavenger2.getText()) < 0
                || Integer.parseInt(this.nbScavenger2.getText()) > 2) {
            JOptionPane.showMessageDialog(null,
                    "Le nombre de piegeur pour l'équipe 2 doit être compris entre 0 et 2", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            b = false;
        } else if (Integer.parseInt(this.nbShooter1.getText()) < 0
                || Integer.parseInt(this.nbShooter1.getText()) > 2) {
            JOptionPane.showMessageDialog(null,
                    "Le nombre de tireur pour l'équipe 1 doit être compris entre 0 et 2", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            b = false;
        } else if (Integer.parseInt(this.nbShooter2.getText()) < 0
                || Integer.parseInt(this.nbShooter2.getText()) > 2) {
            JOptionPane.showMessageDialog(null,
                    "Le nombre de tireur pour l'équipe 2 doit être compris entre 0 et 2", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            b = false;
        } else if (Integer.parseInt(this.nbTank1.getText()) < 0
                || Integer.parseInt(this.nbTank1.getText()) > 2) {
            JOptionPane.showMessageDialog(null,
                    "Le nombre de char pour l'équipe 1 doit être compris entre 0 et 2", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            b = false;
        } else if (Integer.parseInt(this.nbTank2.getText()) < 0
                || Integer.parseInt(this.nbTank2.getText()) > 2) {
            JOptionPane.showMessageDialog(null,
                    "Le nombre de char pour l'équipe 2 doit être compris entre 0 et 2", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            b = false;
        } else if ((Integer.parseInt(this.nbTank1.getText()) + Integer
                .parseInt(this.nbShooter1.getText()) + Integer
                .parseInt(this.nbScavenger1.getText())) != (Integer.parseInt(this.nbTank2.getText())
                + Integer.parseInt(this.nbShooter2.getText()) + Integer
                .parseInt(this.nbScavenger2.getText()))) {
            JOptionPane
                    .showMessageDialog(null, "Les deux équipes n'ont pas le même nombre de robots",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
            b = false;
        } else if (this.pays1.getSelectedItem().equals(this.pays2.getSelectedItem())) {
            JOptionPane
                    .showMessageDialog(null, "Les deux équipes ne peuvent pas avoir le même pays",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
            b = false;
        }
        return b;
    }

    public static void main(String[] args) {
        new Initialize();
    }
}
