package calculator;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CalculatorGUITest {
    private CalculatorGUI calculatorGUI;
    private final Map<String, JButton> buttonsMap = new HashMap<>();

    @BeforeEach
    public void setUp() {
        SwingUtilities.invokeLater(() -> {
            calculatorGUI = new CalculatorGUI();
            getButtonsFromPanel();
        });
    }

    private void getButtonsFromPanel() {
        for (Component component : calculatorGUI.getContentPane().getComponents()) {
            if (component instanceof JPanel) {
                JPanel buttonPanel = (JPanel) component;
                for (Component buttonComponent : buttonPanel.getComponents()) {
                    if (buttonComponent instanceof JButton) {
                        JButton button = (JButton) buttonComponent;
                        buttonsMap.put(button.getText(), button);
                    }
                }
            }
        }
    }

    @Test
    public void testAdd() {
        clickButtons("1 + 1 =");
        Assertions.assertEquals("2", calculatorGUI.getDisplayText());
    }

    @Test
    public void testSubtract() {
        clickButtons("7 - 3 =");
        Assertions.assertEquals("4", calculatorGUI.getDisplayText());
    }

    @Test
    public void testMultiply() {
        clickButtons("3 * 5 =");
        Assertions.assertEquals("15", calculatorGUI.getDisplayText());
    }

    @Test
    public void testDivide() {
        clickButtons("8 / 2 =");
        Assertions.assertEquals("4", calculatorGUI.getDisplayText());
    }

    @Test
    public void testDivideByZero() {
        clickButtons("5 / 0 =");
        Assertions.assertEquals("Error", calculatorGUI.getDisplayText());
    }

    private void clickButtons(String input) {
        for (char c : input.toCharArray()) {
            String label = String.valueOf(c);
            JButton button = buttonsMap.get(label);
            if (button != null) {
                SwingUtilities.invokeLater(button::doClick);
            }
        }
    }
}