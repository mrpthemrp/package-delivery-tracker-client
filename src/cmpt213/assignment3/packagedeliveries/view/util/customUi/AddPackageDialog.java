package cmpt213.assignment3.packagedeliveries.view.util.customUi;

import cmpt213.assignment3.packagedeliveries.control.PackageDeliveryControl;
import cmpt213.assignment3.packagedeliveries.model.PackageFactory;
import cmpt213.assignment3.packagedeliveries.view.PackageDeliveryGUI;
import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class AddPackageDialog extends JDialog implements ActionListener {
    private final CustomDialog exitConfirmDialog;
    private final JPanel contentPane;
    public PackageDeliveryControl control;

    public AddPackageDialog(Frame parent, String title, String btnYesText, String btnNoText, PackageDeliveryControl control) {
        super(parent, title, true);
        this.control = control;
        this.exitConfirmDialog = new CustomDialog(parent, "Remove Package Confirmation",
                "Are you sure you want to exit?",
                "  S T A Y  ", "  E X I T  ", false, true);
        this.setSize(new Dimension((int) (Util.screenWidth * 0.5), (int) (Util.screenHeight * 0.65)));

        JPanel buttonPane = new JPanel();
        RoundButton yesBtn = new RoundButton(btnYesText, "YES", this, Util.lightBrown,
                Util.darkBrown, (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);
        RoundButton noBtn = new RoundButton(btnNoText, "NO", this, Util.midTeal,
                Util.darkTeal, (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);

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
            control.createPackage("Name", "", 50, 0.666, LocalDateTime.now(), "author", PackageFactory.PackageType.BOOK);
            dispose();
            //do something
        } else if (e.getActionCommand().equals("NO")) {
            exitConfirmDialog.run(-1,-1);
            if(!exitConfirmDialog.isRemove()){
                dispose();
            }
        }
    }

}
