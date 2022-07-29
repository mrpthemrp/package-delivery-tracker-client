package cmpt213.assignment4.packagedeliveries.client.view.util;

import javax.swing.*;

/**
 * Class creates a String Verifier, inherits from {@link InputVerifier}
 *
 * @author Deborah Wang
 */
public class StringVerifier extends InputVerifier {
    /**
     * Verifies a JComponent is not empty or blank.
     * Implements {@link InputVerifier#verify(JComponent)}
     *
     * @param input the JComponent to verify
     * @return Returns true if String is not empty or blank, false otherwise.
     */
    @Override
    public boolean verify(JComponent input) {
        JTextArea textArea = (JTextArea) input;
        return !textArea.getText().isEmpty() && !textArea.getText().isBlank();
    }
}
