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

public class SetupPresetsPanel extends JPanel {
    private final String ADD_NEW_MSG = "[add new preset]";

    private JPanel presetListPanel;
    private JLabel presetListHeading;
    private DefaultListModel<String> presetListModel;
    private JList<String> presetList;

    private JPanel itemAddPanel;
    private JScrollPane itemAddScrollPane;
    private JLabel itemAddHeading;
    private List<ItemQtySelector> itemQtySelectors;    

    private JPanel presetInfoPanel;
    private JLabel presetInfoHeading;
    private JLabel nameLabel;
    private JTextField nameInput;
    private JLabel operationLabel;
    private JComboBox<String> operationInput;
    private ImageFileChooser imageChooser;
    private JButton setPresetButton;
    private JButton removePresetButton;

    public SetupPresetsPanel() {
        super(new GridLayout(1, 3));

        initPresetList();
        initItemSelection();
        initPresetInformation();
    }

    /* */

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

    public int getSelectedPresetIndex() {
        if (presetList.getSelectedValue().equalsIgnoreCase(ADD_NEW_MSG)) {
            return -1;
        }

        return presetList.getSelectedIndex();
    }

    public String getNameInput() {
        return nameInput.getText();
    }

    public Map<String, Integer> getItemMapping() {
        Map<String, Integer> itemMap = new HashMap<>();

        for (ItemQtySelector qtySelector : itemQtySelectors) {
            if (qtySelector.getQuantity() == 0) {
                continue;
            }

            itemMap.put(qtySelector.getName(), qtySelector.getQuantity());
        }

        return itemMap;
    }

    public String getOperationInput() {
        return (String) operationInput.getSelectedItem();
    }

    public String getImagePath() {
        return imageChooser.getFilePath();
    }

    /* */

    public void addItemQtySelector(String itemName) {
        ItemQtySelector qtySelector = new ItemQtySelector();
        qtySelector.setItemName(itemName);
        
        itemAddPanel.add(qtySelector);
        itemAddPanel.add(Box.createVerticalStrut(5));
        
        itemQtySelectors.add(qtySelector);
    }

    public void addPreset(String presetName) {
        // Remove the last dummy element
        presetListModel.remove(presetListModel.getSize() - 1);

        presetListModel.addElement(presetName);
        presetListModel.addElement(ADD_NEW_MSG);

        presetList.setSelectedValue(presetName, true);
    }

    public void clearItemQtySelectors() {
        for (int i = itemQtySelectors.size() - 1; i >= 0; i--) {
            ItemQtySelector sel = itemQtySelectors.remove(i);
            itemAddPanel.remove(sel);
        }                
    }

    public void clearPresetList() {
        presetListModel.clear();
        presetListModel.addElement(ADD_NEW_MSG);
    }

    public void setImagePathValue(String imagePath) {
        imageChooser.setFilePath(imagePath);
    }

    public void setNameInputValue(String name) {
        nameInput.setText(name);
    }

    public void setOperations(List<String> operations) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addAll(operations);
        
        operationInput.setModel(model);
        operationInput.setSelectedIndex(0);
    }

    public void setOperationValue(int operationIndex) {
        operationInput.setSelectedIndex(operationIndex);
    }

    public void setOperationValue(String operation) {
        operationInput.setSelectedItem(operation);
    }

    public void updateItemMap(Map<String, Integer> itemMap) {
        for (ItemQtySelector qtySelector : itemQtySelectors) {
            Integer quantity = itemMap.get(qtySelector.getName());

            if (quantity != null) {
                qtySelector.setQuantity(quantity);
            } else {
                qtySelector.setQuantity(0);
            }
        }
    }

    /* */

    public void setPresetAddListener(ActionListener listener) {
        setPresetButton.addActionListener(listener);
    }

    public void setPresetRemoveListener(ActionListener listener) {
        removePresetButton.addActionListener(listener);
    }

    public void setPresetSelectListener(ListSelectionListener listener) {
        presetList.addListSelectionListener(e -> {
            // Don't handle extra invokations of this event: the mouseup part
            // of the selection, and when no selection is actually made.
            if (
                e.getValueIsAdjusting() || 
                presetList.getSelectedIndex() == -1
            ) {
                return;
            }

            listener.valueChanged(e);
        });
    }
}
