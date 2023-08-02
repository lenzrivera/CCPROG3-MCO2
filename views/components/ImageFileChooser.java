package views.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class wraps around a JFileChooser to allow for image file selections. 
 */
public class ImageFileChooser extends JPanel {
    /**
     * The full path of the selected image
     */
    private String fileFullPath;

    /**
     * The main label of the file chooser
     */
    private JLabel mainLabel;

    /**
     * Tha main label indicating the name of the selected file
     */
    private JLabel filename;

    /**
     * The button that pops up the file chooser
     */
    private JButton chooseButton;

    /**
     * The file chooser dialog
     */
    private JFileChooser fileChooser;

    /**
     * Constructs an ImageFileChooser instance with the specified main label.
     * @param mainLabel the main label to be displayed on the file chooser.
     */
    public ImageFileChooser(String mainLabel) {
        super(new GridBagLayout());

        fileFullPath = "";

        GridBagConstraints gbc = new GridBagConstraints();
        this.mainLabel = new JLabel(mainLabel);
        gbc.gridx = 0;
        add(this.mainLabel, gbc);

        filename = new JLabel("");
        filename.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        add(filename, gbc);

        chooseButton = new JButton("Select");
        gbc.gridx = 2;
        gbc.weightx = 0.0;
        add(chooseButton, gbc);

        FileNameExtensionFilter filter =
            new FileNameExtensionFilter("Image Files", "jpg", "png");

        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);

        chooseButton.addActionListener(e -> {
            int returnVal = fileChooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String filepath = fileChooser.getSelectedFile().getAbsolutePath();
                setFilePath(filepath);
            }
        });
    }

    /**
     * Returns the full path of the selected image file.
     * @return the full path of the selected image file.
     */
    public String getFilePath() {
        return fileFullPath;
    }

    /**
     * Sets the full path of the selected image file and updates the displayed 
     * file name label accordingly. If the provided value is null or empty, the 
     * file name label will display "[none]".
     * @param value the full path of the selected image file.
     */
    public void setFilePath(String value) {
        fileFullPath = value;

        if (value == null || value.isBlank()) {
            filename.setText("[none]");
        } else {
            filename.setText(Paths.get(value).getFileName().toString());
        }
    }
}
