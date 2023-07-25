package views.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class SetupBody<T extends JComponent> extends JPanel {
    private T content;
    private JButton nextButton;

    public SetupBody(T content) {
        super(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        this.content = content;
        add(this.content, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.ipadx = 100;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.insets.set(5, 5, 5, 5);

        nextButton = new JButton("Next");
        add(nextButton, gbc);
    }

    public T getContent() {
        return content;
    }

    public void setNextButtonListener(ActionListener listener) {
        nextButton.addActionListener(listener);
    }
}
