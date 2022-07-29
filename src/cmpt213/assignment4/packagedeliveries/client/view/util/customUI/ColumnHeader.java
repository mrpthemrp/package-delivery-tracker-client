package cmpt213.assignment4.packagedeliveries.client.view.util.customUI;

import cmpt213.assignment4.packagedeliveries.client.view.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Custom column header that will be added to a JScroll pane; uses HeaderButtons.
 * Implements how different buttons will behave on click.
 *
 * @author Deborah Wang
 */
public class ColumnHeader extends JPanel {
    private final static String[] titles = {"LIST ALL PACKAGES", "UPCOMING PACKAGES", "OVERDUE PACKAGES"};
    private final JLabel sortTypeTitle;
    public HeaderButton listAllBtn, upcomingBtn, overdueBtn;

    /**
     * Constructor for column header, sets look and feel of buttons.
     *
     * @param al Action Listener that buttons will add.
     */
    public ColumnHeader(ActionListener al) {

        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, (int) (Util.screenHeight * 0.004), 0, Util.lightTeal));

        this.sortTypeTitle = new JLabel(titles[0]);
        this.sortTypeTitle.setFont(Util.sortTitleFont);
        this.sortTypeTitle.setForeground(Util.midTeal);
        this.sortTypeTitle.setBackground(Color.WHITE);

        this.listAllBtn = new HeaderButton("LIST ALL", al);
        this.upcomingBtn = new HeaderButton("UPCOMING", al);
        this.overdueBtn = new HeaderButton("OVERDUE", al);

        //add components
        this.add(sortTypeTitle, RIGHT_ALIGNMENT);
        this.add(Box.createHorizontalGlue());
        this.add(listAllBtn, LEFT_ALIGNMENT);
        this.add(upcomingBtn, LEFT_ALIGNMENT);
        this.add(overdueBtn, LEFT_ALIGNMENT);
    }

    /**
     * Changes the colour and state of each button depending on the state.
     *
     * @param state Current screen state of UI.
     */
    public void changeColumnText(Util.SCREEN_STATE state) {
        switch (state) {
            case LIST_ALL -> this.sortTypeTitle.setText(titles[0]);
            case UPCOMING -> this.sortTypeTitle.setText(titles[1]);
            case OVERDUE -> this.sortTypeTitle.setText(titles[2]);
        }
    }

    /**
     * Updates states of all buttons when a button is clicked.
     * Ensures colour changes correctly.
     *
     * @param btnState Current State of the screen and button.
     */
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
