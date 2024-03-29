package cmpt213.assignment4.packagedeliveries.client.view.screens;

import cmpt213.assignment4.packagedeliveries.client.view.util.Util;
import cmpt213.assignment4.packagedeliveries.client.view.util.customUI.RoundButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Deborah Wang
 * @link <a href="https://www.tutorialsbuddy.com/create-a-digital-clock-in-java">...</a> Refernce for the clock
 */
public class MainScreenLeft extends JPanel {
    private final JLabel title;
    private final JLabel clock;
    private final JLabel currentDay;
    private final RoundButton btn;
    private LocalDate today;

    /**
     * Constructor for the Left side of the main screen.
     * Sets look and feel, as well as initializes fields.
     *
     * @param al Action listner that the ADD PACKAGE button will add.
     */

    public MainScreenLeft(ActionListener al) {
        this.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.setBackground(Color.WHITE);
        this.setSize(new Dimension((int) (Util.screenWidth * 0.257), (int) (Util.screenHeight * 0.75)));
        this.setMaximumSize(new Dimension((int) (Util.screenWidth * 0.257), (int) (Util.screenHeight * 0.75)));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.btn = new RoundButton("   A D D   P A C K A G E   ", "ADD PACKAGE", al, Util.lightBrown,
                Util.darkBrown, (int) (Util.screenHeight * 0.08), Util.addPkgBtnTextFont2);
        this.title = new JLabel();
        this.clock = new JLabel("", SwingConstants.CENTER);
        this.currentDay = new JLabel("", SwingConstants.CENTER);

        setUpMainLeftScreen();
    }

    /**
     * Set up the right side of the main screen.
     */
    private void setUpMainLeftScreen() {

        this.title.setText("today is");
        this.title.setFont(Util.subTitleFont);

        //set alignments
        this.title.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.currentDay.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.clock.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.btn.setAlignmentX(Component.RIGHT_ALIGNMENT);

        //Update screen
        createTimeAndDate();
        setUpMainLeftScreenGrid();
        startClock();
    }

    /**
     * Sets up the clock and date on the screen.
     */
    private void createTimeAndDate() {
        today = LocalDate.now();
        //clock
        //set font
        this.clock.setFont(Util.clockFont);
        //set other things
        this.clock.setPreferredSize(new Dimension((int) (Util.screenWidth * 0.23),
                (int) (Util.screenHeight * 0.054)));
        this.clock.setBackground(Util.transparent);

        //date
        this.currentDay.setText(today.format(Util.currentDayFormat));
        this.currentDay.setFont(Util.mainScreenDateFont);
    }

    /**
     * Helper method to help set up the layout of the left screen.
     */
    private void setUpMainLeftScreenGrid() {
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.09))));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.048))));
        this.add(currentDay);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.04))));
        this.add(clock);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.04))));
        this.add(btn);
    }

    /**
     * Sets up clock and date. Starts timer for clock.
     */
    private void startClock() {
        //update date if cross over to next day
        Timer clockTime = new Timer(100, al -> {
            String formattedDateTime = LocalDateTime.now().format(Util.clockFormat);
            clock.setText(formattedDateTime.toUpperCase());

            //update date if cross over to next day
            if (today.isBefore(LocalDate.now())) {
                today = LocalDate.now();
                this.currentDay.setText(today.format(Util.currentDayFormat));
            }
            repaint();
        });

        clockTime.start();
        repaint();
    }
}
