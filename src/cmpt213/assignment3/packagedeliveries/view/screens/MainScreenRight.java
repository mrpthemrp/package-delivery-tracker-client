package cmpt213.assignment3.packagedeliveries.view.screens;

import cmpt213.assignment3.packagedeliveries.control.PackageDeliveryControl;
import cmpt213.assignment3.packagedeliveries.model.PackageBase;
import cmpt213.assignment3.packagedeliveries.view.util.Util;
import cmpt213.assignment3.packagedeliveries.view.util.customUi.PackageItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//scroll reference:
// https://stackoverflow.com/questions/14615888/list-of-jpanels-that-eventually-uses-a-scrollbar

public class MainScreenRight extends JPanel {
    private GridBagConstraints gbc;
    private final PackageDeliveryControl control;

    public MainScreenRight(ActionListener al, PackageDeliveryControl control) {
        this.control = control;
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 550;
        gbc.weighty = 1;

        this.setVisible(true);
    }

    private void testPackages(ActionListener al) {
        this.add(new PackageItem(control.getPackage(0)),gbc,0);
    }

    private void addPackages(ArrayList<PackageBase> list){
        for(int i = 0; i< list.size();i++ ){
            this.add(new PackageItem(list.get(i)),gbc,0);
        }
    }

    public void populateList(Util.SCREEN_STATE currentState){
        //clear current screen and update lists
        this.control.updateLists();
        this.removeAll();

        //add new items
        switch (currentState){
            case LIST_ALL -> addPackages(control.getAListOfPackages(Util.SCREEN_STATE.LIST_ALL));
            case UPCOMING -> addPackages(control.getAListOfPackages(Util.SCREEN_STATE.UPCOMING));
            case OVERDUE -> addPackages(control.getAListOfPackages(Util.SCREEN_STATE.OVERDUE));
        }
    }
}
