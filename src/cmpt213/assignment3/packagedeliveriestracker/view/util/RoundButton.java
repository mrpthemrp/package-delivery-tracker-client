package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// reference: https://www.javacodex.com/More-Examples/2/14
public class RoundButton extends JButton {
    private Color btnColor;
    private Color btnColorDark;
    private String btnText;

    public RoundButton(String btnText, String label, ActionListener al, Color btnColor, Color btnColorDark) {
        this.btnText = btnText;
        this.btnColor = btnColor;
        this.btnColorDark = btnColorDark;

        setActionCommand(label);
        setBackground(btnColor);
        setText(btnText);
        setFont(Util.btnTextFont);
        setContentAreaFilled(false);
        setFocusPainted(false);
        addActionListener(al);
        setBorderPainted(false);
    }

    private void setUpDimensions(Graphics g) {
        //makes sure that the width is not too long
        double width = g.getFontMetrics().stringWidth(this.btnText) * 1.8;
        if(width > g.getFontMetrics().stringWidth(this.btnText) + 80){
            width = g.getFontMetrics().stringWidth(this.btnText) * 1.4;
        }

        this.setSize(new Dimension((int) (width), (int) (Util.screenHeight * 0.085)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        setUpDimensions(g);
        //button is pressed
        if (getModel().isRollover() | getModel().isPressed()) {
            g.setColor(btnColorDark);
            setForeground(Color.WHITE);
        //button is not pressed
        } else {
            g.setColor(btnColor);
            setForeground(Color.BLACK);
        }

        g.fillRoundRect(0, 0, getSize().width, getSize().height, (int) (getSize().height * 0.8), (int) (getSize().height * 0.8));

        super.paintComponent(g);
    }

    public void changeBtnText(String newText) {
        setText(newText);
        this.btnText = newText;
    }

    public void changeColours(Color newCol, Color newDarkCol){
        this.btnColor = newCol;
        this.btnColorDark = newDarkCol;
    }

}
