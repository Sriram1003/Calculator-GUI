
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class m1proj extends JFrame implements ActionListener {

    private StringBuilder expression;
    private JTextField T1;
    private float result;
    private char operation;
    private Panel nPanel, JPanel;
    private Button[] numButtons;
    private Button add, sub, mul, div, mod, clear, eq;

    public m1proj() {
        expression = new StringBuilder();
        T1 = new JTextField(30);
        T1.setEditable(false);
        T1.setHorizontalAlignment(JTextField.RIGHT);

        nPanel = new Panel();
        nPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        nPanel.add(T1);

        JPanel = new Panel();
        JPanel.setLayout(new GridLayout(4, 4, 3, 3));

        numButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new Button("" + i);
            JPanel.add(numButtons[i]);
            numButtons[i].addActionListener(this);
        }

        add = new Button("+");
        sub = new Button("-");
        mul = new Button("*");
        div = new Button("/");
        mod = new Button("%");
        clear = new Button("C");
        eq = new Button("=");

        JPanel.add(add);
        JPanel.add(sub);
        JPanel.add(mul);
        JPanel.add(div);
        JPanel.add(mod);
        JPanel.add(clear);
        JPanel.add(eq);

        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);
        mod.addActionListener(this);
        clear.addActionListener(this);
        eq.addActionListener(this);

        this.setLayout(new BorderLayout());
        add(nPanel, BorderLayout.NORTH);
        add(JPanel, BorderLayout.CENTER);

        setTitle("Calculator");
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if (Character.isDigit(command.charAt(0))) {
            expression.append(command);
            T1.setText(expression.toString());
        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/") || command.equals("%")) {
            expression.append(" ").append(command).append(" ");
            T1.setText(expression.toString());
        } else if (command.equals("=")) {
            try {

                result = evaluateExpression(expression.toString());
                T1.setText(String.valueOf(result));
                expression.setLength(0);
                expression.append(result);
            } catch (Exception e) {
                T1.setText("Error");
                expression.setLength(0);
            }
        } else if (command.equals("C")) {
            expression.setLength(0);
            T1.setText("");
        }
    }

    private float evaluateExpression(String expr) {
        String[] tokens = expr.split(" ");
        float result = Float.parseFloat(tokens[0]);

        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            float operand = Float.parseFloat(tokens[i + 1]);

            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    if (operand != 0) {
                        result /= operand;
                    } else {
                        throw new ArithmeticException("Division by zero");
                    }
                    break;
                case "%":
                    result %= operand;
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        new m1proj();
    }
}
