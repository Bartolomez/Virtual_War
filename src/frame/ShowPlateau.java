package frame;

import plateau.Plateau;

import javax.swing.*;

/**
 * @author seysn
 */
public class ShowPlateau extends JFrame {

    private Plateau       p;
    private JTable        table;

    public ShowPlateau() {
        p = new Plateau(10, 10, 0.2);
        table = new JTable(p.getLength(), p.getWidth());

        add(table);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Plateau de Jeu");
        pack();
        setVisible(true);
    }

    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        new ShowPlateau();
    }
}
