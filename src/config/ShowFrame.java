package config;

/**
 * @author seysn
 */
public class ShowFrame {

    public static void showTitle(String title) {
        StringBuilder res = new StringBuilder("+--");
        for (int i = 0; i < title.length(); i++) {
            res.insert(res.length(), "-");
        }
        res.insert(res.length(), "--+\n|  " + title + "  |\n+--");
        for (int i = 0; i < title.length(); i++) {
            res.insert(res.length(), "-");
        }
        System.out.println(res + "--+");
    }

    public static void showFrame(String title, String[] content) {
        int max = title.length();
        StringBuilder res = new StringBuilder("+-");
        for (String s : content) {
            if (s.length() > max) {
                max = s.length();
            }
        }
        /*if (max % 2 == 0) {
            max += 1;
        }*/
        for (int i = 0; i < max; i++) {
            res.insert(res.length(), "-");
        }
        res.insert(res.length(), "-+\n| ");
        for (int i = 0; i < (max - title.length()) / 2; i++) {
            res.insert(res.length(), " ");
        }
        res.insert(res.length(), title);
        for (int i = 0; i < (max - title.length()) / 2; i++) {
            res.insert(res.length(), " ");
        }
        res.insert(res.length(), " |\n+-");
        for (int i = 0; i < max; i++) {
            res.insert(res.length(), "-");
        }
        res.insert(res.length(), "-+\n");
        for (String s : content) {
            res.insert(res.length(), "| " + s);
            for (int i = 0; i < max - s.length(); i++) {
                res.insert(res.length(), " ");
            }
            res.insert(res.length(), " |\n");
        }
        res.insert(res.length(), "+-");
        for (int i = 0; i < max; i++) {
            res.insert(res.length(), "-");
        }
        res.insert(res.length(), "-+");

        System.out.println(res);
    }

    public static void showErr(String err) {
        System.err.println(err);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
