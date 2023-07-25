package views.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridy = 0;
        gbc.weighty = 1.0;

        this.content = content;
        add(this.content, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.0;

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
