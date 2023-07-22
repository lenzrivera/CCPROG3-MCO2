package views.components;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SectionContainer extends JPanel {
    private JLabel header;

    public SectionContainer(String heading) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new EmptyBorder(5, 10, 10, 10));

        header = new JLabel(heading);
        header.setAlignmentY(Component.TOP_ALIGNMENT); 
        header.setFont(new Font("Sans-Serif", Font.PLAIN, 24));
        add(header);

        add(Box.createVerticalStrut(5));
    }

    public void setBody(Component body) {
        add(body);
    }
}
