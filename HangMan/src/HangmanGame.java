import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HangmanGame {

    private String wordToGuess;
    private StringBuilder maskedWord;
    private int score;
    private int incorrectGuesses;

    public HangmanGame() {
        wordToGuess = selectRandomWord("res/words.txt");
        maskedWord = new StringBuilder("_".repeat(wordToGuess.length()));
        score = 0;
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
        score++;
    }

    public String getMaskedWord() {
        return maskedWord.toString();
    }

    public boolean getScore() {
        return (score==6);
    }

    public boolean isGameOver() {
        return incorrectGuesses >= 6 || maskedWord.toString().equals(wordToGuess);
    }
}
