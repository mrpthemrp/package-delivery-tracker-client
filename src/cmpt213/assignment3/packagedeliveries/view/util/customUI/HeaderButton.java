package cmpt213.assignment3.packagedeliveries.view.util.customUI;

import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Custom button that is used in ColumnHeader. Inherits {@link JButton}.
 *
 * @author Deborah Wang
 */
public class HeaderButton extends JButton {
    public boolean clicked;

    /**
     * Constructor for HeaderButton. Sets look and feel and certain JButton fields.
     * Adds a {@link java.awt.event.MouseListener} that helps with changing colours on hover.
     *
     * @param name Name and ActionCommand of the button
     * @param al   ActionListener that button will add.
     */
    public HeaderButton(String name, ActionListener al) {
        clicked = false;
        this.setText(name);
        this.setActionCommand(name);
        this.setFocusPainted(false);
        this.setForeground(Util.midTeal);
        this.setBackground(Color.WHITE);
        this.setBorderPainted(false);
        this.addActionListener(al);
        this.setFont(Util.sortBtnsFont);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(Util.darkTeal);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!clicked) {
                    setForeground(Util.midTeal);
                }
            }
        });
    }

    /**
     * Updates the colour of the button on status change.
     */
    public void changeClickStatus() {
        if (clicked) {
            clicked = false;
            setForeground(Util.midTeal);
        } else {
            clicked = true;
            setForeground(Util.darkTeal);
        }
    }
}
