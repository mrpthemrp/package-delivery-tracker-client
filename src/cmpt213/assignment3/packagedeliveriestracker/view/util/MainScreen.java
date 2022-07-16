package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

//clock reference : https://www.tutorialsbuddy.com/create-a-digital-clock-in-java

public class MainScreen extends JPanel {
    private final JLabel title;
    private final JLabel clock;
    private final JLabel currentDay;
    private final RoundButton btn;
    private JScrollPane packageScroll;
    private LocalDate today;


    public MainScreen(ActionListener al) {
        this.setLayout(new FlowLayout());
        this.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension((int) (Util.screenWidth * 0.75), (int) (Util.screenHeight * 0.75)));
        this.btn = new RoundButton("A D D   P A C K A G E", "SCREEN BUTTON", al, Util.lightBrown, Util.darkBrown);
        this.title = new JLabel();
        this.clock = new JLabel("", SwingConstants.CENTER);
        this.currentDay = new JLabel("", SwingConstants.CENTER);
        this.packageScroll = new JScrollPane();

        createMainScreen();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //draw line
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Util.lightTeal);
        g2.fillRoundRect((int) (Util.screenWidth * 0.30), (int) (Util.screenHeight * 0.11),
                4, (int) (Util.screenHeight * 0.55), 3, 3);
    }

    public void createMainScreen() {

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

    private void createTimeAndDate() {
        today = LocalDate.now();
        //clock
        //set font
        this.clock.setFont(Util.clockFont);
        //set other things
        this.clock.setPreferredSize(new Dimension((int) (Util.screenWidth * 0.23),
                (int) (Util.screenHeight * 0.054)));
        this.clock.setBackground(Color.WHITE);

        //date
        this.currentDay.setText(today.format(Util.currentDayFormat));
        this.currentDay.setFont(Util.mainScreenDateFont);
    }


    private void setUpMainScreenLayout() {

        JPanel leftGroup = new JPanel();
        leftGroup.setLayout(new BoxLayout(leftGroup, BoxLayout.Y_AXIS));
        leftGroup.setBackground(new Color(255, 255, 255, 0));
        leftGroup.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftGroup.setPreferredSize(new Dimension((int) (Util.screenWidth / 3.5), (int) (this.getHeight() * 0.98)));
        leftGroup.setBorder(new LineBorder(Color.BLACK, 5));
        leftGroup.add(title);
        leftGroup.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))));
        leftGroup.add(currentDay);
        leftGroup.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))));
        leftGroup.add(clock);
        leftGroup.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))));
        leftGroup.add(btn);

        JPanel rightGroup = new JPanel();
        rightGroup.setLayout(new BoxLayout(rightGroup, BoxLayout.Y_AXIS));
        rightGroup.setBackground(new Color(255, 255, 255, 0));
        rightGroup.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightGroup.setPreferredSize(new Dimension((int) (Util.screenWidth / 2.3), (int) (this.getHeight() * 0.98)));
        rightGroup.setBorder(new LineBorder(Color.BLACK, 5));
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
