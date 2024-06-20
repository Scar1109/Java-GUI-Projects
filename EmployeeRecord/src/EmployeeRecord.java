import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Employee {
    String employeeIdNumber, firstName, lastName, annualSalary, startDate;

    // Constructor
    Employee(String _employeeIdNumber, String _firstName, String _lastName, String _annualSalary, String _startDate) {
        employeeIdNumber = _employeeIdNumber;
        firstName = _firstName;
        lastName = _lastName;
        annualSalary = _annualSalary;
        startDate = _startDate;
    }
}

public class EmployeeRecord extends JFrame {

    // UI Components
    private JPanel employeeRecord;
    private JTextField inputEmpId;
    private JTextField inputFirstName;
    private JTextField inputLastName;
    private JTextField inputAnnualSalary;
    private JTextField inputStartDate;
    private JTextArea displayRecords;
    private JButton addButton;
    private JButton listButton;
    private JButton removeButton;

    // List to store Employee records
    private ArrayList<Employee> employeeList = new ArrayList<>();

    public EmployeeRecord(){
        setTitle("Employee Record");
        setContentPane(employeeRecord);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Add button action listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        // List button action listener
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listEmployees();
            }
        });

        // Remove button action listener
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEmployee();
            }
        });

        setVisible(true);
        pack();
    }


    // Main method to run the application
    public static void main(String[] args) {
        new EmployeeRecord();
    }

    // Method to add employee to the list with validation
    private void addEmployee() {
        String employeeId = inputEmpId.getText().trim();
        String firstName = inputFirstName.getText().trim();
        String lastName = inputLastName.getText().trim();
        String annualSalary = inputAnnualSalary.getText().trim();
        String startDate = inputStartDate.getText().trim();

        // Validate input fields
        if (employeeId.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || annualSalary.isEmpty() || startDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Employee employee = new Employee(employeeId, firstName, lastName, annualSalary, startDate);
        employeeList.add(employee);

        // Clear input fields
        inputEmpId.setText("");
        inputFirstName.setText("");
        inputLastName.setText("");
        inputAnnualSalary.setText("");
        inputStartDate.setText("");

        JOptionPane.showMessageDialog(this, "Employee added successfully.");
    }

    // Method to list employees in the text area
    private void listEmployees() {
        StringBuilder records = new StringBuilder();

        for (Employee emp : employeeList) {
            records.append(emp.employeeIdNumber).append(" ")
                    .append(emp.firstName).append(" ")
                    .append(emp.lastName).append(" ")
                    .append(emp.annualSalary).append(" ")
                    .append(emp.startDate).append("\n");
        }

        displayRecords.setText(records.toString());
    }

    // Method to remove employee by ID
    private void removeEmployee() {
        String employeeId = inputEmpId.getText().trim();

        if (employeeId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the Employee ID to remove.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean removed = employeeList.removeIf(emp -> emp.employeeIdNumber.equals(employeeId));

        if (removed) {
            JOptionPane.showMessageDialog(this, "Employee removed successfully.");
            // Clear the ID field after removal
            inputEmpId.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Employee not found.");
        }

    }



}
