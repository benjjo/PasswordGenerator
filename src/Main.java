
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

/**
 * Generates a random set of keyboard characters to the length of the specified argument.
 *
 * Thanks to: https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
 */

public class Main {
    public static Popup popups = new Popup();
    public static final CharLists LISTS = new CharLists();
    private int strength;
    private int length;

    public static void main(String[] args){
        new Main();
    }

    private Main(){
        this.strength = Main.popups.popupStrengthWindow();
        this.length = Main.popups.popupLengthWindow();
        JOptionPane.showMessageDialog(null, this.generator());
    }

    /**
     * Getter for the strength int
     */
    private int getStrength(){
        return this.strength;
    }

    /**
     * Getter for the length int
     */
    private int getLength(){
        return this.length;
    }

    /**
     * Generates a random char between 33-128 and assigns that char to the
     * string var to the length of the args argument.
     */
    private StringBuilder generator(){

        StringBuilder val = new StringBuilder(this.getLength());
        int start = 0, finish;
        List<Character> aList;

            if(this.getStrength() == 1) {
                finish = Main.LISTS.ALPHA_NUMERIC.size();
                aList = Main.LISTS.ALPHA_NUMERIC;
            } else if (this.getStrength() == 2) {
                finish = Main.LISTS.ALPHA.size();
                aList = Main.LISTS.ALPHA;
            } else if (this.getStrength() == 3) {
                finish = Main.LISTS.NUMERIC.size();
                aList = Main.LISTS.NUMERIC;
            } else {
                finish = Main.LISTS.ALPHA_NUMERIC_SPECIAL.size();
                aList = Main.LISTS.ALPHA_NUMERIC_SPECIAL;
            }
        for (int i = 0; i < this.getLength(); i++) {
            val.append((char) aList.get(ThreadLocalRandom.current().nextInt(start, finish)));
        }
        return val;
    }

}
