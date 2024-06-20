import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FractionReducerView extends JFrame {
    private JPanel fractionReducer;
    private JTextField inputNumerator;
    private JTextField inputDenominator;
    private JButton reduceButton;
    private JTextField resultField;

    public FractionReducerView() {
        setTitle("Fraction Reducer");
        setContentPane(fractionReducer);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Add action listener to the reduce button
        reduceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reduceFraction();
            }
        });

        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        new FractionReducerView();
    }

    // Recursive function to calculate the GCD of two integers
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    // Function to reduce the fraction using GCD
    public static String reduceFraction(int numerator, int denominator) {
        int gcd = gcd(numerator, denominator);
        int reducedNumerator = numerator / gcd;
        int reducedDenominator = denominator / gcd;
        return reducedNumerator + "/" + reducedDenominator;
    }

    // Method to handle the fraction reduction and update the result field
    private void reduceFraction() {
        try {
            int numerator = Integer.parseInt(inputNumerator.getText());
            int denominator = Integer.parseInt(inputDenominator.getText());
            if (denominator == 0) {
                resultField.setText("Denominator cannot be zero");
            } else {
                String reducedFraction = reduceFraction(numerator, denominator);
                resultField.setText(numerator + "/" + denominator + " lowest terms is " + reducedFraction);
            }
        } catch (NumberFormatException ex) {
            resultField.setText("Please enter valid integers");
        }
    }
}
