package frame;

import plateau.Plateau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author seysn
 */
public class ShowPlateau extends JPanel {

    private List<MyCell> list       = new ArrayList<>();
    private int          tailleCase = 30;
    private Plateau p;
    private Robot selected;

    public ShowPlateau(Plateau p) {
        this.p = p;
        init();
        this.setPreferredSize(new Dimension(tailleCase * p.getLength(), tailleCase * p.getWidth()));
        this.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                for (MyCell c : list) {

                }
            }
        });
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
                } else if (p.getCell(i, j).getRobot() != null
                        && p.getCell(i, j).getRobot().getTeam().getTeam() == 1) {
                    list.add(new MyCell(tailleCase * i, tailleCase * j, tailleCase, tailleCase,
                            "Robot1"));
                } else if (p.getCell(i, j).getRobot() != null
                        && p.getCell(i, j).getRobot().getTeam().getTeam() == 2) {
                    list.add(new MyCell(tailleCase * i, tailleCase * j, tailleCase, tailleCase,
                            "Robot2"));
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
            } else if (c.isRobotTeam1()) {
                graphics.setColor(new Color(0x1500FF));
            } else if (c.isRobotTeam2()) {
                graphics.setColor(new Color(0xDD0C00));
            }
            graphics.fillRect((int)c.getX(), (int)c.getY(), (int)c.getHeight(), (int)c.getWidth());
        }
    }
}


class MyCell extends Rectangle {
    public String type;

    public MyCell(int posX, int posY, int sizeX, int sizeY, String type) {
        super(posX, posY, sizeX, sizeY);
        this.type = type;
    }

    public boolean isVide() { return type == null; }

    public boolean isBase() { return type.equals("Base"); }

    public boolean isObstacle() { return type.equals("Obstacle"); }

    public boolean isRobotTeam1() { return type.equals("Robot1"); }

    public boolean isRobotTeam2() { return type.equals("Robot2"); }
}
