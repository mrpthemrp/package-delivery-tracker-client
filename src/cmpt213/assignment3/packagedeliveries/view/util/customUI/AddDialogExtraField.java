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

public class AddDialogExtraField extends JPanel {

    private final JTextArea handleFee;
    private final JTextArea blankField;
    private final JTextArea authorName;

    private final DateTimePicker expiryDate;
    private LocalDateTime date;

    private PackageFactory.PackageType type;
    public JLabel title;

    public AddDialogExtraField(FocusListener fl, DateTimeChangeListener dtel) {
        this.title = new JLabel("S E L E C T   T Y P E   F I R S T");


        this.blankField = new JTextArea();
        blankField.setBackground(Color.WHITE);
        blankField.setForeground(Color.BLACK);
        blankField.setFont(Util.sortBtnsFont);


        this.authorName = new JTextArea();
        authorName.setName("NAME");
        authorName.setBackground(Color.WHITE);
        authorName.setForeground(Color.BLACK);
        authorName.setFont(Util.bodyFont);
        authorName.setPreferredSize(new Dimension((int) (this.getWidth() * 0.3), (int) (this.getHeight() * 0.05)));
        authorName.setBorder(BorderFactory.createMatteBorder((int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009),
                (int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009), Color.BLACK));
        authorName.addFocusListener(fl);

        this.handleFee = new JTextArea();
        handleFee.setName("ELECTRONIC");
        handleFee.setBackground(Color.WHITE);
        handleFee.setForeground(Color.BLACK);
        handleFee.setFont(Util.bodyFont);
        handleFee.setPreferredSize(new Dimension((int) (this.getWidth() * 0.3), (int) (this.getHeight() * 0.05)));
        handleFee.setBorder(BorderFactory.createMatteBorder((int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009),
                (int) (Util.screenWidth * 0.0009), (int) (Util.screenWidth * 0.0009), Color.BLACK));
        handleFee.addFocusListener(fl);

        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setAllowEmptyDates(false);
        dateSettings.setFirstDayOfWeek(DayOfWeek.SUNDAY);
        dateSettings.setAllowKeyboardEditing(false);
        TimePickerSettings timeSettings = new TimePickerSettings();
        timeSettings.setAllowEmptyTimes(false);
        timeSettings.setAllowKeyboardEditing(false);

        this.expiryDate = new DateTimePicker(dateSettings, timeSettings);
        this.expiryDate.addDateTimeChangeListener(dtel);
        expiryDate.setName("PERISHABLE");
        setUpExpiryDate();
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout());
    }

    public JLabel getTitle() {
        return title;
    }

    private void setUpExpiryDate() {
        this.expiryDate.setBackground(Util.transparent);
        this.expiryDate.setForeground(Color.BLACK);
        this.expiryDate.setOpaque(true);
        this.expiryDate.getDatePicker().setBackground(Util.transparent);
        this.expiryDate.getDatePicker().getComponentToggleCalendarButton().
                setBorder(BorderFactory.createEmptyBorder());
        this.expiryDate.getDatePicker().getComponentToggleCalendarButton().setBackground(Util.midTeal);
        this.expiryDate.getDatePicker().getComponentToggleCalendarButton().setForeground(Color.BLACK);

        this.expiryDate.getTimePicker().getComponentToggleTimeMenuButton().setBackground(Util.midTeal);
        this.expiryDate.getTimePicker().getComponentToggleTimeMenuButton().setForeground(Color.BLACK);
        this.expiryDate.getTimePicker().getComponentToggleTimeMenuButton().
                setBorder(BorderFactory.createEmptyBorder());
    }

    private void setUpExtraFieldTitle(boolean packageTypeSelected) {
        if (packageTypeSelected) {
            switch (type) {
                case BOOK -> title.setText("A U T H O R    N A M E:");
                case PERISHABLE -> title.setText("E X P I R Y    D E L I V E R Y     D A T E:");
                case ELECTRONIC -> title.setText("E N V I R O N.  H A N D L E     F E E   ( $ C A D ):");
            }
        } else {
            title.setText("S E L E C T   T Y P E   F I R S T");
        }

        title.setBackground(Color.WHITE);
        title.setForeground(Color.BLACK);
        title.setFont(Util.sortBtnsFont);
    }

    public void setRed() {

    }

    public String getField() {
        return "";
    }

    public void changeType(PackageFactory.PackageType type, boolean packageTypeSelected) {
        this.type = type;
        setUpExtraFieldTitle(packageTypeSelected);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx =0;
        gbc.gridy=0;
        switch (type){
            case BOOK -> {
                this.removeAll();
                this.add(authorName,gbc);
                this.setEnabled(true);
                this.setVisible(true);
            }
            case ELECTRONIC -> {
                this.removeAll();
                this.add(handleFee,gbc);
                this.setEnabled(true);
                this.setVisible(true);
            }
            case PERISHABLE -> {
                this.removeAll();
                this.add(expiryDate,gbc);
                this.setEnabled(true);
                this.setVisible(true);
            }
        }
    }

    public void setDate(LocalDateTime newDate) {
        this.date = newDate;
    }
}
