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
    private final GridBagConstraints constraints;


    public Screens(ActionListener al) {

        this.btn = new RoundButton("E N T E R", "SCREEN BUTTON", al, Util.lightTeal, Util.midTeal);
        this.title = new JLabel();
        this.subtitle = new JLabel();
        this.clock = new JLabel("", SwingConstants.CENTER);
        this.currentDay = new JLabel("", SwingConstants.CENTER);
        this.constraints = new GridBagConstraints();

        this.setLayout(new GridBagLayout());
        createStartPanel();
    }

    public void switchToMainScreen() {
        this.btn.changeBtnText("A D D   P A C K A G E");
        this.btn.changeColours(Util.lightBrown,Util.darkBrown);
        this.title.setText("today is");
        this.subtitle.setText("subtitle");

        createTimeAndDate();
        this.removeAll();
        this.add(title);
        this.add(subtitle);
        this.add(btn);
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

        //text fields
        this.title.setText("P A C K A G E   D E L I V E R Y   T R A C K E R");
        this.subtitle.setText("Click enter to start.");
        this.title.setFont(Util.titleFont);
        this.title.setForeground(Color.BLACK);
        this.subtitle.setFont(Util.subTitleFont);

        resetConstraint(0,0);

        //background stuff
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight*0.2))), constraints);
        resetConstraint(0,0);
        this.add(title, constraints);
        resetConstraint(0,1);
        this.add(Box.createRigidArea(new Dimension(0,(int) (Util.screenHeight*0.06))), constraints);
        resetConstraint(0,2);
        this.add(subtitle, constraints);
        resetConstraint(0,3);
        this.add(Box.createRigidArea(new Dimension(0,(int) (Util.screenHeight*0.06))), constraints);
        resetConstraint(0,4);
        this.add(btn, constraints);
        resetConstraint(0,5);
        this.setBackground(Color.WHITE);
    }

    private void resetConstraint(int x, int y){

        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridx = x;
        constraints.gridy = y;
    }
}
