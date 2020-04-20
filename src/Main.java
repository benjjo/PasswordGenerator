
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

/**
 * Generates a random set of keyboard characters to the length of the specified argument.
 *
 * Thanks to: https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
 */

public class Main {

    public static void main(String[] args){
        int repetitions = 0;
        try {
            repetitions = Integer.parseInt(JOptionPane.showInputDialog(null, "How long do you want the password?"));
        }catch (Exception anException){
            JOptionPane.showMessageDialog(null, "Please enter a number.");
        }
        new Main(repetitions);
    }

    private Main(int rep){
        JOptionPane.showMessageDialog(null, this.generator(rep));
    }

    /**
     * Generates a random char between 33-128 and assigns that char to the
     * string var to the length of the args argument.
     */
    private StringBuilder generator(int rep){
        StringBuilder val = new StringBuilder(rep);
        //String val = new String();
        for(int i = 0; i < rep; i++) {
            val.append((char) ThreadLocalRandom.current().nextInt(33, 128));
        }
        return val;
    }

}
