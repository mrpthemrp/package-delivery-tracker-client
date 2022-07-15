package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// reference: https://www.javacodex.com/More-Examples/2/14
public class RoundButton extends JButton {
    private Dimension buttonDimension;
    private String label, btnText;
    private Color btnColor, btnColorDark;

    public RoundButton(String btnText, String label, ActionListener al, Color btnColor, Color btnColorDark) {
        this.label = label;
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
        buttonDimension = new Dimension((int)(g.getFontMetrics().stringWidth(this.btnText)*1.4),
                (int)(Util.screenHeight * 0.05));
        setPreferredSize(buttonDimension);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(btnColor);
        g.drawRoundRect(0,0,getWidth(),getHeight(),6,6);
    }

    @Override
    public void paint(Graphics g) {
        setUpDimensions(g);
        super.paint(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //button is pressed
        if( getModel().isRollover() | getModel().isPressed()){
            setBackground(btnColorDark);
            setForeground(Color.WHITE);
        } else{
            setBackground(btnColor);
            setForeground(Color.BLACK);
        }
        super.paintComponent(g);
    }

    public void changeBtnText(String newText){
        setText(newText);
        this.btnText = newText;
    }

}
