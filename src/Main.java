import action.Action;
import config.Constants;
import config.Input;
import config.ShowFrame;
import ia.ArtificialIntelligence;
import plateau.Axis;
import plateau.Plateau;
import robot.Robot;
import robot.Scavenger;
import robot.Shooter;
import robot.Tank;
import team.Team;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * La classe Main permet de lancer l application.
 *
 * @author boinc
 * @author seysn
 */
public class Main {
 
    public static void main( String[] args ) throws Exception {
        Robot robot;
        Action action;
        ArrayList<Robot> deadRobot = new ArrayList<>();
        boolean end = false;
        int count = 0;

        int x = Input.readInt( "Largeur du plateau : " );
        int y = Input.readInt( "Longeur du plateau : " );
        double obstacle;
        obstacle = Input.readDouble( "Pourcentage d'obstacle (de 0 à 0.99): ", 0.0, 0.99 );
        Plateau plateau = new Plateau( x, y, obstacle );
        System.out.printf( "Choisissez le mode de jeu :\n" + "\t1: Affrontement de deux IA\n"
            + "\t2: Affrontement d'une équipe contre une IA\n"
            + "\t3: Affrontement de deux équipes\n" );
        switch( Input.readInt( "Votre choix : ", 1, 3 ) - 1 ) {
            /** IA vs IA **/
            case 0:
                ArtificialIntelligence.nbRobots = 3 + Constants.random.nextInt( 3 );
                ArtificialIntelligence[] ia = new ArtificialIntelligence[ 2 ];
                System.out.println( "\nChoisissez un pays pour la première IA : " );
                ia[ 0 ] = new ArtificialIntelligence( new Axis( 0, 0 ), plateau, 1 );
                System.out.println( "\nChoisissez un pays pour la deuxième IA : " );
                do {
                    ia[ 1 ] = new ArtificialIntelligence(
                        new Axis( plateau.getLength() - 1, plateau.getWidth() - 1 ), plateau, 2 );
                    if( sameCountry( ia[ 0 ].getNomPays(), ia[ 1 ].getNomPays() ) ) {
                        ShowFrame
                            .showErr( "Vous devez choisir un pays different de la première IA !" );
                    }
                } while( sameCountry( ia[ 0 ].getNomPays(), ia[ 1 ].getNomPays() ) );

                do {
                    System.out.println(
                        "\n[Tour " + count + "] " + ia[ count % 2 ].getNomPays() + " à joué :\n" );
                    robot = ia[ count % 2 ].chooseRobot();
                    action = robot.selectActionForIa();
                    if( action != null ) {
                        action.doSomething();
                    }
                    System.out.printf( robot.getView().toString() );
                    count += 1;
                    for( ArtificialIntelligence foo : ia ) {
                        if( foo.lose() ) {
                            end = true;
                        }
                        deadRobot.addAll( foo.getRobots().stream().filter( r -> r.isDead() )
                            .collect( Collectors.toList() ) );
                    }
                    ia[ count % 2 ].getRobots().stream().filter( r -> r.isBased() )
                        .forEach( r -> r.isHeals() );
                    if( !deadRobot.isEmpty() ) {
                        for( Robot r : deadRobot ) {
                            r.revoke();
                        }
                        deadRobot.clear();
                    }
                    System.out.println( "< APPUYEZ SUR ENTREE POUR CONTINUER >" );
                    Constants.sc.nextLine();
                } while( !end );
                break;

            /** J1 vs IA **/
            case 1:
                Team[] team = new Team[ 2 ];
                System.out.println( "\nChoisissez un pays pour le Joueur 1 : " );
                team[ 0 ] = new Team( new Axis( 0, 0 ), plateau, 1 );
                System.out.println( "\nChoisissez un pays pour l'IA : " );
                do {
                    team[ 1 ] = new ArtificialIntelligence(
                        new Axis( plateau.getLength() - 1, plateau.getWidth() - 1 ), plateau, 2 );
                    if( sameCountry( team[ 0 ].getNomPays(), team[ 1 ].getNomPays() ) ) {
                        ShowFrame
                            .showErr( "Vous devez choisir un pays different de la première IA !" );
                    }
                } while( sameCountry( team[ 0 ].getNomPays(), team[ 1 ].getNomPays() ) );
                System.out.println(
                    "\nChoix des robots :\n1: Robots par defaut (1 de chaque)" + "\n2: Manuel" );
                switch( Input.readInt( "Votre choix : ", 1, 2 ) - 1 ) {
                    case 0:
                        for( Team t : team ) {
                            t.addRobot( new Tank( t.getView(), t ) );
                            t.addRobot( new Scavenger( t.getView(), t ) );
                            t.addRobot( new Shooter( t.getView(), t ) );
                        }
                        break;
                    case 1:
                        int nbT, nbSc, nbSh;
                        System.out.println(
                            "\n" + team[ 0 ].getNomPays() + ", choisissez vos robots "
                                + "(3 minimum, 5 maximum)" );
                        do {
                            nbT = Input.readInt( "Nombre de Char (2 max) : ", 0, 2 );
                            nbSc = Input.readInt( "Nombre de Piégeur (2 max) : ", 0, 2 );
                            nbSh = Input.readInt( "Nombre de Tireur (2 max) : ", 0, 2 );
                            if( ( nbT + nbSc + nbSh ) < 3 ) {
                                ShowFrame.showErr( "Il n'y a pas assez de robots au total (3 min" );
                            } else if( ( nbT + nbSc + nbSh ) > 5 ) {
                                ShowFrame.showErr( "Il y a trop de robots au total (5 max" );
                            }
                        } while( ( nbT + nbSc + nbSh ) < 3 && ( nbT + nbSc + nbSh ) > 5 );
                        for( int i = 0; i < nbT; i++ )
                            team[ 0 ].addRobot( new Tank( team[ 0 ].getView(), team[ 0 ] ) );
                        for( int i = 0; i < nbSc; i++ )
                            team[ 0 ].addRobot( new Scavenger( team[ 0 ].getView(), team[ 0 ] ) );
                        for( int i = 0; i < nbSh; i++ )
                            team[ 0 ].addRobot( new Shooter( team[ 0 ].getView(), team[ 0 ] ) );
                        ArtificialIntelligence.nbRobots = nbT + nbSc + nbSh;
                        team[ 1 ] = new ArtificialIntelligence(
                            new Axis( plateau.getLength() - 1, plateau.getWidth() - 1 ), plateau, 2,
                            team[ 1 ].getNomPays() );
                        break;
                }

                do {
                    Team teamCourante = team[ count % 2 ];
                    if( teamCourante.isIa() ) {
                        System.out.printf(
                            "[Tour " + count + "] " + teamCourante.getNomPays() + " à joué :\n" );
                    } else {
                        System.out.printf(
                            "[Tour " + count + "]\n" + teamCourante.getView().getPlateau() + "\n"
                                + teamCourante.getNomPays() + " c'est à votre tour ! \n" );
                    }
                    robot = team[ count % 2 ].chooseRobot();
                    if( teamCourante.isIa() ) {
                        action = robot.selectActionForIa();
                        if( action != null ) {
                            action.doSomething();
                        }
                        System.out.printf( robot.getView().toString() );
                    } else {
                        System.out.printf( robot.getView().toString() );
                        action = robot.selectedAction();
                        if( action != null ) {
                            action.doSomething();
                        }
                    }
                    count += 1;
                    for( Team t : team ) {
                        if( t.lose() ) {
                            end = true;
                        }
                        deadRobot.addAll( t.getRobots().stream().filter( r -> r.isDead() )
                            .collect( Collectors.toList() ) );
                    }
                    team[ count % 2 ].getRobots().stream().filter( r -> r.isBased() )
                        .forEach( r -> r.isHeals() );
                    if( !deadRobot.isEmpty() ) {
                        for( Robot r : deadRobot ) {
                            r.revoke();
                        }
                        deadRobot.clear();
                    }
                } while( !end );
                break;

            /** J1 vs J2 **/
            case 2:
                Team[] teams = new Team[ 2 ];
                System.out.println( "\nChoisissez un pays pour le Joueur 1 : " );
                teams[ 0 ] = new Team( new Axis( 0, 0 ), plateau, 1 );
                count = 0;
                System.out.println( "\nChoisissez un pays pour le Joueur 2 : " );
                do {
                    if( count > 0 ) {
                        System.err.printf( "Vous ne pouvez pas faire de coup d'état !! \n" );
                    }
                    teams[ 1 ] =
                        new Team( new Axis( plateau.getLength() - 1, plateau.getWidth() - 1 ),
                            plateau, 2 );
                    count += 1;
                } while( sameCountry( teams[ 0 ].getNomPays(), teams[ 1 ].getNomPays() ) );
                System.out.println(
                    "\nChoix des robots :\n1: Robots par defaut (1 de chaque)" + "\n2: Manuel" );
                switch( Input.readInt( "Votre choix : ", 1, 2 ) - 1 ) {
                    case 0:
                        for( Team t : teams ) {
                            t.addRobot( new Tank( t.getView(), t ) );
                            t.addRobot( new Scavenger( t.getView(), t ) );
                            t.addRobot( new Shooter( t.getView(), t ) );
                        }
                        break;
                    case 1:
                        int nbT, nbSc, nbSh;
                        System.out.println(
                            "\n" + teams[ 0 ].getNomPays() + ", choisissez vos robots "
                                + "(3 minimum, 5 maximum)" );
                        do {
                            nbT = Input.readInt( "Nombre de Char (2 max) : ", 0, 2 );
                            nbSc = Input.readInt( "Nombre de Piégeur (2 max) : ", 0, 2 );
                            nbSh = Input.readInt( "Nombre de Tireur (2 max) : ", 0, 2 );
                            if( ( nbT + nbSc + nbSh ) < 3 ) {
                                ShowFrame.showErr( "Il n'y a pas assez de robots au total (3 min" );
                            } else if( ( nbT + nbSc + nbSh ) > 5 ) {
                                ShowFrame.showErr( "Il y a trop de robots au total (5 max" );
                            }
                        } while( ( nbT + nbSc + nbSh ) < 3 && ( nbT + nbSc + nbSh ) > 5 );
                        for( int i = 0; i < nbT; i++ )
                            teams[ 0 ].addRobot( new Tank( teams[ 0 ].getView(), teams[ 0 ] ) );
                        for( int i = 0; i < nbSc; i++ )
                            teams[ 0 ]
                                .addRobot( new Scavenger( teams[ 0 ].getView(), teams[ 0 ] ) );
                        for( int i = 0; i < nbSh; i++ )
                            teams[ 0 ].addRobot( new Shooter( teams[ 0 ].getView(), teams[ 0 ] ) );
                        System.out.println(
                            "\n" + teams[ 1 ].getNomPays() + ", choisissez vos robots "
                                + "(3 minimum, 5 maximum)" );
                        do {
                            nbT = Input.readInt( "Nombre de Char (2 max) : ", 0, 2 );
                            nbSc = Input.readInt( "Nombre de Piégeur (2 max) : ", 0, 2 );
                            nbSh = Input.readInt( "Nombre de Tireur (2 max) : ", 0, 2 );
                            if( teams[ 0 ].getRobots().size() != teams[ 1 ].getRobots().size() ) {
                                ShowFrame.showErr(
                                    "Vous devez avoir le même nombre de robots que l'équipe 1" );
                            }
                        } while( teams[ 0 ].getRobots().size() != nbT + nbSc + nbSh );
                        for( int i = 0; i < nbT; i++ )
                            teams[ 1 ].addRobot( new Tank( teams[ 1 ].getView(), teams[ 1 ] ) );
                        for( int i = 0; i < nbSc; i++ )
                            teams[ 1 ]
                                .addRobot( new Scavenger( teams[ 1 ].getView(), teams[ 1 ] ) );
                        for( int i = 0; i < nbSh; i++ )
                            teams[ 1 ].addRobot( new Shooter( teams[ 1 ].getView(), teams[ 1 ] ) );
                }
                count = 0;
                do {
                    System.out.printf(
                        "[ Tour " + count + "]\n" + teams[ count % 2 ].getView().getPlateau() + "\n"
                            + teams[ count % 2 ].getNomPays() + " c'est à votre tour ! \n" );
                    robot = teams[ count % 2 ].chooseRobot();
                    System.out.printf( robot.getView().toString() );
                    action = robot.selectedAction();
                    if( action != null ) {
                        action.doSomething();
                    }
                    count += 1;
                    for( Team t : teams ) {
                        if( t.lose() ) {
                            end = true;
                        }
                        deadRobot.addAll( t.getRobots().stream().filter( r -> r.isDead() )
                            .collect( Collectors.toList() ) );
                    }
                    teams[ count % 2 ].getRobots().stream().filter( r -> r.isBased() )
                        .forEach( r -> r.isHeals() );
                    if( !deadRobot.isEmpty() ) {
                        for( Robot r : deadRobot ) {
                            r.revoke();
                        }
                        deadRobot.clear();
                    }
                } while( !end );
        }
    }

    private static boolean sameCountry( String nomPays, String nomPays1 ) {
        return nomPays.equals( nomPays1 );
    }


}
