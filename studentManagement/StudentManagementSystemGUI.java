import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentManagementSystemGUI extends JFrame {
    private StudentManagementSystem sms;
    private JTextField nameField, rollNumberField, gradeField, emailField;
    private DefaultTableModel tableModel;
    private JTable table;

    public StudentManagementSystemGUI() {
        sms = new StudentManagementSystem();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);
        formPanel.add(new JLabel("Roll Number:"));
        rollNumberField = new JTextField();
        formPanel.add(rollNumberField);
        formPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        formPanel.add(gradeField);
        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new AddStudentActionListener());
        formPanel.add(addButton);
        JButton removeButton = new JButton("Remove Student");
        removeButton.addActionListener(new RemoveStudentActionListener());
        formPanel.add(removeButton);

        add(formPanel, BorderLayout.NORTH);

        // Table Panel
        tableModel = new DefaultTableModel(new String[]{"Name", "Roll Number", "Grade", "Email"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        add(tableScrollPane, BorderLayout.CENTER);

        // Load existing students into table
        loadStudentsIntoTable();

        setVisible(true);
    }

    private void loadStudentsIntoTable() {
        List<Student> students = sms.getAllStudents();
        for (Student student : students) {
            tableModel.addRow(new Object[]{student.getName(), student.getRollNumber(), student.getGrade(), student.getEmail()});
        }
    }

    private class AddStudentActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty.");
                return;
            }

            int rollNumber;
            try {
                rollNumber = Integer.parseInt(rollNumberField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid roll number. Please enter a valid integer.");
                return;
            }

            String grade = gradeField.getText();
            if (grade.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Grade cannot be empty.");
                return;
            }

            String email = emailField.getText();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Email cannot be empty.");
                return;
            }

            Student student = new Student(name, rollNumber, grade, email);
            sms.addStudent(student);
            tableModel.addRow(new Object[]{name, rollNumber, grade, email});
            clearFormFields();
        }
    }

    private class RemoveStudentActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a student to remove.");
                return;
            }

            int rollNumber = (int) tableModel.getValueAt(selectedRow, 1);
            sms.removeStudent(rollNumber);
            tableModel.removeRow(selectedRow);
        }
    }

    private void clearFormFields() {
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementSystemGUI::new);
    }
}

