package cmpt213.assignment3.packagedeliveriestracker.view.screens;

import cmpt213.assignment3.packagedeliveriestracker.control.PackageDeliveryTracker;
import cmpt213.assignment3.packagedeliveriestracker.view.util.PackageItem;
import cmpt213.assignment3.packagedeliveriestracker.view.util.Util;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainScreenRight extends JTable {
    private TableModel tableModel;
    private JButton listAllBtn, upcomingBtn, overdueBtn;
    private JLabel sortTypeTitle;
    private final static String[] titles = {"LIST ALL PACKAGES", "UPCOMING PACKAGES", "OVERDUE PACKAGES"};
    public JPanel columnHeader;

    public MainScreenRight(ActionListener al, int width, TableModel tableModel) {
        this.tableModel = tableModel;
//        this.setSize(width, (int) (Util.screenHeight * 0.65));
        this.setPreferredScrollableViewportSize(new Dimension(width, (int) (Util.screenHeight * 0.65)));
        this.setBackground(Util.transparent);

        sortTypeTitle = new JLabel(titles[0]);
        sortTypeTitle.setFont(Util.bodyFont);
        this.columnHeader = new JPanel();

        setUpViews(al);
        this.setVisible(true);
    }

    private void setUpViews(ActionListener al) {
        setUpHeaderButtons(al);

        //set up column header
        this.columnHeader.setLayout(new BoxLayout(columnHeader, BoxLayout.X_AXIS));
        this.columnHeader.add(Box.createRigidArea(new Dimension(0, 0)));
        this.columnHeader.add(sortTypeTitle, RIGHT_ALIGNMENT);
        this.columnHeader.add(Box.createHorizontalGlue());
        this.columnHeader.add(listAllBtn, LEFT_ALIGNMENT);
        this.columnHeader.add(upcomingBtn, LEFT_ALIGNMENT);
        this.columnHeader.add(overdueBtn, LEFT_ALIGNMENT);
    }

    private void setUpHeaderButtons(ActionListener al) {
        this.listAllBtn = new JButton("LIST ALL");
        this.listAllBtn.setActionCommand("LIST ALL");
        this.listAllBtn.addActionListener(al);

        this.upcomingBtn = new JButton("UPCOMING");
        this.upcomingBtn.setActionCommand("UPCOMING");
        this.upcomingBtn.addActionListener(al);

        this.overdueBtn = new JButton("OVERDUE");
        this.overdueBtn.setActionCommand("OVERDUE");
        this.overdueBtn.addActionListener(al);

    }



    public JPanel getColumnHeader() {
        return this.columnHeader;
    }

    public void changeColumnText(Util.SCREEN_STATE state) {
        switch (state) {
            case LIST_ALL -> this.sortTypeTitle.setText(titles[0]);
            case UPCOMING -> this.sortTypeTitle.setText(titles[1]);
            case OVERDUE -> this.sortTypeTitle.setText(titles[2]);
        }
    }
}
