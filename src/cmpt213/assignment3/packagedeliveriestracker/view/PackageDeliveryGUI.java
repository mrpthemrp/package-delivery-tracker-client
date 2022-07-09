package cmpt213.assignment3.packagedeliveriestracker.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PackageDeliveryGUI implements ActionListener, ItemListener {
    //CONSTANTS

    //Containers
    private final Dimension screenSize;
    private final JFrame appFrame;
    //VARIABLES
    private Color darkTeal;
    private Color midTeal;
    private Color lightTeal;
    private Color lightBrown;
    private Color darkBrown;
    private Color ashRed;
    private Color sageGreen;

    private JPanel welcomePanel, mainPanel;

    public PackageDeliveryGUI() {
        //LOGIC
        screenSize = new Dimension();
        screenSize.setSize(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2,
                Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
        setUpColors();

        //set up frame
        appFrame = new JFrame("Package Delivery Tracker");//change string input later
        appFrame.setBackground(Color.WHITE);
        appFrame.setSize(screenSize);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set up panels
        setWelcomePanel();
        setMainPanel();

        //complete frame setup
        appFrame.setContentPane(welcomePanel);
        appFrame.setVisible(true);

    }

    private void setUpColors() {
        darkTeal = new Color(0, 109, 119);
        midTeal = new Color(131, 197, 190);
        lightTeal = new Color(237, 246, 249);
        lightBrown = new Color(255, 221, 210);
        darkBrown = new Color(226, 149, 120);
        ashRed = new Color(233, 90, 90);
        sageGreen = new Color(164, 226, 142);
    }

    private void setMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(screenSize);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
    }

    private void setWelcomePanel() {
        welcomePanel = new JPanel();
        welcomePanel.setPreferredSize(screenSize);
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.X_AXIS));

        //Components
        JButton startBtn = new JButton("ENTER");//change inner later
        startBtn.setActionCommand("START");
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startBtn.addActionListener(this);
        welcomePanel.add(startBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //invoked only when start screen is in
        if (e.getActionCommand().equals("START")) {
            System.out.println("Start was pressed");
            updateScreenDisplay(SCREEN_STATE.MAIN_SCREEN);
        }

        //home screen

    }

    private void updateScreenDisplay(SCREEN_STATE newState) {
        switch (newState) {
            case MAIN_SCREEN -> {
                appFrame.remove(welcomePanel);
                appFrame.setContentPane(mainPanel);
                appFrame.repaint();
            }
            case ADD_PACKAGE -> {
                System.out.println("ADD PACKAGE");
            }
            case REMOVE_PACKAGE -> {
                System.out.println("REMOVE PACKAGE");
            }
            case LIST_OVERDUE -> {
                System.out.println("LIST OVERDUE PACKAGES");
            }
            case LIST_UPCOMING -> {
                System.out.println("LIST UPCOMING PACKAGES");
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    private static enum SCREEN_STATE {
        WELCOME_SCREEN, MAIN_SCREEN, ADD_PACKAGE, REMOVE_PACKAGE, LIST_OVERDUE, LIST_UPCOMING;
    }
}
