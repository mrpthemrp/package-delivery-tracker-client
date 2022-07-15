package cmpt213.assignment3.packagedeliveriestracker.view;

import cmpt213.assignment3.packagedeliveriestracker.view.util.Screens;
import cmpt213.assignment3.packagedeliveriestracker.view.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PackageDeliveryGUI implements ActionListener, ItemListener {

    //STATES - to be moved?
    public enum SCREEN_STATE {
        START, MAIN, ADD_PACKAGE, UPCOMING, OVERDUE;
    }

    private SCREEN_STATE currentState;
    private Util util;

    //Components
    private JButton startBtn;

    private Screens screen;


    //Containers
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double xSize = (screenSize.getWidth() *0.75 );
    private double ySize = (screenSize.getHeight() * 0.75);
    private JFrame appFrame;

    public PackageDeliveryGUI() {
        this.util = new Util();
        currentState = SCREEN_STATE.START;
        screen = new Screens(this);
        //set up buttons
        setUpButtons();

        setUpStartPanel();

        //set up frame
        appFrame = new JFrame("Package Delivery Tracker");//change string input later
        appFrame.setSize((int) xSize, (int) ySize);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //complete frame setup
        appFrame.setContentPane(screen);
        appFrame.setVisible(true);
    }

    private void setUpMainPanel() {
    }

    private void setUpStartPanel() {
        //buttons

        //text

    }

    private void setUpButtons() {

        //add package button


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
                screen.switchToMainScreen();
                setUpMainPanel();
                currentState = SCREEN_STATE.MAIN;
                appFrame.setTitle("Package Delivery Tracker - Main");
                appFrame.repaint();
                break;
            }
            case MAIN -> {
                System.out.println("in main");
            }
            default -> appFrame.repaint();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
