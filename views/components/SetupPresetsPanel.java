package views.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

/**
 * This class represents the interface for the user to set up the presets
 * of a special vending machine.
 */
public class SetupPresetsPanel extends JPanel {
    /**
     * The content of the item allowing the user to add a new preset.
     */
    private final String ADD_NEW_MSG = "[add new preset]";

    /**
     * The panel containing the preset list.
     */
    private JPanel presetListPanel;

    /**
     * The label for the preset list.
     */
    private JLabel presetListHeading;

    /**
     * The model to display preset names in the list.
     */
    private DefaultListModel<String> presetListModel;

    /**
     * The JList to display the presets.
     */
    private JList<String> presetList;

    /**
     * The panel to add and display item quantity selectors.
     */
    private JPanel itemAddPanel;

    /**
     * The scroll pane to accommodate a large number of item quantity selectors.
     */
    private JScrollPane itemAddScrollPane;

    /**
     * The label for the item quantity selector panel.
     */
    private JLabel itemAddHeading;

    /**
     * The list of item quantity selectors.
     */
    private List<ItemQtySelector> itemQtySelectors;

    /**
     * The panel to display information about the selected preset.
     */
    private JPanel presetInfoPanel;

    /**
     * The label for the preset information panel.
     */
    private JLabel presetInfoHeading;

    /**
     * The label for the preset name input field.
     */
    private JLabel nameLabel;

    /**
     * The text field to input the preset name.
     */
    private JTextField nameInput;

    /**
     * The label for the operation selection combo box.
     */
    private JLabel operationLabel;

    /**
     * The combo box to select the operation.
     */
    private JComboBox<String> operationInput;

    /**
     * The image chooser to set the image for the preset.
     */
    private ImageFileChooser imageChooser;

    /**
     * The button to set or update the preset.
     */
    private JButton setPresetButton;

    /**
     * The button to remove the selected preset.
     */
    private JButton removePresetButton;

    /**
     * Creates a new instance of a SetupPresetsPanel and its associated
     * components.
     */
    public SetupPresetsPanel() {
        super(new GridLayout(1, 3));

        initPresetList();
        initItemSelection();
        initPresetInformation();
    }

    /* */

    /**
     * Initializes the item selection panel and its components.
     */
    private void initItemSelection() {
        itemAddPanel = new JPanel();
        itemAddPanel.setLayout(new BoxLayout(itemAddPanel, BoxLayout.PAGE_AXIS));
        itemAddPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        itemAddScrollPane = new JScrollPane(itemAddPanel);
        add(itemAddScrollPane);

        itemAddHeading = new JLabel("Items");
        itemAddHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemAddPanel.add(itemAddHeading);

        itemAddPanel.add(Box.createVerticalStrut(5));

        itemQtySelectors = new ArrayList<>();    
    }

