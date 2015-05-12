package frame;

import config.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * @author seysn
 */
public class Initialize extends JFrame {

    public Initialize() {
        setLayout(new BorderLayout());

        JPanel pageStart = new JPanel(new GridLayout(1, 0));
        ButtonGroup bGroup = new ButtonGroup();
        JRadioButton ia = new JRadioButton("Ordinateur");
        JRadioButton player = new JRadioButton("Joueur");
        ia.setSelected(true);
        bGroup.add(ia);
        bGroup.add(player);
        pageStart.add(ia);
        pageStart.add(player);
        pageStart.setBorder(BorderFactory
                .createTitledBorder(BorderFactory.createLineBorder(new Color(0)), "Adversaire"));
        add(pageStart, BorderLayout.PAGE_START);

        JPanel team1 = new JPanel(new GridLayout(0, 2));
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

        JPanel team2 = new JPanel();
        JComboBox pays2 = new JComboBox(Constants.NAME_COUNTRY);
        team2.add(pays2);
        team2.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Equipe 2"));
        add(team2, BorderLayout.LINE_END);

        //setPreferredSize(new Dimension(400, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Initialisation de la partie");
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Initialize();
    }
}
