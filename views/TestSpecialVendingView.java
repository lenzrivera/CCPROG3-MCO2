package views;

import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeListener;

import model.Denomination;
import util.View;
import views.components.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestSpecialVendingView extends View {
    private SectionContainer mainContainer;

    private List<SpecialItemDisplay> specialItemDisplays;
    private List<PresetDisplay> presetDisplays;

    private JPanel itemsPanel;
    private JPanel leftSidePanel;
    private JPanel logPanel;
    private JPanel optionsPanel;
    private JPanel presetsPanel;
    private JPanel rightSidePanel;
    private JPanel mainPanel;
    private JPanel leftSideOptionsPanel;
    private JPanel rightSideOptionsPanel;
    private JPanel rightSideOptionsSubPanel;
    private JButton finalizeButton;
    private JButton inputCreditButton;
    private JButton returnCreditButton;
    private JButton backButton;
    private JLabel creditLabel;
    private JLabel paymentLabel;
    private JLabel caloriesLabel;
    private JScrollPane logScrollPane;
    private JScrollPane presetsScrollPane;
    private JScrollPane itemsScrollPane;
    private JScrollPane dialogScrollPane;
    private JTextArea logTextArea;
    private JTextArea dialogTextArea;
    private JDialog returnDenomDialog;
    private JComboBox<Double> denomComboBox;

    public TestSpecialVendingView() {
        mainContainer = new SectionContainer("");

        specialItemDisplays = new ArrayList<>();

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
        backButton = new JButton();
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

        mainPanel.setBorder(BorderFactory.createTitledBorder("Test Vending Features"));
        mainPanel.setPreferredSize(new Dimension(850, 525));
        mainPanel.setLayout(new GridLayout(1, 2));

        leftSidePanel.setLayout(new GridLayout(2, 1));

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

        backButton.setText("Back");
        backButton.setMaximumSize(new Dimension(125, 25));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(20, 0, 0, 0);
        rightSideOptionsPanel.add(backButton, gridBagConstraints);

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

    public PresetDisplay getPresetDisplay(int slotNo) {
        return presetDisplays.get(slotNo - 1);
    }

    public SpecialItemDisplay getItemDisplay(int slotNo) {
        return specialItemDisplays.get(slotNo - 1);
    }

    public double getSelectedDenom() {
        return (double) denomComboBox.getSelectedItem();
    }

    public void setHeading(String heading) {
        mainContainer.setHeading(heading);
    }

    public void setDenominations(List<Double> denominations) {
        DefaultComboBoxModel<Double> model = new DefaultComboBoxModel<>();
        model.addAll(denominations);

        denomComboBox.setModel(model);
        denomComboBox.setSelectedIndex(0);
    }

    /* */

    public void addSlot(
            String name,
            double price,
            double calories,
            int itemStock,
            String imagePath,
            boolean enabled
    ) throws IOException {
        SpecialItemDisplay specialItemDisplay = new SpecialItemDisplay(
                name,
                calories,
                price,
                itemStock,
                imagePath
        );

        specialItemDisplay.setSpinnerEnabled(true);

        itemsPanel.add(specialItemDisplay);
        itemsPanel.add(Box.createVerticalStrut(10));

        specialItemDisplays.add(specialItemDisplay);
    }

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

    public void inputLog(String text) {
        if (logTextArea.getText().isEmpty()) {
            logTextArea.setText(text);
        } else {
            logTextArea.setText(logTextArea.getText() + "\n" + text);
        }
    }

    /* */

    public void setExitButtonListener(ActionListener listener) {
        mainContainer.setExitButtonListener(listener);
    }

    public void setInputCreditButtonListener(ActionListener listener) {
        returnCreditButton.addActionListener(listener);
    }

    public void setReturnCreditButtonListener(ActionListener listener) {
        inputCreditButton.addActionListener(listener);
    }

    public void setFinalizeButtonListener(ActionListener listener) {
        finalizeButton.addActionListener(listener);
    }

    public void updateTotalCredit(double credit) {
        creditLabel.setText("Credit/Balance: P" + credit);
    }

    public void updateTotalPayment(double payment) {
        paymentLabel.setText("Total Payment: P" + payment);
    }

    public void updateTotalCalories(double calories) {
        caloriesLabel.setText("Total Calories: " + calories);
    }
}
