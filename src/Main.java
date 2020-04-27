
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Generates a random set of keyboard characters to the length of the specified argument.
 *
 * Thanks to: https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
 */

public class Main {

    public static void main(String[] args) {
        new CharLists();
        SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run () {
            JFrame frame = new MainFrame("Password GEN");
            frame.setSize(266, 270); // sets the size of the frame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // sets up JFrame to close when cancelled.

            // Setup the icon
            try {
                URL resource = frame.getClass().getResource("./password-icon-5.png");
                BufferedImage image = ImageIO.read(resource);
                frame.setIconImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Display the form
            frame.setVisible(true);

            frame.setVisible(true); // Draws the frame to the screen
        }});
    }

}