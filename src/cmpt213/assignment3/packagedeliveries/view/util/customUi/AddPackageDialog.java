package cmpt213.assignment3.packagedeliveries.view.util.customUi;

import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPackageDialog extends JDialog implements ActionListener {
    private final CustomDialog exitConfirmDialog;
    private final JPanel contentPane;
    public AddPackageDialog(Frame parent, String title, String btnYesText, String btnNoText) {
        super(parent, title, true);
        this.exitConfirmDialog = new CustomDialog(parent,"Remove Package Confirmation"
                        ,"  S T A Y  ","  E X I T  ", new DialogContent("Are you sure you want to exit?"));;
        this.setSize(new Dimension((int) (Util.screenWidth * 0.4), (int) (Util.screenHeight * 0.25)));

        JPanel buttonPane = new JPanel();
        RoundButton yesBtn = new RoundButton(btnYesText, "YES", this, Util.midTeal, Util.darkTeal
                , (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);
        RoundButton noBtn = new RoundButton(btnNoText, "NO", this, Util.lightBrown, Util.darkBrown
                , (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);

        buttonPane.setBackground(Color.WHITE);
        buttonPane.add(yesBtn);
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(noBtn);

        contentPane = new JPanel();
        contentPane.add(buttonPane);

        this.add(contentPane);

    }

    public final void run() {
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("YES")) {
            System.out.println("yes was pressed");
            //do something
        } else if (e.getActionCommand().equals("NO")) {
            System.out.println("no was pressed");
        }

        dispose();
    }

}
