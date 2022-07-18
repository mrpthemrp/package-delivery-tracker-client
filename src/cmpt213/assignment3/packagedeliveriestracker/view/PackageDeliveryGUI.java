package cmpt213.assignment3.packagedeliveriestracker.view;

import cmpt213.assignment3.packagedeliveriestracker.control.TableOfPackages;
import cmpt213.assignment3.packagedeliveriestracker.view.screens.*;
import cmpt213.assignment3.packagedeliveriestracker.view.util.Util;
import cmpt213.assignment3.packagedeliveriestracker.view.util.Util.SCREEN_STATE;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class PackageDeliveryGUI extends JFrame implements ItemListener, ActionListener {
    private SCREEN_STATE currentState;
    private final JSplitPane mainPanel;
    private final StartScreen startPanel;
    private final JScrollPane scrollPane;
    private final MainScreenRight screenRight;

    public PackageDeliveryGUI() {

        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        currentState = SCREEN_STATE.START;
        startPanel = new StartScreen(this);

        screenRight = new MainScreenRight(this,(this.getWidth()), new TableOfPackages());
        scrollPane = new JScrollPane(screenRight);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().getView().setBackground(Color.WHITE);
        scrollPane.getViewport().getView().setSize(new Dimension((int) (screenRight.getWidth()*0.8), (int) (screenRight.getHeight()*0.75)));
        scrollPane.getViewport().getView().setMaximumSize(new Dimension((int) (screenRight.getWidth()*0.8), (int) (screenRight.getHeight()*0.75)));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setViewportBorder(new LineBorder(Util.transparent,0));
        scrollPane.setColumnHeaderView(screenRight.getColumnHeader());

        mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new MainScreenLeft(this), scrollPane);
        mainPanel.setDividerLocation((int) (Util.screenWidth * 0.257));
        mainPanel.setDividerSize(0);
        mainPanel.setEnabled(false);
        mainPanel.setBackground(Color.WHITE);


        //set up frame
        this.setTitle("Package Delivery Tracker");//change string input later

        //Containers
        this.setSize((int) (Util.screenWidth * 0.75), (int) (Util.screenHeight * 0.75));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //complete frame setup
        this.add(startPanel);
        this.setVisible(true);
    }

    public void updateStates() {

        switch (currentState) {
            case START -> {
                this.remove(startPanel);
                this.add(mainPanel);
                this.setVisible(true);

                this.setTitle("Package Delivery Tracker - Home");
                currentState = SCREEN_STATE.MAIN;
            }
            case MAIN -> System.out.println("in main");

            default -> this.repaint();
        }

        screenRight.changeColumnText(currentState);
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
            this.currentState = Util.SCREEN_STATE.LIST_ALL;
        } else if (e.getActionCommand().equals("UPCOMING")) {
            System.out.println("upcoming view");
            this.currentState = SCREEN_STATE.UPCOMING;
        } else if (e.getActionCommand().equals("OVERDUE")) {
            System.out.println("overdue view");
            this.currentState = SCREEN_STATE.OVERDUE;
        }

        repaint();
        updateStates();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {



    }


}
