package cmpt213.assignment3.packagedeliveries;

import cmpt213.assignment3.packagedeliveries.view.PackageDeliveryGUI;

import javax.swing.*;

/**
 * 'main' for this program. Starts the entire program.
 *
 * @author Deborah Wang
 */
public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PackageDeliveryGUI::new);
    }
}
