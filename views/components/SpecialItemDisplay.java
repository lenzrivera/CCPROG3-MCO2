package views.components;

import javax.swing.*;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.io.IOException;

/**
 * This class represents the item displayed in the vending machine. It allows
 * the user to see information about the item.
 */
public class SpecialItemDisplay extends ItemDisplay {
    /**
     * The JSpinner to get a value.
     */
    private JSpinner itemSpinner;

    /**
     * Creates a new SpecialItemDisplay component to represent a special item 
     * or slot in the vending machine.
     * @param itemName     name of the item.
     * @param itemCalories calories of the item.
     * @param itemPrice    price of the item.
     * @param itemStock    stock of the item.
     * @param imagePath    image path of the item.
     * @param isStandalone whether the item is standalone
     * @throws IOException If there is an error reading or loading the image 
     *                     from the given imagePath.
     */
    public SpecialItemDisplay(
        String itemName,
        double itemCalories,
        double itemPrice,
        int itemStock,
        String imagePath,
        boolean isStandalone
    ) throws IOException {
        super(
            isStandalone ? itemName : itemName + " [non-standalone]", 
            itemCalories, 
            itemPrice, 
            itemStock, 
            imagePath
        );

        itemSpinner = new JSpinner();
        itemSpinner.setMaximumSize(new Dimension(50,50));
        itemSpinner.setPreferredSize(new Dimension(50,50));
        add(itemSpinner);
    }

    /* */

    @Override
    public void setSelectEnable(boolean enabled) {
        super.setSelectEnable(enabled);
        itemSpinner.setEnabled(enabled);
    }

    /**
     * Retrieves the current selected value from the spinner component.
     * @return The current selected value of the spinner as an integer.
     */
    public int getSpinnerValue() {
        return (int) itemSpinner.getValue();
    }

    /**
     * Sets the value of the spinner component to the specified quantity.
     * @param selQty The new quantity value to be set in the spinner.
     */
    public void setSpinnerValue(int selQty) {
        ChangeListener[] listeners = itemSpinner.getChangeListeners();

        // Can't let change listeners be run on value change like this.
        for (int i = 0; i < listeners.length; i++) {
            itemSpinner.removeChangeListener(listeners[i]);
        }

        SpinnerModel temp = itemSpinner.getModel();
        temp.setValue(selQty);

        // Prevent swing from magically not changing the value >:)
        itemSpinner.setModel(
            new SpinnerNumberModel(
                selQty, 
                selQty, 
                selQty,
                0
            )
        );

        itemSpinner.setModel(temp);

        for (int i = 0; i < listeners.length; i++) {
            itemSpinner.addChangeListener(listeners[i]);
        }
    }

    /**
     * Sets the maximum value for the spinner component.
     * @param max The maximum value that can be selected in the 
     *            spinner.
     */
    public void setSpinnerMaximum(int max) {
        itemSpinner.setModel(
            new SpinnerNumberModel(
                (int) itemSpinner.getValue(), 
                0, 
                max, 
                1
            )
        );
    }

    /**
     * Sets the visibility of the spinner and select button.
     * @param visible A boolean value 
     */
    public void setSpinnerVisible(boolean visible) {
        itemSpinner.setVisible(visible);
        selectButton.setVisible(!visible);
    }  

    /* */

    /**
     * Sets a ChangeListener for the spinner component.
     * @param listener The ChangeListener to be registered for the 
     *                 spinner component.
     */
    public void setSpinnerChangeListener(ChangeListener listener) {
        itemSpinner.addChangeListener(listener);
    }
}