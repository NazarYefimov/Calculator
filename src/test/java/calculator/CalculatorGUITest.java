package calculator;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorGUITest {
    private CalculatorGUI calculatorGUI;

    @BeforeEach
    public void setUp() {
        calculatorGUI = new CalculatorGUI();
    }

    @Test
    public void testAdd() {
        pressButtons("3 + 5 =");
        Assertions.assertEquals("8", calculatorGUI.getDisplayText());
    }

    @Test
    public void testSubtract() {
        pressButtons("10 - 4 =");
        Assertions.assertEquals("6", calculatorGUI.getDisplayText());
    }

    @Test
    public void testMultiply() {
        pressButtons("2 * 3 =");
        Assertions.assertEquals("6", calculatorGUI.getDisplayText());
    }

    @Test
    public void testDivide() {
        pressButtons("10 / 2 =");
        Assertions.assertEquals("5", calculatorGUI.getDisplayText());
    }

    @Test
    public void testDivideByZero() {
        pressButtons("10 / 0 =");
        Assertions.assertEquals("Error", calculatorGUI.getDisplayText());
    }

    private void pressButtons(String buttonSequence) {
        for (char c : buttonSequence.toCharArray()) {
            pressButton(c);
        }
    }

    private void pressButton(char c) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Component[] components = calculatorGUI.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals(String.valueOf(c))) {
                    SwingUtilities.invokeLater(() -> button.doClick());
                    return;
                }
            }
        }
    }
    }
