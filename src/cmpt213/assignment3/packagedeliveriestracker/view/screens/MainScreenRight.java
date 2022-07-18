package cmpt213.assignment3.packagedeliveriestracker.view.screens;

import cmpt213.assignment3.packagedeliveriestracker.control.PackageDeliveryTracker;
import cmpt213.assignment3.packagedeliveriestracker.view.util.PackageItem;
import cmpt213.assignment3.packagedeliveriestracker.view.util.Util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainScreenRight extends JPanel {
    private JButton listAllBtn, overdueBtn, upcomingBtn;
    private  JLabel listAllText, overdueText, upcomingText;
    private final JPanel basePanel;
    private static final JPanel listAllCard = new JPanel();
    private static final JPanel overdueCard = new JPanel();
    private static final JPanel upcomingCard = new JPanel();
    private JPanel columnHeader;

    public MainScreenRight(ActionListener al) {

        this.basePanel = new JPanel(new CardLayout());
//        this.columnHeader = new JPanel();
//        int width = 200;
//        int height = 200;
//        basePanel.setSize(new Dimension((width), (height)));
//        basePanel.setMaximumSize(new Dimension(width, (height)));
//        basePanel.setBorder(new LineBorder(Color.BLACK, 5));
//
//        setUpViews(al);
//        addCards();

//        this.getViewport().setSize(new Dimension (width,height));
//        this.getViewport().setMaximumSize(new Dimension (width,height));
    }

    private void setUpViews(ActionListener al) {
        setUpListAll(al);
        setUpUpcoming(al);
        setUpOverdue(al);

        //set up column header
        this.columnHeader.setLayout(new BoxLayout(columnHeader, BoxLayout.LINE_AXIS));
        this.columnHeader.add(listAllBtn);
        this.columnHeader.add(overdueBtn);
        this.columnHeader.add(upcomingBtn);
    }

    private void addCards() {
        basePanel.add(listAllCard);
//        basePanel.add(upcomingCard);
//        basePanel.add(overdueCard);
        upcomingCard.setVisible(false);
        overdueCard.setVisible(false);

//        this.setRowHeaderView(columnHeader);
    }

    private void setUpListAll(ActionListener al) {
        this.listAllText = new JLabel("LIST OF ALL PACKAGES");
        listAllCard.setLayout(new BoxLayout(listAllCard, BoxLayout.Y_AXIS));
        this.listAllBtn = new JButton("LIST ALL");
        this.listAllBtn.setActionCommand("LIST ALL");
        this.listAllBtn.addActionListener(al);

        listAllCard.add(new PackageItem(basePanel.getWidth(), PackageDeliveryTracker.getPackage(0)), Box.CENTER_ALIGNMENT);
        listAllCard.add(new PackageItem(basePanel.getWidth(), PackageDeliveryTracker.getPackage(0)), Box.CENTER_ALIGNMENT);
        listAllCard.add(new PackageItem(basePanel.getWidth(), PackageDeliveryTracker.getPackage(0)), Box.CENTER_ALIGNMENT);
        listAllCard.add(new PackageItem(basePanel.getWidth(), PackageDeliveryTracker.getPackage(0)), Box.CENTER_ALIGNMENT);
        listAllCard.add(new PackageItem(basePanel.getWidth(), PackageDeliveryTracker.getPackage(0)), Box.CENTER_ALIGNMENT);
    }

    private void setUpUpcoming(ActionListener al) {
        this.upcomingText = new JLabel("UPCOMING PACKAGES");
        upcomingCard.setLayout(new BoxLayout(upcomingCard, BoxLayout.Y_AXIS));
        this.upcomingBtn = new JButton("UPCOMING");
        this.upcomingBtn.setActionCommand("UPCOMING");
        this.upcomingBtn.addActionListener(al);

//        upcomingCard.add(new PackageItem(basePanel.getWidth(), PackageDeliveryTracker.getPackage(0)), Box.CENTER_ALIGNMENT);
    }

    private void setUpOverdue(ActionListener al) {
        this.overdueText = new JLabel("OVERDUE PACKAGES");
        overdueCard.setLayout(new BoxLayout(overdueCard, BoxLayout.Y_AXIS));
        this.overdueBtn = new JButton("OVERDUE");
        this.overdueBtn.setActionCommand("OVERDUE");
        this.overdueBtn.addActionListener(al);

//        this.overdueBtn.add(new PackageItem(basePanel.getWidth()), Box.CENTER_ALIGNMENT);
    }

    public static void switchView(Util.SCREEN_STATE newState) {

        switch (newState) {
            case LIST_ALL -> {
                listAllCard.setVisible(true);
                upcomingCard.setVisible(false);
                overdueCard.setVisible(false);
            }
            case UPCOMING -> {
                listAllCard.setVisible(false);
                upcomingCard.setVisible(true);
                overdueCard.setVisible(false);
            }
            case OVERDUE -> {
                listAllCard.setVisible(false);
                upcomingCard.setVisible(false);
                overdueCard.setVisible(true);
            }
        }

    }
}
