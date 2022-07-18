package cmpt213.assignment3.packagedeliveriestracker.view.screens;

import cmpt213.assignment3.packagedeliveriestracker.view.util.RoundButton;
import cmpt213.assignment3.packagedeliveriestracker.view.util.Util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

//clock reference : https://www.tutorialsbuddy.com/create-a-digital-clock-in-java

public class MainScreenLeft extends JPanel {
    private final JLabel title;
    private final JLabel clock;
    private final JLabel currentDay;
    private final RoundButton btn;
    private LocalDate today;
    private ActionListener al;


    public MainScreenLeft(ActionListener al) {
        this.al = al;
        this.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.setBackground(Color.WHITE);
        this.setSize(new Dimension((int) (Util.screenWidth * 0.257), (int) (Util.screenHeight * 0.75)));
        this.setMaximumSize(new Dimension((int) (Util.screenWidth * 0.257 ), (int) (Util.screenHeight * 0.75)));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.btn = new RoundButton("   A D D   P A C K A G E   ", "ADD PACKAGE", al, Util.lightBrown, Util.darkBrown);
        this.btn.setFont(Util.btnTextFont2);
        this.title = new JLabel();
        this.clock = new JLabel("", SwingConstants.CENTER);
        this.currentDay = new JLabel("", SwingConstants.CENTER);

        createMainScreen();
    }

    public void createMainScreen() {

        this.title.setText("today is");
        this.title.setFont(Util.subTitleFont);

        //set alignments
        this.title.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.currentDay.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.clock.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.btn.setAlignmentX(Component.RIGHT_ALIGNMENT);

        //Update screen
        createTimeAndDate();
        setUpMainScreenLayout();
        startClock();
    }

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




    private void setUpMainScreenLayout() {
        this.add(Box.createRigidArea(new Dimension((int) (Util.screenHeight * 0.05), 0)));
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.2))));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.048))));
        this.add(currentDay);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.04))));
        this.add(clock);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.04))));
        this.add(btn);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.2))));
    }

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
