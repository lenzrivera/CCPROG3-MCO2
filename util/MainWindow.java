package util;

import java.awt.CardLayout;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MainWindow {
    private JFrame frame;
    private View currentView;

    public MainWindow() {
        frame = new JFrame("Vending Machine Simulator!");
        frame.setLayout(new CardLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(700, 500);
        
        currentView = null;

        frame.setVisible(true);
    }

    public View getCurrentView() {
        return currentView;
    }

    public void setCurrentView(View v) {
        currentView = v;
        frame.setContentPane(v.getContainer());

        // Ensure that the new content is seen.
        frame.setVisible(true);
    }

    public void close() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
