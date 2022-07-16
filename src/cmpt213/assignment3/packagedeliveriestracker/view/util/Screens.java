package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

//clock reference : https://www.tutorialsbuddy.com/create-a-digital-clock-in-java

public class Screens extends JPanel {
    private final RoundButton btn;
    private final JLabel title;
    private final JLabel subtitle;
    private final JLabel clock;
    private final JLabel currentDay;

    private JScrollPane packageScroll;
    private final GridBagConstraints constraints;
    private LocalDate today;


    public Screens(ActionListener al) {

        this.btn = new RoundButton("E N T E R", "SCREEN BUTTON", al, Util.lightTeal, Util.midTeal);
        this.title = new JLabel();
        this.subtitle = new JLabel();
        this.clock = new JLabel("", SwingConstants.CENTER);
        this.currentDay = new JLabel("", SwingConstants.CENTER);
        this.packageScroll = new JScrollPane();
        this.constraints = new GridBagConstraints();

        this.setLayout(new GridBagLayout());
        createStartPanel();
    }

    private void createStartPanel() {

        //text fields
        this.title.setText("P A C K A G E   D E L I V E R Y   T R A C K E R");
        this.subtitle.setText("Welcome to your personal package tracker! Click ENTER to start.");
        this.title.setFont(Util.titleFont);
        this.title.setForeground(Color.BLACK);
        this.subtitle.setFont(Util.subTitleFont);

        //background stuff
        resetConstraint(0, 0, GridBagConstraints.CENTER);
        this.add(title, constraints);
        resetConstraint(0, 1, GridBagConstraints.CENTER);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))), constraints);
        resetConstraint(0, 2, GridBagConstraints.CENTER);
        this.add(subtitle, constraints);
        resetConstraint(0, 3, GridBagConstraints.CENTER);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))), constraints);
        resetConstraint(0, 4, GridBagConstraints.CENTER);
        this.add(btn, constraints);
        this.setBackground(Color.WHITE);
    }


    public void switchToMainScreen() {
        this.btn.changeBtnText("A D D   P A C K A G E");
        this.btn.changeColours(Util.lightBrown, Util.darkBrown);
        this.btn.setAlignmentX(JButton.RIGHT_ALIGNMENT);
        this.title.setText("today is");
        this.title.setFont(Util.subTitleFont);
        this.title.setAlignmentX(JLabel.CENTER);
        this.packageScroll.createVerticalScrollBar();
        this.packageScroll.setLayout(new ScrollPaneLayout());

        createTimeAndDate();
        this.removeAll();
        this.setLayout(new GridBagLayout());
        resetConstraint(0, 0, GridBagConstraints.HORIZONTAL);
        this.add(title, constraints);
        resetConstraint(0, 1, GridBagConstraints.HORIZONTAL);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.001))), constraints);
        resetConstraint(0, 2, GridBagConstraints.HORIZONTAL);
        this.add(currentDay, constraints);
        resetConstraint(0, 3, GridBagConstraints.HORIZONTAL);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.001))), constraints);
        resetConstraint(0, 4, GridBagConstraints.HORIZONTAL);
        this.add(clock, constraints);
        resetConstraint(0, 5, GridBagConstraints.HORIZONTAL);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.001))), constraints);
        resetConstraint(0, 6, GridBagConstraints.HORIZONTAL);
        this.add(btn, constraints);


        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.001))), constraints);
        resetConstraint(1, 0, GridBagConstraints.HORIZONTAL);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.08))), constraints);
        resetConstraint(1, 1, GridBagConstraints.HORIZONTAL);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.08))), constraints);
        resetConstraint(1, 2, GridBagConstraints.HORIZONTAL);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))), constraints);
        resetConstraint(1, 3, GridBagConstraints.HORIZONTAL);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))), constraints);
        resetConstraint(1, 4, GridBagConstraints.HORIZONTAL);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))), constraints);
        resetConstraint(1, 5, GridBagConstraints.HORIZONTAL);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))), constraints);
        resetConstraint(1, 6, GridBagConstraints.HORIZONTAL);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))), constraints);
        resetConstraint(2, 0, GridBagConstraints.HORIZONTAL);
        this.add(packageScroll,constraints);


        //start time for clock
        startClock();
    }

    public void startClock() {
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
        this.clock.setAlignmentX(JLabel.RIGHT_ALIGNMENT);

        //date
        this.currentDay.setText(today.format(Util.currentDayFormat));
        this.currentDay.setFont(Util.mainScreenDateFont);
        this.currentDay.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
    }

    private void resetConstraint(int x, int y, int gridBagConstant) {
        constraints.fill = gridBagConstant;
        constraints.gridx = x;
        constraints.gridy = y;
    }

    public void changeSubtitle(String newText){
        this.subtitle.setText(newText);
    }
}
