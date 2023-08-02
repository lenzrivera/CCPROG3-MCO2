package views;

import java.awt.event.ActionListener;
import javax.swing.*;

import model.Denomination;
import util.View;
import views.components.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The TestSpecialVendingView class represents a test view for the 
 * SpecialVendingMachine application.This class extends the View 
 * class and serves as a graphical user interface (GUI) representation 
 * of the SpecialVendingMachine application. Unlike the TestRegularVendingView, 
 * it has additional GUI for the extra functions.
 */
public class TestSpecialVendingView extends View {
    /**
     * Represents the main container and components of the vending machine application.
     */
    private SectionContainer mainContainer;

    /**
     * The list of SpecialItemDisplay components representing special items in the vending machine.
     */
    private List<SpecialItemDisplay> specialItemDisplays;

    /**
     * The list of PresetDisplay components representing presets in the vending machine.
     */
    private List<PresetDisplay> presetDisplays;

    /**
     * The panel to display items in the vending machine.
     */
    private JPanel itemsPanel;

    /**
     * The panel on the left side of the main container.
     */
    private JPanel leftSidePanel;

    /**
     * The panel to display the log or transaction history.
     */
    private JPanel logPanel;

    /**
     * The panel to display various options for the vending machine.
     */
    private JPanel optionsPanel;

    /**
     * The panel to display presets in the vending machine.
     */
    private JPanel presetsPanel;

    /**
     * The panel on the right side of the main container.
     */
    private JPanel rightSidePanel;

    /**
     * The main panel that contains all the other sections and components of the vending machine.
     */
    private JPanel mainPanel;

    /**
     * The options panel on the left side of the main container.
     */
    private JPanel leftSideOptionsPanel;

    /**
     * The options panel on the right side of the main container.
     */
    private JPanel rightSideOptionsPanel;

    /**
     * A sub-panel of the right side options panel to hold additional controls.
     */
    private JPanel rightSideOptionsSubPanel;

    /**
     * The button to finalize transactions or selections.
     */
    private JButton finalizeButton;

    /**
     * The button to input credit or currency.
     */
    private JButton inputCreditButton;

    /**
     * The button to return credit or currency.
     */
    private JButton returnCreditButton;

    /**
     * The label to display the current credit amount.
     */
    private JLabel creditLabel;

    /**
     * The label to display payment-related information.
     */
    private JLabel paymentLabel;

    /**
     * The label to display the total calories of selected items or presets.
     */
    private JLabel caloriesLabel;

    /**
     * The JScrollPane to hold the log text area for scrolling.
     */
    private JScrollPane logScrollPane;

    /**
     * The JScrollPane to hold the presets panel for scrolling.
     */
    private JScrollPane presetsScrollPane;

    /**
     * The JScrollPane to hold the items panel for scrolling.
     */
    private JScrollPane itemsScrollPane;

    /**
     * The JScrollPane to hold the dialog text area for scrolling.
     */
    private JScrollPane dialogScrollPane;

    /**
     * The JTextArea to display the log or transaction history.
     */
    private JTextArea logTextArea;

    /**
     * The JTextArea to display dialog messages or prompts.
     */
    private JTextArea dialogTextArea;

    /**
     * The JDialog for handling the return denominations.
     */
    private JDialog returnDenomDialog;

    /**
     * The JComboBox to select denominations.
     */
    private JComboBox<Double> denomComboBox;

