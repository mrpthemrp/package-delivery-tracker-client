package cmpt213.assignment3.packagedeliveriestracker.view.custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeaderButton extends JButton {
    public boolean clicked;

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
                if(!clicked){
                    setForeground(Util.midTeal);
                }
            }
        });
    }

    public void changeClickStatus(){
        if(clicked){
            clicked = false;
            setForeground(Util.midTeal);
        } else {
            clicked = true;
            setForeground(Util.darkTeal);
        }
    }
}
