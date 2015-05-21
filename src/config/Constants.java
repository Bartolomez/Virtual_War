package config;

import plateau.Axis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * La classe Constants regroupe les constantes utilisees dans le jeu.
 *
 * @author boinc
 */

public class Constants {

    /**
     * Permet de generer des nombres aleatoires.
     */
    public static final Random random = new Random();
    /**
     * L entier de la premiere equipe.
     */
    public static final int FIRST_TEAM = 1;
    /**
     * L entier de la seconde equipe.
     */
    public static final int SECOND_TEAM = 2;
    /**
     * La liste des pays disponible.
     */
    public static final String[] NAME_COUNTRY = new String[] { "France", "Japon", "USA", "Russie" };
    /**
     * Les PV soignes a la fin de chaque tour passe en base.
     */
    public static final int CARE = 2;
    /**
     * Le nombre de mine maximale d un Piegeur.
     */
    public static final int MINES_MAX = 10;
    /**
     * Le nom d'un Char.
     */
    public static final String IS_TANK = "Char";
    /* Definition of a tank by these assets */
    /**
     * Les degats d action du Char.
     */
    public static final int DAMAGE_ACTION_TANK = 1;
    /**
     * Le nombre de case du deplacement du Char
     */
    public static final int CAN_MOVE_TANK = 2;
    /**
     * Les degats que subit un Char quand il se deplace.
     */
    public static final int DAMAGE_MOVE_TANK = 5;
    /**
     * Les degats qu inflige un Char quand il tire.
     */
    public static final int DAMAGE_SHOOT_TANK = 6;
    /**
     * Les degats que subit un Char quand il passe sur une mine.
     */
    public static final int DAMAGA_MINE_TANK = 6;
    /**
     * L energie initiale du Char.
     */
    public static final int ENERGY_TANK = 60;
    /**
     * Le nom d un Tireur.
     */
    public static final String IS_SHOOTER = "Tireur";
    /* Definition of a shooter by these assets */
    /**
     * Les degats d action du Tireur.
     */
    public static final int DAMAGE_ACTION_SHOOTER = 2;
    /**
     * Les degats que subit un Tireur quand il se deplace.
     */
    public static final int DAMAGE_MOVE_SHOOTER = 1;
    /**
     * Les degats qu inflige un Tireur quand il tire.
     */
    public static final int DAMAGE_SHOOT_SHOOTER = 3;
    /**
     * Les degats que subit un Tireur quand il passe sur une mine.
     */
    public static final int DAMAGA_MINE_SHOOTER = 3;
    /**
     * L energie initiale du Tireur.
     */
    public static final int ENERGY_SHOOTER = 40;
    /**
     * Le nom d un Piegeur.
     */
    public static final String IS_SCAVENGER = "Piegeur";
    /* Definition of a scanvenger assets */
    /**
     * Les degats qu inflige un Piegeur.
     */
    public static final int DAMAGE_SCAVENGER = 3;
    /**
     * Les degats d action du Piegeur.
     */
    public static final int DAMAGE_ACTION_SCAVENGER = 2;
    /**
     * Les degats que subit un Piegeur quand il se deplace.
     */
    public static final int DAMAGE_MOVE_SCAVENGER = 2;
    /**
     * Les degats qu'inflige un Piegeur quand il tire.
     */
    public static final int DAMAGE_SHOOT_SCAVENGER = 2;
    /**
     * Les degats que subit un Piegeur quand il passe sur une Mine.
     */
    public static final int DAMAGE_MINE_SCAVENGER = 0;
    /**
     * L energie initiale du Piegeur.
     */
    public static final int ENERGY_SCAVENGER = 40;
    /**
     * Coordonnee de deplacement vers le Nord.
     */
    public static final Axis NORTH = new Axis( 0, -1 );
    /* Moves */
    /**
     * Coordonnee de deplacement vers le Nord Est.
     */
    public static final Axis NORTH_EST = new Axis( 1, -1 );
    /**
     * Coordonnee de deplacement vers le Nord Ouest.
     */
    public static final Axis NORTH_WEST = new Axis( -1, -1 );
    /**
     * Coordonnee de deplacement vers le Sud.
     */
    public static final Axis SOUTH = new Axis( 0, 1 );
    /**
     * Coordonnee de deplacement vers le Sud Est.
     */
    public static final Axis SOUTH_EST = new Axis( 1, 1 );
    /**
     * Coordonnee de deplacement vers le Sud Ouest.
     */
    public static final Axis SOUTH_WEST = new Axis( -1, 1 );
    /**
     * Coordonnee de deplacement vers l Est.
     */
    public static final Axis EST = new Axis( -1, 0 );
    /**
     * Coordonnee de deplacement vers l Ouest.
     */
    public static final Axis WEST = new Axis( 1, 0 );
    /**
     * Coordonnee de deplacement vers le Nord pour le Char.
     */
    public static final Axis TANK_NORTH = new Axis( 0, -2 );
    /* Only because tank are hipster */
    /**
     * Coordonnee de deplacement vers le Sud pour le Char.
     */
    public static final Axis TANK_SOUTH = new Axis( 0, 2 );
    /**
     * Coordonnee de deplacement vers l Est pour le Char.
     */
    public static final Axis TANK_EST = new Axis( 2, 0 );
    /**
     * Coordonnee de deplacement vers l Ouest pour le Char.
     */
    public static final Axis TANK_WEST = new Axis( -2, 0 );
    /**
     * La portee de tir du Char.
     */
    public static final int STRIKING_SCOPE_TANK = 10;
    /* List of each moves for each robots */
    /**
     * La portee de tir du Tireur.
     */
    public static final int STRIKING_SCOPE_SHOOTER = 3;
    /**
     * La portee de tir du Piegeur.
     */
    public static final int STRIKING_SCOPE_SCAVENGER = 1;
    /**
     * Permet de lire les entrees de l utilisateur.
     */
    public static Scanner sc = new Scanner( System.in );
    /* Strike zone of these robots */
    /**
     * Liste des deplacements possibles du Char.
     */
    public static List<Axis> MOVES_TANK;
    /**
     * Liste des deplacements possibles du Tireur.
     */
    public static List<Axis> MOVES_SHOOTER;
    /* Striking scope */
    /**
     * Liste des deplacements possibles du Piegeur.
     */
    public static List<Axis> MOVES_SCAVENGER;
    /**
     * Liste des cibles possibles du Char.
     */
    public static List<Axis> STRIKE_ZONE_TANK;
    /**
     * Liste des cibles possibles du Tireur.
     */
    public static List<Axis> STRIKE_ZONE_SHOOTER;

