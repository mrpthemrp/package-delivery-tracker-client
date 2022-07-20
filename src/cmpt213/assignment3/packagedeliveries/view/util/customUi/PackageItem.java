package cmpt213.assignment3.packagedeliveries.view.util.customUi;

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
    private final CustomDialog removePackageDialog;

    public PackageItem(PackageBase pkg) {
        this.pkg = pkg;
        this.removePackageDialog = new CustomDialog(PackageDeliveryGUI.getFrames()[0], "Remove Package Confirmation"
                ,"  Y E S  ","   N O   ", new DialogContent("Are you sure you want to remove this package?"));



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
        this.setBorder(BorderFactory.createMatteBorder(0,0,(int)(Util.screenHeight*0.004),0,Util.lightTeal));

        name = new JLabel(pkg.getName());
        notes = new JLabel(pkg.getNotes());
        price = new JLabel(Util.priceFormat.format(pkg.getPrice()));
        weight = new JLabel(Util.weightFormat.format(pkg.getWeight()));
        date = new JLabel(pkg.getExpectedDeliveryDate().format(Util.packageDateFormat).toUpperCase());
        deliveredCheckBox = new JCheckBox("Delivered?",new CheckBoxUI(),pkg.isDelivered());
        removeButton = new RoundButton(" R E M O V E ", "REMOVE", this, Util.redLight, Util.redDark,
                (int) (Util.screenHeight * 0.024), Util.removeBtnTextFont);

        setUpComponents();


        this.add(name, gbcLeft,0);
        this.add(notes, gbcLeft,0);
        this.add(price, gbcLeft,0);
        this.add(weight, gbcRight,0);
        this.add(date, gbcRight,0);
        this.add(removeButton, gbcLeft,0);
        this.add(deliveredCheckBox, gbcLeft,0);
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
            removePackageDialog.run();
        } else if (e.getActionCommand().equals("DELIVERY STATUS")){
            System.out.println("delivery check box clicked");
        }
    }
}
