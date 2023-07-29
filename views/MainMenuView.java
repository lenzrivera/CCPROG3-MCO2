package views;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.View;

public class MainMenuView extends View {
    private JPanel panel;
    
    private JLabel heading;
    private JButton createButton;
    private JButton testButton;
    private JButton exitButton;

    public MainMenuView() {
        panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();  
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        heading = new JLabel("Vending Machine Factory!");
        heading.setFont(new Font("Sans-Serif", Font.PLAIN, 24));
        heading.setHorizontalAlignment(JLabel.CENTER);

        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(heading, gbc);

        gbc.insets = new Insets(5, 0, 5, 0);

        createButton = new JButton("Create Vending Machine");
        panel.add(createButton, gbc);

        testButton = new JButton("Test Vending Machine");
        panel.add(testButton, gbc);

        exitButton = new JButton("Exit Simulator");
        panel.add(exitButton, gbc);
    }

    @Override
    public Container getContainer() {
        return panel;
    }

    public void setCreateButtonListener(ActionListener listener) {
        createButton.addActionListener(listener);
    }

    public void setExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

    public void setTestButtonEnabled(boolean v) {
        testButton.setEnabled(v);
    }

    public void setTestButtonListener(ActionListener listener) {
        testButton.addActionListener(listener);
    }

    public void setTestButtonToolTip(String tooltip) {
        testButton.setToolTipText(tooltip);
    }
}
