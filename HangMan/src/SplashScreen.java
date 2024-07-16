import javax.swing.*;

public class SplashScreen extends JFrame {

    private JPanel splashScreen;

    public SplashScreen(){

        setTitle("RollerCoaster Ride");
        setContentPane(splashScreen);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
        pack();
    }
}
