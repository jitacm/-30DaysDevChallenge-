import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileCompressionTool extends JFrame {
    private JButton selectFileButton, compressButton, decompressButton;
    private JLabel selectedFileLabel, statusLabel;
    private File selectedFile;

    public FileCompressionTool() {
        setTitle("File Compression Tool");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        selectFileButton = new JButton("Select File");
        compressButton = new JButton("Compress");
        decompressButton = new JButton("Decompress");

        selectedFileLabel = new JLabel("No file selected");
        statusLabel = new JLabel("Status: Waiting for input");

        add(selectFileButton);
        add(selectedFileLabel);
        add(compressButton);
        add(decompressButton);
        add(statusLabel);

        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    selectedFileLabel.setText("Selected: " + selectedFile.getName());
                    statusLabel.setText("Status: Ready to compress/decompress");
                }
            }
        });

        compressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile != null) {
                    statusLabel.setText("Status: Compressing...");
                    CompressionHandler.compressFile(selectedFile);
                    statusLabel.setText("Status: Compression complete!");
                } else {
                    statusLabel.setText("Status: No file selected");
                }
            }
        });

        decompressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile != null) {
                    statusLabel.setText("Status: Decompressing...");
                    CompressionHandler.decompressFile(selectedFile);
                    statusLabel.setText("Status: Decompression complete!");
                } else {
                    statusLabel.setText("Status: No file selected");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FileCompressionTool().setVisible(true);
            }
        });
    }
}
