package frame;

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

        JPanel team1 = new JPanel();
        
        team1.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Equipe 1"));
        add(team1, BorderLayout.LINE_START);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Initialisation de la partie");
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Initialize();
    }
}
