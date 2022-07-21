package cmpt213.assignment3.packagedeliveries.view.util.customUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * Custom class that creates a Rounded JButton; inherits from {@link JButton}
 *
 * @author Deborah Wang
 * @link <a href="https://www.javacodex.com/More-Examples/2/14">...</a> Reference for writing this class.
 */
public class RoundButton extends JButton {
    private final Color btnColor;
    private final Color btnColorDark;
    private final int height;

    /**
     * Constructor for RoundButton, sets fields and certain JButton fields.
     *
     * @param btnText      Text of Button
     * @param label        ActionCommand of Button
     * @param al           ActionListener that the button will add.
     * @param btnColor     Normal colour of button.
     * @param btnColorDark Colour that will appear on button hover or selection.
     * @param height       Height of the button.
     * @param font         Font that button text uses.
     */
    public RoundButton(String btnText, String label, ActionListener al, Color btnColor,
                       Color btnColorDark, int height, Font font) {
        this.btnColor = btnColor;
        this.btnColorDark = btnColorDark;
        this.height = getHeight() + height;

        addActionListener(al);
        setActionCommand(label);
        setBackground(btnColor);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFont(font);
        setText(btnText);

    }

    /**
     * Paints the button to screen.
     *
     * @param g the <code>Graphics</code> object to protect
     */
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
