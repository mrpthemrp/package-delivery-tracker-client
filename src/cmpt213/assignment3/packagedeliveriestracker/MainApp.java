package cmpt213.assignment3.packagedeliveriestracker;

import cmpt213.assignment3.packagedeliveriestracker.model.Package;

import javax.swing.*;
import java.util.Comparator;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PackageDeliveryGUI());
    }
}
