package cmpt213.assignment3.packagedeliveries.view.util.customUI;

import cmpt213.assignment3.packagedeliveries.control.PackageDeliveryControl;
import cmpt213.assignment3.packagedeliveries.model.Book;
import cmpt213.assignment3.packagedeliveries.model.Electronic;
import cmpt213.assignment3.packagedeliveries.model.PackageBase;
import cmpt213.assignment3.packagedeliveries.model.Perishable;
import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates a UI version of a PackageBase object as a JPanel subclass.
 * Action Listener determines what to show when a button is clicked.
 *
 * @author Deborah Wang
 * @link <a href="https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html">...</a> GridBagLayout reference
 */
public class PackageItem extends JPanel implements ActionListener {
    public final int panelItemIndex;
    public final int pkgIndex;
    private final PackageBase pkg;
    private final JTextArea name;
    private final JTextArea notes;
    private final JLabel price;
    private final JLabel weight;
    private final JLabel date;
    private final JLabel dateHeader;
    private final RoundButton removeButton;
    private final JCheckBox deliveredCheckBox;
    private final PackageDeliveryControl control;
    private final CustomDialog removePackageDialog;
    private final GridBagConstraints gbc;
    private JLabel extraField;
    private JLabel pkgHeader;

    /**
     * Constructor for PackageItem; sets look and feel of UI.
     *
     * @param pkg            PackageBase this object represents
     * @param packageNumber  The PackageBase index number
     * @param control        The control class instance
     * @param parent         The JFrame parent needed to create a removePackage Dialog
     * @param panelItemIndex The index of this object in an ArrayList
     */
    public PackageItem(PackageBase pkg, int packageNumber, PackageDeliveryControl control, Frame parent, int panelItemIndex) {
        this.pkg = pkg;
        this.control = control;
        this.panelItemIndex = panelItemIndex;
        this.pkgIndex = (packageNumber - 1);

        this.removePackageDialog = new CustomDialog(parent, "Remove Package Confirmation",
                ("Are you sure you want to remove Package #" + packageNumber + "?"), "  Y E S  ", "   N O   ",
                true, false);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;


        this.setBackground(Color.WHITE);
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createMatteBorder(0, 0, (int) (Util.screenHeight * 0.004), 0, Util.lightTeal));

        dateHeader = new JLabel("Expected Delivery Date:");
        name = new JTextArea(pkg.getName());
        notes = new JTextArea("Notes: " + pkg.getNotes());
        price = new JLabel(Util.priceFormat.format(pkg.getPrice()));
        weight = new JLabel(Util.weightFormat.format(pkg.getWeight()));
        date = new JLabel(pkg.getExpectedDeliveryDate().format(Util.packageDateFormat).toUpperCase());
        deliveredCheckBox = new JCheckBox("Delivered?", new CheckBoxUI(), pkg.isDelivered());
        removeButton = new RoundButton(" R E M O V E ", "REMOVE", this, Util.redLight, Util.redDark,
                (int) (Util.screenHeight * 0.024), Util.removeBtnTextFont);

