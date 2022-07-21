package cmpt213.assignment3.packagedeliveries.view.util.customUI;

import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import java.awt.*;

//resource: http://www.java2s.com/Code/Java/Swing-JFC/IconCheckBoxDemo.htm
//scaling resource: https://stackoverflow.com/questions/21857915/how-do-i-reduce-the-size-of-the-jcheckbox-icon

public class CheckBoxUI implements Icon {

    private final ImageIcon checkedIcon = updateSizes(Util.checkBoxFilled);

    private final ImageIcon uncheckedIcon = updateSizes(Util.checkBoxOutline);

    private ImageIcon updateSizes(ImageIcon old){
        Image oldImg = old.getImage();
        Image newImg = oldImg.getScaledInstance(getIconWidth(), getIconHeight(),
                Image.SCALE_DEFAULT);
        return  new ImageIcon(newImg);
    }

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
        return (int)(Util.screenWidth*0.015);
    }

    @Override
    public int getIconHeight() {
        return (int)(Util.screenWidth*0.015);
    }
}
