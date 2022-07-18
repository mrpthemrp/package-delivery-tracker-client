package cmpt213.assignment3.packagedeliveriestracker;

import cmpt213.assignment3.packagedeliveriestracker.control.PackageDeliveryTracker;
import cmpt213.assignment3.packagedeliveriestracker.view.PackageDeliveryGUI;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        //to delte
        PackageDeliveryTracker pkgTracker = new PackageDeliveryTracker();

        SwingUtilities.invokeLater(() -> {
            new PackageDeliveryGUI();
        });
    }
}
