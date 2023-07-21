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

public class CreateMachineMenuView extends View {
    private JPanel panel;

    private JLabel heading;
    private JLabel subHeading;
    private JButton createRegularButton;
    private JButton createSpecialButton;
    private JButton backButton;

    public CreateMachineMenuView() {
        super();

        panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();  
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        
        heading = new JLabel("Create a Vending Machine");
        heading.setFont(new Font("Sans-Serif", Font.PLAIN, 24));
        heading.setHorizontalAlignment(JLabel.CENTER);

        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(heading, gbc);

        gbc.insets = new Insets(5, 0, 5, 0);

        subHeading = new JLabel("<html><u>What kind?</u></html>");
        panel.add(subHeading, gbc);

        createRegularButton = new JButton("Regular Vending Machine");
        panel.add(createRegularButton, gbc);

        createSpecialButton = new JButton("Special Vending Machine");
        panel.add(createSpecialButton, gbc);

        backButton = new JButton("Back to Main Menu");
        panel.add(backButton, gbc);
    }

    @Override
    public Container getContainer() {
        return panel;
    }

    public void setCreateRegularButtonListener(ActionListener listener) {
        createRegularButton.addActionListener(listener);
    }

    public void setCreateSpecialButtonListener(ActionListener listener) {
        createSpecialButton.addActionListener(listener);
    }

    public void setBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}
