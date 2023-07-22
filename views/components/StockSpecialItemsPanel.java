package views.components;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class StockSpecialItemsPanel extends StockItemsPanel {
    private JLabel standaloneLabel;
    private JCheckBox standaloneInput;
    
    private JLabel operationLabel;
    private JComboBox<String> operationInput;

    public interface ItemAddListener {
        public void run(
            int slotNo,
            String name,
            double price,
            double calories,
            String imagePath,
            boolean standalone,
            String operation,
            int stock
        );
    }

    public StockSpecialItemsPanel() {
    }

    @Override
    protected void initInputComponents() {
        super.initInputComponents();

        standaloneLabel = new JLabel("Standalone:");
        standaloneInput = new JCheckBox();

        operationLabel = new JLabel("Operation:");
        operationInput = new JComboBox<>();
    }

    @Override
    protected void setupInputComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        /* Row 1 */

        gbc.gridy = 0;

        gbc.gridx = 0;
        gbc.gridwidth = 3;
        inputPanel.add(inputHeading, gbc);

        /* Row 2 */

        gbc.gridy = 1;
        
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        inputPanel.add(nameLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        inputPanel.add(nameInput, gbc);

        /* Row 3 */

        gbc.gridy = 2;

        gbc.gridx = 0;
        gbc.gridwidth = 1;
        inputPanel.add(priceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        inputPanel.add(priceInput, gbc);

        /* Row 4 */ 

        gbc.gridy = 3;

        gbc.gridx = 0;
        gbc.gridwidth = 1;
        inputPanel.add(caloriesLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        inputPanel.add(caloriesInput, gbc);

        /* Row 5 */

        gbc.gridy = 4;

        gbc.gridx = 0;
        gbc.gridwidth = 1;
        inputPanel.add(stockLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        inputPanel.add(stockInput, gbc);

        /* Row 6 */

        gbc.gridy = 5;
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        inputPanel.add(imageLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(imagePath, gbc);

        gbc.gridx = 2;
        inputPanel.add(imageInput, gbc);

        /* Row 7 */

        gbc.gridy = 6;
        
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        inputPanel.add(standaloneLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        inputPanel.add(standaloneInput, gbc);

        /* Row 8 */

        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridwidth = 1;
        inputPanel.add(operationLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        inputPanel.add(operationInput, gbc);

        /* Row 9 */

        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.gridwidth = 3;

        inputPanel.add(addItemButton, gbc);
    
        /* Row 10 */

        gbc.gridy = 9;
        inputPanel.add(removeItemButton, gbc);

        /* Row 11 */

        gbc.gridy = 10;
        inputPanel.add(nextButton, gbc);
    }

    public void setStandaloneChecked(boolean isChecked) {
        standaloneInput.setSelected(isChecked);
    }

    public void setOperationValue(int index) {
        operationInput.setSelectedIndex(index);
    }

    public void setOperationValue(String value) {
        // Assume that `value` is in the JComboBox model.
        operationInput.setSelectedItem(value);
    }

    public void setItemAddListener(ItemAddListener listener) {
        addItemButton.addActionListener(e -> {
            listener.run(
                selectedSlotNo, 
                nameInput.getText(),
                (double) priceInput.getValue(),
                (double) caloriesInput.getValue(),
                imageFullPath,
                standaloneInput.isSelected(),
                (String) operationInput.getSelectedItem(),
                (int) stockInput.getValue()
            );
        });
    }
}
