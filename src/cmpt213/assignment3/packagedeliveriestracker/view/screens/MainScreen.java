package cmpt213.assignment3.packagedeliveriestracker.view.screens;

import cmpt213.assignment3.packagedeliveriestracker.view.util.PackageScrollPane;
import cmpt213.assignment3.packagedeliveriestracker.view.util.RoundButton;
import cmpt213.assignment3.packagedeliveriestracker.view.util.Util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

//clock reference : https://www.tutorialsbuddy.com/create-a-digital-clock-in-java

public class MainScreen extends JPanel {
    private final JLabel title;
    private final JLabel clock;
    private final JLabel currentDay;
    private final RoundButton btn;
    private LocalDate today;
    private ActionListener al;


    public MainScreen(ActionListener al) {
        this.al = al;
        this.setLayout(new FlowLayout());
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setBackground(Color.WHITE);
        this.setSize(new Dimension((int) (Util.screenWidth * 0.75 * 0.38), (int) (Util.screenHeight * 0.75)));
        this.setMaximumSize(new Dimension((int) (Util.screenWidth * 0.75 * (0.38)), (int) (Util.screenHeight * 0.75)));

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

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //draw line
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Util.midTeal);
        g2.fillRoundRect((int) (Util.screenWidth * 0.257), (int) (Util.screenHeight * 0.06),
                4, (int) (Util.screenHeight * 0.6), 3, 3);
    }

    private void setUpMainScreenLayout() {

        JPanel leftGroup = new JPanel();
        leftGroup.setLayout(new BoxLayout(leftGroup, BoxLayout.Y_AXIS));
        leftGroup.setBackground(Util.transparent);
        leftGroup.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftGroup.setSize(new Dimension((int) (Util.screenWidth / 3.5), (int) (this.getHeight() * 0.98)));
        leftGroup.setMaximumSize(new Dimension((int) (Util.screenWidth / 3.5), (int) (this.getHeight() * 0.98)));
        leftGroup.setBorder(new LineBorder(Color.BLACK,5));

        leftGroup.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.18))));
        leftGroup.add(title);
        leftGroup.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.048))));
        leftGroup.add(currentDay);
        leftGroup.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.04))));
        leftGroup.add(clock);
        leftGroup.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.04))));
        leftGroup.add(btn);
        leftGroup.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.19))));

        JPanel middleGroup = new JPanel();
        middleGroup.setLayout(new BoxLayout(middleGroup, BoxLayout.Y_AXIS));
        middleGroup.setBackground(Util.transparent);
        middleGroup.setAlignmentX(Component.RIGHT_ALIGNMENT);
        middleGroup.setSize(new Dimension((int) (Util.screenWidth * 0.1), (int) (this.getHeight() * 0.98)));
        middleGroup.setMaximumSize(new Dimension((int) (Util.screenWidth * 0.1), (int) (this.getHeight() * 0.98)));
        middleGroup.setBorder(new LineBorder(Color.BLACK,5));

        middleGroup.add(Box.createRigidArea(new Dimension((int) (Util.screenWidth * 0.03), 0)));

        JPanel rightGroup = new JPanel();
        rightGroup.setLayout(new BoxLayout(rightGroup, BoxLayout.Y_AXIS));
        rightGroup.setBackground(Util.transparent);
        rightGroup.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightGroup.setSize(new Dimension((int) (Util.screenWidth / 2.2), (int) (this.getHeight() * 0.98)));
        rightGroup.setMaximumSize(new Dimension((int) (Util.screenWidth / 2.2), (int) (this.getHeight() * 0.98)));
        rightGroup.setBorder(new LineBorder(Color.BLACK, 5));

        rightGroup.add(Box.createRigidArea(new Dimension(rightGroup.getWidth(),rightGroup.getHeight())));

        this.add(leftGroup);
        this.add(middleGroup);
//        this.add(rightGroup);
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
    }


}
