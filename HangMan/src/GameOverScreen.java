import javax.swing.*;
import java.awt.*;

public class GameOverScreen extends JFrame {

    private JPanel gameOverScreen;
    private JLabel scoreLabel;
    private JButton restartButton;

    public GameOverScreen(boolean finalResult) {
        setTitle("Game Over");
        setContentPane(gameOverScreen);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        restartButton.addActionListener(e -> {
            new SplashScreen();
            dispose(); // Close game over screen
        });

        setVisible(true);
        pack();
    }
}
