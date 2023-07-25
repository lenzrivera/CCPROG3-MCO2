package views.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class BasicInfoPanel extends JPanel {
    private JLabel heading;

    private JLabel nameLabel;
    private JTextField nameInput;

    private JLabel slotCountLabel;
    private JSpinner slotCountInput;

    private JLabel slotCapacityLabel;
    private JSpinner slotCapacityInput;

    public BasicInfoPanel() {
        super(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();  
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;

        heading = new JLabel("<html><u>Enter Basic Information</u></html>");
        gbc.gridwidth = 2;        
        add(heading, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;

        nameLabel = new JLabel("Name: ");
        gbc.gridx = 0;
        add(nameLabel, gbc);

        nameInput = new JTextField(15);
        gbc.gridx = 1;
        add(nameInput, gbc);

        gbc.gridy = 2;

        slotCountLabel = new JLabel("Number of slots: ");
        gbc.gridx = 0;
        add(slotCountLabel, gbc);

        slotCountInput = new JSpinner();
        gbc.gridx = 1;
        add(slotCountInput, gbc);

        gbc.gridy = 3;

        slotCapacityLabel = new JLabel("Slot capacity: ");
        gbc.gridx = 0;
        add(slotCapacityLabel, gbc);

        slotCapacityInput = new JSpinner();
        gbc.gridx = 1;
        add(slotCapacityInput, gbc);
        
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 4;
    }

    public String getName() {
        return nameInput.getText();
    }

    public int getSlotCount() {
        return (int) slotCountInput.getValue();
    }

    public int getSlotCapacity() {
        return (int) slotCapacityInput.getValue();
    }

    public void setMinSlotCount(int minSlotCount) {
        SpinnerNumberModel model = new SpinnerNumberModel();
        model.setMinimum(minSlotCount);  
        model.setValue(minSlotCount);

        slotCountInput.setModel(model);
    }

    public void setMinSlotCapacity(int minSlotCapacity) {
        SpinnerNumberModel model = new SpinnerNumberModel();
        model.setMinimum(minSlotCapacity);  
        model.setValue(minSlotCapacity);

        slotCapacityInput.setModel(model);
    }
}
