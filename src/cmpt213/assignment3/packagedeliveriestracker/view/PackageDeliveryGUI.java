package cmpt213.assignment3.packagedeliveriestracker.view;

import cmpt213.assignment3.packagedeliveriestracker.view.screens.MainScreen;
import cmpt213.assignment3.packagedeliveriestracker.view.screens.StartScreen;
import cmpt213.assignment3.packagedeliveriestracker.view.util.PackageScrollPane;
import cmpt213.assignment3.packagedeliveriestracker.view.util.Util;
import cmpt213.assignment3.packagedeliveriestracker.view.util.Util.SCREEN_STATE;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class PackageDeliveryGUI extends JFrame implements ItemListener, ActionListener {
    private SCREEN_STATE currentState;
    private final JPanel basePanel;
    private final MainScreen mainPanel;

    public PackageDeliveryGUI() {
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.basePanel = new JPanel(new CardLayout());
        currentState = SCREEN_STATE.START;
        StartScreen startPanel = new StartScreen(this);
        mainPanel = new MainScreen(this);


        //set up frame
        this.setTitle("Package Delivery Tracker");//change string input later

        //Containers
        this.setSize((int) (Util.screenWidth * 0.75), (int) (Util.screenHeight * 0.75));
        this.basePanel.setSize(new Dimension((this.getWidth()), this.getHeight()));
        this.basePanel.setMaximumSize(new Dimension( this.getWidth(), this.getHeight()));
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
                this.basePanel.setSize(new Dimension(mainPanel.getSize()));
                this.basePanel.add(mainPanel);
                PackageScrollPane packageScroll = new PackageScrollPane((int) (Util.screenWidth / 2.2), (int) (this.getHeight() * 0.98), this);
//                packageScroll.setBorder(new LineBorder(Color.BLACK, 5));
                this.add(basePanel, BorderLayout.WEST);
                this.add(packageScroll, BorderLayout.EAST);
                this.setTitle("Package Delivery Tracker - Home");
                currentState = SCREEN_STATE.MAIN;
            }
            case MAIN -> System.out.println("in main");

            default -> this.repaint();
        }

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ENTER")) {
            System.out.println("start was pressed");
        } else if (e.getActionCommand().equals("ADD PACKAGE")) {
            System.out.println("add package was pressed");
        } else if (e.getActionCommand().equals("LIST ALL")) {
            System.out.println("list all view");
            PackageScrollPane.switchView(Util.SCREEN_STATE.LIST_ALL);
        } else if (e.getActionCommand().equals("UPCOMING")) {
            System.out.println("upcoming view");
            PackageScrollPane.switchView(SCREEN_STATE.UPCOMING);
        } else if (e.getActionCommand().equals("OVERDUE")) {
            System.out.println("overdue view");
            PackageScrollPane.switchView(SCREEN_STATE.OVERDUE);
        }

        repaint();
        updateStates();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
