import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SplashScreen extends JFrame {

    private JPanel splashScreen;
    private JLabel playBtn;
    private JLabel logoImg;

    public SplashScreen(){

        setTitle("Splash Screen");
        setContentPane(splashScreen);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon playBtnImg = resizeImageIcon(new ImageIcon("res/Playbutton.png"), 110, 110);
        ImageIcon logoImgSrc = resizeImageIcon(new ImageIcon("res/hangman-logo.png"), 240, 150);

        logoImg.setIcon(logoImgSrc);
        playBtn.setIcon(playBtnImg);

        playBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameScreen();
                dispose(); // Close splash screen
            }
        });

        setVisible(true);
        pack();
    }

    //Image resize helper method
    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(resizedImg);
    }
}