    /* Subpoena of each moves to their lists */
    static {
        MOVES_SHOOTER = new ArrayList<>();
        MOVES_SHOOTER.add( NORTH );
        MOVES_SHOOTER.add( NORTH_EST );
        MOVES_SHOOTER.add( NORTH_WEST );
        MOVES_SHOOTER.add( SOUTH );
        MOVES_SHOOTER.add( SOUTH_EST );
        MOVES_SHOOTER.add( SOUTH_WEST );
        MOVES_SHOOTER.add( EST );
        MOVES_SHOOTER.add( WEST );
        MOVES_SCAVENGER = new ArrayList<>();
        MOVES_SCAVENGER.addAll( MOVES_SHOOTER );
        /* Again only because they are hipster */
        MOVES_TANK = new ArrayList<>();
        MOVES_TANK.add( TANK_NORTH );
        MOVES_TANK.add( TANK_SOUTH );
        MOVES_TANK.add( TANK_EST );
        MOVES_TANK.add( TANK_WEST );
        STRIKE_ZONE_SHOOTER = new ArrayList<>();
        for( int i = 0; i < STRIKING_SCOPE_SHOOTER; i++ ) {
            STRIKE_ZONE_SHOOTER.add( new Axis( 0, i ) );
        }
        for( int i = 0; i < STRIKING_SCOPE_SHOOTER; i++ ) {
            STRIKE_ZONE_SHOOTER.add( new Axis( i, 0 ) );
        }
        for( int i = 0; i < STRIKING_SCOPE_SHOOTER; i++ ) {
            STRIKE_ZONE_SHOOTER.add( new Axis( i, 0 ) );
        }
        for( int i = 0; i < STRIKING_SCOPE_SHOOTER; i++ ) {
            STRIKE_ZONE_SHOOTER.add( new Axis( 0, ( i * -1 ) ) );
        }
        for( int i = 0; i < STRIKING_SCOPE_SHOOTER; i++ ) {
            STRIKE_ZONE_SHOOTER.add( new Axis( ( i * -1 ), 0 ) );
        }
        STRIKE_ZONE_TANK = new ArrayList<>();
        for( int i = 0; i < STRIKING_SCOPE_TANK; i++ ) {
            STRIKE_ZONE_TANK.add( new Axis( 0, i ) );
        }
        for( int i = 0; i < STRIKING_SCOPE_TANK; i++ ) {
            STRIKE_ZONE_TANK.add( new Axis( i, 0 ) );
        }
        for( int i = 0; i < STRIKING_SCOPE_TANK; i++ ) {
            STRIKE_ZONE_TANK.add( new Axis( i, 0 ) );
        }
        for( int i = 0; i < STRIKING_SCOPE_TANK; i++ ) {
            STRIKE_ZONE_TANK.add( new Axis( 0, ( i * -1 ) ) );
        }
        for( int i = 0; i < STRIKING_SCOPE_TANK; i++ ) {
            STRIKE_ZONE_TANK.add( new Axis( ( i * -1 ), 0 ) );
        }
    }
}
