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


public class PackageItem extends JPanel implements ActionListener{
    private final PackageBase pkg;
    private final JLabel name, notes, price, weight, date;
    private final RoundButton removeButton;
    private final JCheckBox deliveredCheckBox;

    public PackageItem(PackageBase pkg) {
        this.pkg = pkg;

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;

        this.setBackground(Util.darkBrown);
        this.setLayout(new GridBagLayout());

        name = new JLabel(pkg.getName());
        notes = new JLabel(pkg.getNotes());
        price = new JLabel(Util.priceFormat.format(pkg.getPrice()));
        weight = new JLabel(Util.weightFormat.format(pkg.getWeight()));
        date = new JLabel(pkg.getExpectedDeliveryDate().format(Util.packageDateFormat).toUpperCase());
        deliveredCheckBox = new JCheckBox("Delivered?",new CheckBoxUI(),pkg.getDeliveryStatus());
        deliveredCheckBox.addActionListener(this);
        removeButton = new RoundButton(" R E M O V E ", "REMOVE", this, Util.redLight, Util.redDark,
                (int) (Util.screenHeight * 0.024), Util.removeBtnTextFont);

        setUpComponents();


        this.add(name, gbc,0);
        this.add(notes, gbc,0);
        this.add(price, gbc,0);
        this.add(weight, gbc,0);
        this.add(date, gbc,0);
        this.add(removeButton, gbc,0);
        this.add(deliveredCheckBox, gbc,0);
    }

    private void setUpTextStyle(JLabel text, Color textColour, Font font, float alignment) {
        text.setFont(font);
        text.setForeground(textColour);
        text.setBackground(Util.transparent);
        text.setAlignmentX(alignment);
    }

    private void setUpComponents(){

        setUpTextStyle(name,Color.BLACK,Util.subTitleFont,LEFT_ALIGNMENT);
        setUpTextStyle(notes,Color.BLACK,Util.bodyFont,LEFT_ALIGNMENT);
        setUpTextStyle(price,Util.darkBrown,Util.subTitleFont,RIGHT_ALIGNMENT);
        setUpTextStyle(weight,Color.BLACK,Util.subTitleFont,RIGHT_ALIGNMENT);
        setUpTextStyle(date,Color.BLACK,Util.pkgDateFont,RIGHT_ALIGNMENT);

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
        } else if (e.getActionCommand().equals("DELIVERY STATUS")){
            System.out.println("delivery check box clicked");
        }
    }
}
