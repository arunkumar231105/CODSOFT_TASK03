import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface extends JFrame {
    private double balance;
    private JTextField amountField;
    private JTextArea messageArea;

    public ATMInterface(double initialBalance) {
        this.balance = initialBalance;

        setTitle("ATM Interface");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));

        JLabel balanceLabel = new JLabel("Current Balance: $" + balance);
        amountField = new JTextField();
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton exitButton = new JButton("Exit");
        messageArea = new JTextArea();
        messageArea.setEditable(false);

        add(balanceLabel);
        add(new JLabel("Amount:"));
        add(amountField);
        add(checkBalanceButton);
        add(depositButton);
        add(withdrawButton);
        add(exitButton);
        add(new JScrollPane(messageArea));

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void checkBalance() {
        messageArea.setText("Current Balance: $" + balance);
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0) {
                balance += amount;
                messageArea.setText("Deposited: $" + amount + "\nNew Balance: $" + balance);
                amountField.setText("");
            } else {
                messageArea.setText("Deposit amount must be positive.");
            }
        } catch (NumberFormatException e) {
            messageArea.setText("Invalid amount. Please enter a number.");
        }
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                messageArea.setText("Withdrawn: $" + amount + "\nNew Balance: $" + balance);
                amountField.setText("");
            } else if (amount > balance) {
                messageArea.setText("Insufficient balance.");
            } else {
                messageArea.setText("Invalid amount.");
            }
        } catch (NumberFormatException e) {
            messageArea.setText("Invalid amount. Please enter a number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ATMInterface(1000.0); // initial balance
            }
        });
    }
}
