import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RollerCoasterRide extends JFrame {
    private JPanel RollerCoasterRide;
    private JButton exitButton;
    private JButton calculateButton;
    private JTextField Result_View;
    private JTextField back_trouble;
    private JTextField height_input;
    private JTextField hart_trouble;

    public RollerCoasterRide(){

        setTitle("RollerCoaster Ride");
        setContentPane(RollerCoasterRide);
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
                calculateRideEligibility();
            }
        });

        setVisible(true);
        pack();

    }

    private void calculateRideEligibility() {
        // Get input values
        String heightStr = height_input.getText();
        int height = Integer.parseInt(heightStr);
        String backTrouble = back_trouble.getText();
        String heartTrouble = hart_trouble.getText();

        // Check if height is within limits and if the rider has back or heart trouble
        if (height >= 122 && height <= 188 && !backTrouble.equalsIgnoreCase("Y") && !heartTrouble.equalsIgnoreCase("Y")) {
            Result_View.setText("It is OK for you to ride this roller coaster, Have fun!");
        } else {
            Result_View.setText("Sorry, it is not safe fpr you to ride this roller coaster");
        }
    }

    public static void main(String[] args) {
        new RollerCoasterRide();
    }

}
