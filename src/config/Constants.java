package config;

import plateau.Axis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author boinc
 */

public class Constants {

	public static final Random random = new Random(0);
	public static Scanner sc = new Scanner(System.in);

	public static final int FIRST_TEAM = 1;
	public static final int SECOND_TEAM = 2;

	public static final String[] NAME_COUNTRY = new String[] { "France",
			"Japon", "USA", "Russie" };

    public static final int CARE = 2;
    public static final int MINES_MAX = 10;

    /* Definition of a tank by these assets */
    public static final String IS_TANK = "Char";
    public static final int DAMAGE_ACTION_TANK = 1;
    public static final int CAN_MOVE_TANK = 2;
    public static final int DAMAGE_MOVE_TANK = 5;
    public static final int DAMAGE_SHOOT_TANK = 6;
    public static final int DAMAGA_MINE_TANK = 6;
    public static final int ENERGY_TANK = 60;

    /* Definition of a shooter by these assets */
    public static final String IS_SHOOTER = "Tireur";
    public static final int DAMAGE_ACTION_SHOOTER = 2;
    public static final int DAMAGE_MOVE_SHOOTER = 1;
    public static final int DAMAGE_SHOOT_SHOOTER = 3;
    public static final int DAMAGA_MINE_SHOOTER = 3;
    public static final int ENERGY_SHOOTER = 40;

    /* Definition of a scanvenger assets */
    public static final String IS_SCAVENGER = "Piegeur";
    public static final int DAMAGE_SCAVENGER = 3;
    public static final int DAMAGE_ACTION_SCAVENGER = 2;
    public static final int DAMAGE_MOVE_SCAVENGER = 2;
    public static final int DAMAGE_SHOOT_SCAVENGER = 2;
    public static final int DAMAGE_MINE_SCAVENGER = 0;
    public static final int ENERGY_SCAVENGER = 50;

    /* Moves */
    public static final Axis NORTH = new Axis(0, -1);
    public static final Axis NORTH_EST = new Axis(1, -1);
    public static final Axis NORTH_WEST = new Axis(-1, -1);
    public static final Axis SOUTH = new Axis(0, 1);
    public static final Axis SOUTH_EST = new Axis(1, 1);
    public static final Axis SOUTH_WEST = new Axis(-1, 1);
    public static final Axis EST = new Axis(-1, 0);
    public static final Axis WEST = new Axis(1, 0);
    /* Only because tank are hipster */
    public static final Axis TANK_NORTH = new Axis(0, -2);
    public static final Axis TANK_SOUTH = new Axis(0, 2);
    public static final Axis TANK_EST = new Axis(2, 0);
    public static final Axis TANK_WEST = new Axis(-2, 0);

    /* List of each moves for each robots */
    public static List<Axis> MOVES_TANK;
    public static List<Axis> MOVES_SHOOTER;
    public static List<Axis> MOVES_SCAVENGER;

    /* Strike zone of these robots */
    public static List<Axis> STRIKE_ZONE_TANK;
    public static List<Axis> STRIKE_ZONE_SHOOTER;

    /* Striking scope */
    public static final int STRIKING_SCOPE_TANK = 10;
    public static final int STRIKING_SCOPE_SHOOTER = 3;




    /* Subpoena of each moves to their lists */
    static {
        MOVES_SHOOTER = new ArrayList<Axis>();
        MOVES_SHOOTER.add(NORTH);
        MOVES_SHOOTER.add(NORTH_EST);
        MOVES_SHOOTER.add(NORTH_WEST);
        MOVES_SHOOTER.add(SOUTH);
        MOVES_SHOOTER.add(SOUTH_EST);
        MOVES_SHOOTER.add(SOUTH_WEST);
        MOVES_SHOOTER.add(EST);
        MOVES_SHOOTER.add(WEST);

        MOVES_SCAVENGER = new ArrayList<Axis>();
        MOVES_SCAVENGER.addAll(MOVES_SHOOTER);

        /* Again only because they are hipster */
        MOVES_TANK = new ArrayList<Axis>();
        MOVES_TANK.add(TANK_NORTH);
        MOVES_TANK.add(TANK_SOUTH);
        MOVES_TANK.add(TANK_EST);
        MOVES_TANK.add(TANK_WEST);

        STRIKE_ZONE_SHOOTER = new ArrayList<Axis>();
        for ( int i = 0; i < STRIKING_SCOPE_SHOOTER; i++ ) {
            STRIKE_ZONE_SHOOTER.add(new Axis(0, i));
        }
        for ( int i = 0; i < STRIKING_SCOPE_SHOOTER; i++ ) {
            STRIKE_ZONE_SHOOTER.add(new Axis(i, 0));
        }
        for ( int i = 0; i < STRIKING_SCOPE_SHOOTER; i++ ) {
            STRIKE_ZONE_SHOOTER.add(new Axis(i, 0));
        }
        for ( int i = 0; i < STRIKING_SCOPE_SHOOTER; i++ ) {
            STRIKE_ZONE_SHOOTER.add(new Axis(0, (i * -1)));
        }
        for ( int i = 0; i < STRIKING_SCOPE_SHOOTER; i++ ) {
            STRIKE_ZONE_SHOOTER.add(new Axis((i * -1), 0));
        }

        STRIKE_ZONE_TANK = new ArrayList<Axis>();
        for ( int i = 0; i < STRIKING_SCOPE_TANK; i++ ) {
            STRIKE_ZONE_TANK.add(new Axis(0, i));
        }
        for ( int i = 0; i < STRIKING_SCOPE_TANK; i++ ) {
            STRIKE_ZONE_TANK.add(new Axis(i, 0));
        }
        for ( int i = 0; i < STRIKING_SCOPE_TANK; i++ ) {
            STRIKE_ZONE_TANK.add(new Axis(i, 0));
        }
        for ( int i = 0; i < STRIKING_SCOPE_TANK; i++ ) {
            STRIKE_ZONE_TANK.add(new Axis(0, (i * -1)));
        }
        for ( int i = 0; i < STRIKING_SCOPE_TANK; i++ ) {
            STRIKE_ZONE_TANK.add(new Axis((i * -1), 0));
        }

    }


}
