/** Programmer:
 * Date:
 * Program Name: TicTacEvent.java
 * Program Description: This program runs the GUI for Tic Tac Toe
 */

import javax.swing.*; //imports libraries we need
import java.awt.event.*;
import java.awt.*;



class TicTacEvent implements ActionListener {
    TicTac gui;
    ImageIcon originalXIcon = new ImageIcon("x.jpg");
    ImageIcon originalOIcon = new ImageIcon("o.jpg");
    ImageIcon a = resizeImageIcon(originalXIcon, 100, 100);  // Set desired width and height
    ImageIcon b = resizeImageIcon(originalOIcon, 100, 100);
    int clicks = 0;
    int win = 0;
    int[][] check = new int[3][3];

    private int xWins = 0;
    private int oWins = 0;
    private int ties = 0;

    public TicTacEvent(TicTac in) {
        gui = in;
        for (int row = 0; row <= 2; row++) {
            for (int col = 0; col <= 2; col++) {
                check[row][col] = 0;
            }
        }
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        switch (command) {
            case "1": b1(); break;
            case "2": b2(); break;
            case "3": b3(); break;
            case "4": b4(); break;
            case "5": b5(); break;
            case "6": b6(); break;
            case "7": b7(); break;
            case "8": b8(); break;
            case "9": b9(); break;
        }
    }

    void b1() { updateGameState(0, 0); }
    void b2() { updateGameState(0, 1); }
    void b3() { updateGameState(0, 2); }
    void b4() { updateGameState(1, 0); }
    void b5() { updateGameState(1, 1); }
    void b6() { updateGameState(1, 2); }
    void b7() { updateGameState(2, 0); }
    void b8() { updateGameState(2, 1); }
    void b9() { updateGameState(2, 2); }

    void updateGameState(int x, int y) {
        clicks++;
        if ((clicks % 2) == 1) {
            check[x][y] = 1;
            gui.boxes[x][y].setEnabled(false);
            gui.boxes[x][y].setDisabledIcon(a);
        } else {
            check[x][y] = 2;
            gui.boxes[x][y].setEnabled(false);
            gui.boxes[x][y].setDisabledIcon(b);
        }
        winner();
    }

    void winner() {
        for (int x = 0; x <= 2; x++) {
            if ((check[x][0] == check[x][1]) && (check[x][0] == check[x][2])) {
                if (check[x][0] == 1) {
                    JOptionPane.showMessageDialog(null, "X is the winner");
                    xWins++;
                    gui.xWinLabel.setText("X Wins: " + xWins);
                    win = 1;
                } else if (check[x][0] == 2) {
                    JOptionPane.showMessageDialog(null, "O is the winner");
                    oWins++;
                    gui.oWinLabel.setText("O Wins: " + oWins);
                    win = 1;
                }
            }
        }

        for (int x = 0; x <= 2; x++) {
            if ((check[0][x] == check[1][x]) && (check[0][x] == check[2][x])) {
                if (check[0][x] == 1) {
                    JOptionPane.showMessageDialog(null, "X is the winner");
                    xWins++;
                    gui.xWinLabel.setText("X Wins: " + xWins);
                    win = 1;
                } else if (check[0][x] == 2) {
                    JOptionPane.showMessageDialog(null, "O is the winner");
                    oWins++;
                    gui.oWinLabel.setText("O Wins: " + oWins);
                    win = 1;
                }
            }
        }

        if (((check[0][0] == check[1][1]) && (check[0][0] == check[2][2])) ||
                ((check[2][0] == check[1][1]) && (check[1][1] == check[0][2]))) {
            if (check[1][1] == 1) {
                JOptionPane.showMessageDialog(null, "X is the winner");
                xWins++;
                gui.xWinLabel.setText("X Wins: " + xWins);
                win = 1;
            } else if (check[1][1] == 2) {
                JOptionPane.showMessageDialog(null, "O is the winner");
                oWins++;
                gui.oWinLabel.setText("O Wins: " + oWins);
                win = 1;
            }
        }

        if ((clicks == 9) && (win == 0)) {
            JOptionPane.showMessageDialog(null, "The game is a tie");
            ties++;
            gui.tieLabel.setText("Ties: " + ties);
        }
    }

    //Image resize helper method
    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}