package cmpt213.assignment3.packagedeliveriestracker;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new PackageDeliveryGUI();
            } catch (IOException | FontFormatException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
