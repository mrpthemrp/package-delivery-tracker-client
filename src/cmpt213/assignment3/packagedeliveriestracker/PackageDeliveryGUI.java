package cmpt213.assignment3.packagedeliveriestracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PackageDeliveryGUI implements ActionListener, ItemListener {
    //Components
    private JButton startBtn;


    //Containers
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double xSize = (screenSize.getWidth() / 2);
    private double ySize = (screenSize.getHeight() / 2);
    private JFrame appFrame;

    public PackageDeliveryGUI() {
        //set up frame
        appFrame = new JFrame("Package Delivery Tracker");//change string input later
        appFrame.setSize((int) xSize, (int) ySize);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set up components
        startBtn = new JButton("Click to start");//change inner later
        appFrame.add(startBtn);

        //complete frame setup
        appFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
