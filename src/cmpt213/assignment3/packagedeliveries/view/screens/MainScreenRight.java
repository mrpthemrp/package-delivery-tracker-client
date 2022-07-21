package cmpt213.assignment3.packagedeliveries.view.screens;

import cmpt213.assignment3.packagedeliveries.control.PackageDeliveryControl;
import cmpt213.assignment3.packagedeliveries.model.PackageBase;
import cmpt213.assignment3.packagedeliveries.view.util.Util;
import cmpt213.assignment3.packagedeliveries.view.util.customUi.PackageItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//scroll reference:
// https://stackoverflow.com/questions/14615888/list-of-jpanels-that-eventually-uses-a-scrollbar

public class MainScreenRight extends JPanel {
    private GridBagConstraints gbc;
    private static PackageDeliveryControl control = null;
    private final Frame parent;
    private final JLabel noItemsMessage;
    private static ArrayList<PackageItem> panelItems;
    private static ActionListener parentListener = null;

    public MainScreenRight(PackageDeliveryControl control, Frame parent, ActionListener al) {
        MainScreenRight.control = control;
        parentListener = al;
        noItemsMessage = new JLabel("No packages to show.");
        noItemsMessage.setFont(Util.subTitleFont);
        noItemsMessage.setForeground(Color.BLACK);
        noItemsMessage.setBackground(Color.WHITE);
        this.parent = parent;
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 5;

        panelItems = new ArrayList<>();

        this.setVisible(true);
    }

    private void addPackages(ArrayList<PackageBase> list, String noneMessage) {
        if (list.isEmpty()) {
            noItemsMessage.setText(noneMessage);
            this.add(noItemsMessage, gbc);
        } else {
            for (int i = (list.size()-1); i >=0 ; i--) {
                gbc.gridy = i;
                PackageItem pkg = new PackageItem(list.get(i), (i+1), control, parent,i);
                panelItems.add(pkg);
                this.add(pkg, gbc);
            }
        }
    }

    public void populateList(Util.SCREEN_STATE currentState) {
        //update lists
        control.updateLists();

        //add new items
        switch (currentState) {
            case LIST_ALL ->
                    addPackages(control.getAListOfPackages(Util.SCREEN_STATE.LIST_ALL), "No packages to show.");
            case UPCOMING ->
                    addPackages(control.getAListOfPackages(Util.SCREEN_STATE.UPCOMING), "No upcoming packages to show.");
            case OVERDUE ->
                    addPackages(control.getAListOfPackages(Util.SCREEN_STATE.OVERDUE), "No overdue packages to show.");
        }
    }

    public static void updatePackages(int panelItemIndex, int arrayIndex) {
        if (panelItemIndex >= 0) {
            PackageItem pkg = panelItems.get(panelItemIndex);
            PackageBase pkgBase = control.getAListOfPackages(Util.SCREEN_STATE.LIST_ALL).get(arrayIndex);
            panelItems.remove(pkg);
            control.adjustPackage(pkgBase, PackageDeliveryControl.REMOVE, false);
        }
        parentListener.actionPerformed(new ActionEvent(MainScreenRight.class, ActionEvent.ACTION_PERFORMED, "UPDATE"));
    }

    }
