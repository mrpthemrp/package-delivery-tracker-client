package cmpt213.assignment3.packagedeliveriestracker;

import cmpt213.assignment3.packagedeliveriestracker.view.PackageDeliveryGUI;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PackageDeliveryGUI();
        });
    }
}
