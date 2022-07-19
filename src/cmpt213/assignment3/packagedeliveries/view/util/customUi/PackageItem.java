package cmpt213.assignment3.packagedeliveries.view.util.customUi;

import cmpt213.assignment3.packagedeliveries.model.PackageBase;
import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PackageItem extends JPanel {
    private final int width;
    private final PackageBase pkg;
    private final JLabel name,notes, price, weight;
    public PackageItem(int width, PackageBase pkg){
        this.width = width;
        this.pkg = pkg;

        this.setSize(new Dimension(this.width,this.getHeight()));
        this.setMaximumSize(new Dimension(this.width,this.getHeight()));
        this.setBackground(Util.darkBrown);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        name = new JLabel(pkg.getName());
        notes = new JLabel(pkg.getNotes());
        price = new JLabel(String.valueOf(pkg.getPrice()));
        weight = new JLabel(String.valueOf(pkg.getWeight()));

        this.add(name);
        this.add(notes);
        this.add(price);
        this.add(weight);
    }
}
