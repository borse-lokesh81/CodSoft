import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class ATMApplication {

    public static void main(String[] args) {
        // Initialize the BankAccount with $1000
        BankAccount account = new BankAccount(1000.00);
        
        // Launch the ATM GUI
        SwingUtilities.invokeLater(() -> new ATMGUI(account));
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class ATMGUI {
    private BankAccount account;
    private JFrame frame;
    private JTextField amountField;
    private JTextArea textArea;

    public ATMGUI(BankAccount account) {
        this.account = account;
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("ATM Application");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title Label
        JLabel titleLabel = new JLabel("Welcome to the ATM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(titleLabel, gbc);

        // Amount Field
        JLabel lblAmount = new JLabel("Amount:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(lblAmount, gbc);

        amountField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(amountField, gbc);

        // Buttons
        JButton btnCheckBalance = new JButton("Check Balance");
        JButton btnDeposit = new JButton("Deposit");
        JButton btnWithdraw = new JButton("Withdraw");
        JButton btnExit = new JButton("Exit");

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(btnCheckBalance, gbc);

        gbc.gridy = 3;
        frame.add(btnDeposit, gbc);

        gbc.gridy = 4;
        frame.add(btnWithdraw, gbc);

        gbc.gridy = 5;
        frame.add(btnExit, gbc);

        // Status Text Area
        textArea = new JTextArea(5, 30);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(textArea);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 6;
        frame.add(scrollPane, gbc);

        // Add action listeners
        btnCheckBalance.addActionListener(new CheckBalanceListener());
        btnDeposit.addActionListener(new DepositListener());
        btnWithdraw.addActionListener(new WithdrawListener());
        btnExit.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    private class CheckBalanceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.setText("Current Balance: " + formatCurrency(account.getBalance()));
        }
    }

    private class DepositListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount <= 0) {
                    textArea.setText("Deposit amount must be positive.");
                    return;
                }
                account.deposit(amount);
                textArea.setText("Deposited: " + formatCurrency(amount) + "\nNew Balance: " + formatCurrency(account.getBalance()));
            } catch (NumberFormatException ex) {
                textArea.setText("Invalid amount. Please enter a valid number.");
            }
        }
    }

    private class WithdrawListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount <= 0) {
                    textArea.setText("Withdrawal amount must be positive.");
                    return;
                }
                if (account.withdraw(amount)) {
                    textArea.setText("Withdrew: " + formatCurrency(amount) + "\nNew Balance: " + formatCurrency(account.getBalance()));
                } else {
                    textArea.setText("Insufficient funds or invalid amount.");
                }
            } catch (NumberFormatException ex) {
                textArea.setText("Invalid amount. Please enter a valid number.");
            }
        }
    }

    private String formatCurrency(double amount) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        return currencyFormat.format(amount);
    }
}
