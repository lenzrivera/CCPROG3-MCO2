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

public class TestRegularVendingView extends View {
    private SectionContainer mainContainer;

    private List<ItemDisplay> itemDisplays;

    private JPanel mainPanel;
    private JPanel itemsPanel;
    private JPanel rightSidePanel;
    private JPanel upperRightSidePanel;
    private JPanel lowerRightSidePanel;
    private JButton inputCreditButton;
    private JButton returnCreditButton;
    private JLabel creditLabel;
    private JScrollPane logScrollPane;
    private JScrollPane itemsScrollPane;
    private JScrollPane dialogScrollPane;
    private JTextArea dialogTextArea;
    private JTextArea logTextArea;
    private JComboBox<Double> denomComboBox;

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

        dialogTextArea.setEditable(false);
        dialogTextArea.setColumns(20);
        dialogTextArea.setLineWrap(true);
        dialogTextArea.setRows(5);
        dialogTextArea.setWrapStyleWord(true);
        dialogScrollPane.setViewportView(dialogTextArea);

        mainPanel.setBorder(BorderFactory.createTitledBorder("Test Vending Features"));
        mainPanel.setPreferredSize(new Dimension(850, 525));
        mainPanel.setLayout(new GridLayout(1, 2));

        itemsScrollPane.setBorder(BorderFactory.createTitledBorder("Items"));
        itemsScrollPane.setPreferredSize(new Dimension(250, 250));

        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.PAGE_AXIS));
        itemsScrollPane.setViewportView(itemsPanel);

        mainPanel.add(itemsScrollPane);

        rightSidePanel.setPreferredSize(new Dimension(250, 250));
        rightSidePanel.setLayout(new GridLayout(2, 1));

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
        ItemDisplay itemDisplay = new ItemDisplay(
            name, 
            calories, 
            price, 
            itemStock,
            imagePath
        );
        itemDisplay.setButtonEnabled(enabled);

        itemsPanel.add(itemDisplay);
        itemsPanel.add(Box.createVerticalStrut(10));

        itemDisplays.add(itemDisplay);
    }

    public void displayDenominations(
        String heading, 
        Map<Denomination, Integer> denomMap
    ) {
        DisplayTable table = new DisplayTable(new String[] { 
            "Denomination", 
            "Quantity" 
        });
        
        int i = 0;
        
        for (var entry : denomMap.entrySet()) {
            table.setCell(0, i, entry.getKey().getValue());
            table.setCell(1, i, entry.getValue());

            i += 1;
        }
    
        JOptionPane.showMessageDialog(
            mainContainer, 
            table, 
            heading, 
            JOptionPane.PLAIN_MESSAGE
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
        inputCreditButton.addActionListener(listener);
    }

    public void setReturnCreditButtonListener(ActionListener listener) {
        returnCreditButton.addActionListener(listener);
    }

    public void setSlotSelectListener(int slotNo, ActionListener listener) {    
        itemDisplays.get(slotNo - 1).setSelectButtonListener(listener);
    }
    
    public void updateTotalCredit(double credit) {
        creditLabel.setText("Credit/Balance: P" + credit);
    }
}
