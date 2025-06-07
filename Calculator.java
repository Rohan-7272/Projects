package basics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    JTextField inputField;
    double num1, num2, result;
    char operator;

    Calculator() {
        setTitle("Simple Calculator");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.BOLD, 30));
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        inputField.setEditable(false);
        add(inputField, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        String[] buttonLabels = {
                "0", "C", "=", "+",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
        };
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.addActionListener(this);
            panel.add(button);
        }
        add(panel);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.charAt(0) >= '0' && cmd.charAt(0) <= '9') {
            inputField.setText(inputField.getText() + cmd);
        } else if (cmd.equals("C")) {
            inputField.setText("");
            num1 = num2 = result = 0;
        } else if (cmd.equals("=")) {
            try {
                num2 = Double.parseDouble(inputField.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 == 0) {
                            inputField.setText("Error");
                            return;
                        }
                        result = num1 / num2; break;
                }
                inputField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                inputField.setText("Error");
            }
        } else {
            try {
                num1 = Double.parseDouble(inputField.getText());
                operator = cmd.charAt(0);
                inputField.setText("");
            } catch (NumberFormatException ex) {
                inputField.setText("Error");
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
