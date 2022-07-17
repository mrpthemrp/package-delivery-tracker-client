package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CustomScrollPane extends JScrollPane {
    private final int width;
    private final int height;
    private final JPanel innerPanel;
    public CustomScrollPane(int width, int height) {

        this.width = width;
        this.height = height;
        this.innerPanel = new JPanel(new CardLayout());

        innerPanel.setSize(new Dimension((width),(height*2)));
        innerPanel.setMaximumSize(new Dimension(width,(height*2)));
        innerPanel.setBackground(Color.blue);
        innerPanel.setBorder(new LineBorder(Color.BLACK, 5));

        addPackages();

        this.setViewportView(innerPanel);
    }

    private void addPackages() {
        innerPanel.add(new PackageItem());
        innerPanel.add(new PackageItem());
        innerPanel.add(new PackageItem());
        innerPanel.add(new PackageItem());
    }
}
