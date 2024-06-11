import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IntegerSums extends JFrame {

    private JPanel intCalculator;
    private JButton removeButton;
    private JButton addButton;
    private JTextField inputTxt;
    private JList integerList;
    private JButton sumAllButton;
    private JButton sumEvenButton;
    private JButton sumOddButton;
    private JTextField resultText;
    private JButton exitButton;
    private JButton listButton;

    private ArrayList<Integer> integers;

    public IntegerSums(){

        // Initialize the array list
        integers = new ArrayList<>();

        setTitle("Sleep Calculator");
        setContentPane(intCalculator);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Add action listeners for the buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addInteger();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeInteger();
            }
        });

        sumAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sumAllIntegers();
            }
        });

        sumEvenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sumEvenIntegers();
            }
        });

        sumOddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sumOddIntegers();
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listIntegers();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });



        setVisible(true);
        pack();

    }

    // Method to add an integer to the list
    private void addInteger() {
        try {
            int number = Integer.parseInt(inputTxt.getText());
            integers.add(number);
            inputTxt.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
        }
    }

    // Method to remove an integer from the list
    private void removeInteger() {
        try {
            int number = Integer.parseInt(inputTxt.getText());
            if (integers.contains(number)) {
                integers.remove(Integer.valueOf(number));
                inputTxt.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "The number is not in the list.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
        }
    }

    // Method to update the JList display
    private void updateIntegerList() {
        DefaultListModel<Integer> model = new DefaultListModel<>();
        for (Integer integer : integers) {
            model.addElement(integer);
        }
        integerList.setModel(model);
    }

    // Method to list all integers
    private void listIntegers() {
        updateIntegerList();
    }

    // Method to sum all integers
    private void sumAllIntegers() {
        int sum = 0;
        for (int number : integers) {
            sum += number;
        }
        resultText.setText("Sum of all integers: " + sum);
    }

    // Method to sum even integers
    private void sumEvenIntegers() {
        int sum = 0;
        for (int number : integers) {
            if (number % 2 == 0) {
                sum += number;
            }
        }
        resultText.setText("Sum of even integers: " + sum);
    }

    // Method to sum odd integers
    private void sumOddIntegers() {
        int sum = 0;
        for (int number : integers) {
            if (number % 2 != 0) {
                sum += number;
            }
        }
        resultText.setText("Sum of odd integers: " + sum);
    }


    public static void main(String[] args) {
        new IntegerSums();
    }

}
