import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen extends JFrame {

    private JPanel gameScreen;
    private JLabel wordLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel scoreLabel;
    private HangmanGame hangmanGame;

    public GameScreen() {
        setTitle("Hangman Game");
        setContentPane(gameScreen);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        hangmanGame = new HangmanGame();
        updateWordLabel();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guess = guessField.getText();
                if (!guess.isEmpty()) {
                    hangmanGame.makeGuess(guess.charAt(0));
                    updateWordLabel();
                    if (hangmanGame.isGameOver()) {
                        new GameOverScreen(hangmanGame.getScore());
                        dispose(); // Close game screen
                    }
                }
            }
        });

        setVisible(true);
        pack();
    }

    private void updateWordLabel() {
        wordLabel.setText(hangmanGame.getMaskedWord());
        scoreLabel.setText("Score: " + hangmanGame.getScore());
    }
}
