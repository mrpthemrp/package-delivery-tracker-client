package cmpt213.assignment3.packagedeliveriestracker.view.util;

import java.awt.*;

public class Util {

    public Color darkTeal, midTeal, lightTeal, lightBrown, darkBrown;
    public  Font boldRoboto, mediumRoboto, regularRoboto, italicRoboto;

    public Util(){
        setUpColours();
        setUpFonts();
    }

    private void setUpColours() {
        this.darkTeal = new Color(0,109,119);
        this.midTeal = new Color(131,197,190);
        this.lightTeal = new Color(237,246,249);
        this.lightBrown = new Color(255,221,210);
        this.darkBrown = new Color(226,149,120);
    }

    private void setUpFonts() {

    }
}
