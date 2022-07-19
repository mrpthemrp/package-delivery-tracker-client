package cmpt213.assignment3.packagedeliveries.view.screens;

import cmpt213.assignment3.packagedeliveries.control.PackageDeliveryTracker;
import cmpt213.assignment3.packagedeliveries.view.util.Util;
import cmpt213.assignment3.packagedeliveries.view.util.customUi.PackageItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainScreenRight extends JPanel {

    public MainScreenRight(ActionListener al) {
        int arraySize = 7;
        this.setLayout(new GridLayout(7,1,0,50));

        this.setSize(new Dimension(200,200));
        this.setMaximumSize(new Dimension(200,200));
        this.add(new PackageItem(this.getWidth(),PackageDeliveryTracker.getPackage(0)));
        this.add(new PackageItem(this.getWidth(),PackageDeliveryTracker.getPackage(0)));
        this.add(new PackageItem(this.getWidth(),PackageDeliveryTracker.getPackage(0)));
        this.add(new PackageItem(this.getWidth(),PackageDeliveryTracker.getPackage(0)));
        this.add(new PackageItem(this.getWidth(),PackageDeliveryTracker.getPackage(0)));
        this.add(new PackageItem(this.getWidth(),PackageDeliveryTracker.getPackage(0)));
        this.add(new PackageItem(this.getWidth(),PackageDeliveryTracker.getPackage(0)));

        this.setVisible(true);
    }
}
