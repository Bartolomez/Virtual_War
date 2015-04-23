package robot;

import config.Constants;
import plateau.Axis;
import team.Team;
import team.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boinc
 */

public class Shooter extends Robot {
    private List<Axis> axis;

    public Shooter(View view, Team team) {
        super (view, team);
        this.setEnergy (Constants.ENERGY_SHOOTER);
        this.axis = new ArrayList<Axis> ();
    }

    @Override public void suddenByShoot(Robot robot) {
        if ( robot instanceof Tank || robot instanceof Shooter ) {
            if ( robot.getTeam ().getTeam () != this.getTeam ().getTeam () ) {
                this.setEnergy (this.getEnergy () - robot.getDamageByShoot ());
            }
        }
    }

    @Override public void suddenByMine() {
        this.setEnergy (this.getEnergy () - Constants.DAMAGE_SCAVENGER);
    }

    @Override public String getType() {
        return Constants.IS_SHOOTER;
    }

    @Override public int getDamageByAction() {
        return Constants.DAMAGE_ACTION_SHOOTER;
    }

    @Override public int getDamageByShoot() {
        return Constants.DAMAGE_SHOOT_SHOOTER;
    }

    @Override public int getDamageByMove() {
        return Constants.DAMAGE_MOVE_SHOOTER;
    }

    @Override public int getDamageByMine() {
        return Constants.DAMAGA_MINE_SHOOTER;
    }

    @Override public List<Axis> getMoves() {
        return this.axis;
    }

    @Override public void isHeals() {
        if ( ( this.getEnergy () + Constants.CARE ) > Constants.ENERGY_SHOOTER ) {
            this.setEnergy (Constants.ENERGY_SHOOTER);
        } else {
            this.setEnergy (this.getEnergy () + Constants.CARE);
        }

        System.out.printf (this.getType () + " a regainé " + Constants.CARE + " PV");
    }

    @Override public Action selectedAction() {
        List<Axis> moves = searchMoves ();
        List<Axis> target = searchTarget ();

        if ( !target.isEmpty () ) {
            System.out.printf (
                "Vous pouvez selectionné : \n \t1 - Se deplacer \n \t2 - Attaquer " + "une cible");
            int choosen = Constants.sc.nextInt ();

            switch ( choosen ) {
                case 1:
                    return chosesDisplacement (moves);
                case 2:
                    int count = 0;
                    System.out.printf ("Vous pouvez attaquer : ");

                    for ( Axis axis : target ) {
                        System.out.printf (( count++ ) + " : " + axis + " " + direction (axis));
                    }
                    choosen = Constants.sc.nextInt ();
                    this.setObjective (target.get (choosen));

                    return new Attack (this);
                default:
                    System.err.printf ("Choix impossible");

            }

        } else {
            System.out.printf (
                "Aucune cible autour de vous, choisiez un déplcement dans la " + "list ci-dessous");
            return chosesDisplacement (moves);
        }

        return null;
    }

    @Override public String toString() {
        return this.getType () + ", " + super.toString ();
    }

    private List<Axis> searchMoves() {
        List<Axis> moves = new ArrayList<Axis> ();
        List<Axis> movesTmp = new ArrayList<Axis> ();

        for ( Axis axis : Constants.MOVES_SHOOTER ) {
            moves.add (this.getAxis ().add (axis));
        }

        movesTmp.addAll (moves);

        for ( Axis axis : movesTmp ) {
            if ( this.valueIsSuitable (axis) || this.valueContainsRobot (axis) ) {
                moves.remove (axis);
            }
            try {
                if ( thisAxisIsObstacle (axis) ) {
                    moves.remove (axis);
                }
            } catch ( Exception e ) {

            }
        }

        return moves;
    }

    private boolean valueIsSuitable(Axis axis) {
        return axis.getX () < 0 || axis.getY () < 0 || axis.getX () >= this.getView ().getPlateau ()
            .getLength () || axis.getY () >= this.getView ().getPlateau ().getWidth ();
    }

    private boolean valueContainsRobot(Axis axis) {
        return (this.getView ().getPlateau ().getCell (axis).getRobot () != null) || (this
            .getView ().getPlateau ().getCell (axis).isRobot () != 0);
    }

    private boolean thisAxisIsObstacle(Axis axis) {
        return this.getView ().getPlateau ().getCell (axis).isObstacle ();
    }

    private List<Axis> searchTarget() {
        List<Axis> target = new ArrayList<Axis> ();
        List<Axis> targetTmp = new ArrayList<Axis> ();

        for ( Axis axis : Constants.STRIKE_ZONE_SHOOTER ) {
            target.add (this.getAxis ().add (axis));
        }

        for ( int i = 0; i < target.size (); i++ ) {
            Axis axis = target.get (i);

            try {
                if ( thisAxisIsObstacle (axis) ) {
                    i = ( ( ( i + Constants.STRIKING_SCOPE_SHOOTER ) / Constants.STRIKING_SCOPE_SHOOTER )
                        * Constants.STRIKING_SCOPE_SHOOTER ) - 1;
                    continue;
                }
                if ( isNotSameTeam(axis) && valueContainsRobot(axis) ) {
                    targetTmp.add(axis);
                }
            } catch ( Exception e ) {

            }
        }

        return target;
    }

    private boolean isNotSameTeam(Axis axis) {
        return this.getView ().getPlateau ().getCell (axis).isRobot () != this.getTeam ()
            .getTeam ();
    }
}
