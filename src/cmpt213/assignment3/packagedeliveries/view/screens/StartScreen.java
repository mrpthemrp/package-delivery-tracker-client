package cmpt213.assignment3.packagedeliveries.view.screens;

import cmpt213.assignment3.packagedeliveries.view.util.customUi.RoundButton;
import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//clock reference : https://www.tutorialsbuddy.com/create-a-digital-clock-in-java

public class StartScreen extends JPanel{
    private final RoundButton btn;
    private final JLabel title;
    private final JLabel subtitle;
    private final GridBagConstraints constraints;

    public StartScreen(ActionListener al) {

        this.setBackground(Color.WHITE);
        this.setSize(new Dimension((int) (Util.screenWidth * 0.75), (int) (Util.screenHeight * 0.75)));
        this.btn = new RoundButton("   E N T E R   ", "ENTER", al, Util.midTeal, Util.darkTeal,
                (int) (Util.screenHeight*0.08), Util.enterBtnTextFont);
        this.title = new JLabel();
        this.subtitle = new JLabel();
        this.constraints = new GridBagConstraints();

        createStartPanel();
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
        resetConstraint(0);
        this.add(title, constraints);
        resetConstraint(1);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))), constraints);
        resetConstraint(2);
        this.add(subtitle, constraints);
        resetConstraint(3);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))), constraints);
        resetConstraint(4);
        this.add(btn, constraints);
    }

    private void resetConstraint(int y) {
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = y;
    }

}
