package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

//clock reference : https://www.tutorialsbuddy.com/create-a-digital-clock-in-java

public class Screens extends JPanel {
    private LocalDate today;
    private final RoundButton btn;
    private final JLabel title;
    private final JLabel subtitle;
    private final JLabel clock;
    private final JLabel currentDay;


    public Screens(ActionListener al) {

        this.btn = new RoundButton("E N T E R", "SCREEN BUTTON", al, Util.lightBrown, Util.darkBrown);
        this.title = new JLabel();
        this.subtitle = new JLabel();
        this.clock = new JLabel("", SwingConstants.CENTER);
        this.currentDay = new JLabel("", SwingConstants.CENTER);

        createStartPanel();
    }

    public void switchToMainScreen() {
        this.btn.changeBtnText("A D D   P A C K A G E");
        this.title.setText("today is");
        this.subtitle.setText("subtitle");

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

    private void createStartPanel() {

        //start button
        this.btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        //text fields
        this.title.setText("P A C K A G E   D E L I V E R Y   T R A C K E R");
        this.subtitle.setText("Click enter to start.");
        this.title.setFont(Util.titleFont);
        this.title.setForeground(Color.GRAY);
        this.subtitle.setFont(Util.subTitleFont);
        this.title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        //background stuff
        this.add(btn);
        this.add(title);
        this.add(subtitle);
        this.setBackground(Color.WHITE);
    }
}
