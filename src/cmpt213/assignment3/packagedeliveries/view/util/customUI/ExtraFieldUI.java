package cmpt213.assignment3.packagedeliveries.view.util.customUI;

import cmpt213.assignment3.packagedeliveries.model.PackageFactory;
import cmpt213.assignment3.packagedeliveries.view.util.Util;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateTimeChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * Custom class that holds all possible extra field UIs.
 * Swaps out different JComponents depending on PackageType.
 *
 * @author Deborah Wang
 */
public class ExtraFieldUI extends JPanel {

    private final JTextArea handleFee;
    private final JTextArea authorName;

    private final DateTimePicker expiryDate;
    public JLabel title;
    private LocalDateTime date;
    private PackageFactory.PackageType type;

    /**
     * Constructor for ExtraField, sets look, feel, and fields.
     *
     * @param fl   FocusListener that JTextAreas will add.
     * @param dtel DateTimeChangeListener that DateTimePickers will add.
     */
    public ExtraFieldUI(FocusListener fl, DateTimeChangeListener dtel) {
        this.title = new JLabel("S E L E C T   T Y P E   F I R S T");

        JTextArea blankField = new JTextArea();
        blankField.setBackground(Color.WHITE);
        blankField.setForeground(Color.BLACK);
        blankField.setFont(Util.sortBtnsFont);

        this.authorName = new JTextArea();
        authorName.setName("NAME");
        authorName.setBackground(Color.WHITE);
        authorName.setForeground(Color.BLACK);
        authorName.setFont(Util.bodyFont);
        authorName.setPreferredSize(new Dimension((int) (this.getWidth() * 0.3), (int) (this.getHeight() * 0.05)));
        authorName.setBorder(BorderFactory.createMatteBorder((int) (Util.screenWidth * 0.0009),
                (int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009),
                (int) (Util.screenWidth * 0.0009), Color.BLACK));
        authorName.addFocusListener(fl);
        authorName.setLineWrap(true);

        this.handleFee = new JTextArea();
        handleFee.setName("ELECTRONIC");
        handleFee.setBackground(Color.WHITE);
        handleFee.setForeground(Color.BLACK);
        handleFee.setFont(Util.bodyFont);
        handleFee.setPreferredSize(new Dimension((int) (this.getWidth() * 0.3), (int) (this.getHeight() * 0.05)));
        handleFee.setBorder(BorderFactory.createMatteBorder((int) (Util.screenWidth * 0.0009),
                (int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009),
                (int) (Util.screenWidth * 0.0009), Color.BLACK));
        handleFee.addFocusListener(fl);

        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setAllowEmptyDates(false);
        dateSettings.setFirstDayOfWeek(DayOfWeek.SUNDAY);
        dateSettings.setAllowKeyboardEditing(true);
        TimePickerSettings timeSettings = new TimePickerSettings();
        timeSettings.setAllowEmptyTimes(false);
        timeSettings.setAllowKeyboardEditing(true);

        this.expiryDate = new DateTimePicker(dateSettings, timeSettings);
        this.expiryDate.addDateTimeChangeListener(dtel);
        expiryDate.setName("PERISHABLE");
        setUpExpiryDate();
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout());
    }

    private void setUpExpiryDate() {
        this.expiryDate.setBackground(Util.transparent);
        this.expiryDate.setForeground(Color.BLACK);
        this.expiryDate.setOpaque(true);
        this.expiryDate.getDatePicker().setBackground(Util.transparent);
        this.expiryDate.getDatePicker().getComponentToggleCalendarButton().setBorder(BorderFactory.createEmptyBorder());
        this.expiryDate.getDatePicker().getComponentToggleCalendarButton().setBackground(Util.midTeal);
        this.expiryDate.getDatePicker().getComponentToggleCalendarButton().setForeground(Color.BLACK);

        this.expiryDate.getTimePicker().getComponentToggleTimeMenuButton().setBackground(Util.midTeal);
        this.expiryDate.getTimePicker().getComponentToggleTimeMenuButton().setForeground(Color.BLACK);
        this.expiryDate.getTimePicker().getComponentToggleTimeMenuButton().setBorder(BorderFactory.createEmptyBorder());
    }

    /**
     * Getter method for the title.
     *
     * @return The title field.
     */
    public JLabel getTitle() {
        return title;
    }

    /**
     * Getter method that returns the String version of this object.
     *
     * @param type The PackageBase type that determines what to return
     * @return A String version of the object that can be saved in {@link cmpt213.assignment3.packagedeliveries.control} classes.
     */
    public String getField(PackageFactory.PackageType type) {
        switch (type) {
            case BOOK -> {
                return authorName.getText();
            }
            case PERISHABLE -> {
                return date.toString();
            }
            case ELECTRONIC -> {
                return handleFee.getText();
            }
        }
        return null;
    }

    /**
     * Sets the date on the Perishable date.
     *
     * @param newDate A LocalDateTime object that holds the expiry date.
     */
    public void setDate(LocalDateTime newDate) {
        this.date = newDate;
    }

    /**
     * Changes the type of the extra field, determines what is shown on the UI.
     *
     * @param type                Which Type's extra field to change to.
     * @param packageTypeSelected If no type is selected, this will be true.
     */
    public void changeType(PackageFactory.PackageType type, boolean packageTypeSelected) {
        this.type = type;
        setUpExtraFieldTitle(packageTypeSelected);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        switch (type) {
            case BOOK -> {
                this.removeAll();
                this.add(authorName, gbc);
                this.setEnabled(true);
                this.setVisible(true);
            }
            case ELECTRONIC -> {
                this.removeAll();
                this.add(handleFee, gbc);
                this.setEnabled(true);
                this.setVisible(true);
            }
            case PERISHABLE -> {
                this.removeAll();
                this.add(expiryDate, gbc);
                this.setEnabled(true);
                this.setVisible(true);
            }
        }
    }

    /**
     * Changes the text of the title.
     *
     * @param packageTypeSelected Tells method which text to change to depending on type.
     */
    private void setUpExtraFieldTitle(boolean packageTypeSelected) {
        if (packageTypeSelected) {
            switch (type) {
                case BOOK -> title.setText("A U T H O R    N A M E:");
                case PERISHABLE -> title.setText("P R O D U C T     E X P I R Y    D A T E:");
                case ELECTRONIC -> title.setText("E N V I R O N.  H A N D L E     F E E   ( $ C A D ):");
            }
        } else {
            title.setText("S E L E C T   T Y P E   F I R S T");
        }

        title.setBackground(Color.WHITE);
        title.setForeground(Color.BLACK);
        title.setFont(Util.sortBtnsFont);
    }

    /**
     * Verifies if field is set, updates UI accordingly.
     *
     * @param type Tells method which extra field to check.
     * @return True if set properly, false otherwise.
     */
    public boolean isSet(PackageFactory.PackageType type) {
        switch (type) {
            case BOOK -> {
                if (!Util.stringVerifier.verify(authorName)) {
                    authorName.setBorder(BorderFactory.createMatteBorder((int) (Util.screenWidth * 0.0009),
                            (int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009),
                            (int) (Util.screenWidth * 0.0009), Util.errorRed));
                    return false;
                } else {
                    authorName.setBorder(BorderFactory.createMatteBorder((int) (Util.screenWidth * 0.0009),
                            (int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009),
                            (int) (Util.screenWidth * 0.0009), Color.BLACK));
                    return true;
                }
            }
            case PERISHABLE -> {
                if (date == null) {
                    expiryDate.getDatePicker().getComponentToggleCalendarButton().setBackground(Util.errorRed);
                    expiryDate.getTimePicker().getComponentToggleTimeMenuButton().setBackground(Util.errorRed);
                    return false;
                } else {
                    expiryDate.getDatePicker().getComponentToggleCalendarButton().setBackground(Util.lightTeal);
                    expiryDate.getTimePicker().getComponentToggleTimeMenuButton().setBackground(Util.lightTeal);
                    return true;
                }
            }
            case ELECTRONIC -> {

                if (!Util.doubleVerifier.verify(handleFee)) {
                    handleFee.setBorder(BorderFactory.createMatteBorder((int) (Util.screenWidth * 0.0009),
                            (int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009),
                            (int) (Util.screenWidth * 0.0009), Util.errorRed));
                    return false;
                } else {
                    handleFee.setBorder(BorderFactory.createMatteBorder((int) (Util.screenWidth * 0.0009),
                            (int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009),
                            (int) (Util.screenWidth * 0.0009), Color.BLACK));
                    return true;
                }
            }
        }
        return false;
    }
}
