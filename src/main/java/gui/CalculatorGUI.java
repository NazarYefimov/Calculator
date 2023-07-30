package gui;

import calculator.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame {
    private JTextField displayField;
    private StringBuilder input;

    public CalculatorGUI() {
        input = new StringBuilder();
        displayField = new JTextField(20);
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.PLAIN, 20));

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
        add(displayField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("=")) {
                calculateResult();
            } else if (command.equals("Clear")) {
                input.setLength(0);
                displayField.setText("");
            } else {
                input.append(" ").append(command);
                displayField.setText(input.toString());
            }
        }
    }

    private void calculateResult() {
        try {
            int result = new Calculator().eval(input.toString());
            displayField.setText(String.valueOf(result));
            input.setLength(0);
            input.append(result);
        } catch (ArithmeticException ex) {
            displayField.setText("Error");
            input.setLength(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI());
    }
}
