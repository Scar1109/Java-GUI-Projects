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

        ImageIcon logoImgSrc = resizeImageIcon(new ImageIcon("res/hangman-logo.png"), 240, 150);

        // Ensure the gameScreen panel uses BorderLayout
        gameScreen.setLayout(new BorderLayout());

        hangmanGame = new HangmanGame();

        // Initialize UI components with styles
        wordLabel = new JLabel();
        wordLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);

        livesLabel = new JLabel();
        livesLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        livesLabel.setHorizontalAlignment(SwingConstants.CENTER);

        logoLabel = new JLabel(); // Adjust the path if necessary
        logoLabel.setIcon(logoImgSrc);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new GridLayout(1, 1, 10, 10));
        logoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Create panels for word display and input
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 1, 10, 10));
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        topPanel.add(logoLabel);
        topPanel.add(wordLabel);
        topPanel.add(livesLabel);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        topPanel.add(inputPanel);

        gameScreen.add(topPanel, BorderLayout.NORTH);

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
        keyboardPanel = new JPanel();
        keyboardPanel.setLayout(new GridLayout(3, 9, 5, 5));
        keyboardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (char letter = 'A'; letter <= 'Z'; letter++) {
            JButton letterButton = new JButton(String.valueOf(letter));
            letterButton.setFont(new Font("SansSerif", Font.BOLD, 18));
            letterButton.setBackground(Color.LIGHT_GRAY);
            letterButton.setForeground(Color.BLACK);
            letterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton source = (JButton) e.getSource();
                    String letter = source.getText();
                    makeGuess(letter.toLowerCase()); // Convert guess to lowercase
                    source.setEnabled(false); // Disable button after it's used
                    source.setBackground(Color.GRAY);
                }
            });
            keyboardPanel.add(letterButton);
        }

        gameScreen.add(keyboardPanel, BorderLayout.SOUTH); // Add keyboard panel to the game screen
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
