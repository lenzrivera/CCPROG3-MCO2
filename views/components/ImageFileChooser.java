package views.components;

import java.awt.GridLayout;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageFileChooser extends JPanel {
    private String fileFullPath;
    
    private JLabel mainLabel;
    private JLabel filepathLabel;
    private JButton chooseButton;
    private JFileChooser fileChooser;
    
    public ImageFileChooser(String mainLabel) {
        super(new GridLayout(1, 3));

        fileFullPath = "";

        this.mainLabel = new JLabel(mainLabel);
        add(this.mainLabel);

        filepathLabel = new JLabel("");
        add(filepathLabel);

        chooseButton = new JButton("Select");
        add(chooseButton);

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

        if (value == null) {
            filepathLabel.setText("[none]");
        } else {
            filepathLabel.setText(Paths.get(value).getFileName().toString());
        }
    }
}
