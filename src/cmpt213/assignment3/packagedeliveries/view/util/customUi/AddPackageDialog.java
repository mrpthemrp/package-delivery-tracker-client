package cmpt213.assignment3.packagedeliveries.view.util.customUi;

import cmpt213.assignment3.packagedeliveries.control.PackageDeliveryControl;
import cmpt213.assignment3.packagedeliveries.model.PackageFactory;
import cmpt213.assignment3.packagedeliveries.view.util.Util;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateTimeChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateTimeChangeEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class AddPackageDialog extends JDialog implements ActionListener, ItemListener, DateTimeChangeListener, FocusListener {
    private final CustomDialog exitConfirmDialog;
    private final JPanel contentPane;
    private final JComboBox<String> choosePackageType;
    private PackageFactory.PackageType packageType;
    private final String[] comboBoxTitles;
    public PackageDeliveryControl control;
    private final JTextArea name, notes, price, weight;
    private Object extraField;
    private DateTimePicker expectedDeliveryDate;
    private String finalName, finalNotes, finalExtraField;
    private double finalPrice, finalWeight;
    private final JLabel titleName, titleNotes, titleDate,
            titleExtraField, titlePackageType, titleWeight, titlePrice, symbolWeight, symbolPrice;
    private LocalDateTime finalExpectedDate;

    public AddPackageDialog(Frame parent, String title, String btnYesText, String btnNoText, PackageDeliveryControl control) {
        super(parent, title, true);
        this.control = control;
        this.exitConfirmDialog = new CustomDialog(parent, "Remove Package Confirmation",
                "Are you sure you want to exit?",
                "  S T A Y  ", "  E X I T  ", false, true);
        this.setSize(new Dimension((int) (Util.screenWidth * 0.5), (int) (Util.screenHeight * 0.65)));



        titleName = new JLabel("N A M E:");
        setUpJLabel(titleName);
        titleNotes = new JLabel("N O T E S:");
        setUpJLabel(titleNotes);
        titleDate = new JLabel("E X P E C T E D   D E L I V E R Y  D A T E:");
        setUpJLabel(titleDate);
        titleExtraField = new JLabel("A U T H O R   N A M E:");
        setUpJLabel(titleExtraField);
        titlePackageType = new JLabel("P A C K A G E T Y P E:");
        setUpJLabel(titlePackageType);
        titleWeight = new JLabel("W E I G H T:");
        setUpJLabel(titleWeight);
        titlePrice = new JLabel("P R I C E:");
        setUpJLabel(titlePrice);
        symbolWeight = new JLabel("kg");
        setUpJLabel(symbolWeight);
        symbolWeight.setFont(Util.sortTitleFont);
        symbolPrice = new JLabel("$");
        setUpJLabel(symbolPrice);
        symbolPrice.setFont(Util.sortTitleFont);

        JPanel buttonPane = new JPanel();
        RoundButton yesBtn = new RoundButton(btnYesText, "YES", this, Util.lightBrown,
                Util.darkBrown, (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);
        RoundButton noBtn = new RoundButton(btnNoText, "NO", this, Util.midTeal,
                Util.darkTeal, (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);

        buttonPane.setBackground(Color.WHITE);
        buttonPane.add(yesBtn);
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(noBtn);

        comboBoxTitles = new String[]{"Book", "Perishable", "Electronic"};

        choosePackageType = new JComboBox<>(comboBoxTitles);
        choosePackageType.setEditable(false);
        choosePackageType.addItemListener(this);
        choosePackageType.setSize(new Dimension((int) (this.getWidth() * 0.02), (int) (this.getHeight() * 0.5)));
        this.packageType = PackageFactory.PackageType.BOOK;


        name = new JTextArea();
        name.setName("NAME");
        setUpTextArea(name);
        name.addFocusListener(this);
        notes = new JTextArea();
        price = new JTextArea();
        weight = new JTextArea();

        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setAllowEmptyDates(false);
        dateSettings.setFirstDayOfWeek(DayOfWeek.SUNDAY);
        TimePickerSettings timeSettings = new TimePickerSettings();
        timeSettings.setAllowEmptyTimes(false);
        expectedDeliveryDate = new DateTimePicker(dateSettings, timeSettings);
        expectedDeliveryDate.addDateTimeChangeListener(this);


        contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout());
        contentPane.setBackground(Color.WHITE);
        contentPane.add(buttonPane);

        contentPane.add(titleName);
        contentPane.add(name);
        contentPane.add(expectedDeliveryDate);
//        contentPane.add(notes);
//        contentPane.add(price);
//        contentPane.add(weight);

        this.add(contentPane);
    }

    private void setUpJLabel(JLabel label) {
        label.setBackground(Color.WHITE);
        label.setForeground(Color.BLACK);
        label.setFont(Util.subTitleFont);
    }

    private void setUpTextArea(JTextArea object) {
        object.setFont(Util.bodyFont);
        object.setPreferredSize(new Dimension((int) (this.getWidth() * 0.3), (int) (this.getHeight() * 0.05)));
        object.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
//        object.
    }

    private void initializeFinalValues(String name, String notes, double price, double weight,
                                       LocalDateTime date, Object extraField) {

        finalName = name;
        finalNotes = notes;
        finalPrice = price;
        finalWeight = weight;
        finalExpectedDate = date;

        switch (packageType) {
            case BOOK, ELECTRONIC -> ((JTextArea) extraField).getText();
            case PERISHABLE -> finalExtraField = (((DateTimePicker) extraField).getDateTimePermissive()).toString();
        }
    }

    public final void run() {
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("YES")) {
            if (fieldsAreFilledInCorrectly()) {
//                initializeFinalValues(name.getText(), notes.getText(), Double.parseDouble(price.getText()),
//                        Double.parseDouble(weight.getText()), expectedDeliveryDate.getDateTimePermissive(), this.extraField);
                control.createPackage("Name", "", 50, 0.666, LocalDateTime.now(), "author", this.packageType);
                dispose();
            } else {
//                pop-up
            }
            //do something
        } else if (e.getActionCommand().equals("NO")) {
            exitConfirmDialog.run(-1, -1);
            if (!exitConfirmDialog.isRemove()) {
                dispose();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.ITEM_STATE_CHANGED) {
            Object source = e.getSource();
            if (source instanceof JComboBox) {
                JComboBox comboBox = (JComboBox) source;
                Object selectedItem = comboBox.getSelectedItem();
                if (selectedItem.equals(this.comboBoxTitles[0])) {
                    this.packageType = PackageFactory.PackageType.BOOK;
                    this.extraField = new JTextArea();
                } else if (selectedItem.equals(this.comboBoxTitles[1])) {
                    this.packageType = PackageFactory.PackageType.PERISHABLE;
//                    this.extraField = new LGoodDatePicker();
                } else if (selectedItem.equals(this.comboBoxTitles[2])) {
                    this.packageType = PackageFactory.PackageType.ELECTRONIC;
                    this.extraField = new JTextArea();
                }
            }
        }

        repaint();
    }

    private boolean fieldsAreFilledInCorrectly() {
//        if(){
//
//        }
        return true;
    }

    @Override
    public void dateOrTimeChanged(DateTimeChangeEvent dateTimeChangeEvent) {
        this.finalExpectedDate = dateTimeChangeEvent.getNewDateTimePermissive();
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        Component component = e.getComponent();
        if (component.getName().equals("NAME")) {
            if (!Util.stringVerifier.verify((JComponent) component)) {
                //pop-up
                System.out.println("string cannot be empty");
            }
        }

    }
}
