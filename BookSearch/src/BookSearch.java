import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Main class extending JFrame for GUI
public class BookSearch extends JFrame {
    // GUI components
    private JPanel bookSearch;
    private JTextField reference_input;
    private JButton findBookButton;
    private JTextField linear_output;
    private JTextField binary_output;
    private JButton exitButton;

    // List to store books
    private List<Book> books;

    // Constructor to set up the GUI and load the books
    public BookSearch() {
        // Set the title of the JFrame
        setTitle("Book Search");
        // Set the content pane to the bookSearch panel
        setContentPane(bookSearch);
        // Center the JFrame on the screen
        setLocationRelativeTo(null);
        // Exit the application when the JFrame is closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Add an action listener to the button to handle button clicks
        findBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the searchBook method when the button is clicked
                searchBook();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Load books from the specified file
        loadBooks("resources/BooksFile.txt");

        // Make the JFrame visible and pack its components
        setVisible(true);
        pack();
    }

    // Main method to start the application
    public static void main(String[] args) {
        new BookSearch();
    }

    // Method to load books from the file
    private void loadBooks(String filePath) {
        books = new ArrayList<>(); // Initialize the books list
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read the file line by line
            while ((line = br.readLine()) != null) {
                // Parse the reference number
                int refNumber = Integer.parseInt(line);
                // Read the title of the book
                String title = br.readLine();
                // Add the book to the list
                books.add(new Book(refNumber, title));
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace if an error occurs
        }
    }

    // Method to search for a book
    private void searchBook() {
        int refNumber;
        try {
            // Parse the reference number from the input field
            refNumber = Integer.parseInt(reference_input.getText());
        } catch (NumberFormatException e) {
            // Display an error message if the input is not a valid number
            linear_output.setText("Please enter a valid reference number");
            binary_output.setText("");
            return;
        }

        // Perform a linear search for the book
        String linearResult = linearSearch(refNumber);
        // Display the result of the linear search
        linear_output.setText(linearResult);

        // Perform a binary search for the book
        String binaryResult = binarySearch(refNumber);
        // Display the result of the binary search
        binary_output.setText(binaryResult);
    }

    // Method to perform a linear search
    private String linearSearch(int refNumber) {
        // Iterate through the list of books
        for (Book book : books) {
            // If the reference number matches, return the book title
            if (book.getRefNumber() == refNumber) {
                return book.getTitle();
            }
        }
        // Return a message if the book is not found
        return "Book not found";
    }

    // Method to perform a binary search
    private String binarySearch(int refNumber) {
        int left = 0;
        int right = books.size() - 1;

        // Perform the binary search
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Book book = books.get(mid);

            if (book.getRefNumber() == refNumber) {
                return book.getTitle();
            }

            if (book.getRefNumber() < refNumber) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // Return a message if the book is not found
        return "Book not found";
    }
}
