import javax.swing.*;
import java.awt.*;

public class GameWonScreen extends JFrame {

    public GameWonScreen() {
        setTitle("Congratulations!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("Congratulations! You won the game!", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 24));
        messageLabel.setForeground(Color.GREEN);

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(e -> {
            // Restart the game (you might want to implement this method)
            new GameScreen();
            dispose(); // Close GameWonScreen
        });

        add(messageLabel, BorderLayout.CENTER);
        add(playAgainButton, BorderLayout.SOUTH);

        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
