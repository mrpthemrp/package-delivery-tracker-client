package cmpt213.assignment3.packagedeliveriestracker.view.screens;

import cmpt213.assignment3.packagedeliveriestracker.control.TableOfPackages;
import cmpt213.assignment3.packagedeliveriestracker.view.custom.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainScreenRight extends JPanel {
    private TableOfPackages tableModel;

    public MainScreenRight(ActionListener al, int width, TableOfPackages tableModel) {
        this.tableModel = tableModel;
//        this.setSize(width, (int) (Util.screenHeight * 0.65));
//        this.setPreferredScrollableViewportSize(new Dimension(width, (int) (Util.screenHeight * 0.65)));
        this.setBackground(Util.transparent);

        this.setVisible(true);
    }
}
