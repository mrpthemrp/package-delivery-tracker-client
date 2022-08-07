package cmpt213.assignment4.packagedeliveries.client.view.screens;

import cmpt213.assignment4.packagedeliveries.client.control.PackageDeliveryControl;
import cmpt213.assignment4.packagedeliveries.client.model.PackageBase;
import cmpt213.assignment4.packagedeliveries.client.view.PackageDeliveryGUI;
import cmpt213.assignment4.packagedeliveries.client.view.util.Util;
import cmpt213.assignment4.packagedeliveries.client.view.util.customUI.PackageItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Class holds JPanel object that holds the components of the right side of the main screen.
 *
 * @author Deborah Wang
 * @link <a href="https://stackoverflow.com/questions/14615888/list-of-jpanels-that-eventually-uses-a-scrollbar">...</a> Reference for using scroll pane
 */
public class MainScreenRight extends JPanel {
    private static PackageDeliveryControl control = null;
    private static ArrayList<PackageItem> panelItemsAll,panelItemsUpcoming,panelItemsOverdue;
    private static ActionListener parentListener = null;
    private final GridBagConstraints gbc;
    private final Frame parent;
    private final JLabel noItemsMessage;

    /**
     * Constructor for this object, sets look and feel and initializes fields.
     *
     * @param control Control class instance.
     * @param parent  Frame to be passed to dialogs.
     * @param al      Action Listener that buttons will add.
     */
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

        panelItemsAll = new ArrayList<>();
        panelItemsOverdue = new ArrayList<>();
        panelItemsUpcoming = new ArrayList<>();

        this.setVisible(true);
    }

    /**
     * Updates the package items on the UI according to state.
     * Helps with removal of package item on UI
     *
     * @param panelItemIndex PackageItem array index
     * @param arrayIndex     PackageBase array
     */
    public static void updatePackages(int panelItemIndex, int arrayIndex, int option, boolean newStatus) {
        ArrayList<PackageItem> panelItems = null;
        switch (PackageDeliveryGUI.currentState){
            case LIST_ALL -> panelItems = panelItemsAll;
            case UPCOMING -> panelItems = panelItemsUpcoming;
            case OVERDUE -> panelItems = panelItemsOverdue;
        }
        if (panelItemIndex >= 0) {
            assert panelItems != null;
            PackageItem panelPkg = panelItems.get(panelItemIndex);
            PackageBase pkgBase = control.getAListOfPackages(Util.SCREEN_STATE.LIST_ALL).get(arrayIndex);
            if(option == PackageDeliveryControl.REMOVE){
                panelItems.remove(panelPkg);
                control.adjustPackage(pkgBase, option, newStatus);
            } else if (option == PackageDeliveryControl.DELIVERY_STATUS){
                control.adjustPackage(pkgBase, option, newStatus);
                if(!newStatus){
                    panelItems.remove(panelPkg);
                }
            }
        }
        parentListener.actionPerformed(new ActionEvent(MainScreenRight.class, ActionEvent.ACTION_PERFORMED, "UPDATE"));
    }

    /**
     * Populates UI with list of packages according to state.
     *
     * @param currentState The state that the method will populate according to.
     */
    public void populateList(Util.SCREEN_STATE currentState) {

        //add new items
        switch (currentState) {
            case LIST_ALL ->
                    addPackages(control.getAListOfPackages(Util.SCREEN_STATE.LIST_ALL), "No packages to show.",
                            panelItemsAll);
            case UPCOMING ->
                    addPackages(control.getAListOfPackages(Util.SCREEN_STATE.UPCOMING), "No upcoming packages to show.",
                            panelItemsUpcoming);
            case OVERDUE ->
                    addPackages(control.getAListOfPackages(Util.SCREEN_STATE.OVERDUE), "No overdue packages to show.",
                            panelItemsOverdue);
        }
    }

    /**
     * Helper method that add UI packages to screen.
     *
     * @param list        The arraylist to populate from.
     * @param noneMessage Message to show if list is empty.
     */
    private void addPackages(ArrayList<PackageBase> list, String noneMessage, ArrayList<PackageItem> panelItems) {
        panelItems.clear();
        if (list.isEmpty()) {
            noItemsMessage.setText(noneMessage);
            this.add(noItemsMessage, gbc);
        } else {
            for (int i = (list.size() - 1); i >= 0; i--) {
                gbc.gridy = i;
                System.out.println("panel package index: "+i);
                System.out.println(list.get(i));
                PackageItem pkg = new PackageItem(list.get(i), (i + 1), control, parent, (i));
                panelItems.add(pkg);
                this.add(pkg, gbc);
            }
        }
    }

}
