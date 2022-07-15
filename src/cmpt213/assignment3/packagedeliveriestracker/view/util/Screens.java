package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

//clock reference : https://www.tutorialsbuddy.com/create-a-digital-clock-in-java

public class Screens extends JPanel {
    private JButton btn;
    private JTextField title;
    private JLabel clock;
    private Timer clockTime;

    public Screens(ActionListener al) {

        this.btn = new JButton();
        this.title = new JTextField();
        this.clock = new JLabel("", SwingConstants.CENTER);

        createStartPanel(al);
    }

    public void switchToMainScreen() {
        this.setBackground(Util.darkBrown);
        this.btn.setText("ADD PACKAGE");
        this.title.setText("today is");
        createClock();
        this.add(clock);


        //start time for clock
        startClock();
    }
    public void startClock(){
        clockTime = new Timer(100, al -> {
            LocalDateTime now = LocalDateTime.now();
            String formattedDateTime = now.format(Util.clockFormat);
            clock.setText(formattedDateTime.toUpperCase());
        });


        clockTime.start();
    }

    private void createClock() {
        //set font
//        this.clock.setFont(Util.clockFont);
        //set other things
        this.clock.setPreferredSize(new Dimension((int) (Util.screenSize.getWidth() * 0.15),
                (int) (Util.screenSize.getHeight() * 0.25)));
        this.clock.setBackground(Color.WHITE);
    }

    private void createStartPanel(ActionListener al) {

        //start button
        this.btn.setText("ENTER");
        this.btn.setActionCommand("SCREEN BUTTON");
        this.btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.btn.addActionListener(al);
        this.btn.setFocusPainted(false);
        this.btn.setBackground(Util.lightBrown);

        //text fields
        this.title = new JTextField("TITLE");
        this.title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.title.setEditable(false);

        //background stuff
        this.add(btn);
        this.add(title);
        this.setBackground(Color.WHITE);
    }
}
