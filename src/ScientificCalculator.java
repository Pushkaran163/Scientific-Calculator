import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private StringBuilder currentInput;
    
    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        currentInput = new StringBuilder();
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4, 10, 10));
        
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "sin", "cos", "tan", "log",
            "(", ")", "C", "CE"
        };
        
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            panel.add(button);
        }
        
        add(display, BorderLayout.NORTH);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "C":
                currentInput.setLength(0);
                display.setText("");
                break;
            case "CE":
                if (currentInput.length() > 0) {
                    currentInput.setLength(currentInput.length() - 1);
                    display.setText(currentInput.toString());
                }
                break;
            case "=":
                try {
                    double result = evaluate(currentInput.toString());
                    display.setText(String.valueOf(result));
                    currentInput.setLength(0);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "sin":
            case "cos":
            case "tan":
            case "log":
                currentInput.append(command).append("(");
                display.setText(currentInput.toString());
                break;
            default:
                currentInput.append(command);
                display.setText(currentInput.toString());
        }
    }

    private double evaluate(String expression) throws Exception {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operations = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                int num = 0;
                while (Character.isDigit(c)) {
                    num = num * 10 + (c - '0');
                    i++;
                    if (i < expression.length()) {
                        c = expression.charAt(i);
                    } else {
                        break;
                    }
                }
                i--;
                numbers.push((double) num);
            } else if (c == '(') {
                operations.push(c);
            } else if (c == ')') {
                while (operations.peek() != '(') {
                    numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == 's' || c == 'c' || c == 't' || c == 'l') {
                while (!operations.isEmpty() && precedence(c) <= precedence(operations.peek())) {
                    numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.push(c);
            }
        }
        while (!operations.isEmpty()) {
            numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
        }
        return numbers.pop();
    }

    private double applyOperation(char operation, double b, double a) {
        switch (operation) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
            case 's': // sin
                return Math.sin(Math.toRadians(b));
            case 'c': // cos
                return Math.cos(Math.toRadians(b));
            case 't': // tan
                return Math.tan(Math.toRadians(b));
            case 'l': // log
                return Math.log10(b);
        }
        return 0;
    }

    private int precedence(char operation) {
        switch (operation) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case 's':
            case 'c':
            case 't':
            case 'l':
                return 3;
        }
        return -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ScientificCalculator calculator = new ScientificCalculator();
            calculator.setVisible(true);
        });
    }
}