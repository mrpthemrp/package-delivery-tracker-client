package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public final class Util {

    public enum SCREEN_STATE {
        START, MAIN, ADD_PACKAGE, UPCOMING, OVERDUE
    }

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public static Color darkTeal = new Color(0,109,119);
    public static Color midTeal = new Color(131,197,190);
    public static Color lightTeal = new Color(237,246,249);
    public static Color lightBrown = new Color(255,221,210);
    public static Color darkBrown = new Color(226,149,120);
    public static DateTimeFormatter clockFormat = DateTimeFormatter.ofPattern("hh:mm:ss a");
    public static DateTimeFormatter currentDayFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

    public static Font clockFont = new Font(Font.SANS_SERIF, Font.BOLD, (int) (20 * (Util.screenSize.getWidth() * 0.0018)));
    public static Font mainScreenDateFont = new Font(Font.SANS_SERIF, Font.BOLD, (int) (20 * (Util.screenSize.getWidth() * 0.0008)));
    public static Font btnTextFont = new Font(Font.SANS_SERIF, Font.BOLD, (int) (14 * (Util.screenSize.getWidth() * 0.0013)));

    public static Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, (int) (18 * (Util.screenSize.getWidth() * 0.001)));
    public static Font subTitleFont = new Font(Font.SANS_SERIF, Font.PLAIN, (int) (12 * (Util.screenSize.getWidth() * 0.001)));
    public static Font bodyFont = new Font(Font.SANS_SERIF, Font.PLAIN, (int) (10 * (Util.screenSize.getWidth() * 0.001)));
}
