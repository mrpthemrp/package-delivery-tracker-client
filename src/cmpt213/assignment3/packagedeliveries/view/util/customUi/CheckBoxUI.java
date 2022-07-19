package cmpt213.assignment3.packagedeliveries.view.util.customUi;

import cmpt213.assignment3.packagedeliveries.view.util.Util;

import javax.swing.*;
import java.awt.*;

public class CheckBoxUI extends JCheckBox {
    private Image img;
    public int width;

    public CheckBoxUI() {
        this.setText("Delivered?");
        this.setBorderPaintedFlat(true);
        this.setFocusPainted(false);
    }

    public void setImg(ImageIcon img){
        this.img = img == null ? null : img.getImage();
        Icon icon = UIManager.getIcon("CheckBox.icon");
        width = icon.getIconWidth();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.img != null){
            if(getWidth() > img.getWidth(null) + 8){
                g.drawImage(Util.checkBoxOutline.getImage(), 2 + (getWidth() + width) /2,0,this);
            }
        }
    }
}
