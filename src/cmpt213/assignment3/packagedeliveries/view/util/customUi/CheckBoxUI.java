package cmpt213.assignment3.packagedeliveries.view.util.customUi;

import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import java.awt.*;

//resource: http://www.java2s.com/Code/Java/Swing-JFC/IconCheckBoxDemo.htm

public class CheckBoxUI implements Icon {

    private final ImageIcon checkedIcon = Util.checkBoxFilled;

    private final ImageIcon uncheckedIcon = Util.checkBoxOutline;

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

    @Override
    public int getIconWidth() {
        return (int)(Util.screenWidth*0.005);
    }

    @Override
    public int getIconHeight() {
        return (int)(Util.screenWidth*0.005);
    }
}
