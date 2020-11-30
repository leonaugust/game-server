package common.util.console;

public class ConsoleColorer {
    public final static int RESET = 0;
    public final static int BRIGHT = 1;
    public final static int DIM = 2;
    public final static int UNDERLINE = 3;
    public final static int BLINK = 4;
    public final static int REVERSE = 7;
    public final static int HIDDEN = 8;

    public final static int BLACK = 0;
    public final static int RED = 1;
    public final static int GREEN = 2;
    public final static int YELLOW = 3;
    public final static int BLUE = 4;
    public final static int MAGENTA = 5;
    public final static int CYAN = 6;
    public final static int WHITE = 7;

    public static String color(int attr, int fg, int bg) {
        return String.valueOf(((char) 0x1B)) +
                '[' +
                attr +
                ';' +
                (30 + fg) +
                ';' +
                (40 + bg) +
                "m";
    }

    public static String color(int fg) {
        return String.valueOf(((char) 0x1B)) +
                '[' +
                0 +
                ';' +
                (30 + fg) +
                "m";
    }

    public static String restore() {
        return String.valueOf(((char) 0x1B)) +
                "[00m";
    }
}