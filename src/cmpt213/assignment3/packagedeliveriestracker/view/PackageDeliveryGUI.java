package cmpt213.assignment3.packagedeliveriestracker.view;

import cmpt213.assignment3.packagedeliveriestracker.view.util.MainScreen;
import cmpt213.assignment3.packagedeliveriestracker.view.util.StartScreen;
import cmpt213.assignment3.packagedeliveriestracker.view.util.Util;
import cmpt213.assignment3.packagedeliveriestracker.view.util.Util.SCREEN_STATE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PackageDeliveryGUI extends JFrame implements ItemListener {
    private SCREEN_STATE currentState;
    private final JPanel basePanel;

    private final StartScreen startPanel;
    private final MainScreen mainPanel;

    public PackageDeliveryGUI() {
        this.setResizable(false);
        this.setBackground(Color.WHITE);
        this.basePanel = new JPanel(new CardLayout());
        currentState = SCREEN_STATE.START;
        startPanel = new StartScreen(this);
        mainPanel = new MainScreen(this);


        //set up frame
        this.setTitle("Package Delivery Tracker");//change string input later

        //Containers
        this.setSize((int) (Util.screenWidth * 0.75), (int) (Util.screenHeight * 0.75));
        this.basePanel.setSize(new Dimension(this.getWidth(),this.getHeight()));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //complete frame setup
        this.basePanel.add(startPanel);
        this.setContentPane(basePanel);
        this.setVisible(true);
    }

    public void updateStates() {

        switch (currentState) {
            case START -> {
                this.basePanel.removeAll();
                this.basePanel.add(mainPanel);
                this.setTitle("Package Delivery Tracker - Main");
                currentState = SCREEN_STATE.MAIN;
            }
            case MAIN -> {
                System.out.println("in main");
            }
            default -> this.repaint();
        }

        repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
