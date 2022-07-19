package cmpt213.assignment3.packagedeliveries.view;

import cmpt213.assignment3.packagedeliveries.view.util.customUi.TableOfPackages;
import cmpt213.assignment3.packagedeliveries.view.util.customUi.ColumnHeader;
import cmpt213.assignment3.packagedeliveries.view.screens.*;
import cmpt213.assignment3.packagedeliveries.view.util.Util;
import cmpt213.assignment3.packagedeliveries.view.util.Util.SCREEN_STATE;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.*;

public class PackageDeliveryGUI extends JFrame implements ItemListener, ActionListener {
    private SCREEN_STATE currentState;
    private final JSplitPane mainPanel;
    private final StartScreen startPanel;
    private final JScrollPane scrollPane;
    private ColumnHeader columnHeader;
    private final MainScreenRight screenRight;
    private final JPanel header, leftBar,footer;

    public PackageDeliveryGUI() {

        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.header = new JPanel();
        this.leftBar = new JPanel();
        this.footer = new JPanel();
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
        this.columnHeader = new ColumnHeader(this);
        scrollPane.setColumnHeaderView(columnHeader);

        mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new MainScreenLeft(this), scrollPane);
        mainPanel.setDividerLocation((int) (Util.screenWidth * 0.257));
        mainPanel.setDividerSize((int)(Util.screenWidth*0.07));
        mainPanel.setEnabled(false);
        //refernce for divider clear: https://stackoverflow.com/questions/8934169/how-to-change-the-color-or-background-color-of-jsplitpane-divider
        mainPanel.setUI(new BasicSplitPaneUI(){
            @Override
            public BasicSplitPaneDivider createDefaultDivider()
            {
                return new BasicSplitPaneDivider(this)
                {
                    public void setBorder(Border b) {}

                    @Override
                    public void paint(Graphics g)
                    {

                        g.setColor(Util.transparent);
                        g.fillRect(0, 0, getSize().width, getSize().height);

                        //daw line
                        Graphics2D g2 = (Graphics2D) g;
                        System.out.println("dsf"+mainPanel.getWidth()/3);

                        g2.setColor(Util.midTeal);
                        g2.fillRoundRect(this.getWidth()/2,0 ,
                                4, this.getHeight(), 3, 3);
                        super.paint(g);
                    }
                };
            }
        });
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder());


        //set up frame
        this.setTitle("Package Delivery Tracker");//change string input later

        //Containers
        this.setSize((int) (Util.screenWidth * 0.75), (int) (Util.screenHeight * 0.75));
        header.setLayout(new FlowLayout());
        header.setSize(new Dimension(this.getWidth(), (int) (this.getHeight()*0.15)));
        header.add(Box.createRigidArea(header.getSize()));
        leftBar.setLayout(new FlowLayout());
        leftBar.setSize(new Dimension((int)(Util.screenWidth*0.05), this.getHeight()));
        leftBar.add(Box.createRigidArea(leftBar.getSize()));
        footer.setLayout(new FlowLayout());
        footer.setSize(new Dimension(this.getWidth(), (int) (this.getHeight()*0.1)));
        footer.add(Box.createRigidArea(footer.getSize()));

        header.setBackground(Color.WHITE);
        leftBar.setBackground(Color.WHITE);
        footer.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //complete frame setup
        this.add(header, BorderLayout.NORTH);
        this.add(startPanel, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void updateStates() {

        switch (currentState) {
            case START -> {
                this.remove(startPanel);
                this.add(leftBar,BorderLayout.EAST);
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
