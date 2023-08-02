package views;

import model.Denomination;
import views.components.SectionContainer;
import views.components.DisplayTable;
import views.components.ItemDisplay;
import util.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The TestRegularVendingView class represents a test view for the 
 * RegularVendingMachine application. This class extends the View 
 * class and serves as a graphical user interface (GUI) representation 
 * of the RegularVendingMachine application.
 */
public class TestRegularVendingView extends View {
/**
 * The main container for the vending machine user interface.
 */
private SectionContainer mainContainer;

/**
 * A list that stores the ItemDisplay components representing items available 
 * in the vending machine.
 */
private List<ItemDisplay> itemDisplays;

/**
 * The main panel of the vending machine user interface.
 */
private JPanel mainPanel;

/**
 * The panel that holds the item displays in the vending machine 
 * user interface.
 */
private JPanel itemsPanel;

/**
 * The right-side panel in the vending machine user interface.
 */
private JPanel rightSidePanel;

/**
 * The upper right-side panel in the vending machine user interface.
 */
private JPanel upperRightSidePanel;

/**
 * The lower right-side panel in the vending machine user interface.

 */
private JPanel lowerRightSidePanel;

/**
 * The button used for adding credit to the vending machine.
 */
private JButton inputCreditButton;

/**
 * The button used for returning credit from the vending machine.
 */
private JButton returnCreditButton;

/**
 * The label that displays the current credit balance in the 
 * vending machine.
 */
private JLabel creditLabel;

/**
 * The scroll pane that contains the log text area in the vending machine 
 * user interface.
 */
private JScrollPane logScrollPane;

/**
 * The scroll pane that contains the item displays in the vending machine 
 * user interface.
 */
private JScrollPane itemsScrollPane;

/**
 * The scroll pane that contains the dialog text area in the vending 
 * machine user interface.

 */
private JScrollPane dialogScrollPane;

/**
 * The text area used to display dialog messages in the vending machine 
 * user interface.

 */
private JTextArea dialogTextArea;

/**
 * The text area used to display log messages in the vending machine 
 * user interface.
 */
private JTextArea logTextArea;

/**
 * The combo box used to select denominations (e.g., coins or bills) 
 * for adding credit.
 */
private JComboBox<Double> denomComboBox;

