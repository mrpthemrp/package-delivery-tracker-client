package cmpt213.assignment3.packagedeliveries.view.util;

import javax.swing.*;

/**
 * Class creates a Double Verifier, inherits from {@link InputVerifier}
 *
 * @author Deborah Wang
 */
public class DoubleVerifier extends InputVerifier {

    /**
     * Verifies a JComponent is a valid Double.
     * Implements {@link InputVerifier#verify(JComponent)}
     *
     * @param input the JComponent to verify
     * @return Returns true if valid Double, false otherwise.
     */
    @Override
    public boolean verify(JComponent input) {
        JTextArea textArea = (JTextArea) input;
        boolean returnValue = true;
        try {
            Double.parseDouble(textArea.getText());
        } catch (NumberFormatException nfe) {
            returnValue = false;
        }

        return returnValue;
    }
}
