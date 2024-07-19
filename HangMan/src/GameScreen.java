import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen extends JFrame {

    private JPanel gameScreen;
    private JLabel wordLabel;
    private JLabel livesLabel;
    private JPanel keyboardPanel;
    private HangmanGame hangmanGame;
    private JLabel logoLabel;

    public GameScreen() {
        setTitle("Hangman Game");
        setContentPane(gameScreen);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon logoImgSrc = resizeImageIcon(new ImageIcon("res/hangman-logo.png"), 120, 85);

        hangmanGame = new HangmanGame();

        logoLabel.setIcon(logoImgSrc);

        updateWordLabel();
        updateLivesLabel();

        createKeyboard();

        setVisible(true);
        pack();
    }

    private void updateWordLabel() {
        wordLabel.setText(hangmanGame.getMaskedWord());
    }

    private void updateLivesLabel() {
        livesLabel.setText("Lives: " + hangmanGame.getRemainingLives());
    }

    private void makeGuess(String guess) {
        if (!guess.isEmpty() && guess.length() == 1) {
            hangmanGame.makeGuess(guess.toLowerCase().charAt(0)); // Convert guess to lowercase
            updateWordLabel();
            updateLivesLabel();
            if (hangmanGame.isGameOver()) {
                String message = hangmanGame.isWordGuessed() ? "Congratulations! You guessed the word!" : "Game Over! The word was: " + hangmanGame.getMaskedWord();
                JOptionPane.showMessageDialog(this, message);
                dispose(); // Close game screen
            }
        }
    }

    private void createKeyboard() {
        keyboardPanel.setLayout(new GridLayout(3, 9, 5, 5));
        keyboardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (char letter = 'A'; letter <= 'Z'; letter++) {
            JButton letterButton = new JButton(String.valueOf(letter));
            letterButton.setFont(new Font("SansSerif", Font.BOLD, 18));
            letterButton.setBackground(Color.WHITE);
            letterButton.setForeground(Color.BLACK);
            letterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton source = (JButton) e.getSource();
                    String letter = source.getText();
                    makeGuess(letter.toLowerCase()); // Convert guess to lowercase
                    source.setEnabled(false); // Disable button after it's used
                    source.setBackground(Color.lightGray);
                }
            });
            keyboardPanel.add(letterButton);
        }
    }

    //Image resize helper method
    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(resizedImg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameScreen();
            }
        });
    }
}
