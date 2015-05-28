package frame;

import config.Constants;
import plateau.Axis;
import plateau.Plateau;
import robot.Robot;
import team.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author seysn
 */
public class ShowPlateau extends JFrame {
    public         JLabel       title;
    public static  JPanel       footer;
    public         PanelPlateau pane;
    public static  Robot        selected;
    public static  Action       action;
    private static Team         teamCourante;
    public static ArrayList<JButton> buttons = new ArrayList<>();
    private static JPanel paneButtons;

    public ShowPlateau(Plateau p) {
        setTitle("Jeu");
        setResizable(false);
        pane = new PanelPlateau(p);
        init();
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void init() {
        title = new JLabel();
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setPreferredSize(new Dimension(30, 30));
        setLayout(new BorderLayout());
        add(title, BorderLayout.NORTH);
        add(pane, BorderLayout.CENTER);
        footer = new JPanel();
        footer.setLayout(new GridLayout(0, 1));
        JLabel info = new JLabel();
        footer.add(info);
        footer.setPreferredSize(new Dimension(100, 100));
        pane.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent event) {
                if (!buttons.isEmpty()) {
                    for (JButton b : buttons) {
                        footer.remove(b);
                    }
                    buttons = new ArrayList<JButton>();
                    repaint();
                }
                for (MyCell c : pane.list) {
                    if (c.contains(event.getX(), event.getY()) && (c.isRobotTeam1() || c
                            .isRobotTeam2())) {
                        info.setText("Vous avez selectionné un Robot");
                        selected = pane.p.getCell((int) c.getX() / pane.tailleCase,
                                (int) c.getY() / pane.tailleCase).getRobot();
                        addButtons(selected);
                        repaint();
                    } else if (c.contains(event.getX(), event.getY()) && c.isBaseTeam1()) {
                        selected = null;
                        info.setText("Vous avez selectionné votre Base");
                        addButtons();
                        repaint();
                    } else if (c.contains(event.getX(), event.getY()) && c.isBaseTeam2()) {
                        selected = null;
                        info.setText("Vous avez selectionné la Base adverse");
                        repaint();
                    } else if (c.contains(event.getX(), event.getY()) && (c.isVide() || c
                            .isObstacle())) {
                        selected = null;
                        info.setText("Vous n'avez rien selectionné");
                        repaint();
                    }
                }
            }
        });
        add(footer, BorderLayout.SOUTH);
    }

    public void changeTitle(int tour, String name) {
        this.title.setText("[Tour " + tour + "] " + name);
    }

    public static void addButtons() {
        for (Robot r : teamCourante.getRobots()) {
            if (r.getAxis().equals(teamCourante.getAxisBase())) {
                buttons.add(new JButton(r.getType() + " Energie : " + r.getEnergy()));
                footer.add(buttons.get(buttons.size() - 1));
                buttons.get(buttons.size() - 1).addMouseListener(new MouseAdapter() {
                    @Override public void mouseClicked(MouseEvent event) {
                        selected = r;
                        addButtons(r);
                    }
                });
            }
        }
    }

    public static void addButtons(Robot r) {
        for (Axis a : r.searchMoves()) {
            buttons.add(new JButton("Deplacement vers " + a));
            footer.add(buttons.get(buttons.size() - 1));
            buttons.get(buttons.size() - 1).addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent event) {
                    System.out.println("lol");
                }
            });
        }
    }

    public static void setTeamCourante(Team teamCourante) {
        ShowPlateau.teamCourante = teamCourante;
    }
}


class PanelPlateau extends JPanel {

    public List<MyCell> list       = new ArrayList<>();
    public int          tailleCase = 30;
    public Plateau p;

    public PanelPlateau(Plateau p) {
        this.p = p;
        init();
        this.setPreferredSize(new Dimension(tailleCase * p.getLength(), tailleCase * p.getWidth()));
    }

    private void init() {
        for (int i = 0; i < p.getLength(); i++) {
            for (int j = 0; j < p.getWidth(); j++) {
                if (p.getCell(i, j).getBase() == Constants.FIRST_TEAM) {
                    list.add(new MyCell(tailleCase * i, tailleCase * j, tailleCase, tailleCase,
                            "Base1"));
                } else if (p.getCell(i, j).getBase() == Constants.SECOND_TEAM) {
                    list.add(new MyCell(tailleCase * i, tailleCase * j, tailleCase, tailleCase,
                            "Base2"));
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
                            "Vide"));
                }
            }
        }
    }

    @Override protected void paintComponent(Graphics graphics) {
        for (MyCell c : list) {
            if (c.isObstacle()) {
                graphics.setColor(new Color(0));
            } else if (c.isBaseTeam1() || c.isBaseTeam2()) {
                graphics.setColor(new Color(0x0EFF00));
            } else if (c.isVide()) {
                graphics.setColor(new Color(0xFFFFFF));
            } else if (c.isRobotTeam1()) {
                graphics.setColor(new Color(0x1500FF));
            } else if (c.isRobotTeam2()) {
                graphics.setColor(new Color(0xDD0C00));
            }
            graphics.fillRect((int) c.getX(), (int) c.getY(), (int) c.getHeight(),
                    (int) c.getWidth());
        }
    }

}


class MyCell extends Rectangle {
    public String type;

    public MyCell(int posX, int posY, int sizeX, int sizeY, String type) {
        super(posX, posY, sizeX, sizeY);
        this.type = type;
    }

    public boolean isVide() { return type.equals("Vide"); }

    public boolean isBaseTeam1() { return type.equals("Base1"); }

    public boolean isBaseTeam2() { return type.equals("Base2"); }

    public boolean isObstacle() { return type.equals("Obstacle"); }

    public boolean isRobotTeam1() { return type.equals("Robot1"); }

    public boolean isRobotTeam2() { return type.equals("Robot2"); }
}
