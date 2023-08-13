import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordCounterGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WordCounterFrame frame = new WordCounterFrame();
            frame.setTitle("Word Counter");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class WordCounterFrame extends JFrame {
    private JTextArea inputTextArea;
    private JTextArea resultTextArea;
    private JButton countButton;

    public WordCounterFrame() {
        setLayout(new BorderLayout());

        inputTextArea = new JTextArea(8, 40); // Set rows and columns
        resultTextArea = new JTextArea(10, 40); // Set rows and columns
        countButton = new JButton("Count Words");

        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputTextArea.getText();
                String[] words = text.split("\\s+|\\p{Punct}");
                int wordCount = words.length;

                Set<String> stopWords = new HashSet<>();
                stopWords.add("the");
                stopWords.add("and");
                stopWords.add("in");
                stopWords.add("of");

                Map<String, Integer> wordFrequency = new HashMap<>();
                for (String word : words) {
                    word = word.toLowerCase();
                    if (!stopWords.contains(word)) {
                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    }
                }

                // Update the result text area
                resultTextArea.setText("Total word count: " + wordCount + "\n"
                        + "Total unique words: " + wordFrequency.size() + "\n"
                        + "Word frequency: " + wordFrequency);
            }
        });

        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        JScrollPane resultScrollPane = new JScrollPane(resultTextArea);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter your text:"));
        inputPanel.add(inputScrollPane);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(countButton);

        add(inputPanel, BorderLayout.NORTH);
        add(resultScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }
}
