import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SleepCalculator extends JFrame {
    private JPanel sleepCalculator;
    private JTextField bYear;
    private JTextField tYear;
    private JTextField bMonth;
    private JTextField tMonth;
    private JTextField bDay;
    private JTextField tDay;
    private JButton calculateButton;
    private JButton exitButton;
    private JTextField aliveDays;
    private JTextField aliveHours;

    public SleepCalculator(){

        setTitle("Sleep Calculator");
        setContentPane(sleepCalculator);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateSleep();
            }
        });

        setVisible(true);
        pack();
    }

    private void calculateSleep() {
        int birthYear = Integer.parseInt(bYear.getText());
        int birthMonth = Integer.parseInt(bMonth.getText());
        int birthDate = Integer.parseInt(bDay.getText());

        int todayDate = Integer.parseInt(tDay.getText());
        int todayMonth = Integer.parseInt(tMonth.getText());
        int todayYear = Integer.parseInt(tYear.getText());

        LocalDate birthDateObj = LocalDate.of(birthYear, birthMonth, birthDate);
        LocalDate currentDateObj = LocalDate.of(todayYear, todayMonth, todayDate);

        long days = ChronoUnit.DAYS.between(birthDateObj, currentDateObj);
        long hours = days * 8;

        aliveDays.setText("You have beel alive for " + String.valueOf(days) + " days");
        aliveHours.setText("You have slept " + String.valueOf(hours) + " hours");
    }

    public static void main(String[] args) {
        new SleepCalculator();
    }
}
