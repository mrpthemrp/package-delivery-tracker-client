package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
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
        this.btn.setText("A D D   P A C K A G E");
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
        this.clock.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //set other things
        this.clock.setPreferredSize(new Dimension((int) (Util.screenWidth * 0.23),
                (int) (Util.screenHeight * 0.054)));
        this.clock.setBackground(Color.WHITE);

        //date
        this.currentDay.setText(today.format(Util.currentDayFormat));
        this.currentDay.setFont(Util.mainScreenDateFont);
    }

    private void createStartPanel(ActionListener al) {

        //start button
        this.btn.setText("E N T E R");
        this.btn.setActionCommand("SCREEN BUTTON");
        this.btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.btn.setFocusPainted(false);
        this.btn.addActionListener(al);
        this.btn.setFont(Util.btnTextFont);
        this.btn.setBorderPainted(false);
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
