package frame;

import plateau.Plateau;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author seysn
 */
public class ShowPlateau extends JPanel {

    private List<MyCell> list       = new ArrayList<>();
    private int          tailleCase = 30;
    private Plateau p;

    public ShowPlateau(Plateau p) {
        this.p = p;
        init();
        this.setPreferredSize(new Dimension(tailleCase * p.getLength(), tailleCase * p.getWidth()));
    }

    private void init() {
        for (int i = 0; i < p.getLength(); i++) {
            for (int j = 0; j < p.getWidth(); j++) {
                if (p.getCell(i, j).getBase() != 0) {
                    list.add(new MyCell(tailleCase * i, tailleCase * j, tailleCase, tailleCase,
                            "Base"));
                } else if (p.getCell(i, j).isObstacle()) {
                    list.add(new MyCell(tailleCase * i, tailleCase * j, tailleCase, tailleCase,
                            "Obstacle"));
                } else {
                    list.add(new MyCell(tailleCase * i, tailleCase * j, tailleCase, tailleCase,
                            null));
                }

            }
        }
    }

    @Override protected void paintComponent(Graphics graphics) {
        for (MyCell c : list) {
            if (c.isObstacle()) {
                graphics.setColor(new Color(0));
            } else if (c.isBase()) {
                graphics.setColor(new Color(0x0EFF00));
            } else if (c.isVide()) {
                graphics.setColor(new Color(0xFFFFFF));
            }
            graphics.fillRect(c.posX, c.posY, c.sizeX, c.sizeY);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        Plateau pl = new Plateau(10, 10, 0.2);
        frame.add(new ShowPlateau(pl));
        System.out.println(pl);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}


class MyCell {
    public int posX, posY, sizeX, sizeY;
    public String type;

    public MyCell(int posX, int posY, int sizeX, int sizeY, String type) {
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.type = type;
    }

    public boolean isVide() { return type == null; }

    public boolean isBase() { return type == "Base"; }

    public boolean isObstacle() { return type == "Obstacle"; }

    public boolean isShooter() { return type == "Shooter"; }

    public boolean isTank() { return type == "Tank"; }

    public boolean isScavenger() { return type == "Scavenger"; }
}
