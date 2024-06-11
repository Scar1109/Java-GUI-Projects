import java.awt.*; //import the libraries needed in this app
import javax.swing.*;
import javax.swing.border.Border;

public class TicTac extends JFrame {
    TicTacEvent tictac = new TicTacEvent(this);
    JPanel gameBoardPanel = new JPanel();
    JPanel scorePanel = new JPanel();
    JButton[][] boxes = new JButton[3][3];
    ImageIcon orgBack = new ImageIcon("cardback.png");
    ImageIcon back = resizeImageIcon(orgBack, 100, 100);

    JLabel xWinLabel = new JLabel("X Wins: 0");
    JLabel oWinLabel = new JLabel("O Wins: 0");
    JLabel tieLabel = new JLabel("Ties: 0");
    JButton resetButton = new JButton("Reset");

    public TicTac() {
        super("Tic Tac Toe");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        resetButton.addActionListener(e -> resetGame());

        // Set up the game board panel
        gameBoardPanel.setLayout(new GridLayout(3, 3, 10, 10));
        Border gameBoardBorder = BorderFactory.createTitledBorder(" ");
        gameBoardPanel.setBorder(gameBoardBorder);
        gameBoardPanel.setBackground(Color.LIGHT_GRAY);
        int name = 0;
        String newname;

        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                name = name + 1;
                newname = Integer.toString(name);
                boxes[x][y] = new JButton(newname);
                boxes[x][y].setIcon(back);
                gameBoardPanel.add(boxes[x][y]);
                // Set the text color to be the same as the background color (making it "transparent")
                boxes[x][y].setForeground(gameBoardPanel.getBackground());
                boxes[x][y].setBackground(Color.WHITE);
            }
        }

        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                boxes[x][y].addActionListener(tictac);
            }
        }

        // Set up the score panel
        scorePanel.setLayout(new GridLayout(1, 4, 10, 10));
        Border scorePanelBorder = BorderFactory.createTitledBorder("Scores");
        scorePanel.setBorder(scorePanelBorder);
        scorePanel.setBackground(Color.LIGHT_GRAY);
        scorePanel.add(xWinLabel);
        scorePanel.add(oWinLabel);
        scorePanel.add(tieLabel);
        scorePanel.add(resetButton);

        // Add the panels to the frame
        add(gameBoardPanel, BorderLayout.CENTER);
        add(scorePanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void resetGame() {
        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                boxes[x][y].setEnabled(true);
                boxes[x][y].setIcon(back);
                tictac.check[x][y] = 0;
            }
        }
        tictac.clicks = 0;
        tictac.win = 0;
    }

    //Image resize helper method
    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }


    public static void main(String[] arguments) {
        TicTac frame = new TicTac();
    }
}