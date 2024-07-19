import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SplashScreen extends JFrame {

    private BackgroundPanel splashScreen;
    private JLabel playBtn;
    private JLabel logoImg;

    public SplashScreen() {

        setTitle("Splash Screen");

        // Load the background image
        ImageIcon backgroundImageIcon = new ImageIcon("res/background-image.png");
        Image backgroundImage = backgroundImageIcon.getImage();

        // Create the custom panel with the background image
        splashScreen = new BackgroundPanel(backgroundImage);
        splashScreen.setLayout(null); // Set layout to null for absolute positioning
        splashScreen.setPreferredSize(new Dimension(565, 400)); // Set the preferred size

        // Initialize components
        playBtn = new JLabel();
        logoImg = new JLabel();

        // Add components to splashScreen with absolute positioning
        logoImg.setBounds(200, 40, 240, 150); // x, y, width, height
        playBtn.setBounds(240, 175, 110, 110); // x, y, width, height

        splashScreen.add(logoImg);
        splashScreen.add(playBtn);

        setContentPane(splashScreen);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon playBtnImg = resizeImageIcon(new ImageIcon("res/Playbutton.png"), 80, 80);
        ImageIcon logoImgSrc = resizeImageIcon(new ImageIcon("res/hangman-logo.png"), 165, 110);

        logoImg.setIcon(logoImgSrc);
        playBtn.setIcon(playBtnImg);

        playBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameScreen();
                dispose(); // Close splash screen
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
        SwingUtilities.invokeLater(() -> new SplashScreen());
    }
}
