import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarksCalculatorGUI extends JFrame {
    private JTextField[] markFields;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;

    public MarksCalculatorGUI() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Marks Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Marks Panel
        JPanel marksPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        marksPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        markFields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            marksPanel.add(new JLabel("Subject " + (i + 1) + " Marks:"));
            markFields[i] = new JTextField();
            marksPanel.add(markFields[i]);
        }
        add(marksPanel, BorderLayout.NORTH);

        // Results Panel
        JPanel resultsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        resultsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        resultsPanel.add(new JLabel("Total Marks:"));
        totalMarksLabel = new JLabel();
        resultsPanel.add(totalMarksLabel);
        resultsPanel.add(new JLabel("Average Percentage:"));
        averagePercentageLabel = new JLabel();
        resultsPanel.add(averagePercentageLabel);
        resultsPanel.add(new JLabel("Grade:"));
        gradeLabel = new JLabel();
        resultsPanel.add(gradeLabel);
        add(resultsPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(new Color(0, 153, 51));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 18));
        calculateButton.addActionListener(new CalculateActionListener());
        buttonPanel.add(calculateButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class CalculateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double[] marks = new double[markFields.length];
                for (int i = 0; i < markFields.length; i++) {
                    marks[i] = Double.parseDouble(markFields[i].getText());
                }

                Student student = new Student(marks);
                totalMarksLabel.setText(String.valueOf(student.getTotalMarks()));
                averagePercentageLabel.setText(String.format("%.2f", student.getAveragePercentage()));
                gradeLabel.setText(student.getGrade());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers for all marks.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MarksCalculatorGUI::new);
    }
}