    /**
     * Initializes the preset information panel and its components.
     */
    private void initPresetInformation() {
        presetInfoPanel = new JPanel(new GridBagLayout());
        add(presetInfoPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;
        gbc.gridwidth = 2;
        
        presetInfoHeading = new JLabel("<html><u>Setup Presets</u></html>");
        gbc.gridx = 0;
        presetInfoPanel.add(presetInfoHeading, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;

        nameLabel = new JLabel("Preset Name:");
        gbc.gridx = 0;
        presetInfoPanel.add(nameLabel, gbc);

        nameInput = new JTextField(15);
        gbc.gridx = 1;
        presetInfoPanel.add(nameInput, gbc);

        gbc.gridy = 2;

        operationLabel = new JLabel("Operation:");
        gbc.gridx = 0;
        presetInfoPanel.add(operationLabel, gbc);

        operationInput = new JComboBox<>();
        gbc.gridx = 1;
        presetInfoPanel.add(operationInput, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        
        imageChooser = new ImageFileChooser("Image:");
        imageChooser.setFilePath("");
        presetInfoPanel.add(imageChooser, gbc);

        gbc.gridy = 4;

        setPresetButton = new JButton("Set Preset");
        presetInfoPanel.add(setPresetButton, gbc);

        gbc.gridy = 5;

        removePresetButton = new JButton("Remove Preset");
        presetInfoPanel.add(removePresetButton, gbc);
    }

    /**
     * Initializes the preset list panel and its components.
     */
    private void initPresetList() {
        presetListPanel = new JPanel();
        presetListPanel.setLayout(new GridBagLayout());
        add(presetListPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridy = 0;

        presetListHeading = new JLabel("Presets");
        presetListPanel.add(presetListHeading, gbc);

        gbc.gridy = 1;

        presetListModel = new DefaultListModel<>();
        presetListModel.addElement(ADD_NEW_MSG);
        
        presetList = new JList<>(presetListModel);
        presetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        presetListPanel.add(presetList, gbc);

        presetList.setSelectedIndex(0);
    }

    /* */

    /**
     * Gets the index of the selected preset in the preset list.
     * @return The index of the selected preset, or -1 if the 
     * "add new preset" option is selected.
     */
    public int getSelectedPresetIndex() {
        if (presetList.getSelectedValue().equalsIgnoreCase(ADD_NEW_MSG)) {
            return -1;
        }

        return presetList.getSelectedIndex();
    }

    /**
     * Gets the value of the name input text field.
     * @return the name input value.
     */
    public String getNameInput() {
        return nameInput.getText();
    }

    /**
     * Gets a map representing the selected items and their quantities.
     * Returns a mapping of each item name to quantity to be included in
     * the preset.
     * @return a map of each item name to their quantity
     */
    public Map<String, Integer> getItemMapping() {
        Map<String, Integer> itemMap = new HashMap<>();

        for (ItemQtySelector qtySelector : itemQtySelectors) {
            if (qtySelector.getQuantity() == 0) {
                continue;
            }

            itemMap.put(qtySelector.getItemName(), qtySelector.getQuantity());
        }

        return itemMap;
    }

    /**
     * Gets the selected operation from the operation combo box.
     * @return the selected operation as a string.
     */
    public String getOperationInput() {
        return (String) operationInput.getSelectedItem();
    }

    /**
     * Gets the full file path of the selected image from the image chooser.
     * @return the full file path of the selected image.
     */
    public String getImagePath() {
        return imageChooser.getFilePath();
    }

    /* */

    /**
     * Adds an ItemQtySelector for the given item to the item 
     * selection panel.
     * @param itemName the name of the item to add to the item panel.
     */
    public void addItemQtySelector(String itemName) {
        ItemQtySelector qtySelector = new ItemQtySelector();
        qtySelector.setItemName(itemName);
        
        itemAddPanel.add(qtySelector);
        itemAddPanel.add(Box.createVerticalStrut(5));
        
        itemQtySelectors.add(qtySelector);
    }

    /**
     * Adds a new preset to the preset list.
     * @param presetName the name of the preset to add.
     */
    public void addPreset(String presetName) {
        // Remove the last dummy element
        presetListModel.remove(presetListModel.getSize() - 1);

        presetListModel.addElement(presetName);
        presetListModel.addElement(ADD_NEW_MSG);

        presetList.setSelectedValue(presetName, true);
    }

    /**
     * Clears all item quantity selectors from the item selection panel.
     */
    public void clearItemQtySelectors() {
        for (int i = itemQtySelectors.size() - 1; i >= 0; i--) {
            ItemQtySelector sel = itemQtySelectors.remove(i);
            itemAddPanel.remove(sel);
        }                
    }

    /**
     * Clears the preset list and resets it to show only the "add new preset" 
     * option.
     */
    public void clearPresetList() {
        presetListModel.clear();
        presetListModel.addElement(ADD_NEW_MSG);
        presetList.setSelectedIndex(0);
    }

    /**
     * Sets the full file path for the selected image in the image chooser.
     * @param imagePath the full file path of the image to set.
     */
    public void setImagePathValue(String imagePath) {
        imageChooser.setFilePath(imagePath);
    }

    /**
     * Sets the input value for the preset name text field.
     * @param name the preset name to set as value.
     */
    public void setNameInputValue(String name) {
        nameInput.setText(name);
    }

    /**
     * Sets the available operations in the operation combo box.
     * @param operations the list of available operations.
     */
    public void setOperations(List<String> operations) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addAll(operations);
        
        operationInput.setModel(model);
        operationInput.setSelectedIndex(0);
    }

    /**
     * Sets the selected operation in the operation combo box by index.
     * @param operationIndex the index of the operation to select.
     */
    public void setOperationValue(int operationIndex) {
        operationInput.setSelectedIndex(operationIndex);
    }

    /**
     * Sets the selected operation in the operation combo box by value.
     * @param operation the operation value to select.
     */
    public void setOperationValue(String operation) {
        operationInput.setSelectedItem(operation);
    }

    /**
     * Updates the item quantity selectors in the items panel based on
     * the provided item map.
     * @param itemMap the map of item names and their quantities to update 
     * the selectors with.
     */
    public void updateItemMap(Map<String, Integer> itemMap) {
        for (ItemQtySelector qtySelector : itemQtySelectors) {
            Integer quantity = itemMap.get(qtySelector.getItemName());

            if (quantity != null) {
                qtySelector.setQuantity(quantity);
            } else {
                qtySelector.setQuantity(0);
            }
        }
    }

    /* */

    /**
     * Adds a listener for the add preset button.
     * @param listener the action listener for the button.
     */
    public void setPresetAddListener(ActionListener listener) {
        setPresetButton.addActionListener(listener);
    }

    /**
     * Adds a listener for the remove preset button.
     * @param listener the action listener for the button.
     */
    public void setPresetRemoveListener(ActionListener listener) {
        removePresetButton.addActionListener(listener);
    }

    /**
     * Adds a listener for preset selection in the preset list
     * @param listener the listener to be added.
     */
    public void setPresetSelectListener(ListSelectionListener listener) {
        presetList.addListSelectionListener(e -> {
            // Don't handle extra invocations of this event: the mouseup part
            // of the selection, and when no selection is actually made.
            if (e.getValueIsAdjusting() || presetList.getSelectedIndex() == -1) {
                return;
            }

            listener.valueChanged(e);
        });
    }
}
