import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {

    private JPanel calculator;
    private JButton twoBtn;
    private JButton threeBtn;
    private JButton fourBtn;
    private JButton fiveBtn;
    private JButton sixBtn;
    private JButton sevenBtn;
    private JButton eightBtn;
    private JButton nineBtn;
    private JButton exitBtn;
    private JButton zeroBtn;
    private JButton dotBtn;
    private JButton clearButton;
    private JButton oneBtn;
    private JButton plusBtn;
    private JButton minusBtn;
    private JButton devideBtn;
    private JButton multiplyBtn;
    private JButton powerBtn;
    private JButton squarBtn;
    private JButton calculateBtn;
    private JTextField textInput;

    private double firstNumber = 0;
    private double secondNumber = 0;
    private char operator;

    public Calculator() {

        setTitle("Calculator");
        setContentPane(calculator);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //exit button implementation
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Number buttons
        ActionListener numberListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = ((JButton) e.getSource()).getText();
                textInput.setText(textInput.getText() + number);
            }
        };

        zeroBtn.addActionListener(numberListener);
        oneBtn.addActionListener(numberListener);
        twoBtn.addActionListener(numberListener);
        threeBtn.addActionListener(numberListener);
        fourBtn.addActionListener(numberListener);
        fiveBtn.addActionListener(numberListener);
        sixBtn.addActionListener(numberListener);
        sevenBtn.addActionListener(numberListener);
        eightBtn.addActionListener(numberListener);
        nineBtn.addActionListener(numberListener);
        dotBtn.addActionListener(numberListener);

        // Operator buttons
        plusBtn.addActionListener(new OperatorListener('+'));
        minusBtn.addActionListener(new OperatorListener('-'));
        devideBtn.addActionListener(new OperatorListener('/'));
        multiplyBtn.addActionListener(new OperatorListener('*'));
        powerBtn.addActionListener(new OperatorListener('^'));

        // square button
        squarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double number = Double.parseDouble(textInput.getText());
                textInput.setText(String.valueOf(Math.sqrt(number)));
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textInput.setText("");
                firstNumber = 0;
                secondNumber = 0;
                operator = '\0';
            }
        });

        calculateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondNumber = Double.parseDouble(textInput.getText());
                double result = 0;

                switch (operator) {
                    case '+':
                        result = firstNumber + secondNumber;
                        break;
                    case '-':
                        result = firstNumber - secondNumber;
                        break;
                    case '*':
                        result = firstNumber * secondNumber;
                        break;
                    case '/':
                        if (secondNumber != 0) {
                            result = firstNumber / secondNumber;
                        } else {
                            JOptionPane.showMessageDialog(null, "Cannot divide by zero");
                        }
                        break;
                    case '^':
                        result = Math.pow(firstNumber, secondNumber);
                        break;
                }

                textInput.setText(String.valueOf(result));
            }
        });

        setVisible(true);
        pack();
    }

        private class OperatorListener implements ActionListener {
            private char op;

            public OperatorListener(char op) {
                this.op = op;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                firstNumber = Double.parseDouble(textInput.getText());
                textInput.setText("");
                operator = op;
            }
        }

    public static void main(String[] args) {
        new Calculator();
    }

}
