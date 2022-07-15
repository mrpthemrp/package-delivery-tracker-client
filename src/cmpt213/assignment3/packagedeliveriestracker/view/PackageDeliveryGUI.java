package cmpt213.assignment3.packagedeliveriestracker.view;

import cmpt213.assignment3.packagedeliveriestracker.view.util.Screens;
import cmpt213.assignment3.packagedeliveriestracker.view.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PackageDeliveryGUI extends JFrame implements ActionListener, ItemListener {

    //STATES - to be moved?
    public enum SCREEN_STATE {
        START, MAIN, ADD_PACKAGE, UPCOMING, OVERDUE
    }

    private SCREEN_STATE currentState;

    private final Screens screen;

    public PackageDeliveryGUI() {
        currentState = SCREEN_STATE.START;
        screen = new Screens(this);

        //set up frame
        this.setTitle("Package Delivery Tracker");//change string input later
        //Containers
        this.setSize((int) (Util.screenWidth * 0.75), (int) (Util.screenHeight * 0.75));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //complete frame setup
        this.setContentPane(screen);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        //draw line
        if(currentState != SCREEN_STATE.START){
            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(Util.midTeal);
            g2.fillRoundRect((int)(Util.screenWidth * 0.30),(int)(Util.screenHeight * 0.11),
                    4, (int) (Util.screenHeight * 0.55),3,3);
        }

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
                currentState = SCREEN_STATE.MAIN;
                this.setTitle("Package Delivery Tracker - Main");
                this.repaint();
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
