package cmpt213.assignment4.packagedeliveries.client.view.util.customUI;

import cmpt213.assignment4.packagedeliveries.client.view.screens.MainScreenRight;
import cmpt213.assignment4.packagedeliveries.client.view.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Custom Dialog that uses RoundButtons. Inherits {@link JDialog}.
 * Implements how buttons look like on click.
 *
 * @author Deborah Wang
 * @link <a href="http://www2.hawaii.edu/~takebaya/ics111/jdialog/jdialog.html">...</a> Reference for writing class
 */
public class CustomDialog extends JDialog implements ActionListener {
    public boolean isRemove, dispose;
    private int panelItemIndex;
    private int pkgIndex;

    public CustomDialog(Frame parent, String title, String message, String btnYesText, String btnNoText,
                        boolean isRemove, boolean modal) {

        super(parent, title, modal);
        this.isRemove = isRemove;
        this.dispose = true;
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

    /**
     * Determines whether the dialog will be closed when a button is selected.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("YES")) {
            if (isRemove) {
                MainScreenRight.deleteAPackage(this.pkgIndex, this.panelItemIndex);
            } else {
                dispose = false;
            }
        } else if (e.getActionCommand().equals("NO")) {
            dispose = true;
        }
        dispose();
    }

    /**
     * Invokes the dialog without needing to use Runnable.
     *
     * @param panelItemIndex Index of a PackageItem object in a JPanel ArrayList.
     * @param pkgIndex       Index of a PackageBase object in a PackageBase ArrayList.
     */
    public void run(int panelItemIndex, int pkgIndex) {
        this.pkgIndex = pkgIndex;
        this.panelItemIndex = panelItemIndex;
        this.setVisible(true);
    }

}
