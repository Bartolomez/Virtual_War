package config;

/**
 * Le classe Input gere les entrees de l utilisateur.
 * 
 * @author boinc
 */
public class Input {
	
	/**
	 * Lit un entier entre par l utilisateur.
	 * @return Un entier, qui correspond a l entier entre par l utilisateur.
	 */
    public static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(Constants.sc.nextLine());
            } catch (NumberFormatException e) {
                ShowFrame.showErr("Erreur : La valeur n'est pas un entier");
            }
        }
    }

    /**
     * Lit un entier entre par l utilisateur.
     * @param message - Le message que l on souhaite afficher avant demander l entier.
     * @return Un entier, qui correspond a l entier entre par l utilisateur.
     */
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

    /**
     * Lit un entier entre par l utilisateur.
     * @param borderA - La borne inferieure de l intervalle.
     * @param borderB - La borne exterieure de l intervalle.
     * @return Un entier, qui correspond a l entier entre par l utilisateur.
     */
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

    /**
     * Lit un entier entre par l utilisateur.
     * @param message - Le message que l on souhaite afficher avant demander l entier.
     * @param borderA - La borne inferieure de l intervalle.
     * @param borderB - La borne exterieure de l intervalle.
     * @return Un entier, qui correspond a l entier entre par l utilisateur.
     */
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

    /**
	 * Lit un decimal entre par l utilisateur.
	 * @return Un decimal, qui correspond au decimal entre par l utilisateur.
	 */
    public static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(Constants.sc.nextLine());
            } catch (NumberFormatException e) {
                ShowFrame.showErr("Erreur : La valeur n'est pas un réel");
            }
        }
    }

    /**
     * Lit un decimal entre par l utilisateur.
     * @param borderA - La borne inferieure de l intervalle.
     * @param borderB - La borne exterieure de l intervalle.
     * @return Un decimal, qui correspond au decimal entre par l utilisateur.
     */
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

    /**
     * Lit un decimal entre par l utilisateur.
     * @param message - Le message que l on souhaite afficher avant demander le decimal.
     * @return Un decimal, qui correspond au decimal entre par l utilisateur.
     */
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

    /**
     * Lit un decimal entre par l utilisateur.
     * @param message - Le message que l on souhaite afficher avant demander le decimal.
     * @param borderA - La borne inferieure de l intervalle.
     * @param borderB - La borne exterieure de l intervalle.
     * @return Un decimal, qui correspond au decimal entre par l utilisateur.
     */
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
