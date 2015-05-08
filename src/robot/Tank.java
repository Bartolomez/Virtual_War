package robot;

import action.Action;
import action.Attack;
import action.Move;
import config.Constants;
import plateau.Axis;
import team.Team;
import team.View;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author boinc
 */

public class Tank extends Robot {

    private List<Axis> axis;

    public Tank(View view, Team team) {
        super(view, team);
        this.setEnergy(Constants.ENERGY_TANK);
        this.axis = new ArrayList<>();
    }

    @Override public void suddenByShoot(Robot robot) {
        if (robot instanceof Tank || robot instanceof Shooter) {
            if (robot.getTeam().getTeam() != this.getTeam().getTeam()) {
                this.setEnergy(this.getEnergy() - robot.getDamageByShoot());
            }
        }
    }

    @Override public void suddenByMine() {
        this.setEnergy(this.getEnergy() - Constants.DAMAGE_SCAVENGER);
    }

    @Override public String getType() {
        return Constants.IS_TANK;
    }

    @Override public int getDamageByAction() {
        return Constants.DAMAGE_ACTION_TANK;
    }

    @Override public int getDamageByShoot() {
        return Constants.DAMAGE_SHOOT_TANK;
    }

    @Override public int getDamageByMove() {
        return Constants.DAMAGE_MOVE_TANK;
    }

    @Override public int getDamageByMine() {
        return Constants.DAMAGA_MINE_TANK;
    }

    @Override public List<Axis> getMoves() {
        return this.axis;
    }

    @Override public void isHeals() {
        if ((this.getEnergy() + Constants.CARE) > Constants.ENERGY_TANK) {
            this.setEnergy( Constants.ENERGY_TANK );
        } else {
            this.setEnergy( this.getEnergy() + Constants.CARE );
        }
        System.out.printf(this.getType() + " a regainé " + Constants.CARE + " PV\n");
    }

    @Override public Action selectedAction() {
        List<Axis> moves = searchMoves();
        List<Axis> target = searchTarget();

        if (!target.isEmpty()) {
            System.out.printf( "Vous pouvez selectionné : \n \t1 - Se deplacer \n \t2 - Attaquer "
                + "une cible\n" );
            System.out.printf( "Votre choix : " );
            int choosen = Constants.sc.nextInt();

            switch (choosen) {
                case 1:
                    return chosesDisplacement(moves);
                case 2:
                    int count = 0;
                    System.out.printf("Vous pouvez attaquer : ");

                    for (Axis axis : target) {
                        System.out.printf((count++) + " : " + axis + " \n");
                    }
                    choosen = Constants.sc.nextInt();
                    this.setObjective(target.get(choosen));

                    return new Attack(this);
                default:
                    System.err.printf("Choix impossible");

            }

        } else {
            System.out.printf(
                "Aucune cible autour de vous, choisiez un déplcement dans la " + "list ci-dessous" );
            return chosesDisplacement(moves);
        }

        return null;
    }

    private List<Axis> searchMoves() {
        List<Axis> moves = new ArrayList<>();
        List<Axis> movesTmp = new ArrayList<>();
        moves.addAll( Constants.MOVES_TANK.stream().map( axis -> this.getAxis().add( axis ) )
            .collect( Collectors.toList() ) );
        movesTmp.addAll( moves );
        for( Axis axis : movesTmp ) {
            if( !this.valueIsSuitable( axis ) ) {
                moves.remove( axis );
            } else if( this.valueContainsRobot( axis ) ) {
                moves.remove( axis );
            }
            try {
                if( thisAxisIsObstacle( axis ) ) {
                    moves.remove( axis );
                }
            } catch( Exception e ) {

            }
        }
        return moves;
    }

    private boolean valueIsSuitable(Axis axis) {
        return (axis.getX() >= 0 && axis.getX() < this.getView().getPlateau().getLength()
            && axis.getY() >= 0 && axis.getY() < this.getView().getPlateau().getWidth());
    }

    private boolean valueContainsRobot(Axis axis) {
        return this.getView().getPlateau().getCell(axis).isRobot() != 0;
    }

    private boolean thisAxisIsObstacle(Axis axis) {
        return this.getView().getPlateau().getCell(axis).isObstacle();
    }

    private List<Axis> searchTarget() {
        List<Axis> target = new ArrayList<>();
        List<Axis> targetTmp = new ArrayList<>();

        target.addAll( Constants.STRIKE_ZONE_TANK.stream().map( axis -> this.getAxis().add( axis ) )
            .collect( Collectors.toList() ) );

        for (int i = 0; i < target.size(); i++) {
            Axis axis = target.get(i);

            try {
                if (thisAxisIsObstacle(axis)) {
                    i = (((i + Constants.STRIKING_SCOPE_TANK) / Constants.STRIKING_SCOPE_TANK)
                            * Constants.STRIKING_SCOPE_TANK) - 1;
                    continue;
                }
                if (isNotSameTeam(axis) && valueContainsRobot(axis)) {
                    targetTmp.add(axis);
                }
            } catch (Exception e) {

            }
        }

        return targetTmp;
    }

    private boolean isNotSameTeam(Axis axis) {
        return this.getView().getPlateau().getCell(axis).isRobot() != this.getTeam().getTeam();
    }

    public Action chosesDisplacement(List<Axis> displacement) {
        int count = 0;
        System.out.printf( "Vous pouvez vous déplacer en : \n" );
        for( Axis axis : displacement ) {
            System.out.printf( "\t" + ( ++count ) + ": " + axis + "\n" );
        }
        System.out.printf( "Votre choix : " );
        this.setObjective( displacement.get( Constants.sc.nextInt() - 1 ) );
        return new Move( this );
    }

    public String toString() {
        return "Tank [" + super.getAxis().getX() + "," + super.getAxis().getY() + "] Energy:"
                + getEnergy();
    }
}
