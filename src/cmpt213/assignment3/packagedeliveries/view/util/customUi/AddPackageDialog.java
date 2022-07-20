package cmpt213.assignment3.packagedeliveries.view.util.customUi;

import cmpt213.assignment3.packagedeliveries.control.PackageDeliveryControl;
import cmpt213.assignment3.packagedeliveries.model.PackageFactory;
import cmpt213.assignment3.packagedeliveries.view.PackageDeliveryGUI;
import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;

public class AddPackageDialog extends JDialog implements ActionListener, ItemListener {
    private final CustomDialog exitConfirmDialog;
    private final JPanel contentPane;
    private final JComboBox<String> choosePackageType;
    private PackageFactory.PackageType packageType;
    private final String[] comboBoxTitles;
    public PackageDeliveryControl control;
    private final JTextArea name, notes, price, weight;
    private JComponent extraField;
//    private LGoodDatePicker expectedDeliveryDate;

    public AddPackageDialog(Frame parent, String title, String btnYesText, String btnNoText, PackageDeliveryControl control) {
        super(parent, title, true);
        this.control = control;
        this.exitConfirmDialog = new CustomDialog(parent, "Remove Package Confirmation",
                "Are you sure you want to exit?",
                "  S T A Y  ", "  E X I T  ", false, true);
        this.setSize(new Dimension((int) (Util.screenWidth * 0.5), (int) (Util.screenHeight * 0.65)));

        JPanel buttonPane = new JPanel();
        RoundButton yesBtn = new RoundButton(btnYesText, "YES", this, Util.lightBrown,
                Util.darkBrown, (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);
        RoundButton noBtn = new RoundButton(btnNoText, "NO", this, Util.midTeal,
                Util.darkTeal, (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);

        buttonPane.setBackground(Color.WHITE);
        buttonPane.add(yesBtn);
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(noBtn);

        comboBoxTitles = new String[] {"Book","Perishable","Electronic"};

        choosePackageType = new JComboBox<>(comboBoxTitles);
        choosePackageType.setEditable(false);
        choosePackageType.addItemListener(this);
        choosePackageType.setSize(new Dimension((int) (this.getWidth()*0.02),(int)(this.getHeight()*0.5)));
        this.packageType = PackageFactory.PackageType.BOOK;


        name = new JTextArea();
        name.setFont(Util.bodyFont);
        name.setPreferredSize(new Dimension((int) (this.getWidth()*0.3), (int) (this.getHeight()*0.05)));
        name.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        notes = new JTextArea();
        price  = new JTextArea();
        weight  = new JTextArea();
        contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout());
        contentPane.setBackground(Color.WHITE);
        contentPane.add(buttonPane);

        contentPane.add(name);
//        contentPane.add(notes);
//        contentPane.add(price);
//        contentPane.add(weight);

        this.add(contentPane);
    }

    public final void run() {
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("YES")) {
            control.createPackage("Name", "", 50, 0.666, LocalDateTime.now(), "author", this.packageType);
            dispose();
            //do something
        } else if (e.getActionCommand().equals("NO")) {
            exitConfirmDialog.run(-1,-1);
            if(!exitConfirmDialog.isRemove()){
                dispose();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.ITEM_STATE_CHANGED){
            Object source = e.getSource();
            if(source instanceof JComboBox){
                JComboBox comboBox = (JComboBox) source;
                Object selectedItem = comboBox.getSelectedItem();
                if(selectedItem.equals(this.comboBoxTitles[0])){
                    this.packageType = PackageFactory.PackageType.BOOK;
                    this.extraField = new JTextArea();
                } else if(selectedItem.equals(this.comboBoxTitles[1])){
                    this.packageType = PackageFactory.PackageType.PERISHABLE;
//                    this.extraField = new LGoodDatePicker();
                }else if(selectedItem.equals(this.comboBoxTitles[2])){
                    this.packageType = PackageFactory.PackageType.ELECTRONIC;
                    this.extraField = new JTextArea();
                }
            }
        }

        repaint();
    }
}
