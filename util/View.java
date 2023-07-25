package util;

import java.awt.Container;

import javax.swing.JOptionPane;

/**
 * This abstract class represents an MVC view that contains the contents
 * to graphically display to the user. 
 */
public abstract class View {
    /**
     * Constructs a new View object.
     */
    public View() {
    }

    /**
     * Retrieves the main container of the view. The container holds all the
     * visual components for the specific view.
     * @return the Container instance representing the main container of the 
     * view.
     */
    public abstract Container getContainer();

    /**
     * Displays an error dialog with the specified message. The dialog is
     * associated with the main container of the view. 
     * @param message the error message to be displayed in the dialog.
     */
    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(
            getContainer(), message, "Error!", JOptionPane.ERROR_MESSAGE);
    }
}
