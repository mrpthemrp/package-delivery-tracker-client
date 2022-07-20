package cmpt213.assignment3.packagedeliveries.view.screens;

import cmpt213.assignment3.packagedeliveries.control.PackageDeliveryTracker;
import cmpt213.assignment3.packagedeliveries.view.util.Util;
import cmpt213.assignment3.packagedeliveries.view.util.customUi.PackageItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
//scroll reference:
// https://stackoverflow.com/questions/14615888/list-of-jpanels-that-eventually-uses-a-scrollbar

public class MainScreenRight extends JPanel {
    private GridBagConstraints gbc;

    public MainScreenRight(ActionListener al) {
        int arraySize = 7;
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 550;
        gbc.weighty = 1;

        testPackages(al);

        this.setVisible(true);
    }

    private void testPackages(ActionListener al) {
        this.add(new PackageItem(PackageDeliveryTracker.getPackage(0)),gbc,0);
        this.add(new PackageItem(PackageDeliveryTracker.getPackage(0)),gbc,0);
        this.add(new PackageItem(PackageDeliveryTracker.getPackage(0)),gbc,0);
        this.add(new PackageItem(PackageDeliveryTracker.getPackage(0)),gbc,0);
        this.add(new PackageItem(PackageDeliveryTracker.getPackage(0)),gbc,0);
        this.add(new PackageItem(PackageDeliveryTracker.getPackage(0)),gbc,0);
        this.add(new PackageItem(PackageDeliveryTracker.getPackage(0)),gbc,0);
        this.add(new PackageItem(PackageDeliveryTracker.getPackage(0)),gbc,0);
        this.add(new PackageItem(PackageDeliveryTracker.getPackage(0)),gbc,0);
        this.add(new PackageItem(PackageDeliveryTracker.getPackage(0)),gbc,0);
        this.add(new PackageItem(PackageDeliveryTracker.getPackage(0)),gbc,0);
        this.add(new PackageItem(PackageDeliveryTracker.getPackage(0)),gbc,0);
        this.add(new PackageItem(PackageDeliveryTracker.getPackage(0)),gbc,0);
        this.add(new PackageItem(PackageDeliveryTracker.getPackage(0)),gbc,0);
    }
}
