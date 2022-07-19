package cmpt213.assignment3.packagedeliveries.view.util.customUi;

import cmpt213.assignment3.packagedeliveries.model.PackageBase;
import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

public class PackageItem extends JPanel implements ActionListener, ItemListener{
    private final int width;
    private final PackageBase pkg;
    private final JLabel name, notes, price, weight, date;
    private final RoundButton removeButton;
    private final JComboBox pkgTypeSelecter;
    private final JCheckBox deliveredCheckBox;

    public PackageItem(int width, PackageBase pkg) {
        this.width = width;
        this.pkg = pkg;

        this.setSize(new Dimension(this.width, this.getHeight()));
        this.setMaximumSize(new Dimension(this.width, this.getHeight()));
        this.setBackground(Util.darkBrown);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        name = new JLabel(pkg.getName());
        notes = new JLabel(pkg.getNotes());
        price = new JLabel(Util.priceFormat.format(pkg.getPrice()));
        weight = new JLabel(Util.weightFormat.format(pkg.getWeight()));
        date = new JLabel(pkg.getExpectedDeliveryDate().format(Util.packageDateFormat).toUpperCase());


        removeButton = new RoundButton(" R E M O V E ", "REMOVE", this, Util.redLight, Util.redDark,
                (int) (Util.screenHeight * 0.024), Util.removeBtnTextFont);

        pkgTypeSelecter = null;
        Icon icon = new CheckBoxUI();
        deliveredCheckBox = new JCheckBox("Delivered?",icon);
        deliveredCheckBox.setBackground(Color.WHITE);
        deliveredCheckBox.setFocusPainted(false);
        deliveredCheckBox.setFont(Util.deliveryStatusFont);

//        this.add(name);
//        this.add(notes);
//        this.add(price);
//        this.add(weight);
//        this.add(date);
        this.add(Box.createRigidArea(new Dimension(50,50)));
//        this.add(removeButton);
        this.add(deliveredCheckBox);
        this.add(Box.createRigidArea(new Dimension(50,50)));
    }

    private void setUpTextStyle(JLabel text, Color textColour, Font font, float alignment) {
        text.setFont(font);
        text.setForeground(textColour);
        text.setBackground(Util.transparent);
        text.setAlignmentX(alignment);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
