package cmpt213.assignment3.packagedeliveries.view.util.customUI;

import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import java.awt.*;


/**
 * Custom CheckBox UI that a JComboBox will use.
 * Makes the empty Box a rounded gray and the filled box a green box with a white check mark.
 *
 * @author Deborah Wang
 * @link <a href="http://www.java2s.com/Code/Java/Swing-JFC/IconCheckBoxDemo.htm">...</a> Reference for writing class
 * @link <a href="https://stackoverflow.com/questions/21857915/how-do-i-reduce-the-size-of-the-jcheckbox-icon">...</a> Reference for scaling icon
 */
public class CheckBoxUI implements Icon {
    private final ImageIcon checkedIcon = updateSizes(Util.checkBoxFilled);
    private final ImageIcon uncheckedIcon = updateSizes(Util.checkBoxOutline);

    /**
     * Updates the sizes of the images used for the Icon.
     *
     * @param imageToResize Image to be resized.
     * @return A resized image with correct scaling in proportion to UI.
     */
    private ImageIcon updateSizes(ImageIcon imageToResize) {
        Image oldImg = imageToResize.getImage();
        Image newImg = oldImg.getScaledInstance(getIconWidth(), getIconHeight(),
                Image.SCALE_DEFAULT);
        return new ImageIcon(newImg);
    }

    /**
     * Paints icon correctly to screen.
     *
     * @param component a {@code Component} to get properties useful for painting
     * @param g         the graphics context
     * @param x         the X coordinate of the icon's top-left corner
     * @param y         the Y coordinate of the icon's top-left corner
     */
    @Override
    public void paintIcon(Component component, Graphics g, int x, int y) {
        AbstractButton abstractButton = (AbstractButton) component;
        ButtonModel buttonModel = abstractButton.getModel();
        g.translate(x, y);
        ImageIcon imageIcon = buttonModel.isSelected() ? checkedIcon
                : uncheckedIcon;
        Image image = imageIcon.getImage();
        g.drawImage(image, 0, 0, component);
        g.translate(-x, -y);
    }

    /**
     * Sets icon's new width. Change return value to resize icon.
     * Used in updateSizes()
     *
     * @return An integer that represents the preferred width of the icon.
     */
    @Override
    public int getIconWidth() {
        return (int) (Util.screenWidth * 0.015);
    }

    /**
     * Sets icon's new height. Change return value to resize icon.
     * Used in updateSizes()
     *
     * @return An integer that represents the preferred height of the icon.
     */
    @Override
    public int getIconHeight() {
        return (int) (Util.screenWidth * 0.015);
    }
}
