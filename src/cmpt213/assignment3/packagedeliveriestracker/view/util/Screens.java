package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Screens extends JPanel{

    private Util util;
    private JButton btn;
    private JTextField title;


    public Screens(ActionListener al){
        util = new Util();

        createStartPanel(al);
        this.add(btn);
    }

    public void switchScreen(){
        this.removeAll();
        this.setBackground(util.darkBrown);
    }

    private JPanel createStartPanel(ActionListener al) {

        //start button
        this.btn= new JButton("Click to start");//change inner later
        this.btn.setActionCommand("START");
        this.btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.btn.addActionListener(al);
        this.btn.setBackground(util.lightBrown);

        //text fields

        //background stuff
        this.setBackground(util.lightBrown);
        return new JPanel();
    }
}
