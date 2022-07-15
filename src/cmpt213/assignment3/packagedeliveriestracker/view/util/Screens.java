package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDate;
import java.time.LocalDateTime;

//clock reference : https://www.tutorialsbuddy.com/create-a-digital-clock-in-java

public class Screens extends JPanel {
    private LocalDate today;
    private JButton btn;
    private JLabel title, clock, currentDay;


    public Screens(ActionListener al) {

        this.btn = new JButton();
        this.title = new JLabel();
        this.clock = new JLabel("", SwingConstants.CENTER);
        this.currentDay = new JLabel("", SwingConstants.CENTER);

        createStartPanel(al);
    }

    public void switchToMainScreen() {
        this.setBackground(Util.darkBrown);
        this.btn.setText("ADD PACKAGE");
        this.title.setText("today is");

        createTimeAndDate();
        this.add(clock);
        this.add(currentDay);


        //start time for clock
        startClock();
    }
    public void startClock(){
        //update date if cross over to next day
        Timer clockTime = new Timer(100, al -> {
            String formattedDateTime = LocalDateTime.now().format(Util.clockFormat);
            clock.setText(formattedDateTime.toUpperCase());

            //update date if cross over to next day
            if (today.isBefore(LocalDate.now())) {
                today = LocalDate.now();
                this.currentDay.setText(today.format(Util.currentDayFormat));
            }
        });

        clockTime.start();
    }

    private void createTimeAndDate() {
        today = LocalDate.now();
        //clock
        //set font
        this.clock.setFont(Util.clockFont);
        this.clock.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        //set other things
        this.clock.setPreferredSize(new Dimension((int) (Util.screenWidth * 0.23),
                (int) (Util.screenHeight * 0.054)));
        this.clock.setBackground(Color.WHITE);

        //date
        this.currentDay.setText(today.format(Util.currentDayFormat));
    }

    private void createStartPanel(ActionListener al) {

        //start button
        this.btn.setText("ENTER");
        this.btn.setActionCommand("SCREEN BUTTON");
        this.btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.btn.setFocusPainted(false);
        this.btn.addActionListener(al);
        this.btn.setBackground(Util.lightBrown);

        //text fields
        this.title.setText("Start");
        this.title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //background stuff
        this.add(btn);
        this.add(title);
        this.setBackground(Color.WHITE);
    }
}
