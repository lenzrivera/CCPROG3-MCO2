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

public class SectionContainer extends JPanel {
    private JPanel header;
    private JLabel heading;
    private JButton exitButton;

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

    public void setBody(JComponent body) {
        add(body);
    }

    public void setExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }
}