    /**
     * Constructs a special vending machine view for the interface.
     */
    public TestSpecialVendingView() {
        mainContainer = new SectionContainer("");

        specialItemDisplays = new ArrayList<>();
        presetDisplays = new ArrayList<>();

        GridBagConstraints gridBagConstraints;
        mainPanel = new JPanel();
        leftSidePanel = new JPanel();
        presetsPanel = new JPanel();
        itemsPanel = new JPanel();
        rightSidePanel = new JPanel();
        logPanel = new JPanel();
        optionsPanel = new JPanel();
        leftSideOptionsPanel = new JPanel();
        rightSideOptionsPanel = new JPanel();
        rightSideOptionsSubPanel = new JPanel();
        inputCreditButton = new JButton();
        finalizeButton = new JButton();
        returnCreditButton = new JButton();
        creditLabel = new JLabel();
        paymentLabel = new JLabel();
        caloriesLabel = new JLabel();
        itemsScrollPane = new JScrollPane();
        logScrollPane = new JScrollPane();
        presetsScrollPane = new JScrollPane();
        dialogScrollPane = new JScrollPane();
        dialogScrollPane = new JScrollPane();
        logTextArea = new JTextArea();
        dialogTextArea = new JTextArea();
        returnDenomDialog = new JDialog();
        denomComboBox = new JComboBox<>();

        /* Set dialog window */

        returnDenomDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        returnDenomDialog.setTitle("Denominations");
        returnDenomDialog.setMinimumSize(new Dimension(400, 400));
        returnDenomDialog.setPreferredSize(new Dimension(550, 550));
        returnDenomDialog.setResizable(false);

        dialogTextArea.setEditable(false);
        dialogTextArea.setColumns(20);
        dialogTextArea.setLineWrap(true);
        dialogTextArea.setRows(5);
        dialogTextArea.setWrapStyleWord(true);
        dialogScrollPane.setViewportView(dialogTextArea);

        GroupLayout returnDenomDialogLayout = new GroupLayout(returnDenomDialog.getContentPane());
        returnDenomDialog.getContentPane().setLayout(returnDenomDialogLayout);
        returnDenomDialogLayout.setHorizontalGroup(
                returnDenomDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 268, Short.MAX_VALUE)
                        .addGroup(returnDenomDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.TRAILING, returnDenomDialogLayout.createSequentialGroup()
                                        .addContainerGap(17, Short.MAX_VALUE)
                                        .addComponent(dialogScrollPane, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(17, Short.MAX_VALUE)))
        );
        returnDenomDialogLayout.setVerticalGroup(
                returnDenomDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 371, Short.MAX_VALUE)
                        .addGroup(returnDenomDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.TRAILING, returnDenomDialogLayout.createSequentialGroup()
                                        .addContainerGap(40, Short.MAX_VALUE)
                                        .addComponent(dialogScrollPane, GroupLayout.PREFERRED_SIZE,
                                                300, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(31, Short.MAX_VALUE)))
        );

        /* Set main panel */

        mainPanel.setBorder(BorderFactory.createTitledBorder("Test Vending Features"));
        mainPanel.setPreferredSize(new Dimension(850, 525));
        mainPanel.setLayout(new GridLayout(1, 2));

        leftSidePanel.setLayout(new GridLayout(2, 1));

        /* Set presets and items panel */

        presetsScrollPane.setBorder(BorderFactory.createTitledBorder("Presets"));

        presetsPanel.setLayout(new BoxLayout(presetsPanel, BoxLayout.PAGE_AXIS));
        presetsScrollPane.setViewportView(presetsPanel);

        leftSidePanel.add(presetsScrollPane);

        itemsScrollPane.setBorder(BorderFactory.createTitledBorder("Items"));
        itemsScrollPane.setPreferredSize(new Dimension(260, 423));

        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.PAGE_AXIS));
        itemsScrollPane.setViewportView(itemsPanel);

        leftSidePanel.add(itemsScrollPane);

        mainPanel.add(leftSidePanel);

        rightSidePanel.setPreferredSize(new Dimension(250, 250));
        rightSidePanel.setLayout(new GridLayout(2, 1));

        /* Set log panel */

        logPanel.setBorder(BorderFactory.createTitledBorder("Log"));

        logTextArea.setEditable(false);
        logTextArea.setColumns(20);
        logTextArea.setLineWrap(true);
        logTextArea.setRows(5);
        logTextArea.setWrapStyleWord(true);
        logScrollPane.setViewportView(logTextArea);

        GroupLayout logPanelLayout = new GroupLayout(logPanel);
        logPanel.setLayout(logPanelLayout);
        logPanelLayout.setHorizontalGroup(
                logPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(logScrollPane, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
        );
        logPanelLayout.setVerticalGroup(
                logPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(logScrollPane, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );

        rightSidePanel.add(logPanel);

        /* Set options panel */

        optionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        optionsPanel.setPreferredSize(new Dimension(420, 225));
        optionsPanel.setLayout(new GridLayout(1, 0));

        leftSideOptionsPanel.setLayout(new GridBagLayout());

        creditLabel.setText("Credit/Balance: P" + 0.0);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.weightx = 0.1;
        leftSideOptionsPanel.add(creditLabel, gridBagConstraints);

        paymentLabel.setText("Total Payment: P" + 0.0);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(20, 0, 0, 0);
        leftSideOptionsPanel.add(paymentLabel, gridBagConstraints);

        caloriesLabel.setText("Total Calories: " + 0.0);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(20, 0, 0, 0);
        leftSideOptionsPanel.add(caloriesLabel, gridBagConstraints);

        optionsPanel.add(leftSideOptionsPanel);

        rightSideOptionsPanel.setLayout(new GridBagLayout());

        rightSideOptionsSubPanel.setLayout(new BoxLayout(rightSideOptionsSubPanel, BoxLayout.LINE_AXIS));

        denomComboBox.setMaximumSize(new Dimension(125, 25));
        rightSideOptionsSubPanel.add(denomComboBox);

        inputCreditButton.setText("Input Credit");
        inputCreditButton.setMaximumSize(new Dimension(125, 25));
        rightSideOptionsSubPanel.add(inputCreditButton);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        rightSideOptionsPanel.add(rightSideOptionsSubPanel, gridBagConstraints);

        finalizeButton.setText("Finalize");
        finalizeButton.setMaximumSize(new Dimension(125, 25));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(20, 0, 0, 0);
        rightSideOptionsPanel.add(finalizeButton, gridBagConstraints);

        returnCreditButton.setText("Return Credit");
        returnCreditButton.setMaximumSize(new Dimension(125, 25));
        returnCreditButton.setPreferredSize(new Dimension(125, 23));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(20, 0, 0, 0);
        rightSideOptionsPanel.add(returnCreditButton, gridBagConstraints);

        optionsPanel.add(rightSideOptionsPanel);

        rightSidePanel.add(optionsPanel);

        mainPanel.add(rightSidePanel);

        mainContainer.setBody(mainPanel);
    }

    @Override
    public Container getContainer() {
        return mainContainer;
    }

    /* */

    /**
     * Gets the PresetDisplay at the specified slot number.
     * @param slotNo The slot number of the desired PresetDisplay.
     *               It represents the position of the preset in the list, 
     *               starting from 1.
     * @return The PresetDisplay component at the specified slot number, or 
     *         null if the slot number is invalid.
     */
    public PresetDisplay getPresetDisplay(int slotNo) {
        return presetDisplays.get(slotNo - 1);
    }

    /**
     * Gets the SpecialItemDisplay at the specified slot number.
     * @param slotNo The slot number of the desired SpecialItemDisplay.
     *               It represents the position of the special item in the 
     *               list, starting from 1.
     * @return The SpecialItemDisplay component at the specified slot number, 
     *         or null if the slot number is invalid.
     */
    public SpecialItemDisplay getItemDisplay(int slotNo) {
        return specialItemDisplays.get(slotNo - 1);
    }

    /**
     * Gets the selected denomination from the denominations combo box.
     * @return The selected denomination from the denominations combo box 
     *         as a double.
     */
    public double getSelectedDenom() {
        return (double) denomComboBox.getSelectedItem();
    }

    /**
     * Sets the heading of the main container in the user interface.
     * @param heading The heading or title to be displayed in the main 
     *                container of the user interface.
     */
    public void setHeading(String heading) {
        mainContainer.setHeading(heading);
    }

    /**
     * Sets the list of denominations in the denominations combo box.
     * @param denominations The list of denominations to be displayed 
     *                      in the denominations combo box.
     */
    public void setDenominations(List<Double> denominations) {
        DefaultComboBoxModel<Double> model = new DefaultComboBoxModel<>();
        model.addAll(denominations);

        denomComboBox.setModel(model);
        denomComboBox.setSelectedIndex(0);
    }

    /* */


    /**
     * Adds a slot or special item to the items panel in the vending 
     * machine user interface.
     * @param name         name of the item.
     * @param price        price of the item.
     * @param calories     calories of the item.
     * @param itemStock    stock of the item.
     * @param imagePath    Image path of the item.
     * @param standalone   A boolean value.
     * @throws IOException If there is an error reading the image file 
     *                     from the provided file path.
     */
    public void addSlot(
        String name,
        double price,
        double calories,
        int itemStock,
        String imagePath,
        boolean standalone
    ) throws IOException {
        SpecialItemDisplay specialItemDisplay = new SpecialItemDisplay(
            name,
            calories,
            price,
            itemStock,
            imagePath,
            standalone
        );

        specialItemDisplay.setSelectEnable(true);

        itemsPanel.add(specialItemDisplay);
        itemsPanel.add(Box.createVerticalStrut(10));

        specialItemDisplays.add(specialItemDisplay);
    }

    /**
     * Adds a preset to the presets panel in the vending machine user interface.
     * @param name           The name of the preset to be displayed.
     * @param presetPrice    The price of the preset to be displayed.
     * @param presetCalories The total calorie count of the preset to be displayed.
     * @param imagePath      The file path or resource path to the image representing 
     *                       the preset.
     * @throws IOException   If there is an error reading the image file from the provided 
     *                       file path.
     */
    public void addPreset(
        String name, 
        double presetPrice, 
        double presetCalories,
        String imagePath
    ) throws IOException {
        PresetDisplay presetDisplay = new PresetDisplay(
            name,
            presetCalories,
            presetPrice, 
            imagePath
        );

        presetsPanel.add(presetDisplay);
        presetsPanel.add(Box.createVerticalStrut(10));

        presetDisplays.add(presetDisplay);
    }

    /**
     * Displays a dialog with a table showing denominations and their 
     * corresponding quantities.
     * @param heading   The heading or title for the dialog box.
     * @param denomMap  A Map containing denominations and their corresponding 
     *                  quantities.
     */
    public void displayDenominations(
            String heading,
            Map<Denomination, Integer> denomMap
    ) {
        JPanel display = new JPanel();
        display.setLayout(new BoxLayout(display, BoxLayout.PAGE_AXIS));

        JLabel headingLabel = new JLabel(heading);
        display.add(headingLabel);

        display.add(Box.createVerticalStrut(5));

        DisplayTable table = new DisplayTable(new String[] {
                "Denomination",
                "Quantity"
        });
        table.setPreferredSize(new Dimension(300, 100));
        table.setAlignmentX(0);
        display.add(table);

        int i = 0;

        for (var entry : denomMap.entrySet()) {
            table.setCell(0, i, entry.getKey().getValue());
            table.setCell(1, i, entry.getValue());

            i += 1;
        }

        JOptionPane.showMessageDialog(
                mainContainer,
                display,
                heading,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Appends text to the log text area.
     * @param text The text to be added to the log text area.
     */
    public void inputLog(String text) {
        if (logTextArea.getText().isEmpty()) {
            logTextArea.setText(text);
        } else {
            logTextArea.setText(logTextArea.getText() + "\n" + text);
        }
    }

    /* */

    /**
     * Sets a listener for the exit button.
     * @param listener The ActionListener to be registered for the exit button.
     */
    public void setExitButtonListener(ActionListener listener) {
        mainContainer.setExitButtonListener(listener);
    }

    /**
     * Sets a listener for the input credit button.
     * @param listener The ActionListener to be registered for the input credit 
     *                 button.
     */
    public void setInputCreditButtonListener(ActionListener listener) {
        inputCreditButton.addActionListener(listener);
    }

    /**
     * Sets a listener for the return credit button.
     * @param listener The ActionListener to be registered for the return 
     *                 credit button.
     */
    public void setReturnCreditButtonListener(ActionListener listener) {
        returnCreditButton.addActionListener(listener);
    }

    /**
     * Sets a listener for the finalize button.
     * @param listener The ActionListener to be registered for the finalize 
     *                 button.
     */
    public void setFinalizeButtonListener(ActionListener listener) {
        // Set the provided ActionListener for the finalize button
        finalizeButton.addActionListener(listener);
    }

    /**
     * Updates the display of the total credit or balance.
     * @param credit The new total credit or balance to be displayed.
     */
    public void updateTotalCredit(double credit) {
        creditLabel.setText("Credit/Balance: P" + credit);
    }

    /**
     * Updates the display of the total payment.
     * @param payment The new total payment amount to be displayed.
     */
    public void updateTotalPayment(double payment) {
        paymentLabel.setText("Total Payment: P" + payment);
    }

    /**
     * Updates the display of the total calories.
     * @param calories The new total calorie count to be displayed.
     */
    public void updateTotalCalories(double calories) {
        caloriesLabel.setText("Total Calories: " + calories);
    }

    /**
     * Sets the enabled state of the finalize button.
     * @param b A boolean value indicating whether the finalize button 
     *          should be enabled or disabled.
     */
    public void setFinalizeEnabled(boolean b) {
        finalizeButton.setEnabled(b);
    }
}
