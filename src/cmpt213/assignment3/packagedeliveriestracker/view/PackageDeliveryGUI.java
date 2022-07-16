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

    private SCREEN_STATE currentState;

    private final MainScreen mainPanel;
    private final StartScreen startPanel;

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
        if(e.getActionCommand().equals("SCREEN BUTTON")){
            if(currentState == SCREEN_STATE.START){
                System.out.println("Start was pressed");
            } else{
                System.out.println("add button pressed");
            }
        }
        updateGUI();

    }

    private void updateGUI() {

        switch (currentState){
            case START -> {
                this.remove(startPanel);
                this.setContentPane(mainPanel);
                this.repaint();
                currentState = SCREEN_STATE.MAIN;
                this.setTitle("Package Delivery Tracker - Main");
            }
            case MAIN -> {
                System.out.println("in main");
            }
            default -> this.repaint();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
