package robot;

import action.Action;
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

public class Scavenger extends Robot {
    private List<Axis> axis;
    private int storedMines = Constants.MINES_MAX;

    public Scavenger(View view, Team team) {
        super(view, team);
        this.setEnergy(Constants.ENERGY_SHOOTER);
        this.axis = new ArrayList<>();
    }

    public int getStoredMines() {
        return storedMines;
    }

    public void setStoredMines(int storedMines) {
        this.storedMines = storedMines;
    }

    public boolean putMine(Axis axis) {
        if (this.getStoredMines() <= 0) {
            return false;
        } else if (thisCellIsEmpthy(axis)) {
            mine();
            return true;
        }

        return false;
    }

    private void mine() {
        this.getView().getPlateau().getCell(getObjective()).putMine(this.getNumberTeam());
        this.looseOneMine();
        this.losesEnergyAfterAction();

    }

    public void looseOneMine() {
        this.storedMines = this.storedMines - 1;
    }

    @Override public void suddenByShoot(Robot robot) {
        if (robot instanceof Tank || robot instanceof Shooter) {
            if (robot.getTeam().getTeam() != this.getTeam().getTeam()) {
                this.setEnergy(this.getEnergy() - robot.getDamageByShoot());
            }
        }
    }

    @Override public void suddenByMine() {
        this.setEnergy( this.getEnergy() - Constants.DAMAGE_SCAVENGER );
    }

    @Override public String getType() {
        return Constants.IS_SCAVENGER;
    }

    @Override public int getDamageByAction() {
        return Constants.DAMAGE_ACTION_SCAVENGER;
    }

    @Override public int getDamageByShoot() {
        return Constants.DAMAGE_SHOOT_SCAVENGER;
    }

    @Override public int getDamageByMove() {
        return Constants.DAMAGE_MOVE_SCAVENGER;
    }

    @Override public int getDamageByMine() {
        return Constants.DAMAGE_MINE_SCAVENGER;
    }

    @Override public List<Axis> getMoves() {
        return this.axis;
    }

    @Override public void isHeals() {
        if ((this.getEnergy() + Constants.CARE) > Constants.ENERGY_SCAVENGER) {
            this.setEnergy(Constants.ENERGY_SCAVENGER);
        } else {
            this.setEnergy(this.getEnergy() + Constants.CARE);
        }
        this.setStoredMines(Constants.MINES_MAX);
        System.out.printf(this.getType() + " a regagné " + Constants.CARE + " PV et a remis " + ""
                + Constants.MINES_MAX + " mines dans son stock\n");
    }

    @Override public Action selectedAction() {
        List<Axis> moves = searchMoves();
        List<Axis> mines = initialzedMines();
        if (haveMines(mines)) {
            System.out
                    .printf(
                        "Vous pouvez selectionner : \n \t1 - Se déplacer \n \t2 - Miner le terrain" );
            int choosen = Constants.sc.nextInt();
            switch (choosen) {
                case 1:
                    return chosesDisplacement(moves);
                case 2:
                    int count = 0;
                    System.out.printf("Vous pouvez miner les positions suivantes : ");

                    for (Axis axis : moves) {
                        System.out.printf((count++) + " : " + axis);
                    }
                    choosen = Constants.sc.nextInt();
                    this.setObjective(moves.get(choosen));
                    mine();
                    return null;
                default:
                    System.err.printf("Choix impossible");
            }
        } else {
            System.out.printf(
                "Aucune cible autour de vous, choisissez un déplacement dans la " + "liste ci-dessous" );
            return chosesDisplacement(moves);
        }
        return null;
    }

    public Action chosesDisplacement(List<Axis> displacement) {
        int count = 0;
        System.out.printf( "Vous pouvez vous déplacer en : \n" );
        for( Axis axis : displacement ) {
            System.out.printf( "\t" + ( ++count ) + ": " + direction( axis ) + "\n" );
        }
        System.out.printf( "Votre choix : " );
        this.setObjective( displacement.get( Constants.sc.nextInt() - 1 ) );
        return new Move( this );
    }

    private List<Axis> searchMoves() {
        List<Axis> moves = new ArrayList<>();
        List<Axis> movesTmp = new ArrayList<>();
        moves.addAll( Constants.MOVES_SCAVENGER.stream().map( axis -> this.getAxis().add( axis ) )
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
        return ( ( axis.getX() >= 0 ) && ( axis.getX() < this.getView().getPlateau().getLength() )
            && ( axis.getY() >= 0 ) && ( axis.getY() < this.getView().getPlateau().getWidth() ) );
    }

    private boolean valueContainsRobot(Axis axis) {
        return this.getView().getPlateau().getCell(axis).isRobot() != 0;
    }

    private boolean thisAxisIsObstacle(Axis axis) {
        return this.getView().getPlateau().getCell(axis).isObstacle();
    }

    public boolean haveMines(List<Axis> mines) { return !mines.isEmpty() && this.storedMines < 0; }

    private List<Axis> initialzedMines() {
        List<Axis> mines = new ArrayList<>();
        List<Axis> minesTmp = new ArrayList<>();
        mines.addAll( Constants.MOVES_SCAVENGER.stream().map( axis -> this.getAxis().add( axis ) )
            .collect( Collectors.toList() ) );
        minesTmp.addAll( mines );
        for (Axis axis : minesTmp) {
            try {
                if (thisCellIsEmpthy(axis)) {
                    mines.remove(axis);
                }
            } catch (Exception e) {}
        }
        return mines;
    }

    private boolean thisCellIsEmpthy(Axis axis) {
        return !this.getView().getPlateau().getCell(axis).isEmpty();
    }

    private boolean isNotSameTeam(Axis axis) {
        return this.getView().getPlateau().getCell(axis).isRobot() != this.getTeam().getTeam();
    }

    public String toString() {
        return "Scavenger [" + super.getAxis().getX() + "," + super.getAxis().getY() + "] Energy:"
                + getEnergy();
    }
}
