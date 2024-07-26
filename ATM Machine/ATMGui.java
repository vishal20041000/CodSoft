import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGui extends JFrame {
    private ATM atm;
    private JTextField amountField;
    private JLabel balanceLabel;

    public ATMGui() {
        BankAccount account = new BankAccount(1000); // Initial balance
        atm = new ATM(account);
        initializeUI();
    }

    private void initializeUI() {
        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Balance Panel
        JPanel balancePanel = new JPanel();
        balancePanel.add(new JLabel("Current Balance:"));
        balanceLabel = new JLabel("$" + atm.checkBalance());
        balancePanel.add(balanceLabel);
        add(balancePanel, BorderLayout.NORTH);

        // Amount Panel
        JPanel amountPanel = new JPanel();
        amountPanel.add(new JLabel("Amount:"));
        amountField = new JTextField(10);
        amountPanel.add(amountField);
        add(amountPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new DepositActionListener());
        buttonPanel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new WithdrawActionListener());
        buttonPanel.add(withdrawButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class DepositActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    atm.deposit(amount);
                    balanceLabel.setText("$" + atm.checkBalance());
                    JOptionPane.showMessageDialog(null, "Deposit successful.");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a positive amount.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
            }
        }
    }

    private class WithdrawActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    if (atm.withdraw(amount)) {
                        balanceLabel.setText("$" + atm.checkBalance());
                        JOptionPane.showMessageDialog(null, "Withdrawal successful.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient balance.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a positive amount.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATMGui::new);
    }
}

