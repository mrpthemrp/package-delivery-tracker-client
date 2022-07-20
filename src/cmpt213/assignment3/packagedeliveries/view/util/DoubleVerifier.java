package cmpt213.assignment3.packagedeliveries.view.util;

import javax.swing.*;

public class DoubleVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        JTextArea textArea = (JTextArea) input;
        boolean returnValue = true;
        try{
            Double.parseDouble(textArea.getText());
        } catch (NumberFormatException nfe){
            returnValue = false;
        }

        return returnValue;
    }
}
