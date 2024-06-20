import javax.swing.*;

class Employee {

}
public class EmployeeRecord extends JFrame {

    private JPanel employeeRecord;

    public EmployeeRecord(){
        setTitle("Employee Record");
        setContentPane(employeeRecord);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);



        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        new EmployeeRecord();
    }


}
