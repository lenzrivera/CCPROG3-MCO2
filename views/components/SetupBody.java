package views.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * This class represents a section in the setup panel, containing a
 * next button to advance the setup.
 *
 * @param <T> the type of the content component to be added to the SetupBody.
 */
public class SetupBody<T extends JComponent> extends JPanel {
    /**
     * The content of the SetupBody.
     */
    private T content;

    /**
     * The next button.
     */
    private JButton nextButton;

    /**
     * Creates a new SetupBody with the specified content component.
     * @param content the content component to be displayed in the SetupBody.
     */
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

    /**
     * Returns the content component of the SetupBody.
     * @return the content component.
     */
    public T getContent() {
        return content;
    }

    /**
     * Sets the listener for the "Next" button.
     * @param listener The listener for the button.
     */
    public void setNextButtonListener(ActionListener listener) {
        nextButton.addActionListener(listener);
    }
}
