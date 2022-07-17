package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// reference: https://www.javacodex.com/More-Examples/2/14
public class RoundButton extends JButton {
    private final Color btnColor;
    private final Color btnColorDark;
    private final int height;

    public RoundButton(String btnText, String label, ActionListener al, Color btnColor, Color btnColorDark) {
        this.btnColor = btnColor;
        this.btnColorDark = btnColorDark;
        this.height = (int) (getHeight()+(Util.screenHeight*0.08));

        addActionListener(al);
        setActionCommand(label);
        setBackground(btnColor);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFont(Util.btnTextFont);
        setText(btnText);

    }

    @Override
    protected void paintComponent(Graphics g) {
        //button is pressed
        if (getModel().isRollover() | getModel().isPressed()) {
            g.setColor(btnColorDark);
            setForeground(Color.WHITE);
        //button is not pressed
        } else {
            g.setColor(btnColor);
            setForeground(Color.BLACK);
        }
        setSize(new Dimension(getWidth(), height));

        g.fillRoundRect(0, 0, getWidth(), getHeight(),
                (int) (getSize().height * 0.95), (int) (getSize().height * 0.95));

        super.paintComponent(g);
    }

}
