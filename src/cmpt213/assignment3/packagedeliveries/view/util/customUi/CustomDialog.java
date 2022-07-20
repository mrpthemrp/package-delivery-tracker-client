package cmpt213.assignment3.packagedeliveries.view.util.customUi;

import cmpt213.assignment3.packagedeliveries.view.screens.MainScreenRight;
import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//reference: http://www2.hawaii.edu/~takebaya/ics111/jdialog/jdialog.html
public class CustomDialog extends JDialog implements ActionListener {
    private final boolean isRemove;

    public CustomDialog(Frame parent, String title, String message, String btnYesText, String btnNoText, boolean isRemove) {

        super(parent, title, false);
        this.isRemove = isRemove;
        this.setSize(new Dimension((int) (Util.screenWidth * 0.4), (int) (Util.screenHeight * 0.25)));

        JLabel dialogMessage = new JLabel(message);
        dialogMessage.setFont(Util.dialogMessageFont);
        dialogMessage.setForeground(Color.BLACK);
        dialogMessage.setBackground(Util.transparent);
        dialogMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPane = new JPanel();
        RoundButton yesBtn = new RoundButton(btnYesText, "YES", this, Util.greenLight, Util.greenDark
                , (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);
        RoundButton noBtn = new RoundButton(btnNoText, "NO", this, Util.redLight, Util.redDark
                , (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);

        buttonPane.setBackground(Color.WHITE);
        buttonPane.add(yesBtn);
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(noBtn);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(Box.createRigidArea(new Dimension(this.getWidth(), (int) (Util.screenHeight * 0.05))), CENTER_ALIGNMENT);
        contentPane.add(dialogMessage, CENTER_ALIGNMENT);
        contentPane.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.03))), CENTER_ALIGNMENT);
        contentPane.add(buttonPane);
        contentPane.setVisible(true);

        this.add(contentPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("YES")) {
            System.out.println("yes was pressed");
            if(isRemove){
                MainScreenRight.updatePackages(true);
            }
        } else if (e.getActionCommand().equals("NO")) {
            System.out.println("no was pressed");
        }
        this.dispose();
    }

    public void run() {
        this.setVisible(true);
    }

}
