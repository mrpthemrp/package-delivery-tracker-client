package cmpt213.assignment4.packagedeliveries.client;

import cmpt213.assignment4.packagedeliveries.client.view.PackageDeliveryGUI;

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
