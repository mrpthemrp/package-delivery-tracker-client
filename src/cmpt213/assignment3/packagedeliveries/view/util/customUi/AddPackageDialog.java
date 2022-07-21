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
    private JComboBox<String> choosePackageType;
    private PackageFactory.PackageType packageType;
    private String[] comboBoxTitles;
    public PackageDeliveryControl control;
    private JTextArea name, notes, price, weight;
    private Object extraField;
    private DateTimePicker expectedDeliveryDate;

    private JLabel pageTitle, titleName, titleNotes, titleDate,
            titleExtraField, titlePackageType, titleWeight, titlePrice, symbolWeight, symbolPrice;
    private LocalDateTime finalExpectedDate;

    private final JPanel contentPane;
    private final GridBagConstraints gbc;
    private final JLabel errorMessage;

    public AddPackageDialog(Frame parent, String title, String btnYesText, String btnNoText, PackageDeliveryControl control) {
        super(parent, title, true);
        this.control = control;
        this.exitConfirmDialog = new CustomDialog(parent, "Remove Package Confirmation",
                "Are you sure you want to exit?",
                "  S T A Y  ", "  E X I T  ", false, true);
        this.setSize(new Dimension((int) (Util.screenWidth * 0.65), (int) (Util.screenHeight * 0.65)));
        this.packageType = PackageFactory.PackageType.BOOK;
        this.errorMessage = new JLabel("Error! Required fields not filled!");


        RoundButton yesBtn = new RoundButton(btnYesText, "YES", this, Util.lightBrown,
                Util.darkBrown, (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);
        RoundButton noBtn = new RoundButton(btnNoText, "NO", this, Util.midTeal,
                Util.darkTeal, (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);

        setUpJLabels();
        setUpComboBox();
        setUpDateTimePicker();
        setUpTextAreas();

        this.contentPane = new JPanel();
        this.contentPane.setLayout(new GridBagLayout());
        this.gbc = new GridBagConstraints();
        this.contentPane.setBackground(Color.WHITE);

        setUpContentGrid(yesBtn, noBtn);

        this.add(contentPane);
    }

    private void setUpDateTimePicker() {
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setAllowEmptyDates(false);
        dateSettings.setFirstDayOfWeek(DayOfWeek.SUNDAY);
        dateSettings.setAllowKeyboardEditing(true);
        TimePickerSettings timeSettings = new TimePickerSettings();
        timeSettings.setAllowEmptyTimes(false);
        timeSettings.setAllowKeyboardEditing(true);
        expectedDeliveryDate = new DateTimePicker(dateSettings, timeSettings);
        expectedDeliveryDate.addDateTimeChangeListener(this);
        expectedDeliveryDate.setBackground(Util.transparent);
        expectedDeliveryDate.setForeground(Color.BLACK);
        expectedDeliveryDate.setOpaque(true);
        expectedDeliveryDate.getDatePicker().setBackground(Util.transparent);
        expectedDeliveryDate.getDatePicker().getComponentToggleCalendarButton().
                setBorder(BorderFactory.createEmptyBorder());
        expectedDeliveryDate.getDatePicker().getComponentToggleCalendarButton().setBackground(Util.midTeal);
        expectedDeliveryDate.getDatePicker().getComponentToggleCalendarButton().setForeground(Color.BLACK);

        expectedDeliveryDate.getTimePicker().getComponentToggleTimeMenuButton().setBackground(Util.midTeal);
        expectedDeliveryDate.getTimePicker().getComponentToggleTimeMenuButton().setForeground(Color.BLACK);
        expectedDeliveryDate.getTimePicker().getComponentToggleTimeMenuButton().
                setBorder(BorderFactory.createEmptyBorder());

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

    private void setUpComboBox() {
        comboBoxTitles = new String[]{"Book", "Perishable", "Electronic"};
        choosePackageType = new JComboBox<>(comboBoxTitles);
        choosePackageType.setEditable(false);
        choosePackageType.setBackground(Util.lightTeal);
        choosePackageType.setForeground(Color.BLACK);
        choosePackageType.addItemListener(this);
        choosePackageType.setSize(new Dimension((int) (this.getWidth() * 0.02), (int) (this.getHeight() * 0.5)));
        this.packageType = PackageFactory.PackageType.BOOK;
    }

    private void setUpExtraField() {
        switch (packageType) {
            case BOOK -> {
                extraField = new JTextArea();
                ((JTextArea) extraField).setName("BOOK");
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
                ((DateTimePicker) extraField).setName("PERISHABLE");
                ((DateTimePicker) extraField).addDateTimeChangeListener(this);
            }
            case ELECTRONIC -> {
                extraField = new JTextArea();
                ((JTextArea) extraField).setName("ELECTRONIC");
                setUpTextArea(((JTextArea) extraField));
                ((JTextArea) extraField).addFocusListener(this);
            }
        }
    }

    private void setUpExtraFieldTitle() {
        switch (packageType) {
            case BOOK -> titleExtraField = new JLabel("A U T H O R    N A M E:");
            case PERISHABLE -> titleExtraField = new JLabel("E X P I R Y    D E L I V E R Y     D A T E:");
            case ELECTRONIC -> titleExtraField = new JLabel("E N V I R O N M E N T A L     H A N D L E     F E E:");
        }

        setUpJLabel(titleExtraField);
    }


    private void setUpJLabels() {
        pageTitle = new JLabel("A D D    P A C K A G E");
        setUpJLabel(pageTitle);
        pageTitle.setFont(Util.addTitleFont);
        titleName = new JLabel("N A M E:");
        setUpJLabel(titleName);
        titleNotes = new JLabel("N O T E S:");
        setUpJLabel(titleNotes);
        titleDate = new JLabel("E X P E C T E D    D E L I V E R Y    D A T E:");
        setUpJLabel(titleDate);
        titlePackageType = new JLabel("P A C K A G E    T Y P E:");
        setUpJLabel(titlePackageType);
        titleWeight = new JLabel("W E I G H T:");
        setUpJLabel(titleWeight);
        titlePrice = new JLabel("P R I C E   ( C A D ):");
        setUpJLabel(titlePrice);
        symbolWeight = new JLabel("kg");
        setUpJLabel(symbolWeight);
        symbolWeight.setFont(Util.sortTitleFont);
        symbolPrice = new JLabel("$");
        setUpJLabel(symbolPrice);
        symbolPrice.setFont(Util.sortTitleFont);

        setUpExtraFieldTitle();
    }


    private void setUpContentGrid(RoundButton yesBtn, RoundButton noBtn) {
        //Left of Screen
        this.gbc.insets = new Insets((int) (Util.screenWidth * 0.002), (int) (Util.screenWidth * 0.002),
                (int) (Util.screenWidth * 0.002), (int) (Util.screenWidth * 0.002));
        this.gbc.fill = 0;
        this.gbc.anchor = GridBagConstraints.EAST;
        this.gbc.gridx = 0;
        this.gbc.gridy = 6;
        this.contentPane.add(pageTitle, this.gbc);

        this.gbc.gridx = 0;
        this.gbc.gridy = 8;
        this.gbc.anchor = GridBagConstraints.WEST;
        this.gbc.insets = new Insets((int) (Util.screenWidth * 0.002), 0, (int) (Util.screenWidth * 0.002),
                (int) (Util.screenWidth * 0.1));
        this.contentPane.add(yesBtn, this.gbc);
        this.gbc.insets = new Insets((int) (Util.screenWidth * 0.002), (int) (Util.screenWidth * 0.012),
                (int) (Util.screenWidth * 0.002), 0);
        this.gbc.anchor = GridBagConstraints.EAST;
        this.contentPane.add(noBtn, this.gbc);

        this.gbc.insets = new Insets((int) (Util.screenWidth * 0.02), (int) (Util.screenWidth * 0.012), 0, 0);
        this.gbc.gridx = 0;
        this.gbc.gridy = 10;
        this.contentPane.add(errorMessage, gbc);
        showErrorMessage(false);

        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
        this.contentPane.add(Box.createHorizontalGlue(), this.gbc);

        //Right of Screen
        //Right Screen - left side
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.anchor = GridBagConstraints.WEST;
        this.gbc.insets = new Insets((int) (Util.screenWidth * 0.01), (int) (Util.screenWidth * 0.04), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.gridx = 1;
        this.gbc.gridy = 1;
        this.contentPane.add(titleName, this.gbc);

        this.gbc.insets = new Insets(0, (int) (Util.screenWidth * 0.04), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.gridx = 1;
        this.gbc.gridy = 2;
        this.contentPane.add(name, this.gbc);

        this.gbc.insets = new Insets((int) (Util.screenWidth * 0.01), (int) (Util.screenWidth * 0.04), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.gridx = 1;
        this.gbc.gridy = 4;
        this.contentPane.add(titleDate, this.gbc);

        this.gbc.insets = new Insets(0, (int) (Util.screenWidth * 0.04), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.gridx = 1;
        this.gbc.gridy = 5;
        this.contentPane.add(expectedDeliveryDate, this.gbc);

        this.gbc.insets = new Insets((int) (Util.screenWidth * 0.01), (int) (Util.screenWidth * 0.04), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.gridx = 1;
        this.gbc.gridy = 7;
        this.contentPane.add(titleExtraField, this.gbc);

        this.gbc.insets = new Insets(0, (int) (Util.screenWidth * 0.04), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.gridx = 1;
        this.gbc.gridy = 8;
        this.contentPane.add(((JTextArea) extraField), this.gbc);

        this.gbc.insets = new Insets((int) (Util.screenWidth * 0.01), (int) (Util.screenWidth * 0.04), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.gridx = 1;
        this.gbc.gridy = 10;
        this.contentPane.add(titleNotes, this.gbc);

        this.gbc.insets = new Insets(0, (int) (Util.screenWidth * 0.04), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.ipady = (int) (Util.screenWidth * 0.075);
        this.gbc.gridwidth = 4;
        this.gbc.gridx = 1;
        this.gbc.gridy = 11;
        this.contentPane.add(notes, this.gbc);
        this.gbc.ipady = 0;
        this.gbc.gridwidth = GridBagConstraints.RELATIVE;

        //Right Screen - right side
        this.gbc.insets = new Insets((int) (Util.screenWidth * 0.01), (int) (Util.screenWidth * 0.02), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.gridx = 4;
        this.gbc.gridy = 1;
        this.contentPane.add(titlePackageType, this.gbc);

        this.gbc.insets = new Insets(0, (int) (Util.screenWidth * 0.02), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.gridx = 4;
        this.gbc.gridy = 2;
        this.contentPane.add(choosePackageType, this.gbc);

        this.gbc.insets = new Insets((int) (Util.screenWidth * 0.01), (int) (Util.screenWidth * 0.02), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.gridx = 4;
        this.gbc.gridy = 4;
        this.contentPane.add(titleWeight, this.gbc);

        this.gbc.insets = new Insets(0, (int) (Util.screenWidth * 0.02), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.gridx = 4;
        this.gbc.gridy = 5;
        this.contentPane.add(weight, this.gbc);

        this.gbc.insets = new Insets(0, (int) (Util.screenWidth * 0.007), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.gridx = 5;
        this.gbc.gridy = 5;
        this.contentPane.add(symbolWeight, this.gbc);

        this.gbc.insets = new Insets((int) (Util.screenWidth * 0.01), (int) (Util.screenWidth * 0.02), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.gridx = 4;
        this.gbc.gridy = 7;
        this.contentPane.add(titlePrice, this.gbc);

        this.gbc.insets = new Insets(0, (int) (Util.screenWidth * 0.02), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.gridx = 4;
        this.gbc.gridy = 8;
        this.contentPane.add(symbolPrice, this.gbc);

        this.gbc.insets = new Insets(0, (int) (Util.screenWidth * 0.03), (int) (Util.screenWidth * 0.002), 0);
        this.contentPane.add(price, this.gbc);

    }

    private void setUpJLabel(JLabel label) {
        label.setBackground(Color.WHITE);
        label.setForeground(Color.BLACK);
        label.setFont(Util.sortBtnsFont);
    }

    private void setUpTextArea(JTextArea object) {
        object.setFont(Util.bodyFont);
        object.setPreferredSize(new Dimension((int) (this.getWidth() * 0.3), (int) (this.getHeight() * 0.05)));
        object.setBorder(BorderFactory.createMatteBorder((int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009),
                (int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009), Color.BLACK));
    }

    private void initializeFinalValues() {
        String finalExtraField = "";
        switch (packageType) {
            case BOOK, ELECTRONIC -> finalExtraField = ((JTextArea) extraField).getText();
            case PERISHABLE -> finalExtraField = (((DateTimePicker) extraField).getDateTimePermissive().toString());
        }

        control.createPackage(this.name.getText(), this.notes.getText(), Double.parseDouble(this.price.getText()),
                Double.parseDouble(this.weight.getText()), this.finalExpectedDate, finalExtraField, this.packageType);
    }

    public final void run() {
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        if (e.getActionCommand().equals("YES")) {
            if (fieldsAreFilled()) {
                initializeFinalValues();
                repaint();
                dispose();
            } else {
                showErrorMessage(true);
            }
        } else if (e.getActionCommand().equals("NO")) {
            exitConfirmDialog.run(-1, -1);
            if (exitConfirmDialog.dispose) {
                dispose();
            }
        }

        repaint();
    }

    private void showErrorMessage(boolean isVisible) {
        setUpJLabel(this.errorMessage);
        this.errorMessage.setForeground(Color.RED);
        this.errorMessage.setFont(Util.subTitleFont);
        this.errorMessage.setVisible(isVisible);
        setRequiredFieldRed(Util.stringVerifier, name);
        setRequiredFieldRed(Util.doubleVerifier, price);
        setRequiredFieldRed(Util.doubleVerifier, weight);

        switch (this.packageType) {
            case BOOK -> setRequiredFieldRed(Util.stringVerifier, (JComponent) extraField);
            case ELECTRONIC -> setRequiredFieldRed(Util.doubleVerifier, (JComponent) extraField);
        }
    }

    private void setRequiredFieldRed(InputVerifier verifier, JComponent component) {
        if (!verifier.verify(component)) {
            component.setBorder(BorderFactory.createMatteBorder((int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009),
                    (int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009), Color.RED));
        } else {
            component.setBorder(BorderFactory.createMatteBorder((int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009),
                    (int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009), Color.BLACK));
        }
    }

    private boolean fieldsAreFilled() {
        return !name.getText().isBlank()
                && !name.getText().isEmpty()
                && !price.getText().isEmpty()
                && !price.getText().isBlank()
                && !weight.getText().isEmpty()
                && !weight.getText().isBlank()
                && finalExpectedDate != null
                && extraField != null;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            Object source = e.getSource();
            if (source instanceof JComboBox comboBox) {
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
        if (component.getName().equals("NAME") || component.getName().equals("BOOK")) {
            setRequiredFieldRed(Util.stringVerifier, ((JComponent) component));
        } else if (component.getName().equals("PRICE") || component.getName().equals("ELECTRONIC") || component.getName().equals("WEIGHT")) {
            setRequiredFieldRed(Util.doubleVerifier, ((JComponent) component));
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Util.darkBrown);
        g2.fillRoundRect((int) (this.getWidth() * 0.415), (int) (this.getHeight() * 0.15),
                (int) (this.getWidth() * 0.005), (int) (this.getHeight() * 0.75), 3, 3);
    }
}
