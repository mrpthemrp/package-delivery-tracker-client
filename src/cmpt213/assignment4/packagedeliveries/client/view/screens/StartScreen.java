package cmpt213.assignment4.packagedeliveries.client.view.screens;

import cmpt213.assignment4.packagedeliveries.client.view.util.Util;
import cmpt213.assignment4.packagedeliveries.client.view.util.customUI.RoundButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Custom JPanel that holds the UI for the start screen.
 *
 * @author Deborah Wang
 */

public class StartScreen extends JPanel {
    private final RoundButton btn;
    private final JLabel title;
    private final JLabel subtitle1;
    private final JLabel subtitle2;
    private final GridBagConstraints constraints;

    /**
     * Constructor for object, sets look and feel. Initializes final fields.
     *
     * @param al ActionListener that ENTER button will add.
     */
    public StartScreen(ActionListener al) {

        this.setBackground(Color.WHITE);
        this.setSize(new Dimension((int) (Util.screenWidth * 0.75), (int) (Util.screenHeight * 0.75)));
        this.btn = new RoundButton("   E N T E R   ", "ENTER", al, Util.midTeal, Util.darkTeal,
                (int) (Util.screenHeight * 0.08), Util.enterBtnTextFont);
        this.title = new JLabel();
        this.subtitle1 = new JLabel();
        this.subtitle2 = new JLabel();
        this.constraints = new GridBagConstraints();

        createStartPanel();
    }

    /**
     * Sets up the UI of the start screen.
     */
    private void createStartPanel() {
        this.setLayout(new GridBagLayout());

        //text fields
        this.title.setText("P A C K A G E   D E L I V E R Y   T R A C K E R");
        this.subtitle1.setText("Welcome to your personal package tracker.");
        this.subtitle2.setText("Click ENTER to start!");
        this.title.setFont(Util.titleFont);
        this.title.setForeground(Color.BLACK);
        this.subtitle1.setForeground(Color.DARK_GRAY);
        this.subtitle2.setForeground(Color.DARK_GRAY);
        this.subtitle1.setFont(Util.subTitleFont);
        this.subtitle2.setFont(Util.subTitleFont);

        //background stuff
        resetConstraint(0);
        this.add(title, constraints);
        resetConstraint(1);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.04))), constraints);
        resetConstraint(2);
        this.add(subtitle1, constraints);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))), constraints);
        resetConstraint(3);
        this.add(subtitle2, constraints);
        resetConstraint(4);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.06))), constraints);
        resetConstraint(5);
        this.add(btn, constraints);
    }

    /**
     * Helper method that helps reset the GridBagConstraint
     *
     * @param y The y index of the GridBagConstraint to be set
     */
    private void resetConstraint(int y) {
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = y;
    }

}
