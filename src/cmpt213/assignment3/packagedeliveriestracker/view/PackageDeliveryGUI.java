package cmpt213.assignment3.packagedeliveriestracker.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PackageDeliveryGUI implements ActionListener, ItemListener {

    //STATES - to be moved?
    public enum SCREEN_STATE {
        START, HOME, ADD_PACKAGE, UPCOMING, OVERDUE;
    }

    private SCREEN_STATE currentState;


    //Components
    private JButton startBtn;


    //Containers
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double xSize = (screenSize.getWidth() *0.75 );
    private double ySize = (screenSize.getHeight() * 0.75);
    private JFrame appFrame;
    private JPanel homePanel, startPanel;

    public PackageDeliveryGUI() {
        currentState = SCREEN_STATE.START;
        //set up buttons
        setUpButtons();
        //starter methods
        setUpPanels();
        
        //set up frame
        appFrame = new JFrame("Package Delivery Tracker");//change string input later
        appFrame.setSize((int) xSize, (int) ySize);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //complete frame setup
        appFrame.setContentPane(startPanel);
        appFrame.setVisible(true);
    }

    private void setUpButtons() {
        //start button
        startBtn = new JButton("Click to start");//change inner later
        startBtn.setActionCommand("START");
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startBtn.addActionListener(this);

        //add package button


    }

    private void setUpPanels() {
        homePanel = new JPanel();

        startPanel = new JPanel();
        startPanel.add(startBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("START")){
            System.out.println("Start was pressed");
        }

        updateGUI();

    }

    private void updateGUI() {

        switch (currentState){
            case START -> {
                appFrame.remove(startPanel);
                appFrame.setContentPane(homePanel);
                currentState = SCREEN_STATE.HOME;
                appFrame.repaint();
                break;
            }
            default -> appFrame.repaint();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