    /**
     * Constructs a regular vending machine view for the interface.
     */
    public TestRegularVendingView() {
        mainContainer = new SectionContainer("");

        itemDisplays = new ArrayList<>();

        mainPanel = new JPanel();
        itemsScrollPane = new JScrollPane();
        itemsPanel = new JPanel();
        rightSidePanel = new JPanel();
        upperRightSidePanel = new JPanel();
        logScrollPane = new JScrollPane();
        dialogTextArea = new JTextArea();
        lowerRightSidePanel = new JPanel();
        creditLabel = new JLabel();
        inputCreditButton = new JButton();
        returnCreditButton = new JButton();
        dialogScrollPane = new JScrollPane();
        logTextArea = new JTextArea();
        denomComboBox = new JComboBox<>();

        /* Set dialog window */

        dialogTextArea.setEditable(false);
        dialogTextArea.setColumns(20);
        dialogTextArea.setLineWrap(true);
        dialogTextArea.setRows(5);
        dialogTextArea.setWrapStyleWord(true);
        dialogScrollPane.setViewportView(dialogTextArea);

        /* Set main panel */

        mainPanel.setBorder(BorderFactory.createTitledBorder("Test Vending Features"));
        mainPanel.setPreferredSize(new Dimension(850, 525));
        mainPanel.setLayout(new GridLayout(1, 2));

        /* Set items panel */

        itemsScrollPane.setBorder(BorderFactory.createTitledBorder("Items"));
        itemsScrollPane.setPreferredSize(new Dimension(250, 250));

        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.PAGE_AXIS));
        itemsScrollPane.setViewportView(itemsPanel);

        mainPanel.add(itemsScrollPane);

        rightSidePanel.setPreferredSize(new Dimension(250, 250));
        rightSidePanel.setLayout(new GridLayout(2, 1));

        /* Set log panel */

        upperRightSidePanel.setBorder(BorderFactory.createTitledBorder("Log"));

        logTextArea.setEditable(false);
        logTextArea.setColumns(20);
        logTextArea.setLineWrap(true);
        logTextArea.setRows(5);
        logTextArea.setWrapStyleWord(true);
        logScrollPane.setViewportView(logTextArea);

        GroupLayout upperRightSidePanelLayout = new GroupLayout(
            upperRightSidePanel
        );
        upperRightSidePanel.setLayout(upperRightSidePanelLayout);
        upperRightSidePanelLayout.setHorizontalGroup(
            upperRightSidePanelLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(
                    logScrollPane, 
                    GroupLayout.Alignment.TRAILING,
                    GroupLayout.DEFAULT_SIZE, 
                    410, 
                    Short.MAX_VALUE
                )
        );
        upperRightSidePanelLayout.setVerticalGroup(
            upperRightSidePanelLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(
                    logScrollPane, 
                    GroupLayout.DEFAULT_SIZE, 
                    228, 
                    Short.MAX_VALUE
                )
        );

        rightSidePanel.add(upperRightSidePanel);

        /* Set options panel */

        lowerRightSidePanel.setBorder(BorderFactory.createTitledBorder("Options"));

        creditLabel.setText("Credit/Balance: P" + 0.0);

        inputCreditButton.setText("Input Credit");

        returnCreditButton.setText("Return Credit");

        GroupLayout lowerRightSidePanelLayout = new GroupLayout(lowerRightSidePanel);
        lowerRightSidePanel.setLayout(lowerRightSidePanelLayout);
        lowerRightSidePanelLayout.setHorizontalGroup(
            lowerRightSidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(lowerRightSidePanelLayout
                    .createSequentialGroup().addContainerGap()
                .addGroup(lowerRightSidePanelLayout
                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(creditLabel, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(returnCreditButton, GroupLayout.Alignment.TRAILING,
                    GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addGroup(GroupLayout.Alignment.TRAILING,
                    lowerRightSidePanelLayout.createSequentialGroup()
                .addComponent(denomComboBox, 0, 198, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(inputCreditButton, GroupLayout.PREFERRED_SIZE,
                    182, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        lowerRightSidePanelLayout.setVerticalGroup(
            lowerRightSidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(
                    GroupLayout.Alignment.TRAILING, 
                    lowerRightSidePanelLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(
                        creditLabel, 
                        GroupLayout.DEFAULT_SIZE, 
                        54, 
                        Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(lowerRightSidePanelLayout
                    .createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(
                        inputCreditButton, 
                        GroupLayout.DEFAULT_SIZE, 
                        40, 
                        Short.MAX_VALUE
                    )
                    .addComponent(denomComboBox))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(returnCreditButton, GroupLayout.PREFERRED_SIZE,
                                                40, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addContainerGap())
        );

        rightSidePanel.add(lowerRightSidePanel);

        mainPanel.add(rightSidePanel);

        mainContainer.setBody(mainPanel);
    }

    @Override
    public Container getContainer() {
        return mainContainer;
    }

    /* */


    /**
     * Retrieves the ItemDisplay corresponding to the specified slot number.
     * @param slotNo The slot number for which to retrieve the corresponding 
     *               ItemDisplay.
     * @return The ItemDisplay component representing the item at the specified 
     *         slot number.
     * @throws IndexOutOfBoundsException If the slot number is invalid.
     */
    public ItemDisplay getItemDisplay(int slotNo) {
        return itemDisplays.get(slotNo - 1);
    }

    /**
     * Retrieves the selected denomination value from the denominations 
     * combo box.
     * @return The selected denomination value as a double.
     */
    public double getSelectedDenom() {
        return (double) denomComboBox.getSelectedItem();
    }

    /**
     * Sets the heading of the main container in the vending machine user 
     * interface.
     * @param heading The new heading or title to be set for the main 
     *                container.
     */
    public void setHeading(String heading) {
        mainContainer.setHeading(heading);
    }

    /**
     * Sets the denominations available in the denominations combo box.
     * @param denominations The list of Double values representing the 
     *                      available denominations to be displayed in 
     *                      the combo box.
     */
    public void setDenominations(List<Double> denominations) {
        DefaultComboBoxModel<Double> model = new DefaultComboBoxModel<>();
        model.addAll(denominations);

        denomComboBox.setModel(model);
        denomComboBox.setSelectedIndex(0);
    }

    /* */

    /**
     * Adds a new item slot to the vending machine user interface.
     * @param name         name of the item.
     * @param price        price of the item.
     * @param calories     calories of the item.
     * @param itemStock    stock of the item.
     * @param imagePath    image path of the item.
     * @param enabled      A boolean value.
     * @throws IOException If an error occurs while reading the image 
     *                     file from the provided imagePath.
     */
    public void addSlot(
        String name,
        double price,
        double calories,
        int itemStock,
        String imagePath,
        boolean enabled
    ) {
        ItemDisplay itemDisplay = new ItemDisplay(
            name, 
            calories, 
            price, 
            itemStock,
            imagePath
        );
        itemDisplay.setSelectEnable(enabled);

        itemsPanel.add(itemDisplay);
        itemsPanel.add(Box.createVerticalStrut(10));

        itemDisplays.add(itemDisplay);
    }


    /**
     * Displays a dialog with the denominations and their quantities in the 
     * vending machine.
     * @param heading  The heading or title to be displayed at the top of 
     *                 the dialog.
     * @param denomMap The Map containing the denominations 
     *                 (represented by Denomination) 
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
     * Sets the exit button listener for the vending machine user interface.
     * @param listener The ActionListener to be set for the exit button.
     *                 It defines the actions to be performed when the 
     *                 exit button is clicked.
     */
    public void setExitButtonListener(ActionListener listener) {
        mainContainer.setExitButtonListener(listener);
    }

    /**
     * Sets the input credit button listener for the vending machine user 
     * interface.
     * @param listener The ActionListener to be set for the input credit 
     *                 button.
     */
    public void setInputCreditButtonListener(ActionListener listener) {
        // Set the provided ActionListener for the input credit button
        inputCreditButton.addActionListener(listener);
    }

    /**
     * Sets the return credit button listener for the vending machine 
     * user interface.
     * @param listener The ActionListener to be set for the return 
     * credit button.
     */
    public void setReturnCreditButtonListener(ActionListener listener) {
        // Set the provided ActionListener for the return credit button
        returnCreditButton.addActionListener(listener);
    }

    /**
     * Updates the total credit balance displayed in the vending machine 
     * user interface.
     * @param credit The new credit balance to be displayed in the vending 
     * machine user interface.
     */
    public void updateTotalCredit(double credit) {
        creditLabel.setText("Credit/Balance: P" + credit);
    }
}
