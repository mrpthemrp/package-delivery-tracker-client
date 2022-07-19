package cmpt213.assignment3.packagedeliveries.view.util.customUi;

import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// reference: https://www.javacodex.com/More-Examples/2/14
public class RoundButton extends JButton {
    private final Color btnColor;
    private final Color btnColorDark;
    private final int height;

    public RoundButton(String btnText, String label, ActionListener al, Color btnColor,
                       Color btnColorDark, int height, Font font) {
        this.btnColor = btnColor;
        this.btnColorDark = btnColorDark;
        this.height = getHeight()+height;

        addActionListener(al);
        setActionCommand(label);
        setBackground(btnColor);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFont(font);
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