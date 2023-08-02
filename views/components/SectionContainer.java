package views.components;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * This class represents a particular section in the program, having a heading,
 * exit button, and a content body which is settable.
 */
public class SectionContainer extends JPanel {
    /**
     * The header of the section.
     */
    private JPanel header;

    /**
     * The heading of the section.
     */
    private JLabel heading;

    /**
     * The exit button.
     */
    private JButton exitButton;

    /**
     * Creates a new SectionContainer with the given heading text.
     * @param headingText the text to display as the heading of the section.
     */
    public SectionContainer(String headingText) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new EmptyBorder(5, 10, 10, 10));

        header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.LINE_AXIS));
        add(header);
        
        heading = new JLabel(headingText);
        heading.setFont(new Font("Sans-Serif", Font.PLAIN, 24));
        header.add(heading);

        header.add(Box.createHorizontalGlue());

        exitButton = new JButton("X");
        header.add(exitButton);

        add(Box.createVerticalStrut(5));
    }

    /**
     * Sets the body component to be displayed inside the section container.
     * @param body the body component to be displayed.
     */
    public void setBody(JComponent body) {
        add(body);
    }

    /**
     * Sets the heading text of the section.
     * @param heading the heading text
     */
    public void setHeading(String heading) {
        this.heading.setText(heading);
    }

    /**
     * Adds a listener for the exit button.
     * @param listener The listener for the exit button
     */
    public void setExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }
}
