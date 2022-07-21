package cmpt213.assignment3.packagedeliveries.view;

import cmpt213.assignment3.packagedeliveries.control.PackageDeliveryControl;
import cmpt213.assignment3.packagedeliveries.view.util.customUi.AddPackageDialog;
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
import java.awt.geom.RoundRectangle2D;

import static java.awt.Scrollbar.VERTICAL;

public class PackageDeliveryGUI extends JFrame implements ActionListener {
    public static SCREEN_STATE currentState;
    private final JSplitPane mainPanel;
    private final StartScreen startPanel;
    private final JScrollPane scrollPane;
    private final ColumnHeader columnHeader;
    private final JPanel header;
    private final JPanel leftBar;
    private final JPanel footer;
    private final MainScreenRight screenRight;
    private final AddPackageDialog addPackageDialog;

    public PackageDeliveryGUI() {

        PackageDeliveryControl packageControl = new PackageDeliveryControl();

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
                packageControl.arrayData(PackageDeliveryControl.DATA_SAVE);
            }
        });
        this.setIconImage(Util.appIcon);

        //initialize fields
        currentState = SCREEN_STATE.START;
        this.header = new JPanel();
        this.leftBar = new JPanel();
        this.footer = new JPanel();
        this.addPackageDialog = new AddPackageDialog(this, "Package Delivery Tracker - Add Package",
                "  C R E A T E  ", "  C A N C E L  ", packageControl);

        this.startPanel = new StartScreen(this);
        columnHeader = new ColumnHeader(this);
        screenRight = new MainScreenRight(packageControl, this, this);
        scrollPane = new JScrollPane(screenRight);
        this.mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new MainScreenLeft(this), scrollPane);

        //setUp necessary fields
        setUpScreenPaddings();
        setUpMainScreen();

        //add components to frame
        this.add(header, BorderLayout.NORTH);
        this.add(startPanel, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
        this.setVisible(true);
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

        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().getView().setBackground(Color.WHITE);

        //refernce for making up down buttons look invisible
        //https://stackoverflow.com/questions/7633354/how-to-hide-the-arrow-buttons-in-a-jscrollbar
        scrollPane.getVerticalScrollBar().setOrientation(VERTICAL);
        scrollPane.getVerticalScrollBar().setBackground(Color.WHITE);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneLayout.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Util.transparent);
                g2.fill(trackBounds);
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g;
                RoundRectangle2D roundedThumb = new RoundRectangle2D.Double(thumbBounds.x, thumbBounds.y,
                        thumbBounds.width * 0.9, thumbBounds.height, Util.screenWidth * 0.01, Util.screenWidth * 0.01);
                if (isThumbRollover()) {
                    g2.setColor(Util.lightTeal);
                } else {
                    g2.setColor(Util.transparent);
                }
                g2.fill(roundedThumb);

            }

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
                Dimension zeroDimension = new Dimension(0, 0);

                button.setBorder(BorderFactory.createEmptyBorder());
                button.setFocusPainted(false);
                button.setForeground(Util.transparent);
                button.setBackground(Util.transparent);
                button.setPreferredSize(zeroDimension);

                return button;
            }
        });
        scrollPane.setColumnHeaderView(columnHeader);

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


    public void updateStates() {

        switch (currentState) {
            case LIST_ALL, UPCOMING, OVERDUE -> {
                columnHeader.buttonClicked(currentState);
                screenRight.removeAll();
                screenRight.populateList(currentState);
                columnHeader.buttonClicked(currentState);
                scrollPane.setViewportView(screenRight);
            }
        }
        columnHeader.changeColumnText(currentState);
    }

    private void switchToMain() {
        this.remove(startPanel);
        this.add(leftBar, BorderLayout.EAST);
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);

        this.setTitle("Package Delivery Tracker - Home");
        currentState = SCREEN_STATE.LIST_ALL;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("UPDATE")){
            repaint();
        }
        if (e.getActionCommand().equals("ENTER")) {
            switchToMain();
        }
        if (e.getActionCommand().equals("ADD PACKAGE")) {
            addPackageDialog.run();
        } else if (e.getActionCommand().equals("LIST ALL")) {
            currentState = Util.SCREEN_STATE.LIST_ALL;
        } else if (e.getActionCommand().equals("UPCOMING")) {
            currentState = Util.SCREEN_STATE.UPCOMING;
        } else if (e.getActionCommand().equals("OVERDUE")) {
            currentState = Util.SCREEN_STATE.OVERDUE;
        }
        columnHeader.buttonClicked(currentState);
        updateStates();
        repaint();
    }


}
