import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextFileAnalyzer extends JFrame{
    private JTextArea displayArea;
    private JTextField searchField;
    private JLabel statusLabel;
    private File currentFile;

    public TextFileAnalyzer(){
        setTitle("Text File Analyzer - Internship Task 5");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        initializeUI();
    }

    private void initializeUI() {
        JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton openButton = new JButton("Select Text File");
        openButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Search Word:"));
        searchField = new JTextField(15);
        JButton searchButton = new JButton("Search / Refresh");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        topPanel.add(openButton);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(searchPanel);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        displayArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(displayArea);

        statusLabel = new JLabel("Select a file to begin analysis.");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseAndAnalyzeFile();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentFile != null) {
                    analyzeFile(currentFile);
                } else {
                    JOptionPane.showMessageDialog(TextFileAnalyzer.this, "Please select a file first!");
                }
            }
        });
    }

    private void chooseAndAnalyzeFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            analyzeFile(currentFile);
        }
    }

    private void analyzeFile(File file) {
        int lines = 0, words = 0, chars = 0, searchOccurrences = 0;
        String targetWord = searchField.getText().trim().toLowerCase();
        StringBuilder contentSummary = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines++;
                chars += line.length();

                if (!line.trim().isEmpty()) {
                    String[] wordsArray = line.trim().split("\\s+");
                    words += wordsArray.length;

                    if (!targetWord.isEmpty()) {
                        for (String word : wordsArray) {
                            if(word.replaceAll("[^a-zA-Z]", "").equalsIgnoreCase(targetWord)) {
                                searchOccurrences++;
                            }
                        }
                    }
                }
            }

            contentSummary.append("         ANALYSIS REPORT\n");
            contentSummary.append("------------------------------\n");
            contentSummary.append("File Name: ").append(file.getName()).append("\n");
            contentSummary.append("Total Lines: ").append(lines).append("\n");
            contentSummary.append("Total Words: ").append(words).append("\n");
            contentSummary.append("Total Characters: ").append(chars).append("\n");

            if (!targetWord.isEmpty()) {
                contentSummary.append("Occurrences of '").append(targetWord).append("':").append(searchOccurrences);
            }

            displayArea.setText(contentSummary.toString());
            statusLabel.setText("Analysis complete for: " + file.getName());
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TextFileAnalyzer().setVisible(true);
        });
    }
}
