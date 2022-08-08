package cmpt213.assignment4.packagedeliveries.client.view.util.customUI;

import cmpt213.assignment4.packagedeliveries.client.control.PackageDeliveryControl;
import cmpt213.assignment4.packagedeliveries.client.model.PackageFactory;
import cmpt213.assignment4.packagedeliveries.client.view.util.Util;
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

/**
 * Custom dialog that shows up when a package is being added to the system.
 * Should not be stored as a Singleton class object, needs to be run as a new object each time.
 * Inherits from {@link JDialog}
 *
 * @author Deborah Wang
 */
public class AddPackageDialog extends JDialog implements ActionListener, ItemListener,
        DateTimeChangeListener, FocusListener {
    private final CustomDialog exitConfirmDialog;
    private final RoundButton yesBtn;
    private final RoundButton noBtn;
    private final ExtraFieldUI extraField;
    private final JPanel contentPane;
    private final GridBagConstraints gbc;
    private final JLabel errorMessage;
    public PackageDeliveryControl control;
    private JComboBox<String> choosePackageType;
    private PackageFactory.PackageType packageType;
    private String[] comboBoxTitles;
    private JTextArea name;
    private JTextArea notes;
    private JTextArea price;
    private JTextArea weight;
    private DateTimePicker expectedDeliveryDate;
    private JLabel pageTitle, titleName, titleNotes, titleDate,
            titlePackageType, titleWeight, titlePrice, symbolWeight, symbolPrice;
    private LocalDateTime finalExpectedDate;
    private boolean packageTypeSelected, dateIsPicked;

    /**
     * Constructor for Add Package dialog, sets up look and feel of dialog
     *
     * @param parent     Frame for exitConfirm dialog
     * @param title      Title of Dialog Frame
     * @param btnYesText Text of Affirmative button
     * @param btnNoText  Text of Exit button
     * @param control    Control class instance
     */
    public AddPackageDialog(Frame parent, String title, String btnYesText, String btnNoText, PackageDeliveryControl control) {
        super(parent, title, true);
        this.control = control;
        this.exitConfirmDialog = new CustomDialog(parent, "Remove Package Confirmation",
                "Are you sure you want to exit?",
                "  S T A Y  ", "  E X I T  ", false, true);
        this.setSize(new Dimension((int) (Util.screenWidth * 0.65), (int) (Util.screenHeight * 0.65)));
        this.packageType = PackageFactory.PackageType.BOOK;
        this.errorMessage = new JLabel("Error! Required fields not filled properly!");
        this.packageTypeSelected = false;
        this.dateIsPicked = false;
        this.extraField = new ExtraFieldUI(this, this);

        this.contentPane = new JPanel();
        this.contentPane.setLayout(new GridBagLayout());
        this.gbc = new GridBagConstraints();
        this.contentPane.setBackground(Color.WHITE);
        yesBtn = new RoundButton(btnYesText, "YES", this, Util.lightBrown,
                Util.darkBrown, (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);
        noBtn = new RoundButton(btnNoText, "NO", this, Util.midTeal,
                Util.darkTeal, (int) (Util.screenHeight * 0.045), Util.dialogBtnsFont);

        setUpAllJLabels();
        setUpComboBox();
        setUpDateTimePicker();
        setUpTextAreas();

        setUpContentGrid();

        this.add(contentPane);
        packageType = null;
    }

    /**
     * Helper method, sets up all JLabel objects in class.
     */
    private void setUpAllJLabels() {
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
    }

    /**
     * Helper method, sets up the combo box.
     */
    private void setUpComboBox() {
        comboBoxTitles = new String[]{"Book", "Perishable", "Electronic"};
        choosePackageType = new JComboBox<>(comboBoxTitles);
        choosePackageType.setEditable(false);
        choosePackageType.setBackground(Util.lightTeal);
        choosePackageType.setForeground(Color.BLACK);
        choosePackageType.addItemListener(this);
        choosePackageType.setActionCommand("COMBO BOX");
        choosePackageType.addActionListener(this);
        choosePackageType.setSize(new Dimension((int) (this.getWidth() * 0.02), (int) (this.getHeight() * 0.5)));
        this.packageType = PackageFactory.PackageType.BOOK;
    }

    /**
     * Helper method, sets up the DateTimePicker.
     */
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
        expectedDeliveryDate.setName("EXPECT DATE");

    }

    /**
     * Helper method, sets up all JTextAreas objects in class.
     */
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
    }

    /**
     * Sets up objects in a GridBagLayout on the Panel.
     */
    private void setUpContentGrid() {
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
        this.contentPane.add(this.extraField.getTitle(), this.gbc);

        this.gbc.insets = new Insets(0, (int) (Util.screenWidth * 0.04), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.gridx = 1;
        this.gbc.gridy = 8;
        this.contentPane.add(this.extraField, this.gbc);

        this.gbc.insets = new Insets((int) (Util.screenWidth * 0.01), (int) (Util.screenWidth * 0.04), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.gridx = 1;
        this.gbc.gridy = 10;
        this.contentPane.add(titleNotes, this.gbc);

        this.gbc.insets = new Insets(0, (int) (Util.screenWidth * 0.04), (int) (Util.screenWidth * 0.002), 0);
        this.gbc.ipady = (int) (Util.screenWidth * 0.075);
        this.gbc.gridwidth = 4;
        this.gbc.gridx = 1;
        this.gbc.gridy = 11;
        this.contentPane.add(notes, this.gbc, 0);
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

    /**
     * Helper method, helps set up a single JLabel object.
     */
    private void setUpJLabel(JLabel label) {
        label.setBackground(Color.WHITE);
        label.setForeground(Color.BLACK);
        label.setFont(Util.sortBtnsFont);
    }

    /**
     * Helper method, helps set up a single JTextArea object.
     */
    private void setUpTextArea(JTextArea object) {
        object.setFont(Util.bodyFont);
        object.setPreferredSize(new Dimension((int) (this.getWidth() * 0.3), (int) (this.getHeight() * 0.05)));
        object.setBorder(BorderFactory.createMatteBorder((int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009),
                (int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009), Color.BLACK));
    }

    /**
     * Method to show error message.
     *
     * @param isVisible Determines if error message needs to be shown.
     */
    private void showErrorMessage(boolean isVisible) {
        setUpJLabel(this.errorMessage);
        this.errorMessage.setForeground(Util.errorRed);
        this.errorMessage.setFont(Util.subTitleFont);
        this.errorMessage.setVisible(isVisible);
        setRequiredFieldRed(Util.stringVerifier, name);
        setRequiredFieldRed(Util.doubleVerifier, price);
        setRequiredFieldRed(Util.doubleVerifier, weight);

        if (this.finalExpectedDate == null) {
            setDateRed();
        }

        if (!this.packageTypeSelected) {
            setComboBoxRed(true);
        } else {
            setComboBoxRed(false);
            this.extraField.isSet(packageType);
        }
    }

    /**
     * Sets fields that are not filled out properly red.
     *
     * @param verifier  The verifier to be used.
     * @param component The component to be verified.
     */
    private void setRequiredFieldRed(InputVerifier verifier, JComponent component) {
        if (!verifier.verify(component)) {
            component.setBorder(BorderFactory.createMatteBorder((int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009),
                    (int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009), Util.errorRed));
        } else {
            component.setBorder(BorderFactory.createMatteBorder((int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009),
                    (int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009), Color.BLACK));
        }
    }

    /**
     * Sets the date to be red if not set properly.
     */
    private void setDateRed() {
        if (!dateIsPicked) {
            expectedDeliveryDate.getDatePicker().getComponentToggleCalendarButton().setBackground(Util.errorRed);
            expectedDeliveryDate.getTimePicker().getComponentToggleTimeMenuButton().setBackground(Util.errorRed);
        } else {
            expectedDeliveryDate.getDatePicker().getComponentToggleCalendarButton().setBackground(Util.lightTeal);
            expectedDeliveryDate.getTimePicker().getComponentToggleTimeMenuButton().setBackground(Util.lightTeal);
        }
    }

    /**
     * Changes the UI of the comboBox depending on its state
     *
     * @param isNotSet Tells method if the package type is set.
     */
    private void setComboBoxRed(boolean isNotSet) {
        //set colour
        if (isNotSet) {
            choosePackageType.setBackground(Util.errorRed);

        } else {
            choosePackageType.setBackground(Util.lightTeal);
        }
    }

    /**
     * Invokes this dialog without needing a Runnable object.
     */
    public final void run() {
        this.setVisible(true);
    }

    /**
     * Handles what happens when a package is to be created or creation process is cancelled.
     * Shows exit confirm dialog if Cancel button is pressed.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("YES")) {
            if (fieldsAreFilled()) {
                control.createPackage(this.name.getText(), this.notes.getText(), Double.parseDouble(this.price.getText()),
                        Double.parseDouble(this.weight.getText()), this.finalExpectedDate, this.extraField.getField(packageType), this.packageType);
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

        if (e.getActionCommand().equals("COMBO BOX")) {
            if (!this.packageTypeSelected) {
                if (choosePackageType.getSelectedItem().equals("Book")) {
                    this.packageTypeSelected = true;
                    this.packageType = PackageFactory.PackageType.BOOK;
                    setComboBoxRed(false);
                    this.extraField.changeType(packageType, true);
                } else {
                    this.packageTypeSelected = false;
                    setComboBoxRed(true);
                    extraField.isSet(packageType);
                }
            }
        }

        contentPane.repaint();
    }

    /**
     * Method checks if all fields are valid for package creation.
     *
     * @return Returns true if fields are properly filled, false otherwise.
     */
    private boolean fieldsAreFilled() {
        return !name.getText().isBlank()
                && !name.getText().isEmpty()
                && !price.getText().isEmpty()
                && !price.getText().isBlank()
                && Util.doubleVerifier.verify(price)
                && !weight.getText().isEmpty()
                && !weight.getText().isBlank()
                && Util.doubleVerifier.verify(weight)
                && dateIsPicked
                && extraField.isSet(packageType)
                && this.packageTypeSelected;
    }

    /**
     * Changes UI depending on which item is pressed in the combo box.
     *
     * @param e the event to be processed
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            Object source = e.getSource();
            if (source instanceof JComboBox comboBox) {
                Object selectedItem = comboBox.getSelectedItem();
                if (selectedItem.equals(this.comboBoxTitles[0])) {
                    this.packageTypeSelected = true;
                    this.packageType = PackageFactory.PackageType.BOOK;
                    setComboBoxRed(false);
                    extraField.isSet(packageType);
                    this.extraField.changeType(packageType, true);
                } else if (selectedItem.equals(this.comboBoxTitles[1])) {
                    this.packageTypeSelected = true;
                    this.packageType = PackageFactory.PackageType.PERISHABLE;
                    setComboBoxRed(false);
                    extraField.isSet(packageType);
                    this.extraField.changeType(packageType, true);
                } else if (selectedItem.equals(this.comboBoxTitles[2])) {
                    this.packageTypeSelected = true;
                    this.packageType = PackageFactory.PackageType.ELECTRONIC;
                    setComboBoxRed(false);
                    extraField.isSet(packageType);
                    this.extraField.changeType(packageType, true);
                }
            }
        }
        repaint();
    }

    /**
     * Handles what happens when a date is selected on UI.
     *
     * @param dateTimeChangeEvent Event tells method what happened to DateTimePicker
     */
    @Override
    public void dateOrTimeChanged(DateTimeChangeEvent dateTimeChangeEvent) {
        this.dateIsPicked = true;
        if (dateTimeChangeEvent.getSource().getName().equals("EXPECT DATE")) {
            this.finalExpectedDate = dateTimeChangeEvent.getNewDateTimePermissive();
            setDateRed();
        } else if (dateTimeChangeEvent.getSource().getName().equals("PERISHABLE")) {
            extraField.setDate(dateTimeChangeEvent.getNewDateTimePermissive());
            extraField.isSet(packageType);
        }
    }

    /**
     * Method handles what happens when a TextArea is focused.
     *
     * @param e the event to be processed
     */
    @Override
    public void focusGained(FocusEvent e) {

    }

    /**
     * Method handles what happens when a TextArea is not focused.
     * Checks validity of textAreas.
     *
     * @param e the event to be processed
     */
    @Override
    public void focusLost(FocusEvent e) {
        Component component = e.getComponent();
        if (component.getName().equals("NAME") || component.getName().equals("BOOK")) {
            setRequiredFieldRed(Util.stringVerifier, ((JComponent) component));
        } else if (component.getName().equals("PRICE") || component.getName().equals("ELECTRONIC") || component.getName().equals("WEIGHT")) {
            setRequiredFieldRed(Util.doubleVerifier, ((JComponent) component));
        }
    }

    /**
     * Paints a brown line separator on screen.
     *
     * @param g the specified Graphics window
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Util.darkBrown);
        if (packageType == PackageFactory.PackageType.ELECTRONIC) {
            g2.fillRoundRect((int) (this.getWidth() * 0.39), (int) (this.getHeight() * 0.15),
                    (int) (this.getWidth() * 0.005), (int) (this.getHeight() * 0.75), 3, 3);
        } else {
            g2.fillRoundRect((int) (this.getWidth() * 0.41), (int) (this.getHeight() * 0.15),
                    (int) (this.getWidth() * 0.005), (int) (this.getHeight() * 0.75), 3, 3);
        }
    }

}
