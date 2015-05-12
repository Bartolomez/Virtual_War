package config;

/**
 * @author boinc
 */
public class Input {
    public static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(Constants.sc.nextLine());
            } catch (NumberFormatException e) {
                ShowFrame.showErr("Erreur : La valeur n'est pas un entier");
            }
        }
    }

    public static int readInt(String message) {
        while (true) {
            try {
                if( !message.contains( ":" ) ) {
                    message += "\n";
                }
                System.out.printf( message );
                return Integer.parseInt(Constants.sc.nextLine());
            } catch (NumberFormatException e) {
                ShowFrame.showErr("Erreur : La valeur n'est pas un entier");
            }
        }
    }

    public static int readInt(int borderA, int borderB) {
        while (true) {
            try {
                int foo = Integer.parseInt(Constants.sc.nextLine());
                if( foo >= borderA && foo <= borderB ) {
                    return foo;
                }
            } catch (NumberFormatException e) {
                ShowFrame.showErr("Erreur : La valeur n'est pas un entier ou n'est pas compris "
                    + "entre " + borderA + " et " + borderB);
            }
        }
    }

    public static int readInt(String message, int borderA, int borderB) {
        while (true) {
            try {
                if( !message.contains( ":" ) ) {
                    message += "\n";
                }
                System.out.printf( message );
                int foo = Integer.parseInt(Constants.sc.nextLine());
                if( foo >= borderA && foo <= borderB ) {
                    return foo;
                }
            } catch (NumberFormatException e) {
                ShowFrame.showErr("Erreur : La valeur n'est pas un entier ou n'est pas compris "
                    + "entre " + borderA + " et " + borderB);
            }
        }
    }

    public static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(Constants.sc.nextLine());
            } catch (NumberFormatException e) {
                ShowFrame.showErr("Erreur : La valeur n'est pas un réel");
            }
        }
    }

    public static double readDouble(double borderA, double borderB) {

        while (true) {
            try {
                double foo = Double.parseDouble( Constants.sc.nextLine() );
                if( foo >= borderA && foo <= borderB ) {
                    return foo;
                }
            } catch (NumberFormatException e) {
                ShowFrame.showErr("Erreur : La valeur n'est pas un réel");
            }
        }
    }

    public static double readDouble(String message) {
        while (true) {
            try {
                if (!message.contains(":")) {
                    message += "\n";
                }
                System.out.print( message );
                return Double.parseDouble(Constants.sc.nextLine());
            } catch (NumberFormatException e) {
                ShowFrame.showErr("Erreur : La valeur n'est pas un réel");
            }
        }
    }

    public static double readDouble(String message, double borderA, double borderB) {
        while (true) {
            try {
                if (!message.contains(":")) {
                    message += "\n";
                }
                System.out.print( message );
                double foo = Double.parseDouble( Constants.sc.nextLine() );
                if( foo >= borderA && foo <= borderB ) {
                    return foo;
                }
            } catch (NumberFormatException e) {
                ShowFrame.showErr("Erreur : La valeur n'est pas un réel ou n'est pas compris entre "
                    + borderA + " et " + borderB);
            }
        }
    }
}
