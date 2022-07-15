package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Screens extends JPanel{
    private JButton btn;
    private JTextField title;


    public Screens(ActionListener al){

        this.btn = new JButton();
        this.title = new JTextField();
        this.title.setEditable(false);

        createStartPanel(al);
    }

    public void switchToMainScreen(){
        this.setBackground(Util.darkBrown);
        this.btn.setText("ADD PACKAGE");
        this.title.setText("today is");
    }

    private void createStartPanel(ActionListener al) {

        //start button
        this.btn.setText("ENTER");
        this.btn.setActionCommand("SCREEN BUTTON");
        this.btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.btn.addActionListener(al);
        this.btn.setBackground(Util.lightBrown);

        //text fields
        this.title = new JTextField("TITLE");
        this.title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //background stuff
        this.add(btn);
        this.add(title);
        this.setBackground(Color.WHITE);
    }
}
