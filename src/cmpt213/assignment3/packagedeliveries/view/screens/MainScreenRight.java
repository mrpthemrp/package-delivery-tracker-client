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
        this.setLayout(new GridLayout(arraySize,1,0,(int)(Util.screenHeight*0.004)));

        this.setPreferredSize(new Dimension((int)(Util.screenWidth*0.351),(int)(Util.screenHeight*0.3)));
        this.setMaximumSize(new Dimension((int)(Util.screenWidth*0.351),(int)(Util.screenHeight*0.3)));
        testPackages();

        this.setVisible(true);
    }

    private void testPackages() {
        this.add(new PackageItem(this.getWidth(),PackageDeliveryTracker.getPackage(0)));
        this.add(new PackageItem(this.getWidth(),PackageDeliveryTracker.getPackage(0)));
        this.add(new PackageItem(this.getWidth(),PackageDeliveryTracker.getPackage(0)));
        this.add(new PackageItem(this.getWidth(),PackageDeliveryTracker.getPackage(0)));
        this.add(new PackageItem(this.getWidth(),PackageDeliveryTracker.getPackage(0)));
        this.add(new PackageItem(this.getWidth(),PackageDeliveryTracker.getPackage(0)));
        this.add(new PackageItem(this.getWidth(),PackageDeliveryTracker.getPackage(0)));
    }
}
