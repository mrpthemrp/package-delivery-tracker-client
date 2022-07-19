package cmpt213.assignment3.packagedeliveries.view.util.customUi;

import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import java.awt.*;

public class DialogContent extends JPanel {

    public DialogContent(String message) {
        JLabel dialogMessage = new JLabel(message);

        dialogMessage.setFont(Util.dialogMessageFont);
        dialogMessage.setForeground(Color.BLACK);
        dialogMessage.setBackground(Util.transparent);
        dialogMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(this.getWidth(), (int) (Util.screenHeight * 0.05))), CENTER_ALIGNMENT);
        this.add(dialogMessage, CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, (int) (Util.screenHeight * 0.03))), CENTER_ALIGNMENT);
    }
}
