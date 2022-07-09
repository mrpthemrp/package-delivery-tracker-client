package cmpt213.assignment3.packagedeliveriestracker.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CustomComponentFactory {
    public static class RoundButton extends JButton implements Border {

        private int radius;
        private String btnText;
        private Color btnColor;

        public RoundButton (String btnText,int roundRadius, Color buttonCol){
            this.radius=radius;
            this.btnText=btnText;
            this.btnColor = btnColor;

            initializeBtn();
        }

        private void initializeBtn() {

            this.setBackground(this.btnColor);
            this.setBorder(this);
            this.setFocusPainted(false);//the weird box around the text
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.fillRoundRect(x,y,width-1,height-1,radius,radius);

        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }
}
