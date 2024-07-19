import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameOverScreen extends JFrame {

    private BackgroundPanel gameOverScreen;
    private JLabel tryAgainBtn;
    private JLabel messageLabel;

    public GameOverScreen(String correctWord) {
        setTitle("Game Over");

        // Load the background image
        ImageIcon backgroundImageIcon = new ImageIcon("res/background-image.png");
        Image backgroundImage = backgroundImageIcon.getImage();

        // Create the custom panel with the background image
        gameOverScreen = new BackgroundPanel(backgroundImage);
        gameOverScreen.setLayout(null); // Set layout to null for absolute positioning
        gameOverScreen.setPreferredSize(new Dimension(565, 400)); // Set the preferred size

        // Initialize components
        tryAgainBtn = new JLabel();
        messageLabel = new JLabel();

        // Add components to gameOverScreen with absolute positioning
        messageLabel.setBounds(140, 100, 300, 50); // x, y, width, height
        tryAgainBtn.setBounds(240, 175, 110, 110); // x, y, width, height

        gameOverScreen.add(messageLabel);
        gameOverScreen.add(tryAgainBtn);

        setContentPane(gameOverScreen);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon tryAgainBtnImg = resizeImageIcon(new ImageIcon("res/TryAgainButton.png"), 100, 100);

        messageLabel.setText("Game Over!");
        messageLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 32));
        messageLabel.setForeground(Color.DARK_GRAY);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        tryAgainBtn.setIcon(tryAgainBtnImg);

        tryAgainBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameScreen();
                dispose(); // Close game over screen
            }
        });

        pack();
        setVisible(true);
    }

    // Image resize helper method
    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(resizedImg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameOverScreen("example"));
    }
}
