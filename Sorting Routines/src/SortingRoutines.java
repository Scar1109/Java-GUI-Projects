import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SortingRoutines extends JFrame {
    private JPanel sortingRoutines;
    private JRadioButton selectionRadioButton;
    private JRadioButton bubbleRadioButton;
    private JRadioButton insertionRadioButton;
    private JRadioButton quickRadioButton;
    private JRadioButton ascendingRadioButton;
    private JRadioButton descendingRadioButton;
    private JTextField amountInput;
    private JButton sortButton;
    private JTextArea originalNumbers;
    private JTextArea sortedNumbers;

    public SortingRoutines(){
        setTitle("Sorting Routines");
        setContentPane(sortingRoutines);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Group sorting algorithm radio buttons
        ButtonGroup sortingGroup = new ButtonGroup();
        sortingGroup.add(selectionRadioButton);
        sortingGroup.add(bubbleRadioButton);
        sortingGroup.add(insertionRadioButton);
        sortingGroup.add(quickRadioButton);

        // Group order radio buttons
        ButtonGroup orderGroup = new ButtonGroup();
        orderGroup.add(ascendingRadioButton);
        orderGroup.add(descendingRadioButton);

        // Set default selections
        selectionRadioButton.setSelected(true);
        ascendingRadioButton.setSelected(true);

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Check if input field is empty
                if (amountInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(sortingRoutines, "Please enter the number of elements to sort.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int amount = Integer.parseInt(amountInput.getText());
                int[] numbers = generateRandomNumbers(amount);
                originalNumbers.setText(arrayToString(numbers));

                if (selectionRadioButton.isSelected()) {
                    selectionSort(numbers);
                } else if (bubbleRadioButton.isSelected()) {
                    bubbleSort(numbers);
                } else if (insertionRadioButton.isSelected()) {
                    insertionSort(numbers);
                } else if (quickRadioButton.isSelected()) {
                    quickSort(numbers, 0, numbers.length - 1);
                }

                if (descendingRadioButton.isSelected()) {
                    reverseArray(numbers);
                }

                sortedNumbers.setText(arrayToString(numbers));
            }
        });

        setVisible(true);
        pack();
    }

    private int[] generateRandomNumbers(int amount) {
        Random rand = new Random();
        int[] numbers = new int[amount];
        for (int i = 0; i < amount; i++) {
            numbers[i] = rand.nextInt(2001) - 1000; // Generate random numbers between -1000 and 1000
        }
        return numbers;
    }

    private String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int num : array) {
            sb.append(num).append("\n");
        }
        return sb.toString();
    }

    private void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    private void bubbleSort(int[] array) {
        boolean swapped;
        for (int i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    private void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    private void reverseArray(int[] array) {
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        new SortingRoutines();
    }
}
