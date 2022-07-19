package cmpt213.assignment3.packagedeliveries.view;

import cmpt213.assignment3.packagedeliveries.view.util.customUi.TableOfPackages;
import cmpt213.assignment3.packagedeliveries.view.util.customUi.ColumnHeader;
import cmpt213.assignment3.packagedeliveries.view.screens.*;
import cmpt213.assignment3.packagedeliveries.view.util.Util;
import cmpt213.assignment3.packagedeliveries.view.util.Util.SCREEN_STATE;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.*;

import static java.awt.Scrollbar.VERTICAL;

public class PackageDeliveryGUI extends JFrame implements ItemListener, ActionListener {
    private SCREEN_STATE currentState;
    private final JSplitPane mainPanel;
    private final StartScreen startPanel;
    private final JScrollPane scrollPane;
    private final ColumnHeader columnHeader;
    private final MainScreenRight screenRight;
    private final JPanel header, leftBar, footer;

    public PackageDeliveryGUI() {

        //set up JFrame details
        this.setResizable(false);
        this.setSize(new Dimension((int) (Util.screenWidth * 0.75), (int) (Util.screenHeight * 0.75)));
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setTitle("Package Delivery Tracker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("window closing, save packages here");
            }
        });

        //initialize fields
        this.currentState = SCREEN_STATE.START;
        this.header = new JPanel();
        this.leftBar = new JPanel();
        this.footer = new JPanel();

        this.startPanel = new StartScreen(this);
        this.screenRight = new MainScreenRight(this, (this.getWidth()), new TableOfPackages());
        this.columnHeader = new ColumnHeader(this);
        this.scrollPane = new JScrollPane(this.screenRight);
        this.mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new MainScreenLeft(this), this.scrollPane);

        //setUp necessary fields
        setUpScreenPaddings();
        setUpMainScreen();

        //add components to frame
        this.add(header, BorderLayout.NORTH);
        this.add(startPanel, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    //TODO move this method to control
    public void updateStates() {

        switch (currentState) {
            case START -> {
                this.remove(startPanel);
                this.add(leftBar, BorderLayout.EAST);
                this.add(mainPanel, BorderLayout.CENTER);
                this.setVisible(true);

                this.setTitle("Package Delivery Tracker - Home");
                currentState = SCREEN_STATE.MAIN;
            }
            case MAIN -> System.out.println("in main");

            default -> this.repaint();
        }

        columnHeader.changeColumnText(currentState);
        repaint();
    }

    private void setUpScreenPaddings() {

        this.header.setLayout(new FlowLayout());
        this.header.setSize(new Dimension(this.getWidth(), (int) (this.getHeight() * 0.15)));
        this.header.add(Box.createRigidArea(header.getSize()));
        this.header.setBackground(Color.WHITE);

        this.leftBar.setLayout(new FlowLayout());
        this.leftBar.setSize(new Dimension((int) (Util.screenWidth * 0.05), this.getHeight()));
        this.leftBar.add(Box.createRigidArea(leftBar.getSize()));
        this.leftBar.setBackground(Color.WHITE);

        this.footer.setLayout(new FlowLayout());
        this.footer.setSize(new Dimension(this.getWidth(), (int) (this.getHeight() * 0.1)));
        this.footer.add(Box.createRigidArea(footer.getSize()));
        this.footer.setBackground(Color.WHITE);
    }

    private void setUpMainScreen() {

        this.scrollPane.setBackground(Color.WHITE);
        this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.getViewport().getView().setBackground(Color.WHITE);
        this.scrollPane.getViewport().getView().setSize(new Dimension((int) (screenRight.getWidth() * 0.8),
                (int) (screenRight.getHeight() * 0.75)));
        this.scrollPane.getViewport().getView().setMaximumSize(new Dimension((int) (screenRight.getWidth() * 0.8),
                (int) (screenRight.getHeight() * 0.75)));

        //refernce for making up down buttons look invisible
        //https://stackoverflow.com/questions/7633354/how-to-hide-the-arrow-buttons-in-a-jscrollbar
        this.scrollPane.getVerticalScrollBar().setOrientation(VERTICAL);
        this.scrollPane.getVerticalScrollBar().setBackground(Color.WHITE);
        this.scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createInvisibleButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createInvisibleButton();
            }

            private JButton createInvisibleButton() {
                JButton button = new JButton();
                Dimension zeroDimension = new Dimension(0,0);
                button.setForeground(Util.transparent);
                button.setBackground(Util.transparent);
                button.setPreferredSize(zeroDimension);
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setFocusPainted(false);
                return button;
            }
        });
        this.scrollPane.setColumnHeaderView(columnHeader);

        this.mainPanel.setDividerLocation((int) (Util.screenWidth * 0.257));
        this.mainPanel.setDividerSize((int) (Util.screenWidth * 0.07));
        this.mainPanel.setEnabled(false);
        //reference for divider clear:
        //https://stackoverflow.com/questions/8934169/how-to-change-the-color-or-background-color-of-jsplitpane-divider
        this.mainPanel.setUI(new BasicSplitPaneUI() {
            @Override
            public BasicSplitPaneDivider createDefaultDivider() {
                return new BasicSplitPaneDivider(this) {
                    public void setBorder(Border b) {
                    }

                    @Override
                    public void paint(Graphics g) {

                        g.setColor(Util.transparent);
                        g.fillRect(0, 0, getSize().width, getSize().height);

                        //daw line
                        Graphics2D g2 = (Graphics2D) g;

                        g2.setColor(Util.midTeal);
                        g2.fillRoundRect(this.getWidth() / 2, 0,
                                4, this.getHeight(), 3, 3);
                        super.paint(g);
                    }
                };
            }
        });

        this.mainPanel.setBackground(Color.WHITE);
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder());
    }

    //TODO method actions will be from control classes
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ENTER")) {
            System.out.println("start was pressed");
        } else if (e.getActionCommand().equals("ADD PACKAGE")) {
            System.out.println("add package was pressed");
        } else if (e.getActionCommand().equals("LIST ALL")) {
            System.out.println("list all view");
            this.currentState = Util.SCREEN_STATE.LIST_ALL;
            columnHeader.buttonClicked(currentState);
        } else if (e.getActionCommand().equals("UPCOMING")) {
            System.out.println("upcoming view");
            this.currentState = SCREEN_STATE.UPCOMING;
            columnHeader.buttonClicked(currentState);
        } else if (e.getActionCommand().equals("OVERDUE")) {
            System.out.println("overdue view");
            this.currentState = SCREEN_STATE.OVERDUE;
            columnHeader.buttonClicked(currentState);
        }

        repaint();
        updateStates();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
