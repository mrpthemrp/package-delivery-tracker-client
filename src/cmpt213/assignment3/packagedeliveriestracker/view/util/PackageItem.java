package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import java.awt.*;

public class PackageItem extends JPanel {
    public PackageItem(){
        setSize(new Dimension(300,300));
        setMaximumSize(new Dimension(300,300));
        setBackground(Util.darkBrown);
        this.add(Box.createRigidArea(this.getSize()));
    }
}
