package cmpt213.assignment3.packagedeliveries.view.util;

import javax.swing.*;

public class StringVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        JTextArea textArea = (JTextArea) input;
        if(textArea.getText().isEmpty() || textArea.getText().isBlank()){
            return false;
        }
        return true;
    }
}
