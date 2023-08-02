package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame {
    private JTextField displayTextField;
    private StringBuilder input;

    public CalculatorGUI() {
        input = new StringBuilder();
        displayTextField = new JTextField(20);
        displayTextField.setEditable(false);
        displayTextField.setFont(new Font("Arial", Font.PLAIN, 20));

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));

        String[] buttonLabels = {
                "1", "2", "3", "/",
                "4", "5", "6", "*",
                "7", "8", "9", "-",
                "0", "Clear", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        setLayout(new BorderLayout());
        add(displayTextField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getDisplayText() {
        return displayTextField.getText();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("=")) {
                calculateResult();
            } else if (command.equals("Clear")) {
                input.setLength(0);
                displayTextField.setText("");
            } else {
                input.append(" ").append(command);
                displayTextField.setText(input.toString());
            }
        }
    }

    private void calculateResult() {
        try {
            int result = new Calculator().eval(input.toString());
            displayTextField.setText(String.valueOf(result));
            input.setLength(0);
            input.append(result);
        } catch (ArithmeticException ex) {
            displayTextField.setText("Error");
            input.setLength(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI());
    }
}