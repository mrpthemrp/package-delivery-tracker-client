package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
    private final GridBagConstraints constraints;
    private JScrollPane packageScroll;
    private LocalDate today;


    public Screens(ActionListener al) {

        this.setPreferredSize(new Dimension((int)(Util.screenWidth*0.75),(int)(Util.screenHeight*0.75)));

        this.btn = new RoundButton("E N T E R", "SCREEN BUTTON", al, Util.midTeal, Util.darkTeal);
        this.title = new JLabel();
        this.subtitle = new JLabel();
        this.clock = new JLabel("", SwingConstants.CENTER);
        this.currentDay = new JLabel("", SwingConstants.CENTER);
        this.packageScroll = new JScrollPane();
        this.constraints = new GridBagConstraints();

        createStartPanel();
    }

    public void changeSubtitle(String newText) {
        this.subtitle.setText(newText);
    }

    public void switchToMainScreen() {
        this.removeAll();
        this.setLayout(new FlowLayout());
        this.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Update components
        this.btn.changeBtnText("A D D   P A C K A G E");
        this.btn.changeColours(Util.lightBrown, Util.darkBrown);

        this.title.setText("today is");
        this.title.setFont(Util.subTitleFont);

        this.packageScroll.createVerticalScrollBar();
        this.packageScroll.setLayout(new ScrollPaneLayout());

        //set alignments
        this.title.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.currentDay.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.clock.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.packageScroll.setAlignmentX(Component.RIGHT_ALIGNMENT);

        //Update screen
        createTimeAndDate();
        setUpMainScreenLayout();
        startClock();
    }

    private void createStartPanel() {
        this.setLayout(new GridBagLayout());

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

    private void setUpMainScreenLayout() {

        JPanel leftGroup = new JPanel();
        leftGroup.setLayout(new BoxLayout(leftGroup, BoxLayout.Y_AXIS));
        leftGroup.setBackground(new Color (255,255,255,0));
        leftGroup.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftGroup.setPreferredSize(new Dimension((int) (Util.screenWidth/3.5), (int) (this.getHeight()*0.1)));
        leftGroup.setBorder(new LineBorder(Color.BLACK,5));
        leftGroup.add(title);
        leftGroup.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))));
        leftGroup.add(currentDay);
        leftGroup.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))));
        leftGroup.add(clock);
        leftGroup.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))));
        leftGroup.add(btn);

        JPanel rightGroup = new JPanel();
        rightGroup.setLayout(new BoxLayout(rightGroup, BoxLayout.Y_AXIS));
        rightGroup.setBackground(new Color (255,255,255,0));
        rightGroup.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightGroup.setPreferredSize(new Dimension((int) (Util.screenWidth/2.3), (int) (this.getHeight()*0.1)));
        rightGroup.setBorder(new LineBorder(Color.BLACK,5));
        rightGroup.add(packageScroll);

        this.add(leftGroup);
        this.add(rightGroup);
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
        });

        clockTime.start();
    }
}
