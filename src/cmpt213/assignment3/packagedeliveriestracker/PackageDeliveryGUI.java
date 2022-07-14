package cmpt213.assignment3.packagedeliveriestracker;

import cmpt213.assignment3.packagedeliveriestracker.view.util.CustomComponentFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PackageDeliveryGUI implements ActionListener, ItemListener {


    //Components
    private CustomComponentFactory.RoundButton startBtn;


    //Containers
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double xSize = (screenSize.getWidth() / 2);
    private double ySize = (screenSize.getHeight() / 2);
    private JFrame appFrame;
    private JPanel appPanel;

    public PackageDeliveryGUI() {
        //set up panel
        appPanel = new JPanel();
        
        //set up frame
        appFrame = new JFrame("Package Delivery Tracker");//change string input later
        appFrame.setSize((int) xSize, (int) ySize);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set up components
        startBtn = new CustomComponentFactory.RoundButton("Click to start", 10, Color.CYAN);//change inner later
        startBtn.setActionCommand("START");
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startBtn.addActionListener(this);
        appPanel.add(startBtn);

        System.out.println("JButton width: "+startBtn.getWidth());

        //complete frame setup
        appFrame.setContentPane(appPanel);
        appFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("START")){
            System.out.println("Start was pressed");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
