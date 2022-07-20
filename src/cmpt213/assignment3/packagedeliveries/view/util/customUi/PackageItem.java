package cmpt213.assignment3.packagedeliveries.view.util.customUi;

import cmpt213.assignment3.packagedeliveries.control.PackageDeliveryControl;
import cmpt213.assignment3.packagedeliveries.model.PackageBase;
import cmpt213.assignment3.packagedeliveries.view.PackageDeliveryGUI;
import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PackageItem extends JPanel implements ActionListener {
    private final PackageBase pkg;
    private final JLabel name, notes, price, weight, date;
    private final RoundButton removeButton;
    private final JCheckBox deliveredCheckBox;
    private final PackageDeliveryControl control;
    private final CustomDialog removePackageDialog;
    public final int panelItemIndex;
    public final int pkgIndex;

    public PackageItem(PackageBase pkg, int packageNumber, PackageDeliveryControl control, Frame parent, int panelItemIndex) {
        this.pkg = pkg;
        this.control = control;
        this.panelItemIndex = panelItemIndex;
        this.pkgIndex = (packageNumber-1);

        this.removePackageDialog = new CustomDialog(parent, "Remove Package Confirmation",
                ("Are you sure you want to remove Package #"+packageNumber+"?"), "  Y E S  ", "   N O   ",
                true, false);

        GridBagConstraints gbcLeft = new GridBagConstraints();
        GridBagConstraints gbcRight = new GridBagConstraints();

        gbcLeft.gridwidth = GridBagConstraints.REMAINDER;
        gbcLeft.weightx = 1;
        gbcLeft.fill = GridBagConstraints.HORIZONTAL;

        gbcRight.gridwidth = GridBagConstraints.REMAINDER;
        gbcRight.weightx = 2;
        gbcRight.fill = GridBagConstraints.HORIZONTAL;

        this.setBackground(Util.darkBrown);
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createMatteBorder(0, 0, (int) (Util.screenHeight * 0.004), 0, Util.lightTeal));

        name = new JLabel(pkg.getName()+packageNumber);
        notes = new JLabel(pkg.getNotes());
        price = new JLabel(Util.priceFormat.format(pkg.getPrice()));
        weight = new JLabel(Util.weightFormat.format(pkg.getWeight()));
        date = new JLabel(pkg.getExpectedDeliveryDate().format(Util.packageDateFormat).toUpperCase());
        deliveredCheckBox = new JCheckBox("Delivered?", new CheckBoxUI(), pkg.isDelivered());
        removeButton = new RoundButton(" R E M O V E ", "REMOVE", this, Util.redLight, Util.redDark,
                (int) (Util.screenHeight * 0.024), Util.removeBtnTextFont);

        setUpComponents();

        this.add(name, gbcLeft, 0);
        this.add(notes, gbcLeft, 0);
        this.add(price, gbcLeft, 0);
        this.add(weight, gbcRight, 0);
        this.add(date, gbcRight, 0);
        this.add(removeButton, gbcLeft, 0);
        this.add(deliveredCheckBox, gbcLeft, 0);
    }

    private void setUpTextStyle(JLabel text, Color textColour, Font font, float alignment) {
        text.setFont(font);
        text.setForeground(textColour);
        text.setBackground(Util.transparent);
        text.setAlignmentX(alignment);
    }

    private void setUpComponents() {

        setUpTextStyle(name, Color.BLACK, Util.subTitleFont, LEFT_ALIGNMENT);
        setUpTextStyle(notes, Color.BLACK, Util.bodyFont, LEFT_ALIGNMENT);
        setUpTextStyle(price, Util.darkBrown, Util.subTitleFont, RIGHT_ALIGNMENT);
        setUpTextStyle(weight, Color.BLACK, Util.subTitleFont, RIGHT_ALIGNMENT);
        setUpTextStyle(date, Color.BLACK, Util.pkgDateFont, RIGHT_ALIGNMENT);

        removeButton.addActionListener(this);
        deliveredCheckBox.addActionListener(this);
        deliveredCheckBox.setBackground(Color.WHITE);
        deliveredCheckBox.setActionCommand("DELIVERY STATUS");
        deliveredCheckBox.setFocusPainted(false);
        deliveredCheckBox.setForeground(Color.BLACK);
        deliveredCheckBox.setFont(Util.deliveryStatusFont);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("REMOVE")){
            System.out.println("remove button pressed");
            removePackageDialog.run(this.panelItemIndex, pkgIndex);
        }
        else if (e.getActionCommand().equals("DELIVERY STATUS")) {
            control.adjustPackage(pkg, PackageDeliveryControl.DELIVERY_STATUS, deliveredCheckBox.isSelected());
        }
        repaint();
    }
    public PackageBase getPkg (){ return this.pkg;}

}
