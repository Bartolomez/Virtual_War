package config;

/**
 * La classe ShowFrame gere l affichage.
 *
 * @author seysn
 */
public class ShowFrame {

    /**
     * Affiche un titre.
     *
     * @param title - Le titre que l on souhaite afficher.
     */
    public static void showTitle( String title ) {
        StringBuilder res = new StringBuilder( "+--" );
        for( int i = 0; i < title.length(); i++ ) {
            res.insert( res.length(), "-" );
        }
        res.insert( res.length(), "--+\n|  " + title + "  |\n+--" );
        for( int i = 0; i < title.length(); i++ ) {
            res.insert( res.length(), "-" );
        }
        System.out.println( res + "--+" );
    }

    /**
     * Affiche un titre et un contenu dans un tableau.
     *
     * @param title   - Le titre du tableau.
     * @param content - Le contenu du tableau.
     */
    public static void showFrame( String title, String[] content ) {
        int max = title.length();
        StringBuilder res = new StringBuilder( "+-" );
        for( String s : content ) {
            if( s.length() > max ) {
                max = s.length();
            }
        }
        /*if (max % 2 == 0) {
            max += 1;
        }*/
        for( int i = 0; i < max; i++ ) {
            res.insert( res.length(), "-" );
        }
        res.insert( res.length(), "-+\n| " );
        for( int i = 0; i < ( max - title.length() ) / 2; i++ ) {
            res.insert( res.length(), " " );
        }
        res.insert( res.length(), title );
        for( int i = 0; i < ( max - title.length() ) / 2; i++ ) {
            res.insert( res.length(), " " );
        }
        res.insert( res.length(), " |\n+-" );
        for( int i = 0; i < max; i++ ) {
            res.insert( res.length(), "-" );
        }
        res.insert( res.length(), "-+\n" );
        for( String s : content ) {
            res.insert( res.length(), "| " + s );
            for( int i = 0; i < max - s.length(); i++ ) {
                res.insert( res.length(), " " );
            }
            res.insert( res.length(), " |\n" );
        }
        res.insert( res.length(), "+-" );
        for( int i = 0; i < max; i++ ) {
            res.insert( res.length(), "-" );
        }
        res.insert( res.length(), "-+" );

        System.out.println( res );
    }

    /**
     * Affiche une erreur.
     *
     * @param err - Le message d erreur que l on souhaite afficher.
     */
    public static void showErr( String err ) {
        System.err.println( err );
        try {
            Thread.sleep( 10 );
        } catch( InterruptedException e ) {
            e.printStackTrace();
        }
    }
}
