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
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class AddPackageDialog extends JDialog implements ActionListener, ItemListener, DateTimeChangeListener, FocusListener {
    private final CustomDialog exitConfirmDialog;
    private JComboBox<String> choosePackageType;
    private PackageFactory.PackageType packageType;
    private String[] comboBoxTitles;
    public PackageDeliveryControl control;
    private JTextArea name, notes, price, weight;
    private Object extraField;
    private DateTimePicker expectedDeliveryDate;
    private String finalName, finalNotes, finalExtraField;

    //TODO figure out line blank bug
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Util.darkBrown);
        g2.fillRoundRect((int) (this.getWidth() *0.415), (int) (this.getHeight() *0.15),
                (int) (this.getWidth()*0.005), (int) (this.getHeight() *0.75), 3, 3);

    }

    private double finalPrice, finalWeight;
    private JLabel pageTitle, titleName, titleNotes, titleDate,
            titleExtraField, titlePackageType, titleWeight, titlePrice, symbolWeight, symbolPrice;
    private LocalDateTime finalExpectedDate;

    public AddPackageDialog(Frame parent, String title, String btnYesText, String btnNoText, PackageDeliveryControl control) {
        super(parent, title, true);
        this.control = control;
        this.exitConfirmDialog = new CustomDialog(parent, "Remove Package Confirmation",
                "Are you sure you want to exit?",
                "  S T A Y  ", "  E X I T  ", false, true);
        this.setSize(new Dimension((int) (Util.screenWidth * 0.65), (int) (Util.screenHeight * 0.65)));
        this.packageType = PackageFactory.PackageType.BOOK;

        setUpJLabels();
        JPanel buttonPane = new JPanel();
        setUpButtonPane(btnYesText, btnNoText, buttonPane);
        setUpComboBox();
        setUpDateTimePicker();
        setUpTextAreas();

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        contentPane.setBackground(Color.WHITE);

        setUpContentGrid(buttonPane, gbc, contentPane);

        this.add(contentPane);
    }

    private void setUpButtonPane(String btnYesText, String btnNoText, JPanel buttonPane) {
        RoundButton yesBtn = new RoundButton(btnYesText, "YES", this, Util.lightBrown,
                Util.darkBrown, (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);
        RoundButton noBtn = new RoundButton(btnNoText, "NO", this, Util.midTeal,
                Util.darkTeal, (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);

        buttonPane.setBackground(Color.WHITE);
        buttonPane.add(yesBtn);
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(noBtn);
    }

    private void setUpDateTimePicker() {
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setAllowEmptyDates(false);
        dateSettings.setFirstDayOfWeek(DayOfWeek.SUNDAY);
        TimePickerSettings timeSettings = new TimePickerSettings();
        timeSettings.setAllowEmptyTimes(false);
        expectedDeliveryDate = new DateTimePicker(dateSettings, timeSettings);
        expectedDeliveryDate.addDateTimeChangeListener(this);
    }

    private void setUpTextAreas() {
        name = new JTextArea();
        name.setName("NAME");
        setUpTextArea(name);
        name.addFocusListener(this);

        notes = new JTextArea();
        notes.setName("NOTES");
        setUpTextArea(notes);
        notes.setPreferredSize(new Dimension((int) (this.getWidth() * 0.5), (int) (this.getHeight() * 0.2)));
        notes.addFocusListener(this);

        price = new JTextArea();
        price.setName("PRICE");
        setUpTextArea(price);
        price.addFocusListener(this);

        weight = new JTextArea();
        weight.setName("WEIGHT");
        setUpTextArea(weight);
        weight.addFocusListener(this);

        setUpExtraField();
    }

    private void setUpExtraField() {
        switch (packageType) {
            case BOOK, ELECTRONIC -> {
                extraField = new JTextArea();
                ((JTextArea) extraField).setName("EXTRA FIELD");
                setUpTextArea(((JTextArea) extraField));
                ((JTextArea) extraField).addFocusListener(this);
            }
            case PERISHABLE -> {
                DatePickerSettings dateSettings = new DatePickerSettings();
                dateSettings.setAllowEmptyDates(false);
                dateSettings.setFirstDayOfWeek(DayOfWeek.SUNDAY);
                TimePickerSettings timeSettings = new TimePickerSettings();
                timeSettings.setAllowEmptyTimes(false);
                extraField = new DateTimePicker(dateSettings, timeSettings);
                ((DateTimePicker) extraField).setName("EXTRA FIELD");
                ((DateTimePicker) extraField).addDateTimeChangeListener(this);
            }
        }
    }

    private void setUpComboBox() {
        comboBoxTitles = new String[]{"Book", "Perishable", "Electronic"};
        choosePackageType = new JComboBox<>(comboBoxTitles);
        choosePackageType.setEditable(false);
        choosePackageType.addItemListener(this);
        choosePackageType.setSize(new Dimension((int) (this.getWidth() * 0.02), (int) (this.getHeight() * 0.5)));
        this.packageType = PackageFactory.PackageType.BOOK;
    }

    private void setUpJLabels() {
        pageTitle = new JLabel("A D D   P A C K A G E");
        setUpJLabel(pageTitle);
        pageTitle.setFont(Util.addTitleFont);
        titleName = new JLabel("N A M E:");
        setUpJLabel(titleName);
        titleNotes = new JLabel("N O T E S:");
        setUpJLabel(titleNotes);
        titleDate = new JLabel("E X P E C T E D   D E L I V E R Y  D A T E:");
        setUpJLabel(titleDate);
        titlePackageType = new JLabel("P A C K A G E   T Y P E:");
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

        setUpExtraFieldTitle();
    }

    private void setUpExtraFieldTitle() {
        switch (packageType) {
            case BOOK -> titleExtraField = new JLabel("A U T H O R   N A M E:");

            case PERISHABLE -> titleExtraField = new JLabel("E X P I R Y  D E L I V E R Y  D A T E:");

            case ELECTRONIC -> titleExtraField = new JLabel("E N V I R O N M E N T A L   H A N D L E   F E E:");

        }

        setUpJLabel(titleExtraField);
    }

    private void setUpContentGrid(JPanel buttonPane, GridBagConstraints gbc, JPanel contentPane) {
        //left
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BASELINE_TRAILING;
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPane.add(pageTitle, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;
        contentPane.add(buttonPane, gbc);

        //middle bar
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPane.add(Box.createRigidArea(new Dimension((int) (this.getWidth() * 0.05), 0)), gbc);

        //right
        gbc.insets = new Insets(15, 15, 2, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 1;
        contentPane.add(titleName, gbc);

        gbc.insets = new Insets(0, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        contentPane.add(name, gbc);

        gbc.insets = new Insets(15, 15, 2, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 3;
        contentPane.add(titleDate, gbc);

        gbc.insets = new Insets(0, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 4;
        contentPane.add(expectedDeliveryDate, gbc);

        gbc.insets = new Insets(15, 15, 2, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 5;
        contentPane.add(titleExtraField, gbc);

        gbc.insets = new Insets(0, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 6;
        contentPane.add(((JTextArea) extraField), gbc);

        gbc.insets = new Insets(15, 15, 2, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 1;
        contentPane.add(titlePackageType, gbc);

        gbc.insets = new Insets(0, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 2;
        contentPane.add(choosePackageType, gbc);

        gbc.insets = new Insets(15, 15, 2, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 3;
        contentPane.add(titleWeight, gbc);

        gbc.insets = new Insets(0, 15, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 4;
        contentPane.add(weight, gbc);

        gbc.insets = new Insets(0, 0, 15, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = 4;
        contentPane.add(symbolWeight, gbc);

        gbc.insets = new Insets(15, 15, 2, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 5;
        contentPane.add(titlePrice, gbc);

        gbc.insets = new Insets(0, 15, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 6;
        contentPane.add(symbolPrice, gbc);

        gbc.insets = new Insets(0, 35, 15, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 6;
        contentPane.add(price, gbc);

        gbc.insets = new Insets(15, 15, 2, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 7;
        contentPane.add(titleNotes, gbc);

        //TODO fix notes display height
        gbc.insets = new Insets(0, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        gbc.gridheight = 8;
        contentPane.add(notes, gbc);
    }

    private void setUpJLabel(JLabel label) {
        label.setBackground(Color.WHITE);
        label.setForeground(Color.BLACK);
        label.setFont(Util.sortBtnsFont);
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
        repaint();
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
            if (exitConfirmDialog.dispose) {
                dispose();
            }
        }

        repaint();
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
                    setUpExtraFieldTitle();
                    setUpExtraField();
                } else if (selectedItem.equals(this.comboBoxTitles[1])) {
                    this.packageType = PackageFactory.PackageType.PERISHABLE;
                    setUpExtraFieldTitle();
                    setUpExtraField();
                } else if (selectedItem.equals(this.comboBoxTitles[2])) {
                    this.packageType = PackageFactory.PackageType.ELECTRONIC;
                    setUpExtraFieldTitle();
                    setUpExtraField();
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
