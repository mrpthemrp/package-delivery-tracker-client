package cmpt213.assignment3.packagedeliveries.view.screens;

import cmpt213.assignment3.packagedeliveries.view.util.customUi.TableOfPackages;
import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
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
