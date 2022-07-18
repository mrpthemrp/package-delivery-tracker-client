package cmpt213.assignment3.packagedeliveriestracker.view.custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColumnHeader extends JPanel {
    public HeaderButton listAllBtn, upcomingBtn, overdueBtn;
    private final JLabel sortTypeTitle;
    private final static String[] titles = {"LIST ALL PACKAGES", "UPCOMING PACKAGES", "OVERDUE PACKAGES"};

    public ColumnHeader(ActionListener al) {

        sortTypeTitle = new JLabel(titles[0]);
        sortTypeTitle.setFont(Util.sortTitleFont);
        sortTypeTitle.setForeground(Util.midTeal);
        sortTypeTitle.setBackground(Color.WHITE);

        this.setBackground(Color.WHITE);

        this.listAllBtn = new HeaderButton("LIST ALL", al);
        this.upcomingBtn = new HeaderButton("UPCOMING", al);
        this.overdueBtn = new HeaderButton("OVERDUE", al);

        //set up column header
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(sortTypeTitle, RIGHT_ALIGNMENT);
        this.add(Box.createHorizontalGlue());
        this.add(listAllBtn, LEFT_ALIGNMENT);
        this.add(upcomingBtn, LEFT_ALIGNMENT);
        this.add(overdueBtn, LEFT_ALIGNMENT);
    }

    public void changeColumnText(Util.SCREEN_STATE state) {
        switch (state) {
            case LIST_ALL -> this.sortTypeTitle.setText(titles[0]);
            case UPCOMING -> this.sortTypeTitle.setText(titles[1]);
            case OVERDUE -> this.sortTypeTitle.setText(titles[2]);
        }
    }


    public void buttonClicked(Util.SCREEN_STATE btnState) {
        switch (btnState) {
            case LIST_ALL -> {
                listAllBtn.changeClickStatus();
                if (upcomingBtn.clicked) {
                    upcomingBtn.changeClickStatus();
                }
                if (overdueBtn.clicked) {
                    overdueBtn.changeClickStatus();
                }
            }
            case UPCOMING -> {
                upcomingBtn.changeClickStatus();
                if (listAllBtn.clicked) {
                    listAllBtn.changeClickStatus();
                }
                if (overdueBtn.clicked) {
                    overdueBtn.changeClickStatus();
                }
            }
            case OVERDUE -> {
                overdueBtn.changeClickStatus();
                if (listAllBtn.clicked) {
                    listAllBtn.changeClickStatus();
                }
                if (upcomingBtn.clicked) {
                    upcomingBtn.changeClickStatus();
                }
            }
        }
    }


}
