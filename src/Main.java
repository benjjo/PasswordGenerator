
import javax.swing.*;

/**
 * Generates a random set of keyboard characters to the length of the specified argument.
 *
 * Thanks to: https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
 */

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    private Main() {
        this.invokeGUI();
    }

    /**
     * Setup the swing thread. Creates a new Swing thread so that Swing can manage it.
     */
    private void invokeGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run () {
                JFrame frame = new MainFrame("Password generator");
                frame.setSize(550, 300); // sets the size of the frame
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // sets up JFrame to close when cancelled.
                frame.setVisible(true); // Draws the frame to the screen
            }});
    }

}