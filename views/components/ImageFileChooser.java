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

public class ImageFileChooser extends JPanel {
    private String fileFullPath;
    
    private JLabel mainLabel;
    private JLabel filepathLabel;
    private JButton chooseButton;
    private JFileChooser fileChooser;
    
    public ImageFileChooser(String mainLabel) {
        super(new GridBagLayout());

        fileFullPath = "";

        GridBagConstraints gbc = new GridBagConstraints();
        this.mainLabel = new JLabel(mainLabel);
        gbc.gridx = 0;
        add(this.mainLabel, gbc);

        filepathLabel = new JLabel("");
        filepathLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        add(filepathLabel, gbc);

        chooseButton = new JButton("Select");
        gbc.gridx = 2;
        gbc.weightx = 0.0;
        add(chooseButton, gbc);

        // TODO: actually disallow non-image files as this can be circumvented
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

    public String getFilePath() {
        return fileFullPath;
    }

    public void setFilePath(String value) {
        fileFullPath = value;

        if (value == null || value.isBlank()) {
            filepathLabel.setText("[nonaaaaaae]");
        } else {
            filepathLabel.setText(Paths.get(value).getFileName().toString());
        }
    }
}
