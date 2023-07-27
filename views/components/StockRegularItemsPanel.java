package views.components;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class StockRegularItemsPanel extends StockItemsPanel {
    public StockRegularItemsPanel() {
    }

    @Override
    protected void setupInputComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        /* Row 1 */

        gbc.gridy = 0;

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        inputPanel.add(inputHeading, gbc);

        /* Row 2 */

        gbc.gridy = 1;
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        inputPanel.add(nameLabel, gbc);
        
        gbc.gridx = 1;
        inputPanel.add(nameInput, gbc);

        /* Row 3 */

        gbc.gridy = 2;

        gbc.gridx = 0;
        inputPanel.add(priceLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(priceInput, gbc);

        /* Row 4 */ 

        gbc.gridy = 3;

        gbc.gridx = 0;
        inputPanel.add(caloriesLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(caloriesInput, gbc);

        /* Row 5 */

        gbc.gridy = 4;

        gbc.gridx = 0;
        inputPanel.add(stockLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(stockInput, gbc);

        /* Row 6 */

        gbc.gridy = 5;
        gbc.gridwidth = 2;

        gbc.gridx = 0;
        inputPanel.add(imageChooser, gbc);

        /* Row 7 */

        gbc.gridy = 6;
        gbc.gridx = 0;

        inputPanel.add(setItemButton, gbc);
    
        /* Row 8 */

        gbc.gridy = 7;
        inputPanel.add(removeItemButton, gbc);
    }
}
