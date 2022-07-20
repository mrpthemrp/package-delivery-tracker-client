package cmpt213.assignment3.packagedeliveries.view.util.customUi;

import cmpt213.assignment3.packagedeliveries.view.PackageDeliveryGUI;
import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//reference: http://www2.hawaii.edu/~takebaya/ics111/jdialog/jdialog.html
public class CustomDialog extends JDialog implements ActionListener {
    public boolean isYes;

    public CustomDialog(Frame parent, String title, String btnYesText, String btnNoText, JPanel contentPane) {
        super(parent, title, true);
        this.isYes = false;
        this.setSize(new Dimension((int) (Util.screenWidth * 0.4), (int) (Util.screenHeight * 0.25)));

        JPanel buttonPane = new JPanel();
        RoundButton yesBtn = new RoundButton(btnYesText, "YES", this, Util.greenLight, Util.greenDark
                , (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);
        RoundButton noBtn = new RoundButton(btnNoText, "NO", this, Util.redLight, Util.redDark
                , (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);

        buttonPane.setBackground(Color.WHITE);
        buttonPane.add(yesBtn);
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(noBtn);

        contentPane.add(buttonPane);

        this.add(contentPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("YES")) {
            System.out.println("yes was pressed");
            this.isYes = true;
        } else if (e.getActionCommand().equals("NO")) {
            System.out.println("no was pressed");
            this.isYes = false;
        }
        this.dispose();

    }

    public final void run() {
        this.setVisible(true);
    }

    public boolean getIsYes() {
        return this.isYes;
    }
}
