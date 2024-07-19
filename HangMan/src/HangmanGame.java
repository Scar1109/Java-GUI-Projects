import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HangmanGame {

    private String wordToGuess;
    private StringBuilder maskedWord;
    private int incorrectGuesses;
    private static final int MAX_INCORRECT_GUESSES = 6;

    public HangmanGame() {
        wordToGuess = selectRandomWord("words.txt").toUpperCase();
        maskedWord = new StringBuilder("*".repeat(wordToGuess.length()));
        incorrectGuesses = 0;
    }

    private String selectRandomWord(String filePath) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (words.isEmpty()) {
            return "default"; // Fallback word if file is empty or an error occurs
        }
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    public void makeGuess(char guess) {
        guess = Character.toUpperCase(guess);
        boolean correct = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                maskedWord.setCharAt(i, guess);
                correct = true;
            }
        }
        if (!correct) {
            incorrectGuesses++;
        }
    }

    public String getMaskedWord() {
        return maskedWord.toString();
    }

    public int getRemainingLives() {
        return MAX_INCORRECT_GUESSES - incorrectGuesses;
    }

    public boolean isGameOver() {
        return incorrectGuesses >= MAX_INCORRECT_GUESSES || maskedWord.toString().equals(wordToGuess);
    }

    public boolean isWordGuessed() {
        return maskedWord.toString().equals(wordToGuess);
    }
}
