package cmpt213.assignment3.packagedeliveries.view.util;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

/**
 *
 */
//font resource: https://stackoverflow.com/questions/5652344/how-can-i-use-a-custom-font-in-java
public final class Util {

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public static Color darkTeal = new Color(0, 109, 119);
    public static Color midTeal = new Color(131, 197, 190);
    public static Color lightTeal = new Color(237, 248, 245);
    public static Color lightBrown = new Color(255, 221, 210);
    public static Color darkBrown = new Color(226, 149, 120);

    public static Color redLight = new Color(233,90,90);
    public static Color redDark = new Color(178,42,42);

    public static Color transparent = new Color(255, 255, 255, 0);
    public static DateTimeFormatter clockFormat = DateTimeFormatter.ofPattern("hh:mm:ss a");
    public static DateTimeFormatter currentDayFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
    public static DateTimeFormatter packageDateFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm a");

    public static DecimalFormat priceFormat = new DecimalFormat("$.00");
    public static DecimalFormat weightFormat = new DecimalFormat(".00 kg");
    public static Font clockFont;
    public static Font mainScreenDateFont;
    public static Font enterBtnTextFont;
    public static Font addPkgBtnTextFont2;
    public static Font removeBtnTextFont;
    public static Font titleFont;
    public static Font sortTitleFont;
    public static Font sortBtnsFont;
    public static Font pkgDateFont;
    public static Font subTitleFont;
    public static Font bodyFont = new Font(Font.SANS_SERIF, Font.PLAIN, (int) (10 * (Util.screenSize.getWidth() * 0.001)));

    static {
        try {
            clockFont = createCustomFont("Roboto-Bold.ttf", Font.BOLD, (int) (18 * (Util.screenSize.getWidth() * 0.0018)));
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            mainScreenDateFont = createCustomFont("Roboto-Medium.ttf", Font.PLAIN, (int) (20 * (Util.screenSize.getWidth() * 0.0008)));
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            enterBtnTextFont = createCustomFont("Roboto-Bold.ttf", Font.BOLD, (int) (14 * (Util.screenSize.getWidth() * 0.0013)));
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            sortTitleFont = createCustomFont("Roboto-Bold.ttf", Font.BOLD, (int) (10 * (Util.screenSize.getWidth() * 0.0013)));
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            sortBtnsFont = createCustomFont("Roboto-Bold.ttf", Font.BOLD, (int) (7 * (Util.screenSize.getWidth() * 0.0013)));
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }
    static {
        try {
            removeBtnTextFont = createCustomFont("Roboto-Bold.ttf", Font.BOLD, (int) (5 * (Util.screenSize.getWidth() * 0.0013)));
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            addPkgBtnTextFont2 = createCustomFont("Roboto-Bold.ttf", Font.BOLD, (int) (11 * (Util.screenSize.getWidth() * 0.0013)));
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            subTitleFont = createCustomFont("Roboto-Medium.ttf", Font.PLAIN, (int) (12 * (Util.screenSize.getWidth() * 0.001)));
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            pkgDateFont = createCustomFont("Roboto-Medium.ttf", Font.PLAIN, (int) (10 * (Util.screenSize.getWidth() * 0.001)));
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            titleFont = createCustomFont("Roboto-Bold.ttf", Font.BOLD, (int) (18 * (Util.screenSize.getWidth() * 0.00165)));
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    private static Font createCustomFont(String filename, int type, int fontSize) throws IOException, FontFormatException {
        String fs = File.separator;
        String[] pathNames = {"src", "cmpt213", "assignment3", "packagedeliveries", "view", "util", "font"};
        String path = String.join(fs, pathNames);
        Font newFont;

        newFont = Font.createFont(Font.TRUETYPE_FONT, new File(path + fs + filename)).deriveFont(type, (float) fontSize);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(newFont);

        return newFont;
    }

    public enum SCREEN_STATE {
        START, MAIN, LIST_ALL, UPCOMING, OVERDUE
    }
}
