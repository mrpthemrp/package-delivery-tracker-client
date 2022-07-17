package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PackageItem extends JPanel {
    private final int width;
    private final int height;
    public PackageItem(int width){
        this.width = width;
        this.height = width/3;
        setSize(new Dimension(width,height));
        setMaximumSize(new Dimension(width,height));
        setBackground(Util.darkBrown);
        this.add(Box.createRigidArea(this.getSize()));
        setBorder(new LineBorder(Util.lightBrown,5));
    }
}
