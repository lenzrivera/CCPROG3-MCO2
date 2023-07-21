package util;

import java.awt.Container;

import javax.swing.JOptionPane;

public abstract class View {
    public View() {
    }

    public abstract Container getContainer();

    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(
            getContainer(), message, "Error!", JOptionPane.ERROR_MESSAGE);
    }
}
