package cmpt213.assignment3.packagedeliveriestracker.view;

import cmpt213.assignment3.packagedeliveriestracker.view.util.MainScreen;
import cmpt213.assignment3.packagedeliveriestracker.view.util.StartScreen;
import cmpt213.assignment3.packagedeliveriestracker.view.util.Util;
import cmpt213.assignment3.packagedeliveriestracker.view.util.Util.SCREEN_STATE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PackageDeliveryGUI extends JFrame implements ActionListener, ItemListener {

    private final MainScreen mainPanel;
    private final StartScreen startPanel;
    private SCREEN_STATE currentState;

    public PackageDeliveryGUI() {
        this.setResizable(false);
        this.setBackground(Color.WHITE);
        currentState = SCREEN_STATE.START;
        startPanel = new StartScreen(this);
        mainPanel = new MainScreen(this);

        //set up frame
        this.setTitle("Package Delivery Tracker");//change string input later

        //Containers
        this.setSize((int) (Util.screenWidth * 0.75), (int) (Util.screenHeight * 0.75));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //complete frame setup
        this.setContentPane(startPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("START")) {
            System.out.println("start was pressed");
        }
        if (e.getActionCommand().equals("ADD PACKAGE")) {
            System.out.println("add package was pressed");
        }
        updateStates();

    }

    private void updateStates() {

        switch (currentState) {
            case START -> {
                startPanel.hideScreen();
                this.remove(startPanel);
                this.setContentPane(mainPanel);
                this.setTitle("Package Delivery Tracker - Main");
                currentState = SCREEN_STATE.MAIN;
            }
            case MAIN -> {
                System.out.println("in main");
            }
            default -> this.repaint();
        }

        this.repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
