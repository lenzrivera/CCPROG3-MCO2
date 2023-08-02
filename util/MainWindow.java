package util;

import java.awt.CardLayout;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * Represents the main window to be displayed on screen. It can display the
 * current view which interally has a Container like a JPanel that represents
 * the content displayed on the window.
 */
public class MainWindow {
    /**
     * The main JFrame that represents the application window.
     */
    private JFrame frame;

    /**
     * The currently displayed view in the main window.
     */    
    private View currentView;

    /**
     * Constructs a new MainWindow object and initializes the main application 
     * window. By default, no view is set which results in an empty window.
     */
    public MainWindow() {
        frame = new JFrame("Vending Machine Simulator!");
        frame.setLayout(new CardLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(850, 550);
        
        currentView = null;

        frame.setVisible(true);
    }

    /**
     * Retrieves the currently displayed view in the main window.
     * @return the current view displayed in the main window.
     */
    public View getCurrentView() {
        return currentView;
    }

    /**
     * Sets the current view to be displayed in the main window.
     * @param v the view to be set as the current view.
     */
    public void setCurrentView(View v) {
        currentView = v;
        frame.setContentPane(v.getContainer());

        // Ensure that the new content is seen.
        frame.setVisible(true);
    }

    /**
     * Closes the main window and consequently terminates the application.
     */
    public void close() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