        setUpComponents(packageNumber);
        setUpContentGrid();
    }

    /**
     * Set up other JComponents that are associated with this class.
     *
     * @param packageNumber The PackageBase index number
     */
    private void setUpComponents(int packageNumber) {

        setUpTextStyle(name, Color.BLACK, Util.subTitleFont, LEFT_ALIGNMENT);
        name.setLineWrap(true);
        setUpTextStyle(notes, Color.BLACK, Util.bodyFont, LEFT_ALIGNMENT);
        notes.setLineWrap(true);
        setUpTextStyle(price, new Color(123, 56, 30), Util.sortTitleFont, RIGHT_ALIGNMENT);
        setUpTextStyle(weight, Color.GRAY, Util.sortBtnsFont, RIGHT_ALIGNMENT);
        setUpTextStyle(date, Color.BLACK, Util.pkgDateFont, RIGHT_ALIGNMENT);
        setUpTextStyle(dateHeader, Color.BLACK, Util.bodyFont, RIGHT_ALIGNMENT);

        removeButton.addActionListener(this);
        deliveredCheckBox.addActionListener(this);
        deliveredCheckBox.setBackground(Color.WHITE);
        deliveredCheckBox.setActionCommand("DELIVERY STATUS");
        deliveredCheckBox.setFocusPainted(false);
        deliveredCheckBox.setForeground(Color.BLACK);
        deliveredCheckBox.setFont(Util.deliveryStatusFont);

        if (pkg instanceof Book) {
            extraField = new JLabel(("Author Name: " + ((Book) pkg).getAuthorName()));
            pkgHeader = new JLabel("P A C K A G E   " + packageNumber + "   |   B O O K");
        } else if (pkg instanceof Perishable) {
            extraField = new JLabel(("Expiry Date: " +
                    ((Perishable) pkg).getExpiryDate().format(Util.packageDateFormat).toUpperCase()));
            pkgHeader = new JLabel("P A C K A G E   " + packageNumber + "   |   P E R I S H A B L E");
        } else if (pkg instanceof Electronic) {
            extraField = new JLabel(("Environmental Handling Fee: $" + ((Electronic) pkg).getHandleFee()));
            pkgHeader = new JLabel("P A C K A G E   " + packageNumber + "   |   E L E C T R O N I C");
        }
        setUpTextStyle(pkgHeader, Util.lightBrown, Util.sortBtnsFont, RIGHT_ALIGNMENT);
        setUpTextStyle(extraField, Color.BLACK, Util.bodyFont, LEFT_ALIGNMENT);

    }

    /**
     * Set up this object's components using a GridBagLayout and GridBagLayoutConstraints
     */
    private void setUpContentGrid() {
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Left Side of Screen
        gbc.insets = new Insets((int) (Util.screenWidth * 0.007), 0, (int) (Util.screenWidth * 0.002), 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(pkgHeader, gbc);

        gbc.insets = new Insets((int) (Util.screenWidth * 0.002), 0, (int) (Util.screenWidth * 0.002), 0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(name, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(notes, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(extraField, gbc);

        gbc.insets = new Insets((int) (Util.screenWidth * 0.002), 0, (int) (Util.screenWidth * 0.007), 0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(deliveredCheckBox, gbc);

        //Middle Divider
        gbc.gridx = 2;
        gbc.gridy = 1;
        if (pkg instanceof Perishable || pkg instanceof Electronic) {
            this.add(Box.createRigidArea(new Dimension((int) (Util.screenWidth * 0.07), 0)), gbc);
        } else if (pkg instanceof Book) {
            this.add(Box.createRigidArea(new Dimension((int) (Util.screenWidth * 0.11), 0)), gbc);
        }

        //Right Side of Screen
        gbc.insets = new Insets((int) (Util.screenWidth * 0.007), 0, (int) (Util.screenWidth * 0.002), 0);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 3;
        gbc.gridy = 0;
        this.add(dateHeader, gbc);

        gbc.insets = new Insets((int) (Util.screenWidth * 0.002), 0, (int) (Util.screenWidth * 0.002), 0);
        gbc.gridx = 3;
        gbc.gridy = 1;
        this.add(date, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        this.add(price, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        this.add(weight, gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        this.add(removeButton, gbc);

    }

    /**
     * Helper method for setting up JComponents
     *
     * @param text       Text of component
     * @param textColour Text Colour of component
     * @param font       Font of the text
     * @param alignment  Text alignment
     */
    private void setUpTextStyle(JComponent text, Color textColour, Font font, float alignment) {
        text.setFont(font);
        text.setForeground(textColour);
        text.setBackground(Color.WHITE);
        text.setAlignmentX(alignment);
    }

    /**
     * Determines what will happen is the package is marked as delivered or removed.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("REMOVE")) {
            removePackageDialog.run(this.panelItemIndex, pkgIndex);
        } else if (e.getActionCommand().equals("DELIVERY STATUS")) {
            control.adjustPackage(pkg, PackageDeliveryControl.DELIVERY_STATUS, deliveredCheckBox.isSelected());
        }
        repaint();
    }

}
