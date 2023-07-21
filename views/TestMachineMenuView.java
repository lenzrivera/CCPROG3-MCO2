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

public class TestMachineMenuView extends View {
    private JPanel panel;
    
    private JLabel heading;
    private JLabel subHeading;
    private JButton testVendingButton;
    private JButton testMaintenanceButton;
    private JButton backButton;

    public TestMachineMenuView() {
        panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();  
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        
        heading = new JLabel("Test the Created Vending Machine");
        heading.setFont(new Font("Sans-Serif", Font.PLAIN, 24));
        heading.setHorizontalAlignment(JLabel.CENTER);

        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(heading, gbc);

        gbc.insets = new Insets(5, 0, 5, 0);

        subHeading = new JLabel("Test what?");
        panel.add(subHeading, gbc);

        testVendingButton = new JButton("Vending Features");
        panel.add(testVendingButton, gbc);

        testMaintenanceButton = new JButton("Maintenance Features");
        panel.add(testMaintenanceButton, gbc);

        backButton = new JButton("Back to Main Menu");
        panel.add(backButton, gbc);
    }

    @Override
    public Container getContainer() {
        return panel;
    }

    public void setTestVendingButtonListener(ActionListener listener) {
        testVendingButton.addActionListener(listener);
    }

    public void setTestMaintenanceButtonListener(ActionListener listener) {
        testMaintenanceButton.addActionListener(listener);
    }

    public void setBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}
