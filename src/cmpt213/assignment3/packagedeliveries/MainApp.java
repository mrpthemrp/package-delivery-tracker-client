package cmpt213.assignment3.packagedeliveries;

import cmpt213.assignment3.packagedeliveries.control.PackageDeliveryTracker;
import cmpt213.assignment3.packagedeliveries.view.PackageDeliveryGUI;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        PackageDeliveryTracker pkg = new PackageDeliveryTracker();
        SwingUtilities.invokeLater(PackageDeliveryGUI::new);
    }
}
