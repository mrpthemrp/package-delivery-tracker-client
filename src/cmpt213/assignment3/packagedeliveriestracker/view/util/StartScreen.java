package cmpt213.assignment3.packagedeliveriestracker.view.util;

import cmpt213.assignment3.packagedeliveriestracker.view.PackageDeliveryGUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

//clock reference : https://www.tutorialsbuddy.com/create-a-digital-clock-in-java

public class StartScreen extends JPanel implements ActionListener {
    private RoundButton btn;
    private final JLabel title;
    private final JLabel subtitle;
    private final GridBagConstraints constraints;

    private final PackageDeliveryGUI frame;

    public StartScreen(PackageDeliveryGUI frame) {

        this.frame = frame;
        this.setBackground(Color.WHITE);
        this.setSize(new Dimension((int) (Util.screenWidth * 0.75), (int) (Util.screenHeight * 0.75)));
        this.btn = new RoundButton("   E N T E R   ", "ENTER", this, Util.midTeal, Util.darkTeal);
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
    }

    public void hideScreen(){
        this.remove(btn);
        this.remove(title);
        this.remove(subtitle);
        this.setSize(new Dimension(0,0));
        this.setVisible(false);
        this.removeAll();
    }
    private void resetConstraint(int x, int y, int gridBagConstant) {
        constraints.fill = gridBagConstant;
        constraints.gridx = x;
        constraints.gridy = y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ENTER")) {
            System.out.println("start was pressed");
        }

        frame.updateStates();
    }
}
